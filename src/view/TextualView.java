package view;

import controller.game.GameController;
import controller.menu.MenuController;
import view.subviews.gameview.GameView;
import view.subviews.gameview.TextualGameView;
import view.subviews.menuview.MenuView;
import view.subviews.menuview.TextualMenuView;
import view.utils.text.AppText;

public class TextualView implements View<MenuController, GameController> {
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
        return new TextualMenuView(menuController);
    }
}
