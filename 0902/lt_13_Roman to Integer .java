import java.util.HashMap;
import java.util.Map;

class Solution {
    public int romanToInt(String s) {
        // 建立羅馬字母與數字的對應表
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int result = 0;
        int length = s.length();

        // 遍歷字串中每個字元
        for (int i = 0; i < length; i++) {
            int currentVal = romanMap.get(s.charAt(i));
            // 查看下一個字元的值（若存在）
            if (i + 1 < length && romanMap.get(s.charAt(i + 1)) > currentVal) {
                // 若下一個字元值較大，則減去當前值（減法規則）
                result -= currentVal;
            } else {
                // 否則加上當前值
                result += currentVal;
            }
        }

        return result;
    }
}
