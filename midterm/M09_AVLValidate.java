import java.util.*;

public class M09_AVLValidate {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static final int INVALID = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 0) {
            System.out.println("Valid");
            return;
        }

        int[] vals = new int[n];
        for (int i = 0; i < n; i++) {
            vals[i] = sc.nextInt();
        }

        TreeNode root = buildTree(vals);
        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
            return;
        }

        if (checkAVL(root) == INVALID) {
            System.out.println("Invalid AVL");
            return;
        }

        System.out.println("Valid");
    }

    static TreeNode buildTree(int[] vals) {
        if (vals.length == 0 || vals[0] == -1) return null;
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(vals[0]);
        q.offer(root);
        int i = 1;
        while (!q.isEmpty() && i < vals.length) {
            TreeNode node = q.poll();
            if (i < vals.length && vals[i] != -1) {
                node.left = new TreeNode(vals[i]);
                q.offer(node.left);
            }
            i++;
            if (i < vals.length && vals[i] != -1) {
                node.right = new TreeNode(vals[i]);
                q.offer(node.right);
            }
            i++;
        }
        return root;
    }

    static boolean isBST(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.val <= min || root.val >= max) return false;
        return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
    }

    static int checkAVL(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = checkAVL(root.left);
        if (leftHeight == INVALID) return INVALID;
        int rightHeight = checkAVL(root.right);
        if (rightHeight == INVALID) return INVALID;

        if (Math.abs(leftHeight - rightHeight) > 1) return INVALID;
        return Math.max(leftHeight, rightHeight) + 1;
    }
}

/*
 * 時間複雜度：O(n)
 * 說明：
 * 1. 建樹使用層序遍歷，每個節點訪問一次，為 O(n)。
 * 2. 檢查 BST 條件，遞迴遍歷所有節點，為 O(n)。
 * 3. 檢查 AVL 平衡，後序遍歷所有節點，一次訪問，為 O(n)。
 * 整體時間複雜度為 O(n)。
 */
