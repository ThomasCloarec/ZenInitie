package model.game.team;

public class ArtificialPlayer extends Player {
    public ArtificialPlayer(String name) {
        super(name);
    }

    public ArtificialPlayer() {
        // Used for kryo deserialization.
    }
}
