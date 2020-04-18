package view.sub_views.menu_view.view_sections;

import controller.menu.MenuController;
import view.utils.components.ButtonComponent;
import view.utils.text.Language;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static view.utils.text.AppText.getTextFor;

public class ContentPanel extends JPanel {
    private final JPanel logoZen;
    private final MenuController menuController;

    public ContentPanel(MenuController menuController, JPanel logoZen) {
        this.logoZen = logoZen;
        this.menuController = menuController;
        this.setOpaque(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void changeLanguage() {
        ArrayList<JButton> buttons = new ArrayList<>();

        JButton button1 = new ButtonComponent(getTextFor("menu.settings.language.question1.answer1"));
        JButton button2 = new ButtonComponent(getTextFor("menu.settings.language.question1.answer2"));
        JButton button3 = new ButtonComponent(getTextFor("menu.settings.language.question1.answer3"));

        button1.addActionListener(actionEvent -> this.menuController.setLanguage(Language.ENGLISH));
        button2.addActionListener(actionEvent -> this.menuController.setLanguage(Language.FRENCH));
        button3.addActionListener(actionEvent -> this.menuController.backPreviousPage());

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);

        this.updateButtons(buttons);
    }

    public void changeSettings() {
        ArrayList<JButton> buttons = new ArrayList<>();

        JButton button1 = new ButtonComponent(getTextFor("menu.settings.question1.answer1"));
        JButton button2 = new ButtonComponent(getTextFor("menu.settings.question1.answer2"));

        button1.addActionListener(actionEvent -> this.menuController.changeLanguage());
        button2.addActionListener(actionEvent -> this.menuController.backPreviousPage());

        buttons.add(button1);
        buttons.add(button2);

        this.updateButtons(buttons);
    }

    public void newGame() {
        ArrayList<JButton> buttons = new ArrayList<>();

        JButton button1 = new ButtonComponent(getTextFor("menu.offline.newGame.question1.answer1"));
        JButton button2 = new ButtonComponent(getTextFor("menu.offline.newGame.question1.answer2"));
        JButton button3 = new ButtonComponent(getTextFor("menu.offline.newGame.question1.answer3"));
        JButton button4 = new ButtonComponent(getTextFor("menu.offline.newGame.question1.answer4"));
        JButton button5 = new ButtonComponent(getTextFor("menu.offline.newGame.question1.answer5"));

        button1.addActionListener(actionEvent -> this.menuController.playOneVsOne());
        button2.addActionListener(actionEvent -> this.menuController.playOneVsAI());
        button3.addActionListener(actionEvent -> this.menuController.playTwoVsTwo());
        button4.addActionListener(actionEvent -> this.menuController.playTwoVSAI());
        button5.addActionListener(actionEvent -> this.menuController.backPreviousPage());

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);

        this.updateButtons(buttons);
    }

    public void playOffline() {
        ArrayList<JButton> buttons = new ArrayList<>();

        JButton button1 = new ButtonComponent(getTextFor("menu.offline.question1.answer1"));
        JButton button2 = new ButtonComponent(getTextFor("menu.offline.question1.answer2"));
        JButton button3 = new ButtonComponent(getTextFor("menu.offline.question1.answer3"));

        button1.addActionListener(actionEvent -> this.menuController.newGame());
        button2.addActionListener(actionEvent -> this.menuController.loadGame());
        button3.addActionListener(actionEvent -> this.menuController.backPreviousPage());

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);

        this.updateButtons(buttons);
    }

    public void start() {
        ArrayList<JButton> buttons = new ArrayList<>();

        JButton button1 = new ButtonComponent(getTextFor("menu.question1.answer1"));
        JButton button2 = new ButtonComponent(getTextFor("menu.question1.answer2"));
        JButton button3 = new ButtonComponent(getTextFor("menu.question1.answer3"));
        JButton button4 = new ButtonComponent(getTextFor("menu.question1.answer4"));

        button1.addActionListener(actionEvent -> this.menuController.playOnline());
        button2.addActionListener(actionEvent -> this.menuController.playOffline());
        button3.addActionListener(actionEvent -> this.menuController.changeSettings());
        button4.addActionListener(actionEvent -> this.menuController.exit());

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);

        this.updateButtons(buttons);
    }

    public void updateButtons(ArrayList<JButton> buttonList) {
        this.removeAll();

        this.add(Box.createVerticalGlue());
        this.add(this.logoZen);
        this.add(Box.createVerticalGlue());

        for (JButton button : buttonList) {
            this.add(button);
            if (buttonList.indexOf(button) != buttonList.size() - 1) {
                this.add(Box.createRigidArea(new Dimension(0, 25)));
            }
        }

        this.add(Box.createVerticalGlue());
    }

    public Point getLogoZenCenter() {
        return new Point(
                this.getX() + this.logoZen.getX() + this.logoZen.getWidth() / 2,
                this.getY() + this.logoZen.getY() + this.logoZen.getHeight() / 2
        );
    }
}
