package controller.listeners;

import view.utils.components.ImageComponent;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The type Image on click listener.
 */
public class ImageOnClickListener extends MouseAdapter {
    /**
     * The Image component.
     */
    private final ImageComponent imageComponent;
    /**
     * The Runnable.
     */
    private final Runnable runnable;

    /**
     * Instantiates a new Image on click listener.
     *
     * @param runnable       the runnable
     * @param imageComponent the image component
     */
    public ImageOnClickListener(Runnable runnable, ImageComponent imageComponent) {
        this.runnable = runnable;
        this.imageComponent = imageComponent;
    }

    /**
     * Mouse clicked.
     *
     * @param mouseEvent the mouse event
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        super.mouseClicked(mouseEvent);
        this.runnable.run();
    }

    /**
     * Mouse entered.
     *
     * @param mouseEvent the mouse event
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        super.mouseEntered(mouseEvent);
        this.imageComponent.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
