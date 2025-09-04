class Solution {
    public String countAndSay(int n) {
        if (n == 1) return "1"; // 基本情況
        
        String prev = countAndSay(n - 1); // 遞迴取得上一項
        StringBuilder sb = new StringBuilder();
        
        int count = 1;
        for (int i = 1; i <= prev.length(); i++) {
            // 若已經到字串末尾或者字元與前一字元不同
            if (i == prev.length() || prev.charAt(i) != prev.charAt(i - 1)) {
                sb.append(count);           // 先加入計數
                sb.append(prev.charAt(i - 1)); // 再加入字元
                count = 1;                  // 重置計數
            } else {
                count++; // 遇到連續相同字元，計數增加
            }
        }
        
        return sb.toString();
    }
}
