package view.utils.components;

import view.utils.text.AppText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.BooleanSupplier;

public class ButtonComponent extends JButton {
    private final BooleanSupplier horizontalMode;
    private final JComponent referenceComponent;

    public ButtonComponent(String string, JComponent referenceComponent, BooleanSupplier horizontalMode) {
        super(string);
        this.referenceComponent = referenceComponent;
        this.horizontalMode = horizontalMode;

        this.setAlignmentX(JButton.CENTER_ALIGNMENT);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                ButtonComponent.this.setCursor(new Cursor((Cursor.HAND_CURSOR)));
            }
        });

        this.updateButtons();
    }

    public void updateButtons() {
        if (this.referenceComponent.getWidth() != 0) {
            int frameWidth = this.referenceComponent.getWidth();
            int frameHeight = this.referenceComponent.getHeight();

            Dimension buttonDimension;
            if (this.horizontalMode.getAsBoolean()) {
                this.setFont(AppText.getCustomFont().deriveFont(Math.min(frameWidth * 0.07f, frameHeight * 0.04f)));
                buttonDimension = new Dimension((int) (frameWidth * 0.8f), (int) (frameHeight * 0.1f));
            } else {
                this.setFont(AppText.getCustomFont().deriveFont(Math.min(frameWidth * 0.04f, frameHeight * 0.04f)));
                buttonDimension = new Dimension((int) (frameWidth * 0.6f), (int) (frameHeight * 0.1f));
            }

            this.setPreferredSize(buttonDimension);
            this.setMinimumSize(buttonDimension);
            this.setMaximumSize(buttonDimension);
            this.setSize(buttonDimension);
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.updateButtons();
    }
}
