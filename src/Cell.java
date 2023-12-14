import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
        this.bomb = value.equals("X");
        this.isRevealed = false;
        this.isFlagged = false;
    }

    public boolean isEmpty() {
        return (!bomb || value.equals("[]"));
    }

}
