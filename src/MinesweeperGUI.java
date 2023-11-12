import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinesweeperGUI extends JFrame {
    private final GameMap gameMap;
    private final JButton[][] buttons;

    public MinesweeperGUI(GameMap gameMap) {
        this.gameMap = gameMap;
        this.buttons = new JButton[gameMap.getMapRowAmount()][gameMap.getMapColumnAmount()];

        initializeUI();
    }

    private void initializeUI() {
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(gameMap.getMapRowAmount(), gameMap.getMapColumnAmount()));

        for (int i = 0; i < gameMap.getMapRowAmount(); i++) {
            for (int j = 0; j < gameMap.getMapColumnAmount(); j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setPreferredSize(new Dimension(40, 40));
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 18)); // Set font size

                add(buttons[i][j]);

                final int row = i;
                final int col = j;

                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleButtonClick(row, col);
                    }
                });
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
                // Close the application when "You Lost!" is clicked
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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                int difficulty = getDifficultyFromUser();
                GameMap gameMapSolved = new GameMap(difficulty);
                GameMap gameMap = new GameMap(gameMapSolved.getMapRowAmount(), gameMapSolved.getMapColumnAmount(), gameMapSolved);
                new MinesweeperGUI(gameMap);
            }
        });
    }

    private static int getDifficultyFromUser() {
        return Main.getDifficultyFromUser();
    }
}
