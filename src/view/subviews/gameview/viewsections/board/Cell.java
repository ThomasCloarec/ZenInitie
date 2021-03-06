package view.subviews.gameview.viewsections.board;

import view.utils.components.ImageComponent;

import javax.swing.Box;
import javax.swing.JPanel;

/**
 * The type Cell.
 */
public class Cell extends JPanel {
    /**
     * The Image component.
     */
    private ImageComponent imageComponent;

    /**
     * Sets image component.
     *
     * @param imageComponent the image component
     */
    public void setImageComponent(ImageComponent imageComponent) {
        if (imageComponent == null) {
            this.imageComponent = null;
            this.removeAll();
        } else if (this.imageComponent == null || !imageComponent.getPathName().equals(this.imageComponent.getPathName())) {
            this.imageComponent = imageComponent;
            this.removeAll();
            this.add(Box.createHorizontalGlue());
            this.add(imageComponent);
            this.add(Box.createHorizontalGlue());
        }
    }
}
