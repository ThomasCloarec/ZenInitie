package model.game.team;

/**
 * The type Player.
 */
public class Player {
    /**
     * The Name.
     */
    private String name;

    /**
     * Instantiates a new Player.
     *
     * @param name the name
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Instantiates a new Player.
     */
    public Player() {
        // Used for kryo deserialization.
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Player{" +
                "name='" + this.name + '\'' +
                '}';
    }
}
