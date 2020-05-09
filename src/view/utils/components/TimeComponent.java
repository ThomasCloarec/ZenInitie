package view.utils.components;

import view.utils.text.AppText;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;

public class TimeComponent extends JLabel {
    public TimeComponent() {
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setText("<html><center><p>...Zen l'Initié...</p><h1>GO</h1></center></html>");
        Dimension size = this.getPreferredSize();
        this.setMaximumSize(size);
        this.setMinimumSize(size);
        this.setPreferredSize(size);

        Thread thread = new Thread(() -> {
            for (int minutes = 0; minutes < Integer.MAX_VALUE; minutes++) {
                for (int seconds = 0; seconds < 60; seconds++) {
                    this.setText("<html><center><p>" + AppText.getTextFor("game.time") + "</p><h1 margin=\"0\">" + (minutes == 0 ? "" : minutes + "m") + seconds + "s</h1></center></html>");
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
}
