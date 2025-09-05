import java.util.*;

public class LC17_PhoneCombos_CSShift {
    static final String[] KEYS = {
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.nextLine();
        sc.close();
        
        if (digits == null || digits.length() == 0) {
            // 空字串輸出空結果（無輸出或 0 行）
            return;
        }
        
        List<String> result = new ArrayList<>();
        backtrack(digits, 0, new StringBuilder(), result);
        
        for (String combo : result) {
            System.out.println(combo);
        }
    }
    
    private static void backtrack(String digits, int index, StringBuilder sb, List<String> result) {
        if (index == digits.length()) {
            result.add(sb.toString());
            return;
        }
        
        String letters = KEYS[digits.charAt(index) - '2'];
        for (char c : letters.toCharArray()) {
            sb.append(c);
            backtrack(digits, index + 1, sb, result);
            sb.deleteCharAt(sb.length() - 1); // 回溯
        }
    }
}

