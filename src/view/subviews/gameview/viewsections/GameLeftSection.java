package view.subviews.gameview.viewsections;

import controller.game.Graphic2DGameController;
import model.game.Game;
import model.game.team.Team;
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
 * The type Game left section.
 */
public class GameLeftSection extends Section<Graphic2DGameController> {
    /**
     * The Jumping hand.
     */
    private ImageComponent imageAbovePlayer;
    /**
     * The Player light.
     */
    private LightComponent playerLight;

    /**
     * Instantiates a new Game left section.
     *
     * @param gameController the game controller
     * @param horizontalMode the horizontal mode
     */
    public GameLeftSection(Graphic2DGameController gameController, BooleanSupplier horizontalMode) {
        super(gameController, horizontalMode);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        Supplier<Point> center = () -> new Point((int) (this.getWidth() * -0.5), this.getHeight() / 2);
        Supplier<Float> radius = () -> this.getWidth() * 1.5f;
        LightComponent blueLight = new LightComponent(center, radius, ExtendedColor.CUSTOM_BLUE);
        blueLight.setVisibleCondition(this.horizontalMode);
        this.lights.add(blueLight);
    }

    /**
     * When a new game starts
     *
     * @param game the game
     */
    public void start(Game game) {
        String playerImagePath;
        if (game.isDuoMode()) {
            playerImagePath = "people/man2.png";
        } else {
            playerImagePath = "people/man.png";
        }

        ImageComponent blueDragon = new ScaledImageComponent("left_baner.png", 0.4, 0.9, this, false);
        blueDragon.setVisibleCondition(this.horizontalMode);
        this.add(blueDragon);
        this.add(Box.createHorizontalGlue());

        JPanel playerPanel = new JPanel();
        playerPanel.setOpaque(false);
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

        this.imageAbovePlayer = new ImageComponent("blue_jumper.gif", true);
        playerPanel.add(this.imageAbovePlayer);
        playerPanel.add(Box.createVerticalStrut(10));

        ImageComponent bluePlayer = new ScaledImageComponent(playerImagePath, 0.55, 0.55, this);
        bluePlayer.setVisibleCondition(this.horizontalMode);
        this.add(bluePlayer);
        playerPanel.add(bluePlayer);
        this.add(playerPanel);
        this.add(Box.createHorizontalGlue());
        this.add(Box.createHorizontalGlue());
        this.add(Box.createHorizontalGlue());

        Supplier<Point> playerCenter = () -> new Point(playerPanel.getX() + bluePlayer.getX() + bluePlayer.getWidth() / 2, playerPanel.getY() + bluePlayer.getY() + bluePlayer.getHeight() / 2);
        Supplier<Float> playerRadius = () -> bluePlayer.getHeight() * 1.5f;
        this.playerLight = new LightComponent(playerCenter, playerRadius, Color.GRAY);
        this.playerLight.setVisibleCondition(this.horizontalMode);
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

    /**
     * Game winner.
     *
     * @param team the team
     */
    public void gameWinner(Team team) {
        boolean blueWinner = team == null || team.getTeamColor() == TeamColor.BLUE;
        this.playerLight.setVisible(blueWinner);
        if (blueWinner) {
            this.imageAbovePlayer.setBaseImage("crown.png");
        }
        this.imageAbovePlayer.setVisible(blueWinner);
    }

    /**
     * Update player turn.
     *
     * @param game the game
     */
    private void updatePlayerTurn(Game game) {
        if (game.isRunning()) {
            TeamColor teamColor = game.getCurrentTeam().getTeamColor();
            boolean visible = teamColor == TeamColor.BLUE;
            this.playerLight.setVisible(visible);
            this.imageAbovePlayer.setVisible(visible);
        }
    }
}
