import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }
    
    private void backtrack(int[] candidates, int target, int start, List<Integer> current, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(current)); // 找到一組合法組合，加入結果
            return;
        }
        if (target < 0) return; // 超出目標和，停止探索
        
        for (int i = start; i < candidates.length; i++) {
            current.add(candidates[i]); // 選擇元素
            backtrack(candidates, target - candidates[i], i, current, res); // 繼續探索並允許重複選擇同一元素
            current.remove(current.size() - 1); // 回溯，撤銷之前選擇
        }
    }
}
