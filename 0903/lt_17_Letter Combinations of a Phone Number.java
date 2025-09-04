import java.util.ArrayList;
import java.util.List;

class Solution {
    // 映射數字到相應字母的陣列，索引與數字對應(0和1無字母)
    private static final String[] KEYS = {
        "",     // 0 無對應字母
        "",     // 1 無對應字母
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    private List<String> combinations; // 用來儲存最終結果的字串串列
    private String digits;              // 輸入的數字字串

    public List<String> letterCombinations(String digits) {
        combinations = new ArrayList<>();
        this.digits = digits;
        
        // 輸入為空，直接回傳空列表
        if (digits == null || digits.length() == 0) {
            return combinations;
        }

        // 從字串起點開始回溯生成所有組合
        backtrack(0, new StringBuilder());
        return combinations;
    }

    // 回溯函式，用index追蹤當前處理的digit位置，path累積目前字母組合
    private void backtrack(int index, StringBuilder path) {
        // 若回溯深度等於digits長度，表示一組完整字母組合完成，加入結果
        if (index == digits.length()) {
            combinations.add(path.toString());
            return;
        }
        
        // 取得當前digit對應的所有字母
        String letters = KEYS[digits.charAt(index) - '0'];
        
        // 嘗試每一個字母加入組合中，並繼續回溯下一個digit
        for (char ch : letters.toCharArray()) {
            path.append(ch);           // 加入當前字母
            backtrack(index + 1, path); // 遞迴處理下一位數字
            path.deleteCharAt(path.length() - 1);  // 回溯，移除最後一字母，嘗試下一字母
        }
    }
}
