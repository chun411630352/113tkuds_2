import java.util.*;

public class BSTRangeQuerySystem {
    public static void main(String[] args) {
        TreeNode root = null;
        int[] values = {15, 10, 20, 8, 12, 17, 25};
        for (int val : values) {
            root = insert(root, val);
        }

        int min = 10, max = 20;
        List<Integer> rangeNodes = new ArrayList<>();
        rangeQuery(root, min, max, rangeNodes);
        System.out.println("範圍 [" + min + ", " + max + "] 的節點: " + rangeNodes);
        System.out.println("範圍內節點數: " + rangeCount(root, min, max));
        System.out.println("範圍內節點總和: " + rangeSum(root, min, max));

        int target = 18;
        int closest = findClosest(root, target);
        System.out.println("最接近 " + target + " 的值: " + closest);
    }

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode insert(TreeNode node, int val) {
        if (node == null) return new TreeNode(val);
        if (val < node.val) node.left = insert(node.left, val);
        else if (val > node.val) node.right = insert(node.right, val);
        return node;
    }

    public static void rangeQuery(TreeNode node, int min, int max, List<Integer> result) {
        if (node == null) return;
        if (node.val > min) rangeQuery(node.left, min, max, result);
        if (node.val >= min && node.val <= max) result.add(node.val);
        if (node.val < max) rangeQuery(node.right, min, max, result);
    }

    public static int rangeCount(TreeNode node, int min, int max) {
        if (node == null) return 0;
        if (node.val < min) return rangeCount(node.right, min, max);
        if (node.val > max) return rangeCount(node.left, min, max);
        return 1 + rangeCount(node.left, min, max) + rangeCount(node.right, min, max);
    }

    public static int rangeSum(TreeNode node, int min, int max) {
        if (node == null) return 0;
        if (node.val < min) return rangeSum(node.right, min, max);
        if (node.val > max) return rangeSum(node.left, min, max);
        return node.val + rangeSum(node.left, min, max) + rangeSum(node.right, min, max);
    }

    public static int findClosest(TreeNode node, int target) {
        int closest = node.val;
        while (node != null) {
            if (Math.abs(node.val - target) < Math.abs(closest - target)) {
                closest = node.val;
            }
            if (target < node.val) node = node.left;
            else if (target > node.val) node = node.right;
            else return node.val;
        }
        return closest;
    }
}
