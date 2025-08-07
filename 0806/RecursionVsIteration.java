import java.util.*;

public class RecursionVsIteration {
    public static void main(String[] args) {
        System.out.println("== 二項式係數 C(n, k) = C(20, 10) ==");

        long start1 = System.nanoTime();
        int resultR1 = binomialRec(20, 10);
        long end1 = System.nanoTime();
        System.out.println("遞迴: " + resultR1 + ", 時間: " + (end1 - start1) / 1_000_000.0 + " ms");

        long start2 = System.nanoTime();
        int resultI1 = binomialIter(20, 10);
        long end2 = System.nanoTime();
        System.out.println("迭代: " + resultI1 + ", 時間: " + (end2 - start2) / 1_000_000.0 + " ms");

        int[] array = {2, 3, 4, 5, 6};

        System.out.println("\n== 陣列元素乘積 ==");

        long start3 = System.nanoTime();
        int resultR2 = arrayProductRec(array, 0);
        long end3 = System.nanoTime();
        System.out.println("遞迴: " + resultR2 + ", 時間: " + (end3 - start3) / 1_000_000.0 + " ms");

        long start4 = System.nanoTime();
        int resultI2 = arrayProductIter(array);
        long end4 = System.nanoTime();
        System.out.println("迭代: " + resultI2 + ", 時間: " + (end4 - start4) / 1_000_000.0 + " ms");

        String text = "Recursion and Iteration are both useful.";

        System.out.println("\n== 字串中元音數量 ==");

        long start5 = System.nanoTime();
        int resultR3 = countVowelsRec(text, 0);
        long end5 = System.nanoTime();
        System.out.println("遞迴: " + resultR3 + ", 時間: " + (end5 - start5) / 1_000_000.0 + " ms");

        long start6 = System.nanoTime();
        int resultI3 = countVowelsIter(text);
        long end6 = System.nanoTime();
        System.out.println("迭代: " + resultI3 + ", 時間: " + (end6 - start6) / 1_000_000.0 + " ms");

        String brackets = "((()[{}]))";

        System.out.println("\n== 括號是否配對 ==");

        long start7 = System.nanoTime();
        boolean resultR4 = isBalancedRec(brackets, 0, new Stack<>());
        long end7 = System.nanoTime();
        System.out.println("遞迴: " + resultR4 + ", 時間: " + (end7 - start7) / 1_000_000.0 + " ms");

        long start8 = System.nanoTime();
        boolean resultI4 = isBalancedIter(brackets);
        long end8 = System.nanoTime();
        System.out.println("迭代: " + resultI4 + ", 時間: " + (end8 - start8) / 1_000_000.0 + " ms");
    }

    public static int binomialRec(int n, int k) {
        if (k == 0 || k == n) return 1;
        return binomialRec(n - 1, k - 1) + binomialRec(n - 1, k);
    }

    public static int binomialIter(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            int max = Math.min(i, k);
            for (int j = 0; j <= max; j++) {
                if (j == 0 || j == i) dp[i][j] = 1;
                else dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        return dp[n][k];
    }

    public static int arrayProductRec(int[] arr, int index) {
        if (index == arr.length) return 1;
        return arr[index] * arrayProductRec(arr, index + 1);
    }

    public static int arrayProductIter(int[] arr) {
        int product = 1;
        for (int num : arr) product *= num;
        return product;
    }

    public static int countVowelsRec(String s, int index) {
        if (index == s.length()) return 0;
        char c = Character.toLowerCase(s.charAt(index));
        int count = "aeiou".indexOf(c) >= 0 ? 1 : 0;
        return count + countVowelsRec(s, index + 1);
    }

    public static int countVowelsIter(String s) {
        int count = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) >= 0) count++;
        }
        return count;
    }

    public static boolean isBalancedRec(String s, int index, Stack<Character> stack) {
        if (index == s.length()) return stack.isEmpty();
        char c = s.charAt(index);
        if (c == '(' || c == '[' || c == '{') {
            stack.push(c);
            return isBalancedRec(s, index + 1, stack);
        } else if (c == ')' || c == ']' || c == '}') {
            if (stack.isEmpty()) return false;
            char top = stack.pop();
            if ((c == ')' && top != '(') || (c == ']' && top != '[') || (c == '}' && top != '{')) return false;
            return isBalancedRec(s, index + 1, stack);
        } else {
            return isBalancedRec(s, index + 1, stack);
        }
    }

    public static boolean isBalancedIter(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') stack.push(c);
            else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((c == ')' && top != '(') || (c == ']' && top != '[') || (c == '}' && top != '{')) return false;
            }
        }
        return stack.isEmpty();
    }
}
