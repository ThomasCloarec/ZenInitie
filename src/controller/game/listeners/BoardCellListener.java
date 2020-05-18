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

public class BoardCellListener extends MouseAdapter {
    private final int column;
    private final Graphic2DGameController graphic2DGameController;
    private final int line;
    private final JPanel panel;

    public BoardCellListener(Graphic2DGameController graphic2DGameController, int line, int column, JPanel panel) {
        this.graphic2DGameController = graphic2DGameController;
        this.line = line;
        this.column = column;
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        super.mouseClicked(mouseEvent);
        if (this.graphic2DGameController.isMovingPawn() && !this.graphic2DGameController.isCurrentTeamPawn(this.line, this.column)) {
            this.graphic2DGameController.movePawn(new Position(this.line, this.column));
        } else {
            this.graphic2DGameController.selectPawn(new Position(this.line, this.column));
            this.panel.setBorder(BorderFactory.createLineBorder(ExtendedColor.CUSTOM_GREEN, 3));
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        super.mouseEntered(mouseEvent);
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

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        super.mouseExited(mouseEvent);
        if (!this.graphic2DGameController.isMovingPawn()) {
            Color borderColor = this.graphic2DGameController.getCurrentTeamColor() == TeamColor.BLUE ? Color.BLUE : Color.RED;
            this.panel.setBorder(this.graphic2DGameController.isCurrentTeamPawn(this.line, this.column) ? BorderFactory.createLineBorder(borderColor, 2) : null);
        }
        this.panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
}
