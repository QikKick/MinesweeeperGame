import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameMap extends Map{
    public static final int MEDIUM = 2;
    public static final int HARD = 3;

    private final Cell[][] map;
    private final int mapRowAmount;
    private final int mapColumnAmount;
    private int bombAmount;

    private SolvedGameMap solvedMap;

    private Boolean isGameLost;


    public GameMap(int mapRow, int mapCol, SolvedGameMap solvedMap) {
        this.mapRowAmount = mapRow;
        this.mapColumnAmount = mapCol;
        this.solvedMap = solvedMap;
        isGameLost = false;
        map = new Cell[mapRowAmount][mapColumnAmount];
        for(int i = 0; i < mapRowAmount; i++) {
            for(int j = 0; j < mapColumnAmount; j++) {
                map[i][j] = new Cell(i,j);
            }
        }
    }


    public void revealCell(int row, int column) {
        if (!map[row][column].isRevealed() && !map[row][column].isFlagged()) {
            if (solvedMap.getMap()[row][column].isBomb()) {
                isGameLost = true;
            } else {
                if (map[row][column].isEmpty()) {
                    revealConnectedEmptyCells(row, column);
                }
            }
        }
    }

    public void revealConnectedEmptyCells(int row, int column) {
        if(row < 0 || row >= mapRowAmount || column < 0 || column >= mapColumnAmount) {
            return;
        }
        if(map[row][column].isRevealed()) {
            return;
        }
        if(map[row][column].isBomb()) {
            return;
        }
        if(map[row][column].isFlagged()) {
            return;
        }
        int bombCountNearby = nearbyBombCount(row, column);
        if(bombCountNearby == 0) {
            map[row][column].setValue(".");
            map[row][column].setRevealed(true);
        } else {
            map[row][column].setValue(String.valueOf(bombCountNearby));
            map[row][column].setRevealed(true);
            return;
        }
        map[row][column].setRevealed(true);
        if (map[row][column].isEmpty()) {
            revealConnectedEmptyCells(row-1, column-1);
            revealConnectedEmptyCells(row-1, column);
            revealConnectedEmptyCells(row-1, column+1);
            revealConnectedEmptyCells(row, column-1);
            revealConnectedEmptyCells(row, column+1);
            revealConnectedEmptyCells(row+1, column-1);
            revealConnectedEmptyCells(row+1, column);
            revealConnectedEmptyCells(row+1, column+1);
        }
    }

    public int nearbyBombCount(int row, int column) {
        int bombCount = 0;

        bombCount+=checkCell(row-1, column-1);
        bombCount+=checkCell(row-1, column);
        bombCount+=checkCell(row-1, column+1);
        bombCount+=checkCell(row, column-1);
        bombCount+=checkCell(row, column+1);
        bombCount+=checkCell(row+1, column-1);
        bombCount+=checkCell(row+1, column);
        bombCount+=checkCell(row+1, column+1);

        return bombCount;
    }

    private int checkCell(int row, int column) {
        if(row < 0 || row >= mapRowAmount || column < 0 || column >= mapColumnAmount) {
            return 0;
        }
        if(solvedMap.getMap()[row][column].isBomb()) {
            return 1;
        }
        return 0;
    }

    public boolean isGameWon() {
        int countOfBlanks = 0 ;
        for (int i = 0; i < mapRowAmount; i++) {
            for (int j = 0; j < mapColumnAmount; j++) {
                if (map[i][j].getValue().equals("[]")) {
                    countOfBlanks++;
                }
            }
        }
        return countOfBlanks == solvedMap.getBombAmount();
    }

    @Override
    public void printMap(boolean isGameLost) {
        super.printMap();
    }

    public boolean isGameLost() {
        return isGameLost;
    }
}
