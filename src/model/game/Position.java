package model.game;

public class Position {
    private final int column;
    private final int line;

    public Position(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public Position(int line, char column) {
        this.line = 11 - line;
        this.column = (int) column - 65;
    }

    public Position(int[] position) {
        this.line = position[0];
        this.column = position[1];
    }

    @Override
    public boolean equals(Object o) {
        boolean ret = false;

        if (o != null && this.getClass() == o.getClass()) {
            Position position = (Position) o;
            ret = this.column == position.column && this.line == position.line;
        }

        return ret;
    }

    @Override
    public String toString() {
        return "(" + (this.getFormattedLine()) + ", " + (this.getFormattedColumn()) + ")";
    }

    public int getColumn() {
        return this.column;
    }

    public char getFormattedColumn() {
        return (char) (65 + this.column);
    }

    public int getFormattedLine() {
        return 11 - this.line;
    }

    public int getLine() {
        return this.line;
    }
}
