package view.subviews.gameview.viewsections;

import controller.game.Graphic2DGameController;

import javax.swing.BorderFactory;
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

    BoardPanel(Graphic2DGameController gameController, JComponent referenceComponent) {
        this.gameController = gameController;
        this.referenceComponent = referenceComponent;

        this.setLayout(new GridLayout(11, 11));

        for (int line = 0; line < 11; line++) {
            for (int column = 0; column < 11; column++) {
                JPanel panel1 = new JPanel();
                panel1.setBackground((line + column) % 2 == 0 ? Color.LIGHT_GRAY : Color.BLACK);

                panel1.addMouseListener(Graphic2DGameController.getBoardListener(panel1, line, column));
                this.add(panel1);
            }
        }

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
}
