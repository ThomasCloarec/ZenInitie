package view.utils.components;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Color;

public class FireComponent extends JPanel {
    public FireComponent() {
        this.setBackground(Color.BLACK);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    public void launch() {
        try {
            this.add(Box.createHorizontalGlue());
            this.add(new ImageComponent("logo_zen.png", true));
            this.add(Box.createHorizontalGlue());
            this.revalidate();
            this.repaint();
            Thread.sleep(500);
            this.removeAll();
            this.add(Box.createHorizontalGlue());
            this.add(new ImageComponent("logo_zen_fire.gif", true));
            this.add(Box.createHorizontalGlue());
            this.revalidate();
            this.repaint();
            Thread.sleep(4500);
            this.removeAll();
            this.revalidate();
            this.repaint();
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}