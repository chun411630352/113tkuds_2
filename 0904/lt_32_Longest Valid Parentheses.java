import java.util.Stack;

class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 放入基準索引，方便計算長度
        
        int maxLen = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                // 左括號索引入堆疊
                stack.push(i);
            } else {
                // 遇右括號嘗試配對，彈出一個左括號索引
                stack.pop();
                
                if (stack.isEmpty()) {
                    // 若空堆疊，更新基準為當前索引
                    stack.push(i);
                } else {
                    // 計算有效子串長度，更新最大值
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        
        return maxLen;
    }
}
