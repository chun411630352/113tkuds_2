import java.util.*;

public class M08_BSTRangedSum {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 0) {
            System.out.println("Sum: 0");
            return;
        }

        int[] vals = new int[n];
        for (int i = 0; i < n; i++) {
            vals[i] = sc.nextInt();
        }

        int L = sc.nextInt();
        int R = sc.nextInt();

        TreeNode root = buildTree(vals);
        int sum = rangeSumBST(root, L, R);
        System.out.println("Sum: " + sum);
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

    static int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;

        if (root.val < L) {
            // 節點值小於區間左界，遞迴右子樹
            return rangeSumBST(root.right, L, R);
        } else if (root.val > R) {
            // 節點值大於區間右界，遞迴左子樹
            return rangeSumBST(root.left, L, R);
        } else {
            // 範圍內，節點值加上左右子樹和
            return root.val
                + rangeSumBST(root.left, L, R)
                + rangeSumBST(root.right, L, R);
        }
    }
}

