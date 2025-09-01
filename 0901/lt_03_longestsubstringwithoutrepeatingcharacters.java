class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] index = new int[256];
        for (int i = 0; i < 256; i++) {
            index[i] = -1;
        }
        
        int maxLen = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (index[c] >= left) {
                left = index[c] + 1;
            }
            index[c] = right;
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
}
