package view.utils;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;

public class Sound {
    /**
     * The prefix of the path for the image. It directly goes into the images directory
     */
    private static final String pathPrefix = "/view/resources/sounds/";

    public Sound(String name) {
        new Thread(() -> {
            try {
                BufferedInputStream bis = new BufferedInputStream(Sound.class.getResourceAsStream(Sound.pathPrefix + name));
                Player player = new Player(bis);
                player.play();
                if (player.isComplete()) {
                    player.close();
                }
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
