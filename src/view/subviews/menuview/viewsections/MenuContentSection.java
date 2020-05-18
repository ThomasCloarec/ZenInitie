package view.subviews.menuview.viewsections;

import controller.menu.Graphic2DMenuController;
import view.Graphical2DView;
import view.subviews.Section;
import view.utils.ExtendedColor;
import view.utils.components.ButtonComponent;
import view.utils.components.ImageComponent;
import view.utils.components.LightComponent;
import view.utils.components.ScaledImageComponent;
import view.utils.components.ScrollPaneComponent;
import view.utils.components.TextComponent;
import view.utils.text.AppText;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import java.awt.Point;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**
 * The type Menu content section.
 */
public class MenuContentSection extends Section<Graphic2DMenuController> {
    private final ImageComponent logoZen;

    /**
     * Instantiates a new Menu content section.
     *
     * @param graphics2DMenuController the graphics 2 d menu controller
     * @param horizontalMode           the horizontal mode
     */
    public MenuContentSection(Graphic2DMenuController graphics2DMenuController, BooleanSupplier horizontalMode) {
        super(graphics2DMenuController, horizontalMode);

        this.logoZen = new ScaledImageComponent("logo_zen.png", 0.9, 0.3, this);

        Supplier<Point> center = () -> new Point(
                this.getX() + this.logoZen.getX() + this.logoZen.getWidth() / 2,
                this.getY() + this.logoZen.getY() + this.logoZen.getHeight() / 2);
        Supplier<Float> radius = () -> this.getWidth() * 1.5f;
        LightComponent logoLight = new LightComponent(center, radius, ExtendedColor.CUSTOM_LIGHT_GREY);
        this.lights.add(logoLight);

        this.goHomepage();
    }

    /**
     * Go credits.
     */
    public void goCredits() {
        this.removeAll();

        JButton back = new ButtonComponent(AppText.getTextFor("menu.credits.question1.answer1"), this, this.horizontalMode);
        back.addActionListener(this.controller.getBackPreviousPageListener());

        TextComponent textArea = new TextComponent();
        textArea.setEditable(false);
        textArea.setText("<html><center>" +
                "<h1 style='font-size:30px'>Credits</h1>" +
                "<h3 style='font-size:15px'>" + AppText.getTextFor("menu.credits.hugo") + "</h3>" +
                "<h3 style='font-size:15px'>" + AppText.getTextFor("menu.credits.valentin") + "</h3></center></html>");

        ScrollPaneComponent scrollPane = new ScrollPaneComponent(textArea, this::getWidth, () -> (int) ((this.getHeight() - this.logoZen.getHeight() - back.getHeight()) * 0.6));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        this.add(Box.createVerticalGlue());
        this.add(this.logoZen);
        this.add(Box.createVerticalGlue());
        this.add(scrollPane);
        this.add(Box.createVerticalGlue());
        this.add(Box.createVerticalGlue());
        this.add(back);
        this.add(Box.createVerticalGlue());
        this.add(Box.createVerticalGlue());

        this.revalidate();
        this.repaint();
    }

    /**
     * Change language.
     */
    public void changeLanguage() {
        JButton button1 = new ButtonComponent(AppText.getTextFor("menu.settings.language.question1.answer1"), this, this.horizontalMode);
        JButton button2 = new ButtonComponent(AppText.getTextFor("menu.settings.language.question1.answer2"), this, this.horizontalMode);
        JButton button3 = new ButtonComponent(AppText.getTextFor("menu.settings.language.question1.answer3"), this, this.horizontalMode);

        button1.addActionListener(this.controller.getEnglishListener());
        button2.addActionListener(this.controller.getFrenchListener());

        this.updateButtons(button1, button2, button3);
    }

