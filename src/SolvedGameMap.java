import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolvedGameMap extends Map{


    private final Cell[][] map;
    private final int mapRowAmount;
    private final int mapColumnAmount;
    private int bombAmount;

    public SolvedGameMap(Difficulty difficulty) {
        switch (difficulty) {
            case MEDIUM:
                mapRowAmount = 16;
                mapColumnAmount = 16;
                bombAmount = 40;
                break;
            case HARD:
                mapRowAmount = 16;
                mapColumnAmount = 30;
                bombAmount = 99;
                break;
            default:
                mapRowAmount = 10;
                mapColumnAmount = 10;
                bombAmount = 10;
        }
        map = new Cell[mapRowAmount][mapColumnAmount];
        for(int i = 0; i < mapRowAmount; i++) {
            for(int j = 0; j < mapColumnAmount; j++) {
                map[i][j] = new Cell(i,j,".");
            }
        }

        for(int i = 0; i < bombAmount; i++) {
            int row = (int) (Math.random() * mapRowAmount);
            int column = (int) (Math.random() * mapColumnAmount);
            if(map[row][column].getValue().equals(".")) {
                map[row][column].setValue("X");
                map[row][column].setBomb(true);
            } else {
                i--;
            }
        }
    }
    @Override
    public void printMap(boolean isGameLost) {
        if(isGameLost) {
            for(int i = 0; i < mapRowAmount; i++) {
                for(int j = 0; j < mapColumnAmount; j++) {
                    if(map[i][j].isBomb()) {
                        map[i][j].setValue("X");
                    }
                }
            }
        }
    }

    @Override
    boolean isGameLost() {
        throw new UnsupportedOperationException("isGameLost is not supported in SolvedGameMap");
    }

    @Override
    boolean isGameWon() {
        throw new UnsupportedOperationException("isGameWon is not supported in SolvedGameMap");
    }

    @Override
    void revealCell(int row, int col) {
        throw new UnsupportedOperationException("revealCell is not supported in SolvedGameMap");
    }
}
