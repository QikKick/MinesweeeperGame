import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class  Map {
    Cell[][] map;
    public void printMap(){
        for(Cell[] row : map) {
            for(Cell column : row) {
                System.out.printf("%-3s", column.getValue());
            }
            System.out.println();
        }
    }

    abstract int getMapRowAmount();

    abstract int getMapColumnAmount();

    abstract boolean isGameLost();

    abstract boolean isGameWon();

    abstract void revealCell(int row, int col);

    abstract Cell[][] getMap();
}
