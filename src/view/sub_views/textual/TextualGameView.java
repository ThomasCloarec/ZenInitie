package view.sub_views.textual;

import controller.game.GameController;
import view.sub_views.GameView;

import java.util.Observable;

public class TextualGameView extends TextualSubView implements GameView {
    private final GameController gameController;

    public TextualGameView(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void start() {
        System.out.println("_/\\____/\\\n" +
                "|= ͡° ᆺ ͡°)=\n" +
                "\\╭☞ \\╭☞ GOOD LUCK !\n"

        );
    }

    @Override
    public void update(Observable observable, Object object) {

    }
}
