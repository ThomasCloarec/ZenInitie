package view.subviews.gameview.viewsections;

import controller.game.GameController;
import view.utils.Sound;
import view.utils.components.MenuComponent;
import view.utils.components.ScaledImageComponent;
import view.utils.components.TimeComponent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.function.Supplier;

public class GameToolbar extends JPanel {
    private final Supplier<Integer> height;
    private final Supplier<Integer> width;

    GameToolbar(GameController gameController, Supplier<Integer> width, Supplier<Integer> height) {
        this.width = width;
        this.height = height;

        ScaledImageComponent exit = new ScaledImageComponent("icons/exit.png", 0.3, this);
        exit.addOnClick(gameController::goMenu);

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        ScaledImageComponent volume = new ScaledImageComponent(Sound.isVolumeOn() ? "icons/volume_up.png" : "icons/volume_down.png", exit);
        volume.addOnClick(() -> {
            Sound.toggleVolume();
            volume.setImage(Sound.isVolumeOn() ? "icons/volume_up.png" : "icons/volume_down.png");
            volume.revalidate();
            volume.repaint();
        });

        MenuComponent menu = new MenuComponent();
        menu.addElement(volume);
        menu.setReferenceComponent(exit);

        this.add(Box.createHorizontalGlue());
        this.add(menu);
        this.add(Box.createHorizontalGlue());
        this.add(new TimeComponent());
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
