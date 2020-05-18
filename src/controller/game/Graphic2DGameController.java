package controller.game;

import controller.game.listeners.BoardCellListener;
import controller.game.listeners.TimerTickListener;
import model.game.Game;
import view.utils.components.TimeComponent;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

/**
 * The type Graphic 2 d game controller.
 */
public class Graphic2DGameController extends GameController {
    /**
     * Instantiates a new Graphic 2 d game controller.
     *
     * @param game   the game
     * @param goMenu the go menu
     */
    public Graphic2DGameController(Game game, Runnable goMenu) {
        super(game, goMenu);
    }

    /**
     * Gets timer tick listener.
     *
     * @param timeComponent the time component
     * @return the timer tick listener
     */
    public static ActionListener getTimerTickListener(TimeComponent timeComponent) {
        return new TimerTickListener(timeComponent).invoke();
    }

    /**
     * Gets board cell listener.
     *
     * @param panel  the panel
     * @param line   the line
     * @param column the column
     * @return the board cell listener
     */
    public MouseAdapter getBoardCellListener(JPanel panel, int line, int column) {
        return new BoardCellListener(this, line, column, panel);
    }
}