    /**
     * Change settings.
     */
    public void changeSettings() {
        Graphical2DView frame = ((Graphical2DView) SwingUtilities.getWindowAncestor(this));

        JButton button1 = new ButtonComponent(AppText.getTextFor("menu.settings.question1.answer1"), this, this.horizontalMode);
        JButton button2 = new ButtonComponent(frame.isFullscreenModeActivated() ?
                AppText.getTextFor("global.fullscreen.stop") :
                AppText.getTextFor("global.fullscreen.start"),
                this, this.horizontalMode);
        JButton button3 = new ButtonComponent(AppText.getTextFor("menu.settings.question1.answer2"), this, this.horizontalMode);
        JButton button4 = new ButtonComponent(AppText.getTextFor("menu.settings.question1.answer3"), this, this.horizontalMode);

        button1.addActionListener(this.controller.getChangeLanguageListener());
        button2.addActionListener(Graphic2DMenuController.getToggleFullScreenListener(frame));
        button2.addComponentListener(Graphic2DMenuController.getButtonFullScreenResized(frame, button2));
        button3.addActionListener(this.controller.getCreditsListener());

        this.updateButtons(button1, button2, button3, button4);
    }

    /**
     * Go homepage.
     */
    public void goHomepage() {
        JButton button1 = new ButtonComponent(AppText.getTextFor("menu.question1.answer1"), this, this.horizontalMode);
        JButton button2 = new ButtonComponent(AppText.getTextFor("menu.question1.answer2"), this, this.horizontalMode);
        JButton button3 = new ButtonComponent(AppText.getTextFor("menu.question1.answer3"), this, this.horizontalMode);
        JButton button4 = new ButtonComponent(AppText.getTextFor("menu.question1.answer4"), this, this.horizontalMode);

        button1.addActionListener(this.controller.getPlayOnlineListener());
        button2.addActionListener(this.controller.getPlayOfflineListener());
        button3.addActionListener(this.controller.getChangeSettingsListener());

        this.updateButtons(button1, button2, button3, button4);
    }

    /**
     * New game.
     */
    public void newGame() {
        JButton button1 = new ButtonComponent(AppText.getTextFor("menu.offline.newGame.question1.answer1"), this, this.horizontalMode);
        JButton button2 = new ButtonComponent(AppText.getTextFor("menu.offline.newGame.question1.answer2"), this, this.horizontalMode);
        JButton button3 = new ButtonComponent(AppText.getTextFor("menu.offline.newGame.question1.answer3"), this, this.horizontalMode);
        JButton button4 = new ButtonComponent(AppText.getTextFor("menu.offline.newGame.question1.answer4"), this, this.horizontalMode);
        JButton button5 = new ButtonComponent(AppText.getTextFor("menu.offline.newGame.question1.answer5"), this, this.horizontalMode);

        button1.addActionListener(this.controller.getOneVsOneListener());
        button2.addActionListener(this.controller.getOneVsAIListener());
        button3.addActionListener(this.controller.getTwoVsTwoListener());
        button4.addActionListener(this.controller.getTwoVsAiListener());

        this.updateButtons(button1, button2, button3, button4, button5);
    }

    /**
     * Play offline.
     */
    public void playOffline() {
        JButton button1 = new ButtonComponent(AppText.getTextFor("menu.offline.question1.answer1"), this, this.horizontalMode);
        JButton button2 = new ButtonComponent(AppText.getTextFor("menu.offline.question1.answer2"), this, this.horizontalMode);
        JButton button3 = new ButtonComponent(AppText.getTextFor("menu.offline.question1.answer3"), this, this.horizontalMode);

        button1.addActionListener(this.controller.getNewGameListener());
        button2.addActionListener(this.controller.getLoadGameListener());

        this.updateButtons(button1, button2, button3);
    }

    /**
     * Update buttons.
     *
     * @param buttons the buttons
     */
    public void updateButtons(JButton... buttons) {
        this.removeAll();

        this.add(Box.createVerticalGlue());
        this.add(this.logoZen);
        this.add(Box.createVerticalGlue());
        this.add(Box.createVerticalGlue());
        for (JButton button : buttons) {
            this.add(button);
            this.add(Box.createVerticalGlue());
        }

        buttons[buttons.length - 1].addActionListener(this.controller.getBackPreviousPageListener());

        this.revalidate();
        this.repaint();
    }
}
