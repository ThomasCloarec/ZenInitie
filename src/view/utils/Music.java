package view.utils;

import javax.sound.sampled.*;
import java.io.*;

/**
 * @author Connor Mahaffey
 * <p>
 * This class is a rewrite of the example from: http://www.javazoom.net/mp3spi/documents.html
 * <p>
 * This class needs the following libraries:
 * JLayer - http://www.javazoom.net/javalayer/javalayer.html
 * MP3 SPI - http://www.javazoom.net/mp3spi/mp3spi.html
 * Tritonus Share (tritonus_share-0.3.6.jar) - http://www.tritonus.org/plugins.html
 * <p>
 * All credit goes to the creators of JLayer, MP3 SPI, and Tritonus.
 * <p>
 * This simple re-write adds loop, mute, pause, restart, and stop methods to the example mentioned above.
 * <p>
 * This code is completely free to use for any purpose whatsoever. JLayer, MP3 SPI, and Tritonus are
 * all released under the LGPL.
 * <p>
 * <p>
 * Known Issues:
 * <p>
 * - Though using .stop() and then .play() *technically* works for restarting the audio, doing this too fast
 * causes problems because the old audio stream is never stopped (writing to the audio line takes a bit,
 * and it can't be stopped once it's started).
 * - Distorted audio (rarely? Problem with code or with audio APIs?)
 * - General Efficiency
 */
public class Music implements Runnable {
    private static final String pathPrefix = "/view/resources/sounds/";
    private final int byteChunkSize = 1024;//number of bytes to read at one time
    private final byte[] muteData;
    private File file;
    private boolean running, mute, pause, loop, restart;

    /**
     * Declares default variable values.
     */
    public Music() {
        this.file = null;
        this.running = false;
        this.mute = false;
        this.pause = false;
        this.loop = false;
        this.restart = false;
        this.muteData = this.setMuteData();
    }

