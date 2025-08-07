import java.util.*;

public class BinaryTreeBasicOperations {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(15);

        int sum = sumTree(root);
        int count = countNodes(root);
        double avg = count == 0 ? 0 : (double) sum / count;
        System.out.println("節點總和: " + sum);
        System.out.printf("平均值: %.2f\n", avg);

        int max = findMax(root);
        int min = findMin(root);
        System.out.println("最大值: " + max);
        System.out.println("最小值: " + min);

        int width = treeWidth(root);
        System.out.println("最大寬度: " + width);

        System.out.println("是否為完全二元樹: " + isCompleteBinaryTree(root));
    }

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public static int sumTree(TreeNode node) {
        if (node == null) return 0;
        return node.val + sumTree(node.left) + sumTree(node.right);
    }

    public static int countNodes(TreeNode node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    public static int findMax(TreeNode node) {
        if (node == null) return Integer.MIN_VALUE;
        return Math.max(node.val, Math.max(findMax(node.left), findMax(node.right)));
    }

    public static int findMin(TreeNode node) {
        if (node == null) return Integer.MAX_VALUE;
        return Math.min(node.val, Math.min(findMin(node.left), findMin(node.right)));
    }

    public static int treeWidth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int maxWidth = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            maxWidth = Math.max(maxWidth, size);
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
        }
        return maxWidth;
    }

    public static boolean isCompleteBinaryTree(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean end = false;
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                end = true;
            } else {
                if (end) return false;
                q.offer(node.left);
                q.offer(node.right);
            }
        }
        return true;
    }
}
