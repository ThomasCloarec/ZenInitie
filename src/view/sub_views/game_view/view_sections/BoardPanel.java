package view.sub_views.game_view.view_sections;

import controller.game.GameController;
import view.utils.ExtendedColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardPanel extends JPanel {
    private final GameController gameController;
    private final JComponent referenceComponent;

    BoardPanel(GameController gameController, JComponent referenceComponent) {
        this.gameController = gameController;
        this.referenceComponent = referenceComponent;

        this.setLayout(new GridLayout(11, 11));

        for (int line = 0; line < 11; line++) {
            for (int column = 0; column < 11; column++) {
                JPanel panel1 = new JPanel();
                panel1.setBackground((line + column) % 2 == 0 ? ExtendedColor.LIGHT_GRAY : ExtendedColor.BLACK);

                int finalLine = line;
                int finalColumn = column;
                panel1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        super.mouseClicked(mouseEvent);
                        System.out.println("(" + finalLine + ", " + finalColumn + ")");
                    }

                    @Override
                    public void mouseEntered(MouseEvent mouseEvent) {
                        super.mouseEntered(mouseEvent);
                        panel1.setBorder(BorderFactory.createLineBorder(ExtendedColor.CUSTOM_GREEN, 3));
                        panel1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }

                    @Override
                    public void mouseExited(MouseEvent mouseEvent) {
                        super.mouseExited(mouseEvent);
                        panel1.setBorder(null);
                    }
                });
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
        this.setBorder(BorderFactory.createLineBorder(ExtendedColor.LIGHT_GRAY, 3));
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Dimension dimension = new Dimension(this.referenceComponent.getWidth(), this.referenceComponent.getWidth());
        this.setSize(dimension);
        this.setMinimumSize(dimension);
        this.setMaximumSize(dimension);
        this.setPreferredSize(dimension);
    }
}
