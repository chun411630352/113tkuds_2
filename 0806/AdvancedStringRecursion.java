import java.util.*;

public class AdvancedStringRecursion {
    public static void main(String[] args) {
        String input = "abc";
        System.out.println("所有排列組合：");
        Set<String> permutations = new LinkedHashSet<>();
        generatePermutations("", input, permutations);
        for (String s : permutations) {
            System.out.println(s);
        }

        String text = "hello world";
        String pattern = "world";
        boolean match = isMatch(text, pattern, 0, 0);
        System.out.println("是否匹配：" + match);

        String unique = removeDuplicates(input, 0, new HashSet<>());
        System.out.println("移除重複字符：" + unique);

        System.out.println("所有子字串組合：");
        List<String> substrs = new ArrayList<>();
        generateSubstrings("", input, 0, substrs);
        for (String s : substrs) {
            System.out.println(s);
        }
    }

    public static void generatePermutations(String prefix, String rest, Set<String> result) {
        if (rest.isEmpty()) {
            result.add(prefix);
            return;
        }
        for (int i = 0; i < rest.length(); i++) {
            String before = rest.substring(0, i);
            String after = rest.substring(i + 1);
            generatePermutations(prefix + rest.charAt(i), before + after, result);
        }
    }

    public static boolean isMatch(String text, String pattern, int i, int j) {
        if (j == pattern.length()) return i == text.length();
        if (i == text.length()) return false;
        if (text.charAt(i) == pattern.charAt(j)) 
            return isMatch(text, pattern, i + 1, j + 1);
        return isMatch(text, pattern, i + 1, j);
    }

    public static String removeDuplicates(String s, int index, Set<Character> seen) {
        if (index == s.length()) return "";
        char c = s.charAt(index);
        if (seen.contains(c)) return removeDuplicates(s, index + 1, seen);
        seen.add(c);
        return c + removeDuplicates(s, index + 1, seen);
    }

    public static void generateSubstrings(String current, String s, int index, List<String> result) {
        if (index == s.length()) {
            if (!current.isEmpty()) result.add(current);
            return;
        }
        generateSubstrings(current + s.charAt(index), s, index + 1, result);
        generateSubstrings(current, s, index + 1, result);
    }
}
