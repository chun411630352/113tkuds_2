import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res, "", 0, 0, n);
        return res;
    }
    
    // 使用回溯法生成括號組合
    private void backtrack(List<String> res, String current, int open, int close, int max) {
        // 若目前字串長度達到最大，將字串加入結果
        if (current.length() == max * 2) {
            res.add(current);
            return;
        }
        
        // 如果左括號還沒用完，可以加入左括號後繼續回溯
        if (open < max) {
            backtrack(res, current + "(", open + 1, close, max);
        }
        
        // 只有當右括號數小於左括號時，才能加入右括號
        if (close < open) {
            backtrack(res, current + ")", open, close + 1, max);
        }
    }
}