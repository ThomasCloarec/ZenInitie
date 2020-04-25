package view.utils.components;

import javax.swing.*;
import java.awt.*;

public class TextComponent extends JEditorPane {
    public TextComponent() {
        this.setContentType("text/html");
        this.setMargin(new Insets(10, 10, 10, 10));
        this.setOpaque(false);
    }
}
