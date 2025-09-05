import java.util.*;

public class LC39_CombinationSum_PPE {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) {
            candidates[i] = sc.nextInt();
        }
        sc.close();

        List<List<Integer>> res = combinationSum(candidates, target);
        for (List<Integer> comb : res) {
            for (int i = 0; i < comb.size(); i++) {
                System.out.print(comb.get(i) + (i == comb.size()-1 ? "\n" : " "));
            }
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates); // 非必需但常見習慣
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
            curr.add(candidates[i]);
            backtrack(candidates, remain - candidates[i], i, curr, res); // 下一層仍傳 i（可重複）
            curr.remove(curr.size() - 1);
        }
    }
}
