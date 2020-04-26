package view.sub_views.game_view.view_sections;

import view.utils.components.ScaledImageComponent;

import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;

public class GameToolbar extends JPanel {
    private final Supplier<Integer> height;
    private final Supplier<Integer> width;

    GameToolbar(Supplier<Integer> width, Supplier<Integer> height) {
        this.width = width;
        this.height = height;

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        ScaledImageComponent settings = new ScaledImageComponent("icons/settings.png", 0.7, this);
        settings.addOnClick(() -> System.out.println("SETTINGS"));
        ScaledImageComponent volume = new ScaledImageComponent("icons/volume_up.png", 0.7, this);
        volume.addOnClick(() -> System.out.println("VOLUME"));
        ScaledImageComponent save = new ScaledImageComponent("icons/save.png", 0.7, this);
        save.addOnClick(() -> System.out.println("SAVE"));
        ScaledImageComponent exit = new ScaledImageComponent("icons/exit.png", 0.7, this);
        exit.addOnClick(() -> System.out.println("EXIT"));

        this.add(Box.createHorizontalGlue());
        this.add(settings);
        this.add(Box.createHorizontalGlue());
        this.add(volume);
        this.add(Box.createHorizontalGlue());
        this.add(save);
        this.add(Box.createHorizontalGlue());
        this.add(exit);
        this.add(Box.createHorizontalGlue());

        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Dimension size = new Dimension(this.width.get(), this.height.get());
        this.setSize(size);
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setMaximumSize(size);

        super.paintComponent(graphics);
    }
}
