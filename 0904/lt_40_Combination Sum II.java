import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates); // 排序方便跳過重複元素
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }
    
    private void backtrack(int[] candidates, int target, int start, List<Integer> current, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(current)); // 找到一組合法組合，存入
            return;
        }
        if (target < 0) return; // 超出目標，剪枝
        
        for (int i = start; i < candidates.length; i++) {
            // 跳過同一層中重複數字，以避免重複組合
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            
            current.add(candidates[i]);
            // 下一層從 i + 1 開始，因元素只能用一次
            backtrack(candidates, target - candidates[i], i + 1, current, res);
            current.remove(current.size() - 1); // 回溯撤銷選擇
        }
    }
}
