package view.sub_views.menu_view.view_sections;

import controller.menu.MenuController;
import view.Graphical2DView;
import view.sub_views.Section;
import view.utils.AppColor;
import view.utils.components.ButtonComponent;
import view.utils.components.LightComponent;
import view.utils.components.ScaledImageComponent;
import view.utils.text.Language;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import static view.utils.text.AppText.getTextFor;

public class MenuContentSection extends Section<MenuController> {
    private final JPanel logoZen;

    public MenuContentSection(MenuController menuController, BooleanSupplier horizontalMode) {
        super(menuController, horizontalMode);

        this.logoZen = new ScaledImageComponent("logo_zen.png", 0.9, 0.3, this);

        Supplier<Point> center = () -> new Point(
                this.getX() + this.logoZen.getX() + this.logoZen.getWidth() / 2,
                this.getY() + this.logoZen.getY() + this.logoZen.getHeight() / 2);
        Supplier<Float> radius = () -> this.getWidth() * 1.5f;
        LightComponent logoLight = new LightComponent(center, radius, AppColor.CUSTOM_LIGHT_GREY);
        logoLight.setVisibleCondition(horizontalMode);
        this.lights.add(logoLight);

        this.goHomepage();
    }

    public void changeLanguage() {
        ArrayList<JButton> buttons = new ArrayList<>();

        JButton button1 = new ButtonComponent(getTextFor("menu.settings.language.question1.answer1"), this, this.horizontalMode);
        JButton button2 = new ButtonComponent(getTextFor("menu.settings.language.question1.answer2"), this, this.horizontalMode);
        JButton button3 = new ButtonComponent(getTextFor("menu.settings.language.question1.answer3"), this, this.horizontalMode);

        button1.addActionListener(actionEvent -> this.controller.setLanguage(Language.ENGLISH));
        button2.addActionListener(actionEvent -> this.controller.setLanguage(Language.FRENCH));

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);

        this.updateButtons(buttons);
    }

    public void changeSettings() {
        ArrayList<JButton> buttons = new ArrayList<>();

        JButton button1 = new ButtonComponent(getTextFor("menu.settings.question1.answer1"), this, this.horizontalMode);
        JButton button2 = new ButtonComponent(getTextFor("menu.settings.question1.answer2"), this, this.horizontalMode);
        JButton button3 = new ButtonComponent(getTextFor("global.fullscreen"), this, this.horizontalMode);
        JButton button4 = new ButtonComponent(getTextFor("menu.settings.question1.answer3"), this, this.horizontalMode);
        JButton button5 = new ButtonComponent(getTextFor("menu.settings.question1.answer4"), this, this.horizontalMode);

        button1.addActionListener(actionEvent -> this.controller.changeLanguage());
        button3.addActionListener(actionEvent -> {
            // Hide button and it's glue
            button3.setVisible(false);
            this.getComponent(Arrays.asList(this.getComponents()).indexOf(button3) + 1).setVisible(false);
            ((Graphical2DView) SwingUtilities.getWindowAncestor(this)).toggleFullScreen();
        });

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);

        this.updateButtons(buttons);
    }

    public void goHomepage() {
        ArrayList<JButton> buttons = new ArrayList<>();

        JButton button1 = new ButtonComponent(getTextFor("menu.question1.answer1"), this, this.horizontalMode);
        JButton button2 = new ButtonComponent(getTextFor("menu.question1.answer2"), this, this.horizontalMode);
        JButton button3 = new ButtonComponent(getTextFor("menu.question1.answer3"), this, this.horizontalMode);
        JButton button4 = new ButtonComponent(getTextFor("menu.question1.answer4"), this, this.horizontalMode);

        button1.addActionListener(actionEvent -> this.controller.playOnline());
        button2.addActionListener(actionEvent -> this.controller.playOffline());
        button3.addActionListener(actionEvent -> this.controller.changeSettings());

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);

        this.updateButtons(buttons);
    }

    public void newGame() {
        ArrayList<JButton> buttons = new ArrayList<>();

        JButton button1 = new ButtonComponent(getTextFor("menu.offline.newGame.question1.answer1"), this, this.horizontalMode);
        JButton button2 = new ButtonComponent(getTextFor("menu.offline.newGame.question1.answer2"), this, this.horizontalMode);
        JButton button3 = new ButtonComponent(getTextFor("menu.offline.newGame.question1.answer3"), this, this.horizontalMode);
        JButton button4 = new ButtonComponent(getTextFor("menu.offline.newGame.question1.answer4"), this, this.horizontalMode);
        JButton button5 = new ButtonComponent(getTextFor("menu.offline.newGame.question1.answer5"), this, this.horizontalMode);

        button1.addActionListener(actionEvent -> this.controller.playOneVsOne());
        button2.addActionListener(actionEvent -> this.controller.playOneVsAI());
        button3.addActionListener(actionEvent -> this.controller.playTwoVsTwo());
        button4.addActionListener(actionEvent -> this.controller.playTwoVSAI());

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);

        this.updateButtons(buttons);
    }

    public void playOffline() {
        ArrayList<JButton> buttons = new ArrayList<>();

        JButton button1 = new ButtonComponent(getTextFor("menu.offline.question1.answer1"), this, this.horizontalMode);
        JButton button2 = new ButtonComponent(getTextFor("menu.offline.question1.answer2"), this, this.horizontalMode);
        JButton button3 = new ButtonComponent(getTextFor("menu.offline.question1.answer3"), this, this.horizontalMode);

        button1.addActionListener(actionEvent -> this.controller.newGame());
        button2.addActionListener(actionEvent -> this.controller.loadGame());

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);

        this.updateButtons(buttons);
    }

    public void updateButtons(ArrayList<JButton> buttonList) {
        this.removeAll();

        this.add(Box.createVerticalGlue());
        this.add(this.logoZen);
        this.add(Box.createVerticalGlue());
        this.add(Box.createVerticalGlue());
        for (JButton button : buttonList) {
            this.add(button);
            this.add(Box.createVerticalGlue());
        }

        buttonList.get(buttonList.size() - 1).addActionListener(actionEvent -> this.controller.backPreviousPage());
    }
}
