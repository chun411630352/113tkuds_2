class Solution {
    public String intToRoman(int num) {
        // 羅馬數字對應的整數值（含特殊的減法組合）
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        // 對應的羅馬數字符號
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        // 用於建構結果的字串
        StringBuilder roman = new StringBuilder();

        // 從最大數值開始往下扣，直到num減為0
        for (int i = 0; i < values.length; i++) {
            // 當num仍可扣除當前值，持續扣除並加入對應羅馬符號
            while (num >= values[i]) {
                num -= values[i];          // 扣除對應數字
                roman.append(symbols[i]);  // 加入羅馬字元
            }
        }
        // 回傳組合完成的羅馬數字字串
        return roman.toString();
    }
}