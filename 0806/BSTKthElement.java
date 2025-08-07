import java.util.*;

public class BSTKthElement {
    public static void main(String[] args) {
        BST bst = new BST();
        int[] values = {20, 8, 22, 4, 12, 10, 14};
        for (int v : values) {
            bst.insert(v);
        }

        int k = 3;
        System.out.println("第 " + k + " 小元素: " + bst.kthSmallest(k));
        System.out.println("第 " + k + " 大元素: " + bst.kthLargest(k));

        int j = 6;
        System.out.println("第 " + k + " 到第 " + j + " 小元素: " + bst.rangeKthElements(k, j));

        bst.delete(10);
        System.out.println("刪除 10 後，第 " + k + " 小元素: " + bst.kthSmallest(k));
    }

    static class TreeNode {
        int val;
        TreeNode left, right;
        int size; // 包含自身及左右子樹總節點數
        TreeNode(int val) {
            this.val = val;
            this.size = 1;
        }
    }

    static class BST {
        TreeNode root;

        public void insert(int val) {
            root = insert(root, val);
        }

        private TreeNode insert(TreeNode node, int val) {
            if (node == null) return new TreeNode(val);
            if (val < node.val) node.left = insert(node.left, val);
            else if (val > node.val) node.right = insert(node.right, val);
            // 更新節點大小
            node.size = 1 + size(node.left) + size(node.right);
            return node;
        }

        public void delete(int val) {
            root = delete(root, val);
        }

        private TreeNode delete(TreeNode node, int val) {
            if (node == null) return null;
            if (val < node.val) node.left = delete(node.left, val);
            else if (val > node.val) node.right = delete(node.right, val);
            else {
                if (node.left == null) return node.right;
                if (node.right == null) return node.left;
                TreeNode successor = minNode(node.right);
                node.val = successor.val;
                node.right = delete(node.right, successor.val);
            }
            node.size = 1 + size(node.left) + size(node.right);
            return node;
        }

        private TreeNode minNode(TreeNode node) {
            while (node.left != null) node = node.left;
            return node;
        }

        public int kthSmallest(int k) {
            if (k <= 0 || k > size(root)) throw new IllegalArgumentException("k out of range");
            return kthSmallest(root, k);
        }

        private int kthSmallest(TreeNode node, int k) {
            int leftSize = size(node.left);
            if (k == leftSize + 1) return node.val;
            else if (k <= leftSize) return kthSmallest(node.left, k);
            else return kthSmallest(node.right, k - leftSize - 1);
        }

        public int kthLargest(int k) {
            int n = size(root);
            if (k <= 0 || k > n) throw new IllegalArgumentException("k out of range");
            // 第 k 大 = 第 (n - k + 1) 小
            return kthSmallest(root, n - k + 1);
        }

        public List<Integer> rangeKthElements(int k, int j) {
            if (k > j) throw new IllegalArgumentException("k should be <= j");
            List<Integer> result = new ArrayList<>();
            int n = size(root);
            if (k <= 0) k = 1;
            if (j > n) j = n;
            inorderRange(root, result, new int[]{0}, k, j);
            return result;
        }

        private void inorderRange(TreeNode node, List<Integer> result, int[] count, int k, int j) {
            if (node == null || count[0] >= j) return;
            inorderRange(node.left, result, count, k, j);
            count[0]++;
            if (count[0] >= k && count[0] <= j) {
                result.add(node.val);
            }
            inorderRange(node.right, result, count, k, j);
        }

        private int size(TreeNode node) {
            return node == null ? 0 : node.size;
        }
    }
}
