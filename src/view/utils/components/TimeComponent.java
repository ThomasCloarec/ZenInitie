package view.utils.components;

import controller.game.Graphic2DGameController;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Map;

/**
 * The type Time component.
 */
public class TimeComponent extends JLabel {
    /**
     * The Minutes.
     */
    private int minutes;
    /**
     * The Seconds.
     */
    private int seconds;
    /**
     * The Timer.
     */
    private Timer timer;

    /**
     * Instantiates a new Time component.
     */
    public TimeComponent() {
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setText("<html><center><h1>000m00s</h1></center></html>");
        Dimension size = this.getPreferredSize();
        this.setMaximumSize(size);
        this.setMinimumSize(size);
        this.setPreferredSize(size);
        this.setText("<html><center><h1>00s</h1></center></html>");
    }

    /**
     * Stop timer.
     */
    public void stopTimer() {
        if (this.timer.isRunning()) {
            this.timer.stop();
        }
    }

    /**
     * Start timer.
     */
    public void startTimer() {
        this.timer = new Timer(1000, Graphic2DGameController.getTimerTickListener(this));
        this.timer.setInitialDelay(8000);
        this.timer.start();
    }

    /**
     * Paint component.
     *
     * @param graphics the graphics
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        Map<Object, Object> qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        ((Graphics2D) graphics).setRenderingHints(qualityHints);

        graphics.setColor(new Color(98, 98, 98));
        graphics.fillRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, 20, 20);

        graphics.setColor(new Color(83, 83, 83));
        graphics.fillRoundRect(2, 2, this.getWidth() - 5, this.getHeight() - 5, 20, 20);

        super.paintComponent(graphics);
    }

    /**
     * Gets minutes.
     *
     * @return Value of minutes.
     */
    public int getMinutes() {
        return this.minutes;
    }

    /**
     * Sets new minutes.
     *
     * @param minutes New value of minutes.
     */
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    /**
     * Gets seconds.
     *
     * @return Value of seconds.
     */
    public int getSeconds() {
        return this.seconds;
    }

    /**
     * Sets new seconds.
     *
     * @param seconds New value of seconds.
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
