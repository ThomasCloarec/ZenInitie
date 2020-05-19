package model.game;

/**
 * The type Position.
 */
public class Position {
    /**
     * The Column.
     */
    private int column;
    /**
     * The Line.
     */
    private int line;

    /**
     * Instantiates a new Position.
     *
     * @param line   the line
     * @param column the column
     */
    public Position(int line, int column) {
        this.line = line;
        this.column = column;
    }

    /**
     * Instantiates a new Position.
     */
    public Position() {
        // Used for kryo deserialization.
    }

    /**
     * Instantiates a new Position.
     *
     * @param line   the line
     * @param column the column
     */
    public Position(int line, char column) {
        this.line = 11 - line;
        this.column = (int) column - 65;
    }

    /**
     * Instantiates a new Position.
     *
     * @param position the position
     */
    public Position(int[] position) {
        this.line = position[0];
        this.column = position[1];
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        boolean ret = false;

        if (o != null && this.getClass() == o.getClass()) {
            Position position = (Position) o;
            ret = this.column == position.column && this.line == position.line;
        }

        return ret;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "(" + (this.getFormattedLine()) + ", " + (this.getFormattedColumn()) + ")";
    }

    /**
     * Gets column.
     *
     * @return the column
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Gets formatted column.
     *
     * @return the formatted column
     */
    public char getFormattedColumn() {
        return (char) (65 + this.column);
    }

    /**
     * Gets formatted line.
     *
     * @return the formatted line
     */
    public int getFormattedLine() {
        return 11 - this.line;
    }

    /**
     * Gets line.
     *
     * @return the line
     */
    public int getLine() {
        return this.line;
    }
}
