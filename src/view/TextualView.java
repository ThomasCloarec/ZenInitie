package view;

import controller.game.GameController;
import controller.menu.MenuController;
import view.sub_views.game_view.GameView;
import view.sub_views.game_view.TextualGameView;
import view.sub_views.menu_view.MenuView;
import view.sub_views.menu_view.TextualMenuView;

import static view.utils.text.AppText.getTextFor;
import static view.utils.text.AppText.preInformation;
import static view.utils.text.TextOutput.println;

public class TextualView implements View {
    public TextualView() {
        println("███████╗███████╗███╗   ██╗    ██╗     ▄█╗██╗███╗   ██╗██╗████████╗██╗███████╗\n" +
                "╚══███╔╝██╔════╝████╗  ██║    ██║     ╚═╝██║████╗  ██║██║╚══██╔══╝██║██╔════╝\n" +
                "  ███╔╝ █████╗  ██╔██╗ ██║    ██║        ██║██╔██╗ ██║██║   ██║   ██║█████╗  \n" +
                " ███╔╝  ██╔══╝  ██║╚██╗██║    ██║        ██║██║╚██╗██║██║   ██║   ██║██╔══╝  \n" +
                "███████╗███████╗██║ ╚████║    ███████╗   ██║██║ ╚████║██║   ██║   ██║███████╗\n" +
                "╚══════╝╚══════╝╚═╝  ╚═══╝    ╚══════╝   ╚═╝╚═╝  ╚═══╝╚═╝   ╚═╝   ╚═╝╚══════╝\n");

        println("████████████████████████████████████████\n" +
                "██      by Thomas Cloarec - 2020      ██\n" +
                "██  First Year Undergraduate Project  ██\n" +
                "████████████████████████████████████████\n");
        println(preInformation + getTextFor("global.welcome"));
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
