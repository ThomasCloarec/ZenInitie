package model.game.team.ai;

import model.game.Position;

/**
 * The type Action.
 */
public class Action {
    /**
     * The Move position.
     */
    private final Position movePosition;
    /**
     * The Selected position.
     */
    private final Position selectedPosition;

    /**
     * Instantiates a new Action.
     *
     * @param selectedPosition the selected position
     * @param movePosition     the move position
     */
    Action(Position selectedPosition, Position movePosition) {
        this.selectedPosition = selectedPosition;
        this.movePosition = movePosition;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Action{" +
                "movePosition=" + this.movePosition +
                ", selectedPosition=" + this.selectedPosition +
                '}';
    }

    /**
     * Gets move position.
     *
     * @return the move position
     */
    public Position getMovePosition() {
        return this.movePosition;
    }

    /**
     * Gets selected position.
     *
     * @return the selected position
     */
    public Position getSelectedPosition() {
        return this.selectedPosition;
    }
}
