package view.subviews.gameview.viewsections;

import controller.game.Graphic2DGameController;
import model.game.Game;
import view.subviews.Section;
import view.utils.ExtendedColor;
import view.utils.components.ImageComponent;
import view.utils.components.LightComponent;
import view.utils.components.ScaledImageComponent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Point;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class GameRightSection extends Section<Graphic2DGameController> {
    public GameRightSection(Graphic2DGameController gameController, BooleanSupplier horizontalMode) {
        super(gameController, horizontalMode);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        Supplier<Point> center = () -> new Point((int) (this.getWidth() * 3.5), this.getHeight() / 2);
        Supplier<Float> radius = () -> this.getWidth() * 2.0f;
        LightComponent blueLight = new LightComponent(center, radius, ExtendedColor.CUSTOM_RED);
        blueLight.setVisibleCondition(this.horizontalMode);
        this.lights.add(blueLight);
        blueLight.setVisibleCondition(this.horizontalMode);
    }

    public void start(Game game) {
        String playerImagePath;
        if (game.isAiMode()) {
            playerImagePath = "robot.png";
        } else if (game.isDuoMode()) {
            playerImagePath = "woman2.png";
        } else {
            playerImagePath = "woman.png";
        }

        this.add(Box.createHorizontalGlue());
        this.add(Box.createHorizontalGlue());
        this.add(Box.createHorizontalGlue());
        ImageComponent redPlayer = new ScaledImageComponent(playerImagePath, 0.55, 0.55, this);
        redPlayer.setVisibleCondition(this.horizontalMode);
        this.add(redPlayer);

        this.add(Box.createHorizontalGlue());
        ImageComponent redDragon = new ScaledImageComponent("right_baner.png", 0.4, 0.9, this, false);
        redDragon.setVisibleCondition(this.horizontalMode);
        this.add(redDragon);

        Supplier<Point> playerCenter = () -> new Point(redPlayer.getX() + this.getWidth() * 2 + redPlayer.getWidth() / 2, redPlayer.getY() + redPlayer.getHeight() / 2);
        Supplier<Float> playerRadius = () -> redPlayer.getHeight() * 1.5f;
        LightComponent playerLight = new LightComponent(playerCenter, playerRadius, Color.GRAY);
        this.lights.add(playerLight);
    }
}
