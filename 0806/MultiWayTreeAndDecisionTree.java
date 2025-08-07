import java.util.*;

public class MultiWayTreeAndDecisionTree {
    public static void main(String[] args) {
        Node root = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Node f = new Node("F");
        Node g = new Node("G");
        Node h = new Node("H");

        root.children.add(b);
        root.children.add(c);
        root.children.add(d);
        b.children.add(e);
        b.children.add(f);
        d.children.add(g);
        g.children.add(h);

        System.out.println("深度優先走訪：");
        dfs(root);
        System.out.println("\n廣度優先走訪：");
        bfs(root);

        System.out.println("\n樹高度：" + treeHeight(root));
        System.out.println("每個節點度數：");
        printDegrees(root);

        System.out.println("\n模擬簡單決策樹（猜數字）:");
        DecisionNode start = new DecisionNode("你的數字小於 50 嗎?");
        DecisionNode left = new DecisionNode("你的數字小於 25 嗎?");
        DecisionNode right = new DecisionNode("你的數字大於等於 75 嗎?");
        DecisionNode guess1 = new DecisionNode("你選的是 20");
        DecisionNode guess2 = new DecisionNode("你選的是 35");
        DecisionNode guess3 = new DecisionNode("你選的是 80");
        DecisionNode guess4 = new DecisionNode("你選的是 60");

        start.yes = left;
        start.no = right;
        left.yes = guess1;
        left.no = guess2;
        right.yes = guess3;
        right.no = guess4;

        runDecisionTree(start, new Scanner(System.in));
    }

    static class Node {
        String val;
        List<Node> children = new ArrayList<>();
        Node(String val) {
            this.val = val;
        }
    }

    public static void dfs(Node node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        for (Node child : node.children) {
            dfs(child);
        }
    }

    public static void bfs(Node root) {
        if (root == null) return;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node node = q.poll();
            System.out.print(node.val + " ");
            for (Node child : node.children) q.offer(child);
        }
    }

    public static int treeHeight(Node node) {
        if (node.children.isEmpty()) return 1;
        int max = 0;
        for (Node child : node.children) {
            max = Math.max(max, treeHeight(child));
        }
        return max + 1;
    }

    public static void printDegrees(Node node) {
        System.out.println(node.val + " 的度數：" + node.children.size());
        for (Node child : node.children) {
            printDegrees(child);
        }
    }

    static class DecisionNode {
        String question;
        DecisionNode yes, no;
        DecisionNode(String question) {
            this.question = question;
        }
    }

    public static void runDecisionTree(DecisionNode node, Scanner scanner) {
        while (node.yes != null || node.no != null) {
            System.out.println(node.question + " (y/n)");
            String input = scanner.next().toLowerCase();
            if (input.equals("y")) node = node.yes;
            else node = node.no;
        }
        System.out.println("結果：" + node.question);
    }
}
