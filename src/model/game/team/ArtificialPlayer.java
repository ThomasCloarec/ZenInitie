package model.game.team;

import model.game.GameData;
import model.game.Position;

/**
 * The type Artificial player.
 */
public class ArtificialPlayer extends Player {
    /**
     * The Old move.
     */
    private Position oldMove;

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

    /**
     * Gets selected position.
     *
     * @param gameData the game data
     * @return the selected position
     */
    public Position getSelectedPosition(GameData gameData) {
        Position position;

        if (this.oldMove == null) {
            position = new Position(5, 5);
        } else {
            position = this.oldMove;
        }
        return position;
    }

    /**
     * Gets move position.
     *
     * @param gameData the game data
     * @return the move position
     */
    public Position getMovePosition(GameData gameData) {
        this.oldMove = gameData.getAllowedMoves().get(((int) (Math.random() * gameData.getAllowedMoves().size())));
        return this.oldMove;
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

    /**
     * Gets state value.
     *
     * @return the state value
     */
    private double getStateValue() {
        return 0;
    }
}
