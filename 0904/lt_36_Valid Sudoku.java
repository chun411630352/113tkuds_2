class Solution {
    public boolean isValidSudoku(char[][] board) {
        // 三個二維布林陣列，分別記錄每行、每欄、每個九宮格的數字出現狀態
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] box = new boolean[9][9];
        
        for (int i = 0; i < 9; i++) {        // 遍歷每一行
            for (int j = 0; j < 9; j++) {    // 遍歷每一列
                char c = board[i][j];
                if (c == '.') continue;      // 空白格跳過
                
                int num = c - '1';           // 將字符數字轉成索引 0~8
                
                int boxIndex = (i / 3) * 3 + (j / 3); // 計算子九宮格索引
                
                // 若該數字在該行、該列或該九宮格已出現，違反規則
                if (row[i][num] || col[j][num] || box[boxIndex][num]) {
                    return false;
                }
                
                // 標記出現過該數字
                row[i][num] = true;
                col[j][num] = true;
                box[boxIndex][num] = true;
            }
        }
        
        return true; // 全部合法
    }
}

