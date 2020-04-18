package view.utils.components;

import view.utils.text.AppText;

import javax.swing.*;
import java.awt.*;

public class ButtonComponent extends JButton {
    public ButtonComponent(String string) {
        super(string);
        this.setAlignmentX(JButton.CENTER_ALIGNMENT);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        int frameWidth = this.getParent().getParent().getWidth();
        int frameHeight = this.getParent().getParent().getHeight();

        this.setFont(AppText.getCustomFont().deriveFont(Math.min(frameWidth * 0.6f, frameHeight) / 30f));

        int buttonHeight = frameHeight / 13;
        int buttonWidth = (int) (frameWidth / 3.8);

        Dimension buttonDimension = new Dimension(buttonWidth, buttonHeight);

        this.setPreferredSize(buttonDimension);
        this.setMinimumSize(buttonDimension);
        this.setMaximumSize(buttonDimension);
    }
}
