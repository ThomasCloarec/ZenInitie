package view.subviews.gameview.viewsections;

import controller.Graphic2DController;
import controller.game.Graphic2DGameController;
import model.game.Game;
import model.game.Position;
import model.game.board.Pawn;
import model.game.team.TeamColor;
import view.utils.ExtendedColor;
import view.utils.components.ScaledImageComponent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

/**
 * The type Board panel.
 */
public class BoardPanel extends JPanel {
    /**
     * The Game controller.
     */
    private final Graphic2DGameController gameController;
    /**
     * The Reference component.
     */
    private final JComponent referenceComponent;
    /**
     * The Cells.
     */
    private Cell[][] cells;

    /**
     * Instantiates a new Board panel.
     *
     * @param gameController     the game controller
     * @param referenceComponent the reference component
     */
    BoardPanel(Graphic2DGameController gameController, JComponent referenceComponent) {
        this.gameController = gameController;
        this.referenceComponent = referenceComponent;

        this.addComponentListener(Graphic2DController.getResizeListener(this));
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
    }

    /**
     * When a new game starts
     *
     * @param game the game
     */
    public void start(Game game) {
        this.setLayout(new GridLayout(game.getBoardSize(), game.getBoardSize()));

        this.cells = new Cell[game.getBoardSize()][game.getBoardSize()];

        for (int line = 0; line < game.getBoardSize(); line++) {
            for (int column = 0; column < game.getBoardSize(); column++) {
                Cell cell = new Cell();
                cell.setLayout(new BoxLayout(cell, BoxLayout.X_AXIS));
                cell.setBackground((line + column) % 2 == 0 ? Color.LIGHT_GRAY : Color.BLACK);

                cell.addMouseListener(this.gameController.getBoardCellListener(cell, line, column));
                this.add(cell);
                this.cells[line][column] = cell;
            }
        }

        this.updateBoard(game);
    }

    /**
     * Select pawn.
     *
     * @param game the game
     */
    public void selectPawn(Game game) {
        this.updateBoard(game);
    }

    /**
     * Move pawn.
     *
     * @param game the game
     */
    public void movePawn(Game game) {
        for (Cell[] cellLine : this.cells) {
            for (Cell cell : cellLine) {
                cell.setBorder(null);
            }
        }

        for (Position allowedMove : game.getAllowedMoves()) {
            this.cells[allowedMove.getLine()][allowedMove.getColumn()].setBorder(BorderFactory.createLineBorder(ExtendedColor.CUSTOM_GREEN, 3));
        }
    }

    /**
     * Update board.
     *
     * @param game the game
     */
    private void updateBoard(Game game) {
        Pawn[][] boardArray = game.getBoardArray();

        for (int line = 0; line < this.cells.length; line++) {
            for (int column = 0; column < this.cells[0].length; column++) {
                Cell cell = this.cells[line][column];
                Pawn pawn = boardArray[line][column];
                Color borderColor = game.getCurrentTeam().getTeamColor() == TeamColor.BLUE ? Color.BLUE : Color.RED;
                cell.setBorder(game.isPawnSelectable(pawn) ? BorderFactory.createLineBorder(borderColor, 2) : null);

                if (pawn == Pawn.ZEN) {
                    cell.setImageComponent(new ScaledImageComponent("pawns/zen.png", 0.85, cell));
                } else if (pawn == Pawn.BLUE) {
                    cell.setImageComponent(new ScaledImageComponent("pawns/blue.png", 0.85, cell));
                } else if (pawn == Pawn.RED) {
                    cell.setImageComponent(new ScaledImageComponent("pawns/red.png", 0.85, cell));
                } else {
                    cell.setImageComponent(null);
                }
            }
        }

        this.revalidate();
        this.repaint();
    }

    /**
     * Paint.
     *
     * @param graphics the graphics
     */
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        int size = (int) Math.min(this.referenceComponent.getHeight() * (3.0d / 4.0d), this.referenceComponent.getWidth());
        Dimension dimension = new Dimension(size, size);
        this.setSize(dimension);
        this.setMinimumSize(dimension);
        this.setMaximumSize(dimension);
        this.setPreferredSize(dimension);
    }
}
