package view.subviews.gameview.viewsections;

import controller.game.Graphic2DGameController;
import model.game.Game;
import model.game.Pawn;
import view.utils.components.ScaledImageComponent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class BoardPanel extends JPanel {
    private final Graphic2DGameController gameController;
    private final JComponent referenceComponent;
    private JPanel[][] cells;

    BoardPanel(Graphic2DGameController gameController, JComponent referenceComponent) {
        this.gameController = gameController;
        this.referenceComponent = referenceComponent;

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                super.componentResized(componentEvent);
                BoardPanel.this.doLayout();
            }
        });
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
    }

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

    public void start(Game game) {
        this.setLayout(new GridLayout(game.getBoardSize(), game.getBoardSize()));

        this.cells = new JPanel[game.getBoardSize()][game.getBoardSize()];

        for (int line = 0; line < game.getBoardSize(); line++) {
            for (int column = 0; column < game.getBoardSize(); column++) {
                JPanel cell = new JPanel();
                cell.setLayout(new BoxLayout(cell, BoxLayout.X_AXIS));
                cell.setBackground((line + column) % 2 == 0 ? Color.LIGHT_GRAY : Color.BLACK);

                cell.addMouseListener(Graphic2DGameController.getBoardListener(cell, line, column));
                this.add(cell);
                this.cells[line][column] = cell;
            }
        }

        this.updateBoard(game.getBoardArray());
    }

    private void updateBoard(Pawn[][] boardArray) {
        for (int line = 0; line < this.cells.length; line++) {
            for (int column = 0; column < this.cells[0].length; column++) {
                JPanel cell = this.cells[line][column];

                cell.removeAll();
                cell.add(Box.createHorizontalGlue());

                Pawn pawn = boardArray[line][column];
                if (pawn == Pawn.ZEN) {
                    cell.add(new ScaledImageComponent("pawns/zen.png", 0.85, cell));
                } else if (pawn == Pawn.BLUE) {
                    cell.add(new ScaledImageComponent("pawns/blue.png", 0.85, cell));
                } else if (pawn == Pawn.RED) {
                    cell.add(new ScaledImageComponent("pawns/red.png", 0.85, cell));
                }

                cell.add(Box.createHorizontalGlue());
            }
        }
    }
}
