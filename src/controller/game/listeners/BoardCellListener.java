package controller.game.listeners;

import controller.game.Graphic2DGameController;
import model.game.Position;
import model.game.team.TeamColor;
import view.utils.ExtendedColor;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The type Board cell listener.
 */
public class BoardCellListener extends MouseAdapter {
    /**
     * The Column.
     */
    private final int column;
    /**
     * The Graphic 2 d game controller.
     */
    private final Graphic2DGameController graphic2DGameController;
    /**
     * The Line.
     */
    private final int line;
    /**
     * The Panel.
     */
    private final JPanel panel;

    /**
     * Instantiates a new Board cell listener.
     *
     * @param graphic2DGameController the graphic 2 d game controller
     * @param line                    the line
     * @param column                  the column
     * @param panel                   the panel
     */
    public BoardCellListener(Graphic2DGameController graphic2DGameController, int line, int column, JPanel panel) {
        this.graphic2DGameController = graphic2DGameController;
        this.line = line;
        this.column = column;
        this.panel = panel;
    }

    /**
     * Mouse clicked.
     *
     * @param mouseEvent the mouse event
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        super.mouseClicked(mouseEvent);
        if (this.graphic2DGameController.isHumanUserTurn()) {
            if (this.graphic2DGameController.isMovingPawn() && !this.graphic2DGameController.isCurrentTeamPawn(this.line, this.column)) {
                this.graphic2DGameController.movePawn(new Position(this.line, this.column));
            } else {
                this.graphic2DGameController.selectPawn(new Position(this.line, this.column));
                this.panel.setBorder(BorderFactory.createLineBorder(ExtendedColor.CUSTOM_GREEN, 3));
            }
        }
    }

    /**
     * Mouse entered.
     *
     * @param mouseEvent the mouse event
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        super.mouseEntered(mouseEvent);
        if (this.graphic2DGameController.isHumanUserTurn()) {
            if (this.graphic2DGameController.isMovingPawn()) {
                for (Position position : this.graphic2DGameController.getAllowedMoves()) {
                    if (position.getLine() == this.line && position.getColumn() == this.column) {
                        this.panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }
                }
                if (this.graphic2DGameController.isCurrentTeamPawn(this.line, this.column)) {
                    this.panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            } else {
                this.panel.setBorder(BorderFactory.createLineBorder(ExtendedColor.CUSTOM_GREEN, 3));
                this.panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        }
    }

    /**
     * Mouse exited.
     *
     * @param mouseEvent the mouse event
     */
    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        super.mouseExited(mouseEvent);
        if (this.graphic2DGameController.isHumanUserTurn()) {
            if (!this.graphic2DGameController.isMovingPawn()) {
                Color borderColor = this.graphic2DGameController.getCurrentTeamColor() == TeamColor.BLUE ? Color.BLUE : Color.RED;
                this.panel.setBorder(this.graphic2DGameController.isCurrentTeamPawn(this.line, this.column) ? BorderFactory.createLineBorder(borderColor, 2) : null);
            }
            this.panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }
}
