import java.util.*;

public class LC40_CombinationSum2_Procurement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) {
            candidates[i] = sc.nextInt();
        }
        sc.close();

        List<List<Integer>> res = combinationSum2(candidates, target);
        for (List<Integer> comb : res) {
            for (int i = 0; i < comb.size(); i++) {
                System.out.print(comb.get(i) + (i == comb.size()-1 ? "\n" : " "));
            }
        }
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private static void backtrack(int[] candidates, int remain, int start, List<Integer> curr, List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }
        if (remain < 0) return;

        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i-1]) continue; // 同層跳過重複
            
            curr.add(candidates[i]);
            backtrack(candidates, remain - candidates[i], i + 1, curr, res); // 下一層 i+1 (不可重複)
            curr.remove(curr.size() - 1);
        }
    }
}
