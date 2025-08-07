import java.util.*;

public class TreeReconstruction {
    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        int[] levelorder = {1,2,3,4,5,6,7};

        TreeNode fromPreIn = buildTreeFromPreIn(preorder, inorder);
        TreeNode fromPostIn = buildTreeFromPostIn(postorder, inorder);
        TreeNode fromLevel = buildCompleteTreeFromLevel(levelorder);

        System.out.println("前序+中序建構 => 中序:");
        printInOrder(fromPreIn);
        System.out.println("\n後序+中序建構 => 中序:");
        printInOrder(fromPostIn);
        System.out.println("\n層序建構完全二元樹 => 中序:");
        printInOrder(fromLevel);
    }

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static TreeNode buildTreeFromPreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inMap.put(inorder[i], i);
        return buildPreIn(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private static TreeNode buildPreIn(int[] pre, int ps, int pe, int[] in, int is, int ie, Map<Integer,Integer> map) {
        if (ps > pe || is > ie) return null;
        TreeNode root = new TreeNode(pre[ps]);
        int ri = map.get(pre[ps]);
        int leftSize = ri - is;
        root.left = buildPreIn(pre, ps + 1, ps + leftSize, in, is, ri - 1, map);
        root.right = buildPreIn(pre, ps + leftSize + 1, pe, in, ri + 1, ie, map);
        return root;
    }

    public static TreeNode buildTreeFromPostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inMap.put(inorder[i], i);
        return buildPostIn(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private static TreeNode buildPostIn(int[] post, int ps, int pe, int[] in, int is, int ie, Map<Integer,Integer> map) {
        if (ps > pe || is > ie) return null;
        TreeNode root = new TreeNode(post[pe]);
        int ri = map.get(post[pe]);
        int leftSize = ri - is;
        root.left = buildPostIn(post, ps, ps + leftSize - 1, in, is, ri - 1, map);
        root.right = buildPostIn(post, ps + leftSize, pe - 1, in, ri + 1, ie, map);
        return root;
    }

    public static TreeNode buildCompleteTreeFromLevel(int[] levelOrder) {
        if (levelOrder.length == 0) return null;
        TreeNode root = new TreeNode(levelOrder[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (i < levelOrder.length) {
            TreeNode node = q.poll();
            if (i < levelOrder.length) {
                node.left = new TreeNode(levelOrder[i++]);
                q.offer(node.left);
            }
            if (i < levelOrder.length) {
                node.right = new TreeNode(levelOrder[i++]);
                q.offer(node.right);
            }
        }
        return root;
    }

    public static void printInOrder(TreeNode root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }
}
