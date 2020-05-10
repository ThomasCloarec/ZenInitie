package view;

import controller.game.GameController;
import controller.menu.MenuController;
import view.subviews.gameview.GameView;
import view.subviews.gameview.TextualGameView;
import view.subviews.menuview.MenuView;
import view.subviews.menuview.TextualMenuView;
import view.utils.Sound;
import view.utils.text.AppText;

import java.util.ArrayList;
import java.util.Collection;

public class TextualView implements View<MenuController, GameController> {
    private final Collection<Sound> sounds = new ArrayList<>();

    public TextualView() {
        System.out.println("███████╗███████╗███╗   ██╗    ██╗     ▄█╗██╗███╗   ██╗██╗████████╗██╗███████╗\n" +
                "╚══███╔╝██╔════╝████╗  ██║    ██║     ╚═╝██║████╗  ██║██║╚══██╔══╝██║██╔════╝\n" +
                "  ███╔╝ █████╗  ██╔██╗ ██║    ██║        ██║██╔██╗ ██║██║   ██║   ██║█████╗  \n" +
                " ███╔╝  ██╔══╝  ██║╚██╗██║    ██║        ██║██║╚██╗██║██║   ██║   ██║██╔══╝  \n" +
                "███████╗███████╗██║ ╚████║    ███████╗   ██║██║ ╚████║██║   ██║   ██║███████╗\n" +
                "╚══════╝╚══════╝╚═╝  ╚═══╝    ╚══════╝   ╚═╝╚═╝  ╚═══╝╚═╝   ╚═╝   ╚═╝╚══════╝\n");

        System.out.println("████████████████████████████████████████\n" +
                "██      by Thomas Cloarec - 2020      ██\n" +
                "██  First Year Undergraduate Project  ██\n" +
                "████████████████████████████████████████\n");
        System.out.println(AppText.preInformation + AppText.getTextFor("global.welcome"));
    }

    @Override
    public GameView createGameView(GameController gameController) {
        return new TextualGameView(gameController);
    }

    @Override
    public MenuView createMenuView(MenuController menuController) {
        Sound sound = new Sound("lotus_du_printemps_tombant.mp3");
        sound.play();
        sound.loop();
        this.sounds.add(sound);
        return new TextualMenuView(menuController);
    }

    @Override
    public void close() {
        this.sounds.forEach(Sound::stop);
    }
}
