package view.sub_views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.function.BooleanSupplier;

/**
 * This class should be used as the main ContentPanel to get a responsive interface.
 * It uses a GridLayout containing a Section in each cell. There are two modes :
 * <ul>
 *     <li>1 line x 3 columns for the horizontal mode (LeftSection, ContentSection and RightSection displayed)</li>
 *     <li>1 line x 1 columns for the vertical mode (only ContentSection displayed)</li>
 * </ul>
 * </br>
 * A CustomPanel is composed of three sections (LeftSection, CenterSection and RightSection).
 * <b>LeftSection & RightSection</b> are only used in the horizontalMode <i>(frame width > frame height)</i>
 * and are used to make the interface prettier. They are not displayed in the verticalMode.
 * </br></br>
 * Components of each section can and should have a responsive behavior depending of the size of their section to use the full power of this CustomPanel.
 * Special attention is required for the <b>ContentSection</b> as it's the only one remaining when the verticalMode is activated.
 * </br></br>
 * This class is generic for each component described in the params of the class.
 * The generic types are named following the <a href="https://google.github.io/styleguide/javaguide.html#s5.2.8-type-variable-names" target="_blank">
 * Google Java Style Convention</a> (allowing more than one letter to name the type).
 *
 * @param <ControllerT>     A generic type for the Controller. Used to control the generic type of sections.
 * @param <LeftSectionT>    A generic type for the LeftSection, subtype the Section type
 * @param <ContentSectionT> A generic type for the ContentSection, subtype the Section type
 * @param <RightSectionT>   A generic type for the RightSection, subtype the Section type
 */
public abstract class CustomPanel<ControllerT, LeftSectionT extends Section<ControllerT>, ContentSectionT extends Section<ControllerT>, RightSectionT extends Section<ControllerT>> extends JPanel {
    /**
     * Just another normal controller (MVC pattern). Receive the actions from the user, check them, then transmit them to the model.
     */
    protected final ControllerT controller;
    /**
     * A short function used to know the largest side of the frame.
     */
    protected final BooleanSupplier isHorizontalMode = () -> this.getWidth() > this.getHeight();
    /**
     * This section is horizontally centered in the CustomPanel and is always displayed <i>(in horizontalMode and verticalMode)</i>.
     */
    protected ContentSectionT contentSection;
    /**
     * The LeftSection of the CustomPanel, more information in the javadoc of the class.
     */
    protected LeftSectionT leftSection;
    /**
     * The RightSection of the CustomPanel, more information in the javadoc of the class.
     */
    protected RightSectionT rightSection;

    /**
     * The constructor of the class CustomPanel, setup the component and make him repaint after each resize to avoid painting problems for example if using a fullscreen mode.
     *
     * @param controller The controller of the CustomPanel. Its type is defined by the generic type given to the class.
     */
    public CustomPanel(ControllerT controller) {
        this.controller = controller;

        SwingUtilities.invokeLater(() -> this.setOpaque(false));
    }

    /**
     * Switch the CustomPanel to an horizontalMode. All sections are displayed.
     */
    protected void switchHorizontalMode() {
        this.removeAll();
        this.setLayout(new GridLayout(1, 3));
        this.add(this.leftSection);
        this.add(this.contentSection);
        this.add(this.rightSection);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                super.componentResized(componentEvent);
                CustomPanel.this.revalidate();
                CustomPanel.this.repaint();
            }
        });
    }

    /**
     * Switch the CustomPanel to a verticalMode. Only the ContentSection is displayed (the components should adapt their behavior to this mode too).
     */
    protected void switchVerticalMode() {
        this.removeAll();
        this.setLayout(new GridLayout(1, 1));
        this.add(this.contentSection);
    }

    /**
     * Switch the modes (horizontal or vertical) if it detects a resize that changed the largest side of the frame.
     * Also painting the lights of each section using the <i>paintLights(Graphics2D g)</i> method of the Section class.
     * See <a href="https://docs.oracle.com/javase/7/docs/api/javax/swing/JComponent.html" target="_blank">Official JComponent docs</a> for more information.
     *
     * @param graphics See the link above.
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if ((this.getComponentCount() != 3 && this.isHorizontalMode.getAsBoolean())) {
            this.switchHorizontalMode();
        } else if (this.getComponentCount() != 1 && !this.isHorizontalMode.getAsBoolean()) {
            this.switchVerticalMode();
        }

        Graphics2D graphics2D = (Graphics2D) graphics;
        this.leftSection.paintLights(graphics2D);
        this.contentSection.paintLights(graphics2D);
        this.rightSection.paintLights(graphics2D);
    }
}
