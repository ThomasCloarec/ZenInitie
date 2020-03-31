package view;

import view.sub_view.GameView;
import view.sub_view.MenuView;
import view.utils.GameText;

public class TextualView implements View {
    public TextualView() {
        System.out.println("███████╗███████╗███╗   ██╗    ██╗     ▄█╗██╗███╗   ██╗██╗████████╗██╗███████╗\n" +
                "╚══███╔╝██╔════╝████╗  ██║    ██║     ╚═╝██║████╗  ██║██║╚══██╔══╝██║██╔════╝\t████████████████████████████████████████\n" +
                "  ███╔╝ █████╗  ██╔██╗ ██║    ██║        ██║██╔██╗ ██║██║   ██║   ██║█████╗  \t██      by Thomas Cloarec - 2020      ██\n" +
                " ███╔╝  ██╔══╝  ██║╚██╗██║    ██║        ██║██║╚██╗██║██║   ██║   ██║██╔══╝  \t██  First Year Undergraduate Project  ██\n" +
                "███████╗███████╗██║ ╚████║    ███████╗   ██║██║ ╚████║██║   ██║   ██║███████╗\t████████████████████████████████████████\n" +
                "╚══════╝╚══════╝╚═╝  ╚═══╝    ╚══════╝   ╚═╝╚═╝  ╚═══╝╚═╝   ╚═╝   ╚═╝╚══════╝");

        System.out.println(GameText.preInformation + GameText.get("welcome"));
    }

    @Override
    public void setGameView(GameView gameView) {
        gameView.start();
    }

    @Override
    public void setMenuView(MenuView menuView) {
        menuView.start();
    }
}
