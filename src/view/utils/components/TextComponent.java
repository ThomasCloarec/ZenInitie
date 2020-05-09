package view.utils.components;

import javax.swing.JEditorPane;
import java.awt.Insets;

public class TextComponent extends JEditorPane {
    public TextComponent() {
        this.setContentType("text/html");
        this.setMargin(new Insets(10, 10, 10, 10));
        this.setOpaque(false);
    }
}
