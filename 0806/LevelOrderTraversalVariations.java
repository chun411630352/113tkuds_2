import java.util.*;

public class LevelOrderTraversalVariations {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.right.right = new TreeNode(7);

        List<List<Integer>> levels = levelByLevel(root);
        System.out.println("層別儲存：");
        for (List<Integer> level : levels) System.out.println(level);

        List<List<Integer>> zigzag = zigzagLevelOrder(root);
        System.out.println("之字形層序：");
        for (List<Integer> level : zigzag) System.out.println(level);

        List<Integer> lastNodes = lastNodeEachLevel(root);
        System.out.println("每層最後一個節點：" + lastNodes);

        List<List<Integer>> verticalOrder = verticalTraversal(root);
        System.out.println("垂直層序：");
        for (List<Integer> col : verticalOrder) System.out.println(col);
    }

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static List<List<Integer>> levelByLevel(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                level.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            result.add(level);
        }
        return result;
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean leftToRight = true;
        while (!q.isEmpty()) {
            int size = q.size();
            Deque<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (leftToRight) level.addLast(node.val);
                else level.addFirst(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            result.add(new ArrayList<>(level));
            leftToRight = !leftToRight;
        }
        return result;
    }

    public static List<Integer> lastNodeEachLevel(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            TreeNode node = null;
            for (int i = 0; i < size; i++) {
                node = q.poll();
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            result.add(node.val);
        }
        return result;
    }

    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));
        while (!q.isEmpty()) {
            int size = q.size();
            Map<Integer, List<Integer>> temp = new HashMap<>();
            for (int i = 0; i < size; i++) {
                Pair p = q.poll();
                temp.putIfAbsent(p.col, new ArrayList<>());
                temp.get(p.col).add(p.node.val);
                if (p.node.left != null) q.offer(new Pair(p.node.left, p.col - 1));
                if (p.node.right != null) q.offer(new Pair(p.node.right, p.col + 1));
            }
            for (int col : temp.keySet()) {
                map.putIfAbsent(col, new ArrayList<>());
                map.get(col).addAll(temp.get(col));
            }
        }
        return new ArrayList<>(map.values());
    }

    static class Pair {
        TreeNode node;
        int col;
        Pair(TreeNode n, int c) {
            node = n;
            col = c;
        }
    }
}
