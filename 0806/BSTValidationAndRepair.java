import java.util.*;

public class BSTValidationAndRepair {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(20);

        System.out.println("是否為有效BST? " + isValidBST(root));

        List<TreeNode> errors = findViolationNodes(root);
        System.out.print("錯誤節點: ");
        for (TreeNode n : errors) System.out.print(n.val + " ");
        System.out.println();

        repairBST(root);
        System.out.println("修復後是否為有效BST? " + isValidBST(root));

        int removeCount = countRemoveToValidBST(root);
        System.out.println("最少需移除節點數: " + removeCount);
    }

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static boolean isValidBST(TreeNode root) {
        return isValid(root, null, null);
    }

    public static boolean isValid(TreeNode n, Integer min, Integer max) {
        if (n == null) return true;
        if ((min != null && n.val <= min) || (max != null && n.val >= max)) return false;
        return isValid(n.left, min, n.val) && isValid(n.right, n.val, max);
    }

    public static List<TreeNode> findViolationNodes(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        List<TreeNode> nodes = new ArrayList<>();
        inorder(root, nodes);
        TreeNode x = null, y = null;
        for (int i = 0; i < nodes.size() - 1; i++) {
            if (nodes.get(i).val > nodes.get(i + 1).val) {
                if (x == null) x = nodes.get(i);
                y = nodes.get(i + 1);
            }
        }
        if (x != null && y != null) {
            list.add(x);
            list.add(y);
        }
        return list;
    }

    public static void repairBST(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        inorder(root, nodes);
        TreeNode x = null, y = null;
        for (int i = 0; i < nodes.size() - 1; i++) {
            if (nodes.get(i).val > nodes.get(i + 1).val) {
                if (x == null) x = nodes.get(i);
                y = nodes.get(i + 1);
            }
        }
        if (x != null && y != null) {
            int tmp = x.val;
            x.val = y.val;
            y.val = tmp;
        }
    }

    public static int countRemoveToValidBST(TreeNode root) {
        int total = count(root);
        int maxSize = largestBST(root)[2];
        return total - maxSize;
    }

    public static int count(TreeNode node) {
        if (node == null) return 0;
        return 1 + count(node.left) + count(node.right);
    }

    public static int[] largestBST(TreeNode node) {
        if (node == null) return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        int[] l = largestBST(node.left);
        int[] r = largestBST(node.right);

        if (l[2] == -1 || r[2] == -1 || node.val <= l[1] || node.val >= r[0])
            return new int[]{0, 0, -1};

        int min = node.left == null ? node.val : l[0];
        int max = node.right == null ? node.val : r[1];
        int size = l[2] + r[2] + 1;
        return new int[]{min, max, size};
    }

    public static void inorder(TreeNode n, List<TreeNode> list) {
        if (n == null) return;
        inorder(n.left, list);
        list.add(n);
        inorder(n.right, list);
    }
}
