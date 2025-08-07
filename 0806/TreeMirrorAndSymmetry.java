public class TreeMirrorAndSymmetry {
    public static void main(String[] args) {
        TreeNode tree1 = new TreeNode(1);
        tree1.left = new TreeNode(2);
        tree1.right = new TreeNode(2);
        tree1.left.left = new TreeNode(3);
        tree1.left.right = new TreeNode(4);
        tree1.right.left = new TreeNode(4);
        tree1.right.right = new TreeNode(3);

        System.out.println("是否為對稱樹: " + isSymmetric(tree1));

        TreeNode mirror = cloneTree(tree1);
        mirrorTree(mirror);
        System.out.println("與鏡像互為鏡像: " + isMirror(tree1, mirror));

        TreeNode subtree = new TreeNode(2);
        subtree.left = new TreeNode(3);
        subtree.right = new TreeNode(4);
        System.out.println("是否為子樹: " + isSubtree(tree1, subtree));
    }

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    public static void mirrorTree(TreeNode root) {
        if (root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirrorTree(root.left);
        mirrorTree(root.right);
    }

    public static boolean isMirror(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return isMirror(a.left, b.right) && isMirror(a.right, b.left);
    }

    public static boolean isSubtree(TreeNode root, TreeNode sub) {
        if (root == null) return false;
        if (isSameTree(root, sub)) return true;
        return isSubtree(root.left, sub) || isSubtree(root.right, sub);
    }

    public static boolean isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
    }

    public static TreeNode cloneTree(TreeNode root) {
        if (root == null) return null;
        TreeNode node = new TreeNode(root.val);
        node.left = cloneTree(root.left);
        node.right = cloneTree(root.right);
        return node;
    }
}
