import java.util.*;

public class BSTConversionAndBalance {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(6);
        root.right = new TreeNode(14);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(8);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(16);

        TreeNode[] dll = bstToDoublyLinkedList(root);
        TreeNode head = dll[0];
        System.out.print("BST 轉 DLL：");
        TreeNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + (curr.right != null ? " <-> " : ""));
            curr = curr.right;
        }
        System.out.println();

        int[] sorted = {1, 2, 3, 4, 5, 6, 7};
        TreeNode balanced = sortedArrayToBST(sorted);
        System.out.print("由排序陣列建 BST（中序）：");
        inorder(balanced);
        System.out.println();

        System.out.println("是否平衡: " + isBalanced(root));
        Map<TreeNode, Integer> balanceMap = new HashMap<>();
        calculateBalanceFactors(root, balanceMap);
        System.out.println("各節點平衡因子：");
        printBalanceFactors(balanceMap);

        convertToGreaterTree(root);
        System.out.print("轉換為 Greater Tree 後中序：");
        inorder(root);
        System.out.println();
    }

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) {
            val = v;
        }
    }

    public static TreeNode[] bstToDoublyLinkedList(TreeNode root) {
        TreeNode[] prev = new TreeNode[1];
        TreeNode[] head = new TreeNode[1];
        convertDLL(root, prev, head);
        return new TreeNode[]{head[0], prev[0]};
    }

    private static void convertDLL(TreeNode node, TreeNode[] prev, TreeNode[] head) {
        if (node == null) return;
        convertDLL(node.left, prev, head);
        if (prev[0] != null) {
            prev[0].right = node;
            node.left = prev[0];
        } else {
            head[0] = node;
        }
        prev[0] = node;
        convertDLL(node.right, prev, head);
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return buildBalanced(nums, 0, nums.length - 1);
    }

    private static TreeNode buildBalanced(int[] nums, int l, int r) {
        if (l > r) return null;
        int mid = (l + r) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildBalanced(nums, l, mid - 1);
        node.right = buildBalanced(nums, mid + 1, r);
        return node;
    }

    public static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private static int checkHeight(TreeNode node) {
        if (node == null) return 0;
        int l = checkHeight(node.left);
        if (l == -1) return -1;
        int r = checkHeight(node.right);
        if (r == -1) return -1;
        if (Math.abs(l - r) > 1) return -1;
        return Math.max(l, r) + 1;
    }

    public static void calculateBalanceFactors(TreeNode root, Map<TreeNode, Integer> map) {
        dfsBalance(root, map);
    }

    private static int dfsBalance(TreeNode node, Map<TreeNode, Integer> map) {
        if (node == null) return 0;
        int l = dfsBalance(node.left, map);
        int r = dfsBalance(node.right, map);
        map.put(node, l - r);
        return Math.max(l, r) + 1;
    }

    public static void printBalanceFactors(Map<TreeNode, Integer> map) {
        Queue<TreeNode> q = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        q.offer(map.keySet().iterator().next());
        while (!q.isEmpty()) {
            TreeNode n = q.poll();
            if (!visited.contains(n)) {
                visited.add(n);
                System.out.println("值: " + n.val + " -> 平衡因子: " + map.get(n));
                if (n.left != null) q.offer(n.left);
                if (n.right != null) q.offer(n.right);
            }
        }
    }

    public static void convertToGreaterTree(TreeNode root) {
        int[] sum = new int[1];
        greaterDfs(root, sum);
    }

    private static void greaterDfs(TreeNode node, int[] sum) {
        if (node == null) return;
        greaterDfs(node.right, sum);
        sum[0] += node.val;
        node.val = sum[0];
        greaterDfs(node.left, sum);
    }

    public static void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }
}
