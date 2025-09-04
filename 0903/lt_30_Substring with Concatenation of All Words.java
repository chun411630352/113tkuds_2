import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || words == null || words.length == 0) return res;

        int wordLen = words[0].length();
        int wordCount = words.length;
        int concatLen = wordLen * wordCount;
        if (s.length() < concatLen) return res;

        // 計算 words 中字串的頻率
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String w : words) {
            wordFreq.put(w, wordFreq.getOrDefault(w, 0) + 1);
        }

        // 對於每一個起點偏移量使用滑動視窗檢查
        for (int i = 0; i < wordLen; i++) {
            Map<String, Integer> windowFreq = new HashMap<>();
            int left = i, count = 0;

            // 程式會以 wordLen 為步長檢查字串
            for (int j = i; j <= s.length() - wordLen; j += wordLen) {
                String word = s.substring(j, j + wordLen);
                // 若此字串出現在 words 中，更新 windowFreq
                if (wordFreq.containsKey(word)) {
                    windowFreq.put(word, windowFreq.getOrDefault(word, 0) + 1);
                    count++;

                    // 若該字串在新視窗出現頻率超過 words 頻率，縮小視窗調整頻率
                    while (windowFreq.get(word) > wordFreq.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        windowFreq.put(leftWord, windowFreq.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    // 當視窗大小正好是 words 的總字串數，找到一個起點
                    if (count == wordCount) {
                        res.add(left);
                        String leftWord = s.substring(left, left + wordLen);
                        windowFreq.put(leftWord, windowFreq.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }
                } else {
                    // 不在 words 中字串，清除視窗並重置
                    windowFreq.clear();
                    count = 0;
                    left = j + wordLen;
                }
            }
        }
        return res;
    }
}
