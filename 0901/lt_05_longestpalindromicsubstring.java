class Solution {
    private int left, right;

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int n = s.length();
        left = 0;
        right = 0;
        for (int i = 0; i < n; i++) {
            expandAroundCenter(s, i, i);
            expandAroundCenter(s, i, i + 1);
        }
        return s.substring(left, right + 1);
    }

    private void expandAroundCenter(String s, int L, int R) {
        while (L >=0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        if (R - L - 1 > right - left) {
            left = L + 1;
            right = R - 1;
        }
    }
}
