package view.subviews.gameview.viewsections;

import controller.game.Graphic2DGameController;
import model.game.Game;
import model.game.team.TeamColor;
import view.subviews.Section;
import view.utils.ExtendedColor;
import view.utils.components.ImageComponent;
import view.utils.components.LightComponent;
import view.utils.components.ScaledImageComponent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Point;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**
 * The type Game right section.
 */
public class GameRightSection extends Section<Graphic2DGameController> {
    private ImageComponent jumpingHand;
    private LightComponent playerLight;

    /**
     * Instantiates a new Game right section.
     *
     * @param gameController the game controller
     * @param horizontalMode the horizontal mode
     */
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

    /**
     * When the game starts
     *
     * @param game the game
     */
    public void start(Game game) {
        String playerImagePath;
        if (game.isAiMode()) {
            playerImagePath = "people/robot.png";
        } else if (game.isDuoMode()) {
            playerImagePath = "people/woman2.png";
        } else {
            playerImagePath = "people/woman.png";
        }

        this.add(Box.createHorizontalGlue());
        this.add(Box.createHorizontalGlue());
        this.add(Box.createHorizontalGlue());

        JPanel playerPanel = new JPanel();
        playerPanel.setOpaque(false);
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

        this.jumpingHand = new ImageComponent("red_jumper.gif", true);
        playerPanel.add(this.jumpingHand);
        playerPanel.add(Box.createVerticalStrut(10));
        ImageComponent redPlayer = new ScaledImageComponent(playerImagePath, 0.55, 0.55, this);
        redPlayer.setVisibleCondition(this.horizontalMode);
        playerPanel.add(redPlayer);
        this.add(playerPanel);

        this.add(Box.createHorizontalGlue());
        ImageComponent redDragon = new ScaledImageComponent("right_baner.png", 0.4, 0.9, this, false);
        redDragon.setVisibleCondition(this.horizontalMode);
        this.add(redDragon);

        Supplier<Point> playerCenter = () -> new Point(playerPanel.getX() + redPlayer.getX() + this.getWidth() * 2 + redPlayer.getWidth() / 2, playerPanel.getY() + redPlayer.getY() + redPlayer.getHeight() / 2);
        Supplier<Float> playerRadius = () -> redPlayer.getHeight() * 1.5f;
        this.playerLight = new LightComponent(playerCenter, playerRadius, Color.GRAY);
        this.lights.add(this.playerLight);

        this.updatePlayerTurn(game);
    }

    /**
     * Select pawn.
     *
     * @param game the game
     */
    public void selectPawn(Game game) {
        this.updatePlayerTurn(game);
    }

    private void updatePlayerTurn(Game game) {
        TeamColor teamColor = game.getCurrentTeam().getTeamColor();
        boolean visible = teamColor == TeamColor.RED;
        this.playerLight.setVisibleCondition(() -> visible);
        this.jumpingHand.setVisible(visible);
    }
}
