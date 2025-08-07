import java.util.Scanner;

public class TicTacToeBoard {
    public static void main(String[] args) {
        char[][] board = initializeBoard();
        char currentPlayer = 'X';
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;

        while (!gameOver) {
            printBoard(board);
            System.out.println("玩家 " + currentPlayer + " 請輸入列與行 (0-2)：");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (placeMark(board, row, col, currentPlayer)) {
                if (isWin(board, currentPlayer)) {
                    printBoard(board);
                    System.out.println("玩家 " + currentPlayer + " 獲勝！");
                    gameOver = true;
                } else if (isBoardFull(board)) {
                    printBoard(board);
                    System.out.println("平手！");
                    gameOver = true;
                } else {
                    currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
                }
            } else {
                System.out.println("無效位置，請重新輸入。");
            }
        }
        scanner.close();
    }

    public static char[][] initializeBoard() {
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        return board;
    }

    public static boolean placeMark(char[][] board, int row, int col, char player) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
            board[row][col] = player;
            return true;
        }
        return false;
    }

    public static boolean isWin(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true;
        return false;
    }

    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }

    public static void printBoard(char[][] board) {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j] + " |");
            }
            System.out.println("\n-------------");
        }
    }
}
