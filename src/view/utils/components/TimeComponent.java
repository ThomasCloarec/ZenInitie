package view.utils.components;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Map;

public class TimeComponent extends JLabel {
    public TimeComponent() {
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setText("<html><center><h1>000m00s</h1></center></html>");
        Dimension size = this.getPreferredSize();
        this.setMaximumSize(size);
        this.setMinimumSize(size);
        this.setPreferredSize(size);
        Thread thread = new Thread(() -> {
            for (int minutes = 0; minutes < Integer.MAX_VALUE; minutes++) {
                for (int seconds = 0; seconds < 60; seconds++) {
                    this.setText("<html><center><h1 margin=\"0\">" + (minutes == 0 ? "" : String.format("%02d", minutes) + "m") + String.format("%02d", seconds) + "s</h1></center></html>");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

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
}
