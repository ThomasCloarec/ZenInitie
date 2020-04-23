package view.sub_views.game_view.view_sections;

import controller.game.GameController;
import view.sub_views.Section;
import view.utils.ExtendedColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.BooleanSupplier;

public class GameContentSection extends Section<GameController> {
    JPanel panel;

    public GameContentSection(GameController gameController, BooleanSupplier horizontalMode) {
        super(gameController, horizontalMode);
        this.add(Box.createVerticalGlue());
        this.panel = new JPanel();
        this.panel.setLayout(new GridLayout(11, 11));
        for (int i = 0; i < 121; i++) {
            JPanel panel1 = new JPanel();
            panel1.setBackground(i % 2 == 0 ? ExtendedColor.LIGHT_GRAY : ExtendedColor.BLACK);
            panel1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent mouseEvent) {
                    super.mouseEntered(mouseEvent);
                    panel1.setBorder(BorderFactory.createLineBorder(ExtendedColor.CUSTOM_GREEN, 3));
                    panel1.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent mouseEvent) {
                    super.mouseExited(mouseEvent);
                    panel1.setBorder(null);
                }
            });
            this.panel.add(panel1);
        }
        this.panel.setBorder(BorderFactory.createLineBorder(ExtendedColor.LIGHT_GRAY, 3));
        this.add(this.panel);
        this.add(Box.createVerticalGlue());
        this.add(Box.createVerticalGlue());
        this.add(Box.createVerticalGlue());
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Dimension dimension = new Dimension(this.getWidth(), this.getWidth());
        this.panel.setSize(dimension);
        this.panel.setMinimumSize(dimension);
        this.panel.setMaximumSize(dimension);
        this.panel.setPreferredSize(dimension);
    }
}
