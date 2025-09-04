class Solution {
    public int divide(int dividend, int divisor) {
        // 處理特殊溢位情況
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;  // 超出32位有號整數上限
        }
        
        // 判斷結果符號：若兩數同號結果為正，否則為負
        boolean positive = (dividend > 0) == (divisor > 0);
        
        // 將 dividend 和 divisor 都轉成負數，方便處理溢位
        int dvd = dividend > 0 ? -dividend : dividend;
        int dvs = divisor > 0 ? -divisor : divisor;
        
        int result = 0;
        
        while (dvd <= dvs) { // 當被除數仍小於除數（均為負數）
            int temp = dvs;
            int multiple = 1;
            
            // 嘗試將除數翻倍，直到下一倍大於被除數 (避免溢位判斷)
            while (temp >= Integer.MIN_VALUE >> 1 && dvd <= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            
            // 減去最大可倍增後的除數
            dvd -= temp;
            // 累加倍數
            result += multiple;
        }
        
        // 根據符號，回傳結果正負
        return positive ? result : -result;
    }
}
