//import java.util.InputMismatchException;
//import java.util.Scanner;
//
//public class Main {
//
//    public static final int EASY = 1;
//    public static final int HARD = 3;
//
//
//    static Scanner sc = new Scanner(System.in);
//
//    public static void main(String[] args) {
//        GameMap gameMapSolved = new GameMap(getDifficultyFromUser());
//        System.out.println("The game board is " + gameMapSolved.getMapRowAmount() + " by " + gameMapSolved.getMapColumnAmount() + " and has " + gameMapSolved.getBombAmount() + " mines");
//        GameMap gameMap = new GameMap(gameMapSolved.getMapRowAmount(), gameMapSolved.getMapColumnAmount(), gameMapSolved);
//        gameMap.printMap();
//
//
//        while(!gameMap.isGameLost()) {
//            System.out.println("Enter the coordinates of the cell you want to open: ");
//            int row = sc.nextInt();
//            if(row < 0 || row > gameMap.getMapRowAmount()) {
//                System.out.println("Invalid row number. Please enter a number between 1 and " + gameMap.getMapRowAmount());
//                continue;
//            }
//            int column = sc.nextInt();
//            if(column < 0 || column > gameMap.getMapColumnAmount()) {
//                System.out.println("Invalid column number. Please enter a number between 1 and " + gameMap.getMapColumnAmount());
//                continue;
//            }
//            gameMap.revealCell(row-1, column-1);
//            if(gameMap.isGameLost()) {
//                System.out.println("You lost!");
//                break;
//            }
//
//            if(gameMap.isGameWon()) {
//                System.out.println("You won!");
//                break;
//            }
//            gameMap.printMap();
//        }
//    }
//
//    public static int getDifficultyFromUser() {
//        int difficulty;
//        while (true) {
//            try {
//                System.out.println("Pick the difficulty: (1-3)");
//                difficulty = sc.nextInt();
//                if (difficulty >= EASY && difficulty <= HARD) {
//                    break;
//                } else {
//                    System.out.println("Please select a difficulty from 1 to 3");
//                }
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid input. Please enter a number.");
//                sc.next();
//            }
//        }
//        return difficulty;
//    }
//}
