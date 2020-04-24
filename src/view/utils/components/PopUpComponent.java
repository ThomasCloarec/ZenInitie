package view.utils.components;

import view.utils.ExtendedColor;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.WindowEvent;

public class PopUpComponent extends JDialog {
    public PopUpComponent(String message) {
        super(new JFrame(), false);
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.setAlwaysOnTop(true);
            this.setUndecorated(true);
            this.setLayout(new FlowLayout(FlowLayout.CENTER));

            JLabel label = new JLabel(message);
            label.setForeground(ExtendedColor.LIGHT_GRAY);
            label.setBorder(new CompoundBorder(
                    BorderFactory.createLineBorder(ExtendedColor.CUSTOM_LIGHT_GREY, 3),
                    BorderFactory.createEmptyBorder(20, 20, 20, 20)));

            this.add(label);
            this.pack();

            this.setLocationRelativeTo(SwingUtilities.getWindowAncestor(this));
            this.setVisible(true);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.setVisible(false);
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }).start();

    }
}