package controller.game;

import model.game.Game;
import view.utils.ExtendedColor;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Graphic2DGameController extends GameController{
    public Graphic2DGameController(Game game, Runnable goMenu) {
        super(game, goMenu);
    }

    public static MouseAdapter getBoardListener(JPanel panel, int line, int column) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                System.out.println("(" + line + ", " + column + ")");
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                panel.setBorder(BorderFactory.createLineBorder(ExtendedColor.CUSTOM_GREEN, 3));
                panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                panel.setBorder(null);
            }
        };
    }
}
