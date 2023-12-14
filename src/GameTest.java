import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class GameTest {
    @Test
    public void testRevealCellEmptyCell() {
        SolvedGameMap solvedGameMap = new SolvedGameMap(Difficulty.EASY);
        GameMap gameMap = new GameMap(solvedGameMap.getMapRowAmount(), solvedGameMap.getMapColumnAmount(), solvedGameMap);
        gameMap.revealCell(0, 0);
        assertTrue(gameMap.getMap()[0][0].isRevealed());
    }

    @Test
    public void testRevealConnectedEmptyCells() {
        Cell[][] customMap = {
                {new Cell(0, 0, "[]"), new Cell(0, 1, "[]"), new Cell(0, 2, "[]")},
                {new Cell(1, 0, "[]"), new Cell(1, 1, "[]"), new Cell(1, 2, "[]")},
                {new Cell(2, 0, "[]"), new Cell(2, 1, "[]"), new Cell(2, 2, "[]")}
        };

        SolvedGameMap solvedGameMap = new SolvedGameMap(Difficulty.EASY);
        GameMap gameMap = new GameMap(solvedGameMap.getMapRowAmount(), solvedGameMap.getMapColumnAmount(), solvedGameMap);
        gameMap.setMap(customMap);
        gameMap.revealConnectedEmptyCells(1, 1);
        assertTrue(gameMap.getMap()[0][0].isRevealed());
        assertTrue(gameMap.getMap()[0][1].isRevealed());
        assertTrue(gameMap.getMap()[0][2].isRevealed());
        assertTrue(gameMap.getMap()[1][0].isRevealed());
        assertTrue(gameMap.getMap()[1][1].isRevealed());
        assertTrue(gameMap.getMap()[1][2].isRevealed());
        assertTrue(gameMap.getMap()[2][0].isRevealed());
        assertTrue(gameMap.getMap()[2][1].isRevealed());
        assertTrue(gameMap.getMap()[2][2].isRevealed());
    }

        @Test
        public void testGameWinCondition() {
            SolvedGameMap solvedGameMap = new SolvedGameMap(Difficulty.EASY);
            GameMap gameMap = new GameMap(solvedGameMap.getMapRowAmount(), solvedGameMap.getMapColumnAmount(), solvedGameMap);

            for (int i = 0; i < gameMap.getMapRowAmount(); i++) {
                for (int j = 0; j < gameMap.getMapColumnAmount(); j++) {
                    if (!gameMap.getMap()[i][j].isBomb()) {
                        gameMap.revealCell(i, j);
                    }
                }
            }

            assertTrue(gameMap.isGameWon());
        }
    }

