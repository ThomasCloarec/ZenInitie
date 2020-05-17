package controller.listeners;

import view.utils.components.ImageComponent;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ImageOnClickListener extends MouseAdapter {
    private final ImageComponent imageComponent;
    private final Runnable runnable;

    public ImageOnClickListener(Runnable runnable, ImageComponent imageComponent) {
        this.runnable = runnable;
        this.imageComponent = imageComponent;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        super.mouseClicked(mouseEvent);
        this.runnable.run();
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        super.mouseEntered(mouseEvent);
        this.imageComponent.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
