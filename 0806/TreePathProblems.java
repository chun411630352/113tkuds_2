import java.util.*;

public class TreePathProblems {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);

        List<List<Integer>> paths = allRootToLeafPaths(root);
        System.out.println("所有根到葉路徑:");
        for (List<Integer> p : paths) System.out.println(p);

        int target = 18;
        boolean exists = hasPathSum(root, target);
        System.out.println("是否存在路徑和為 " + target + ": " + exists);

        List<Integer> maxPath = new ArrayList<>();
        maxRootToLeafSum(root, new ArrayList<>(), 0, new int[]{Integer.MIN_VALUE}, maxPath);
        System.out.println("總和最大的根到葉路徑: " + maxPath);

        int maxPathSum = maxPathSum(root);
        System.out.println("任意兩節點最大路徑和: " + maxPathSum);
    }

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static List<List<Integer>> allRootToLeafPaths(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, new ArrayList<>(), result);
        return result;
    }

    private static void dfs(TreeNode node, List<Integer> path, List<List<Integer>> result) {
        if (node == null) return;
        path.add(node.val);
        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(path));
        } else {
            dfs(node.left, path, result);
            dfs(node.right, path, result);
        }
        path.remove(path.size() - 1);
    }

    public static boolean hasPathSum(TreeNode node, int sum) {
        if (node == null) return false;
        if (node.left == null && node.right == null) return sum == node.val;
        return hasPathSum(node.left, sum - node.val) || hasPathSum(node.right, sum - node.val);
    }

    public static void maxRootToLeafSum(TreeNode node, List<Integer> path, int sum, int[] max, List<Integer> result) {
        if (node == null) return;
        path.add(node.val);
        sum += node.val;
        if (node.left == null && node.right == null) {
            if (sum > max[0]) {
                max[0] = sum;
                result.clear();
                result.addAll(path);
            }
        } else {
            maxRootToLeafSum(node.left, path, sum, max, result);
            maxRootToLeafSum(node.right, path, sum, max, result);
        }
        path.remove(path.size() - 1);
    }

    public static int maxPathSum(TreeNode root) {
        int[] max = new int[]{Integer.MIN_VALUE};
        maxPathSumHelper(root, max);
        return max[0];
    }

    public static int maxPathSumHelper(TreeNode node, int[] max) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathSumHelper(node.left, max));
        int right = Math.max(0, maxPathSumHelper(node.right, max));
        max[0] = Math.max(max[0], left + right + node.val);
        return Math.max(left, right) + node.val;
    }
}
