package view.utils;

import javax.sound.sampled.*;
import java.io.IOException;

public class Sound {
    /**
     * The prefix of the path for the image. It directly goes into the images directory
     */
    private static final String pathPrefix = "/view/resources/sounds/";

    public Sound(String name) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(Sound.class.getResource(Sound.pathPrefix + name));
            DataLine.Info info = new DataLine.Info(Clip.class, audioIn.getFormat());
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioIn);
            clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}
