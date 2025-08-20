import java.util.*;

public class M07_BinaryTreeLeftView {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 0) {
            System.out.println("LeftView:");
            return;
        }

        int[] vals = new int[n];
        for (int i = 0; i < n; i++) {
            vals[i] = sc.nextInt();
        }

        TreeNode root = buildTree(vals);
        List<Integer> leftView = getLeftView(root);

        System.out.print("LeftView:");
        for (int v : leftView) {
            System.out.print(" " + v);
        }
        System.out.println();
    }

    static TreeNode buildTree(int[] vals) {
        if (vals.length == 0 || vals[0] == -1) return null;
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(vals[0]);
        q.offer(root);
        int i = 1;

        while (!q.isEmpty() && i < vals.length) {
            TreeNode node = q.poll();

            // 左子節點
            if (i < vals.length && vals[i] != -1) {
                node.left = new TreeNode(vals[i]);
                q.offer(node.left);
            }
            i++;

            // 右子節點
            if (i < vals.length && vals[i] != -1) {
                node.right = new TreeNode(vals[i]);
                q.offer(node.right);
            }
            i++;
        }
        return root;
    }

    static List<Integer> getLeftView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (i == 0) {
                    result.add(node.val);
                }
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
        }
        return result;
    }
}


