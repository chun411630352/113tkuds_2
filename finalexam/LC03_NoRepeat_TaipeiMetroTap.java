import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        sc.close();

        System.out.println(lengthOfLongestSubstring(s));
    }

    private static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastIndex = new HashMap<>();
        int maxLen = 0;
        int left = 0; // 左指標，子串開始位置

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (lastIndex.containsKey(c)) {
                // 避免左指標回退，保持最大起點
                left = Math.max(left, lastIndex.get(c) + 1);
            }
            lastIndex.put(c, right); // 更新字元最後出現位置
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}