import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LC20_ValidParentheses_AlertFormat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        sc.close();

        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        // 空字串視為合法
        if (s == null || s.length() == 0) return true;

        Map<Character, Character> matching = new HashMap<>();
        matching.put(')', '(');
        matching.put(']', '[');
        matching.put('}', '{');

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            // 遇開括號入棧
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                // 遇閉括號，確認棧頂是否匹配
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (matching.get(c) != top) {
                    return false;
                }
            }
        }
        // 棧最後要空才代表完全匹配
        return stack.isEmpty();
    }
}
