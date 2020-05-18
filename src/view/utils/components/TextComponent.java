package view.utils.components;

import javax.swing.JEditorPane;
import java.awt.Insets;

/**
 * The type Text component.
 */
public class TextComponent extends JEditorPane {
    /**
     * Instantiates a new Text component.
     */
    public TextComponent() {
        this.setContentType("text/html");
        this.setMargin(new Insets(10, 10, 10, 10));
        this.setOpaque(false);
    }
}
