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

    @Override
    public String toString() {
        return "(" + (this.getFormattedLine()) + ", " + (this.getFormattedColumn()) + ")";
    }

    public int getColumn() {
        return this.column;
    }

    public char getFormattedColumn() {
        return (char) (65 + this.getColumn());
    }

    public int getFormattedLine() {
        return 11 - this.getLine();
    }

    public int getLine() {
        return this.line;
    }
}
