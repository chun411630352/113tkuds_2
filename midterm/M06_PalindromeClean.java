import java.util.Scanner;

public class M06_PalindromeClean {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        if (isPalindromeClean(s)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    static boolean isPalindromeClean(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            // 移除非字母字元
            while (left < right && !Character.isLetter(s.charAt(left))) left++;
            while (left < right && !Character.isLetter(s.charAt(right))) right--;
            if (left >= right) break;
            char cLeft = Character.toLowerCase(s.charAt(left));
            char cRight = Character.toLowerCase(s.charAt(right));
            if (cLeft != cRight) return false;
            left++;
            right--;
        }
        return true;
    }
}

/*
 * 時間複雜度：O(n)
 * 說明：
 * 1. 使用雙指標從字串兩端往中間遍歷，每個字元最多被訪問一次。
 * 2. 過濾非英文字母與大小寫轉換，均為常數時間操作。
 * 3. 因此整體時間複雜度為線性 O(n)。
 */
