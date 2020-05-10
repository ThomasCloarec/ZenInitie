package controller.game;

import model.game.Game;
import view.utils.ExtendedColor;
import view.utils.components.TimeComponent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Graphic2DGameController extends GameController {
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

    public static ActionListener getTimerTickListener(TimeComponent timeComponent) {
        return actionEvent -> {
            if (!SwingUtilities.getWindowAncestor(timeComponent).isVisible()) {
                timeComponent.stopTimer();
            }

            timeComponent.setSeconds(timeComponent.getSeconds() + 1);
            if (timeComponent.getSeconds() >= 60) {
                timeComponent.setSeconds(0);
                timeComponent.setMinutes(timeComponent.getMinutes() + 1);
            }

            if (timeComponent.getMinutes() < 100) {
                timeComponent.setText("<html><center><h1 margin=\"0\">" + (timeComponent.getMinutes() == 0 ? "" : String.format("%02d", timeComponent.getMinutes()) + "m") + String.format("%02d", timeComponent.getSeconds()) + "s</h1></center></html>");
            } else {
                timeComponent.setText("<html><center><h1 margin=\"0\">" + timeComponent.getMinutes() + "m</h1></center></html>");
            }
        };
    }
}
