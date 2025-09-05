import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class LC32_LongestValidParen_Metro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        sc.close();

        System.out.println(longestValidParentheses(s));
    }

    public static int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // 初始基準索引，方便計算長度

        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // 遇左括號，將索引入棧
                stack.push(i);
            } else {
                // 遇右括號嘗試配對，彈出左括號索引
                stack.pop();

                if (stack.isEmpty()) {
                    // 若棧空，將當前索引當作新基準
                    stack.push(i);
                } else {
                    // 計算有效子串長度
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }
}
