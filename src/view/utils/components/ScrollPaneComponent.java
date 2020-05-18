package view.utils.components;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.function.Supplier;

/**
 * The type Scroll pane component.
 */
public class ScrollPaneComponent extends JScrollPane {
    /**
     * The Height.
     */
    private final Supplier<Integer> height;
    /**
     * The Width.
     */
    private final Supplier<Integer> width;

    /**
     * Instantiates a new Scroll pane component.
     *
     * @param component the component
     * @param width     the width
     * @param height    the height
     */
    public ScrollPaneComponent(JComponent component, Supplier<Integer> width, Supplier<Integer> height) {
        super(component);
        this.width = width;
        this.height = height;
        this.setOpaque(false);
        this.getViewport().setOpaque(false);
        this.setBorder(null);
    }

    /**
     * Paint component.
     *
     * @param graphics the graphics
     */
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
