package model.game.team;

import model.game.GameData;
import model.game.Position;

/**
 * The type Artificial player.
 */
public class ArtificialPlayer extends Player {
    private static Position oldMove;

    /**
     * Instantiates a new Artificial player.
     *
     * @param name the name
     */
    public ArtificialPlayer(String name) {
        super(name);
    }

    /**
     * Instantiates a new Artificial player.
     */
    public ArtificialPlayer() {
        // Used for kryo deserialization.
    }

    public Position getSelectedPosition(GameData gameData) {
        Position position;

        if (ArtificialPlayer.oldMove == null) {
            position = new Position(5, 5);
        } else {
            position = ArtificialPlayer.oldMove;
        }
        return position;
    }

    public Position getMovePosition(GameData gameData) {
        ArtificialPlayer.oldMove = gameData.getAllowedMoves().get(((int) (Math.random() * gameData.getAllowedMoves().size())));
        return ArtificialPlayer.oldMove;
    }

    /**
     * Is human player boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean isHumanPlayer() {
        return false;
    }
}
