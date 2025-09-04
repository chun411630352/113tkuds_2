class Solution {
    public int strStr(String haystack, String needle) {
        // 如果 needle 是空字串，根據題意回傳 0
        if (needle.length() == 0) return 0;
        
        int hLen = haystack.length();
        int nLen = needle.length();
        
        // 若 haystack 長度小於 needle，無法包含，回傳 -1
        if (hLen < nLen) return -1;
        
        // 遍歷 haystack，檢查每個可能起始位置
        for (int i = 0; i <= hLen - nLen; i++) {
            // 取出子字串與 needle 比較
            if (haystack.substring(i, i + nLen).equals(needle)) {
                return i; // 找到，回傳索引
            }
        }
        
        // 全部遍歷未找到，回傳 -1
        return -1;
    }
}
