package view.utils.components;

import view.utils.ExtendedColor;
import view.utils.text.AppText;

import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.BooleanSupplier;

public class ButtonComponent extends JButton {
    private final BooleanSupplier horizontalMode;
    private final JComponent referenceComponent;
    private Font font;

    public ButtonComponent(String text, JComponent referenceComponent, BooleanSupplier horizontalMode) {
        super(text);
        this.referenceComponent = referenceComponent;
        this.horizontalMode = horizontalMode;

        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.addMouseListener(new MouseAdapter() {
            private final int blue = ButtonComponent.this.getBackground().getBlue();
            private final int green = ButtonComponent.this.getBackground().getGreen();
            private final int red = ButtonComponent.this.getBackground().getRed();

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                ButtonComponent.this.setCursor(new Cursor((Cursor.HAND_CURSOR)));
                ButtonComponent.this.setBackground(new ExtendedColor(this.red, this.green, this.blue).brighter(10));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                ButtonComponent.this.setBackground(new ExtendedColor(this.red, this.green, this.blue));
            }
        });

        this.updateButtons();
        this.setFocusable(false);
    }

    public void updateButtons() {
        if (this.referenceComponent.getWidth() != 0) {
            int frameWidth = this.referenceComponent.getWidth();
            int frameHeight = this.referenceComponent.getHeight();

            Dimension buttonDimension;
            Font newFont;
            if (this.horizontalMode.getAsBoolean()) {
                newFont = AppText.getCustomFont().deriveFont(Math.min(frameWidth * 0.07f, frameHeight * 0.04f));
                buttonDimension = new Dimension((int) (frameWidth * 0.8f), (int) (frameHeight * 0.1f));
            } else {
                newFont = AppText.getCustomFont().deriveFont(Math.min(frameWidth * 0.04f, frameHeight * 0.04f));
                buttonDimension = new Dimension((int) (frameWidth * 0.6f), (int) (frameHeight * 0.1f));
            }

            if (!newFont.equals(this.font)) {
                this.font = newFont;
                this.setFont(this.font);
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
