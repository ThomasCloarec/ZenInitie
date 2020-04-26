package view.utils.components;

import javax.swing.*;
import java.awt.*;

public class MenuComponent extends JMenuBar {
    private final JMenu menu = new JMenu();
    private final ImageIcon menuIcon = new ImageIcon(MenuComponent.class.getResource(ImageComponent.pathPrefix + "icons/menu.png"));
    private JComponent referenceComponent;

    public MenuComponent() {
        this.setCustomSize(new Dimension(70, 70));

        this.menu.setIcon(this.menuIcon);
        this.add(this.menu);
    }

    public void addElement(JComponent component) {
        this.menu.add(component);
        this.menu.revalidate();
        this.menu.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setCustomSize(this.referenceComponent.getSize());
    }

    private void setCustomSize(Dimension menuDimension) {
        this.setSize(menuDimension);
        this.setPreferredSize(menuDimension);
        this.setMinimumSize(menuDimension);
        this.setMaximumSize(menuDimension);
        this.menu.setSize(menuDimension);
        this.menu.setPreferredSize(menuDimension);
        this.menu.setMinimumSize(menuDimension);
        this.menu.setMaximumSize(menuDimension);
        this.menu.setIcon(new ImageIcon(this.menuIcon.getImage().getScaledInstance((int) (menuDimension.width * 0.8), (int) (menuDimension.height * 0.82), Image.SCALE_DEFAULT)));
        //Arrays.asList(this.menu.getComponents()).forEach(component -> ((ImageComponent)component).setCustomSize(menuDimension));
    }

    public void setReferenceComponent(JComponent referenceComponent) {
        this.referenceComponent = referenceComponent;
    }
}