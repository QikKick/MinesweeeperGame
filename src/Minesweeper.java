import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class Minesweeper extends JFrame {
    private final Map gameMap;
    private final JButton[][] buttons;

    public Minesweeper(@NotNull GameMap gameMap) {
        this.gameMap = gameMap;
        this.buttons = new JButton[gameMap.getMapRowAmount()][gameMap.getMapColumnAmount()];
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout( gameMap.getMapRowAmount(), gameMap.getMapColumnAmount()));

        for (int i = 0; i < gameMap.getMapRowAmount(); i++) {
            for (int j = 0; j < gameMap.getMapColumnAmount(); j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setPreferredSize(new Dimension(40, 40));
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 18));

                add(buttons[i][j]);

                final int row = i;
                final int col = j;

                buttons[i][j].addActionListener(e -> handleButtonClick(row, col));
            }
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleButtonClick(int row, int col) {
        if (!gameMap.isGameLost() && !gameMap.isGameWon()) {
            gameMap.revealCell(row, col);
            updateButtons();
            if (gameMap.isGameLost()) {
                JOptionPane.showMessageDialog(this, "You lost!");
                dispose();
            } else if (gameMap.isGameWon()) {
                JOptionPane.showMessageDialog(this, "You won!");
            }
        }
    }

    private void updateButtons() {
        for (int i = 0; i < gameMap.getMapRowAmount(); i++) {
            for (int j = 0; j < gameMap.getMapColumnAmount(); j++) {
                Cell cell = gameMap.getMap()[i][j];
                JButton button = buttons[i][j];

                if (cell.isRevealed()) {
                    button.setEnabled(false);
                    button.setText(cell.getValue());
                } else if (cell.isFlagged()) {
                    button.setText("F");
                } else {
                    button.setText("");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Difficulty difficulty = getDifficultyFromUser();
            SolvedGameMap gameMapSolved = new SolvedGameMap(difficulty);
            GameMap gameMap = new GameMap(gameMapSolved.getMapRowAmount(), gameMapSolved.getMapColumnAmount(), gameMapSolved);
            new Minesweeper(gameMap);
        });
    }

    private static Difficulty getDifficultyFromUser() {
        Difficulty[] options = {Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD};

        return (Difficulty) JOptionPane.showInputDialog(
                null,
                "Pick the difficulty:",
                "Difficulty Selection",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                Difficulty.EASY
        );
    }

}

