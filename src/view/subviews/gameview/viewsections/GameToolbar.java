package view.subviews.gameview.viewsections;

import controller.game.Graphic2DGameController;
import view.utils.Sound;
import view.utils.SoundVolume;
import view.utils.components.ScaledImageComponent;
import view.utils.components.TimeComponent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Map;
import java.util.function.Supplier;

/**
 * The type Game toolbar.
 */
public class GameToolbar extends JPanel {
    /**
     * The Height.
     */
    private final Supplier<Integer> height;
    /**
     * The Time component.
     */
    private final TimeComponent timeComponent;
    /**
     * The Width.
     */
    private final Supplier<Integer> width;

    /**
     * Instantiates a new Game toolbar.
     *
     * @param gameController the game controller
     * @param width          the width
     * @param height         the height
     */
    GameToolbar(Graphic2DGameController gameController, Supplier<Integer> width, Supplier<Integer> height) {
        this.width = width;
        this.height = height;

        ScaledImageComponent exit = new ScaledImageComponent("icons/exit.png", 0.25, 0.8, this);
        exit.addOnClick(gameController::goMenu);

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        ScaledImageComponent volume = new ScaledImageComponent(SoundVolume.getIconPath(), exit);
        volume.addOnClick(() -> {
            Sound.nextVolumeTick();
            volume.setBaseImage(SoundVolume.getIconPath(), false);
            volume.revalidate();
            volume.repaint();
        });

        this.add(Box.createHorizontalGlue());
        this.add(volume);
        this.add(Box.createHorizontalGlue());
        this.timeComponent = new TimeComponent();
        this.add(this.timeComponent);
        this.add(Box.createHorizontalGlue());
        this.add(exit);
        this.add(Box.createHorizontalGlue());

        this.setOpaque(false);
    }

    /**
     * Start the timer
     */
    public void start() {
        this.timeComponent.startTimer();
    }

    /**
     * Paint component.
     *
     * @param graphics the graphics
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        Dimension size = new Dimension(this.width.get(), this.height.get());
        this.setSize(size);
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setMaximumSize(size);

        Map<Object, Object> qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        ((Graphics2D) graphics).setRenderingHints(qualityHints);

        graphics.setColor(new Color(77, 77, 77));
        graphics.fillRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, 20, 20);

        graphics.setColor(new Color(62, 62, 62));
        graphics.fillRoundRect(5, 5, this.getWidth() - 11, this.getHeight() - 11, 20, 20);

        super.paintComponent(graphics);
    }
}
