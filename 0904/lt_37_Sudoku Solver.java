class Solution {
    public void solveSudoku(char[][] board) {
        backtrack(board, 0, 0);
    }
    
    // 回溯遞迴填格子
    private boolean backtrack(char[][] board, int row, int col) {
        int n = 9;
        // 如果已超過最後一列，代表填完
        if (row == n) return true;
        
        // 計算下一格索引
        int nextRow = (col == n - 1) ? row + 1 : row;
        int nextCol = (col == n - 1) ? 0 : col + 1;
        
        // 非空格，跳到下一格
        if (board[row][col] != '.') {
            return backtrack(board, nextRow, nextCol);
        }
        
        // 嘗試填入 1~9
        for (char c = '1'; c <= '9'; c++) {
            if (isValid(board, row, col, c)) {
                board[row][col] = c;
                if (backtrack(board, nextRow, nextCol)) {
                    return true;
                }
                // 回溯還原空格
                board[row][col] = '.';
            }
        }
        
        return false;
    }
    
    // 檢查 c 是否能放在 board[row][col]
    private boolean isValid(char[][] board, int row, int col, char c) {
        int blockRow = (row / 3) * 3;
        int blockCol = (col / 3) * 3;
        
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c) return false;             // 同行不能重複
            if (board[i][col] == c) return false;             // 同列不能重複
            if (board[blockRow + i / 3][blockCol + i % 3] == c) return false; // 3x3格中不能重複
        }
        return true;
    }
}
