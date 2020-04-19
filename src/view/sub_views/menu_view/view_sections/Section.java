package view.sub_views.menu_view.view_sections;

import controller.menu.MenuController;
import view.utils.components.LightComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;

public abstract class Section extends JPanel {
    protected final ArrayList<LightComponent> lights = new ArrayList<>();
    protected final BooleanSupplier horizontalMode;
    protected final MenuController menuController;

    public Section(MenuController menuController, BooleanSupplier horizontalMode) {
        this.menuController = menuController;
        this.horizontalMode = horizontalMode;

        this.setOpaque(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void paintLights(Graphics2D graphics2D) {
        for (LightComponent light : this.lights) {
            light.paintLight(graphics2D);
        }
    }
}
