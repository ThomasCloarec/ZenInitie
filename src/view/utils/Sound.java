package view.utils;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Sound implements Runnable {
    private static final int byteChunkSize = 1024;//number of bytes to read at one time
    private static final String pathPrefix = "/view/resources/sounds/";
    private static SoundVolume volume = SoundVolume.LOW;
    private final String filePath;
    private final byte[] muteData;
    private boolean running, mute, pause, loop, restart;

    /**
     * Declares default variable values.
     */
    public Sound(String name) {
        this.filePath = Sound.pathPrefix + name;
        this.running = false;
        this.mute = false;
        this.pause = false;
        this.loop = false;
        this.restart = false;
        this.muteData = Sound.setMuteData();
    }

    public static void nextVolumeTick() {
        Sound.volume = Sound.volume.nextVolumeTick();
    }

    public static SoundVolume getVolume() {
        return Sound.volume;
    }

    /**
     * Gets the line of audio.
     *
     * @param audioFormat The format of the audio.
     * @return The line of audio.
     */
    private static SourceDataLine getLine(AudioFormat audioFormat) {
        SourceDataLine res = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

        try {
            res = (SourceDataLine) AudioSystem.getLine(info);
            res.open(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * Waits a specified number of milliseconds.
     *
     * @param time Time to wait (in milliseconds).
     */
    private static void wait(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets a byte array of all zeros to "play" when audio is muted. This produces no sound.
     *
     * @return Byte array of all zeros.
     */
    private static byte[] setMuteData() {
        return new byte[Sound.byteChunkSize];
    }

    /**
     * Starts playing the audio in a new thread.
     */
    public void play() {
        if (this.filePath != null && !this.running) {
            this.running = true;
            try {
                Thread t = new Thread(this);
                t.start();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Pauses the audio at its current place. Calling this method once pauses the audio stream, calling it
     * again unpauses the audio stream.
     */
    public void pause() {
        this.pause = !this.pause;
    }

    /**
     * Restarts the current song. Always use this method to restart a song and never .stop() followed
     * by .play(), which is not safe.
     */
    public void restart() {
        this.restart = true;
    }

    /**
     * Closes the audio stream. This method takes some time to execute, and as such you should never call
     * .stop() followed immediately by .play(). If you need to restart a song, use .restart().
     */
    public void stop() {
        this.running = false;
    }

    /**
     * Stream continues to play, but no sound is heard. Calling this method once mutes the audio stream,
     * calling it again unmutes the audio stream.
     */
    public void mute() {
        this.mute = !this.mute;
    }

    /**
     * Makes a given audio file loop back to the beginning when the end is reached. Calling this method once
     * will make it loop, calling it again will make it stop looping, but will not stop the audio from playing
     * to the end of a given loop.
     */
    public void loop() {
        this.loop = !this.loop;
    }

    /**
     * Small sections of audio bytes are read off, watching for a call to stop, pause, restart, or mute the audio.
     *
     * @param targetFormat Format the audio will be changed to.
     * @param din          The audio stream.
     */
    private void stream(AudioFormat targetFormat, AudioInputStream din) {
        try {
            byte[] data = new byte[Sound.byteChunkSize];
            SourceDataLine line = Sound.getLine(targetFormat);
            if (line != null) {
                line.start();

                FloatControl gainControl = null;
                if (line.isControlSupported(FloatControl.Type.VOLUME)) {
                    gainControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                } else if (line.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                    gainControl = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
                }
                int nBytesRead = 0;
                while (nBytesRead != -1 && this.running && !this.restart) {
                    if (gainControl != null) {
                        double volume = Sound.volume.getValue();
                        if (gainControl.getType().equals(FloatControl.Type.VOLUME)) {
                            gainControl.setValue((float) ((gainControl.getMaximum() - gainControl.getMinimum()) * volume + gainControl.getMinimum()));
                        } else if (gainControl.getType().equals(FloatControl.Type.MASTER_GAIN)) {
                            gainControl.setValue((float) (StrictMath.log(volume) / StrictMath.log(10.0) * 20.0));
                        }
                    }

                    nBytesRead = din.read(data, 0, data.length);
                    if (nBytesRead != -1) {
                        if (this.mute) {
                            line.write(this.muteData, 0, nBytesRead);
                        } else {
                            line.write(data, 0, nBytesRead);
                        }
                    }

                    while (this.pause && this.running) {
                        Sound.wait(15);
                    }
                }
                line.drain();
                line.stop();
                line.close();
                din.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                AudioInputStream in = AudioSystem.getAudioInputStream(Sound.class.getResourceAsStream(this.filePath));
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
        } catch (IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns if a file is loaded or not.
     *
     * @return File status of null or a file.
     */
    public boolean isLoaded() {
        return this.filePath != null;
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