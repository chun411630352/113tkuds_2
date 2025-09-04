import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            // 如果是左括號，推入對應的右括號
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else {
                // 如果是右括號，判斷堆疊頂端是否匹配
                if (stack.isEmpty() || stack.pop() != c) {
                    return false; // 不匹配或堆疊空則無效
                }
            }
        }
        
        // 最終堆疊應為空，表示所有括號都匹配
        return stack.isEmpty();
    }
}