    public static void copyStream(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    /**
     * Creates a file object. If the file path exists on the system, the given file is an mp3, and
     * a song is not currently playing in this instance of the program, true is returned.
     *
     * @param filePath Path to the file.
     * @return If the file is loaded or not.
     */
    public boolean loadFile(String filePath) {
        this.file = new File(filePath);
        try {
            FileOutputStream out = new FileOutputStream(this.file);
            Music.copyStream(Music.class.getResourceAsStream(Music.pathPrefix + filePath), out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (this.file.exists() && this.file.getName().toLowerCase().endsWith(".mp3") && !this.running) {
            return true;
        } else {
            this.file = null;
            return false;
        }
    }

    /**
     * Starts playing the audio in a new thread.
     */
    public void play() {
        if (this.file != null && !this.running) {
            this.running = true;
            try {
                Thread t = new Thread(this);
                t.start();
            } catch (Exception e) {
                System.err.println("Could not start new thread for audio!");
                e.printStackTrace();
            }
        }
    }

    /**
     * Pauses the audio at its current place. Calling this method once pauses the audio stream, calling it
     * again unpauses the audio stream.
     */
    public void pause() {
        if (this.file != null) {
            this.pause = !this.pause;
        }
    }

    /**
     * Closes the audio stream. This method takes some time to execute, and as such you should never call
     * .stop() followed immediately by .play(). If you need to restart a song, use .restart().
     */
    public void stop() {
        if (this.file != null) {
            this.running = false;
        }
    }

    /**
     * Stream continues to play, but no sound is heard. Calling this method once mutes the audio stream,
     * calling it again unmutes the audio stream.
     */
    public void mute() {
        if (this.file != null) {
            this.mute = !this.mute;
        }
    }

    /**
     * Makes a given audio file loop back to the beginning when the end is reached. Calling this method once
     * will make it loop, calling it again will make it stop looping, but will not stop the audio from playing
     * to the end of a given loop.
     */
    public void loop() {
        if (this.file != null) {
            this.loop = !this.loop;
        }
    }

    /**
     * Restarts the current song. Always use this method to restart a song and never .stop() followed
     * by .play(), which is not safe.
     */
    public void restart() {
        this.restart = true;
    }

    /**
     * Small sections of audio bytes are read off, watching for a call to stop, pause, restart, or mute the audio.
     *
     * @param targetFormat Format the audio will be changed to.
     * @param din          The audio stream.
     */
    private void stream(AudioFormat targetFormat, AudioInputStream din) {
        try {
            byte[] data = new byte[this.byteChunkSize];
            SourceDataLine line = this.getLine(targetFormat);
            if (line != null) {
                line.start();
                int nBytesRead = 0;
                while (nBytesRead != -1 && this.running && !this.restart) {
                    nBytesRead = din.read(data, 0, data.length);
                    if (nBytesRead != -1) {
                        if (this.mute) {
                            line.write(this.muteData, 0, nBytesRead);
                        } else {
                            line.write(data, 0, nBytesRead);
                        }
                    }

                    while (this.pause && this.running) {
                        this.wait(15);
                    }
                }
                line.drain();
                line.stop();
                line.close();
                din.close();
            }
        } catch (Exception e) {
            System.err.println("Problem playing audio!");
            e.printStackTrace();
        }
    }

    /**
     * Gets the line of audio.
     *
     * @param audioFormat The format of the audio.
     * @return The line of audio.
     */
    private SourceDataLine getLine(AudioFormat audioFormat) {
        SourceDataLine res = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

        try {
            res = (SourceDataLine) AudioSystem.getLine(info);
            res.open(audioFormat);
            FloatControl gainControl = (FloatControl) res.getControl(FloatControl.Type.VOLUME);
            gainControl.setValue(gainControl.getMaximum() * 0.5f);
        } catch (Exception e) {
            System.err.println("Could not get audio line!");
            e.printStackTrace();
        }

        return res;
    }

    /**
     * Waits a specified number of milliseconds.
     *
     * @param time Time to wait (in milliseconds).
     */
    private void wait(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            System.err.println("Could not wait!");
            e.printStackTrace();
        }
    }

    /**
     * Sets a byte array of all zeros to "play" when audio is muted. This produces no sound.
     *
     * @return Byte array of all zeros.
     */
    private byte[] setMuteData() {
        return new byte[this.byteChunkSize];
    }

    /**
     * Retrieves the audio stream information and starts the stream. When the stream ends, this method
     * checks to see if it should loop and start again.
     */
    @Override
    public void run() {
        try {
            do {
                this.restart = false;
                AudioInputStream in = AudioSystem.getAudioInputStream(this.file);
                AudioInputStream din;
                AudioFormat baseFormat = in.getFormat();
                AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                        baseFormat.getSampleRate(),
                        16,
                        baseFormat.getChannels(),
                        baseFormat.getChannels() * 2,
                        baseFormat.getSampleRate(),
                        false);
                din = AudioSystem.getAudioInputStream(decodedFormat, in);
                this.stream(decodedFormat, din);
                in.close();
            } while ((this.loop || this.restart) && this.running);
            this.running = false;
        } catch (Exception e) {
            System.err.println("Problem getting audio stream!");
            e.printStackTrace();
        }
    }

    /**
     * Returns if a file is loaded or not.
     *
     * @return File status of null or a file.
     */
    public boolean isLoaded() {
        return this.file != null;
    }

    /**
     * Returns whether or not a clip will loop when it reaches the end.
     *
     * @return Status of the variable loop.
     */
    public boolean isLooping() {
        return this.loop;
    }

    /**
     * Returns if the audio is muted or not.
     *
     * @return Status of mute variable.
     */
    public boolean isMuted() {
        return this.mute;
    }

    /**
     * Returns if the audio is paused or not.
     *
     * @return Status of pause variable.
     */
    public boolean isPaused() {
        return this.pause;
    }

    /**
     * Returns if audio is currently playing (if the audio is muted, this will still be true).
     *
     * @return If the thread is running or not.
     */
    public boolean isPlaying() {
        return this.running;
    }
}