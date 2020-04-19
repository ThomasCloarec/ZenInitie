package view.utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    /**
     * The prefix of the path for the image. It directly goes into the images directory
     */
    private static final String pathPrefix = "/view/resources/sounds/";

    public Sound(String name) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Sound.pathPrefix + name).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}
