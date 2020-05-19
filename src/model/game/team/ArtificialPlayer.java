package model.game.team;

/**
 * The type Artificial player.
 */
public class ArtificialPlayer extends Player {
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
     * Is human player boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean isHumanPlayer() {
        return false;
    }
}
