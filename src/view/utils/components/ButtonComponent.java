package view.utils.components;

import view.utils.text.AppText;

import javax.swing.*;
import java.awt.*;

public class ButtonComponent extends JButton {
    public ButtonComponent(String string) {
        super(string);
        this.setPreferredSize(new Dimension(350, 50));
        this.setMinimumSize(new Dimension(350, 50));
        this.setMaximumSize(new Dimension(350, 50));
        this.setAlignmentX(JButton.CENTER_ALIGNMENT);
        this.setFont(AppText.getCustomFont());
    }
}
