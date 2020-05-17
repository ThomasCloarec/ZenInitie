package controller.game;

import controller.game.listeners.BoardCellListener;
import controller.game.listeners.TimerTickListener;
import model.game.Game;
import view.utils.components.TimeComponent;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class Graphic2DGameController extends GameController {
    public Graphic2DGameController(Game game, Runnable goMenu) {
        super(game, goMenu);
    }

    public static ActionListener getTimerTickListener(TimeComponent timeComponent) {
        return new TimerTickListener(timeComponent).invoke();
    }

    public MouseAdapter getBoardCellListener(JPanel panel, int line, int column) {
        return new BoardCellListener(this, line, column, panel);
    }
}
