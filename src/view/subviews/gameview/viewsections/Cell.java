package view.subviews.gameview.viewsections;

import view.utils.components.ImageComponent;

import javax.swing.Box;
import javax.swing.JPanel;

public class Cell extends JPanel {
    private ImageComponent imageComponent;

    public void setImageComponent(ImageComponent imageComponent) {
        if (imageComponent == null) {
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
