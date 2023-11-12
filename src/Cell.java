public class Cell {
    public int row;
    public int column;
    public String value;
    public boolean bomb;
    public boolean isRevealed;
    public boolean isFlagged;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.value = "[]";
        this.bomb = false;
        this.isRevealed = false;
        this.isFlagged = false;
    }

    public Cell(int row, int column, String value) {
        this.row = row;
        this.column = column;
        this.value = value;
        this.bomb = false;
        this.isRevealed = false;
        this.isFlagged = false;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public boolean isFlagged() {
        return isFlagged;
    }
    public boolean isEmpty() {
        return (!bomb || value.equals("[]"));
    }

}
