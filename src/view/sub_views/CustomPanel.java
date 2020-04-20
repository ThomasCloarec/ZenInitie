package view.sub_views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.function.BooleanSupplier;

public abstract class CustomPanel<T, L extends Section<T>, C extends Section<T>, R extends Section<T>> extends JPanel {
    protected final T controller;
    protected final BooleanSupplier isHorizontalMode = () -> this.getWidth() > this.getHeight();
    protected C contentSection;
    protected L leftSection;
    protected R rightSection;

    public CustomPanel(T controller) {
        this.controller = controller;

        SwingUtilities.invokeLater(() -> this.setOpaque(false));
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                super.componentResized(componentEvent);

                CustomPanel.this.revalidate();
                CustomPanel.this.repaint();
            }
        });
    }

    protected void switchHorizontalMode() {
        this.removeAll();
        this.setLayout(new GridLayout(1, 3));
        this.add(this.leftSection);
        this.add(this.contentSection);
        this.add(this.rightSection);
        this.revalidate();
        this.repaint();
    }

    protected void switchVerticalMode() {
        this.removeAll();
        this.setLayout(new GridLayout(1, 1));
        this.add(this.contentSection);
        this.revalidate();
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if ((this.getComponentCount() != 3 && this.isHorizontalMode.getAsBoolean())) {
            this.switchHorizontalMode();
        } else if (this.getComponentCount() != 1 && !this.isHorizontalMode.getAsBoolean()) {
            this.switchVerticalMode();
        }

        Graphics2D graphics2D = (Graphics2D) graphics;
        this.leftSection.paintLights(graphics2D);
        this.contentSection.paintLights(graphics2D);
        this.rightSection.paintLights(graphics2D);
    }
}
