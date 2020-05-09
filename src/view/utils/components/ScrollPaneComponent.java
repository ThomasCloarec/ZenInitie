package view.utils.components;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.function.Supplier;

public class ScrollPaneComponent extends JScrollPane {
    private final Supplier<Integer> height;
    private final Supplier<Integer> width;

    public ScrollPaneComponent(JComponent component, Supplier<Integer> width, Supplier<Integer> height) {
        super(component);
        this.width = width;
        this.height = height;
        this.setOpaque(false);
        this.getViewport().setOpaque(false);
        this.setBorder(null);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Dimension size = new Dimension(this.width.get(), this.height.get());
        this.setSize(size);
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setMaximumSize(size);

        super.paintComponent(graphics);
    }
}
