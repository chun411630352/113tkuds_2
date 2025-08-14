import java.util.*;

public class AVLRangeQueryExercise {
    // 節點類
    static class Node {
        int data;
        Node left, right;
        int height = 1;
        Node(int data) { this.data = data; }
    }

    private Node root;

    // ========== 插入 (用於建立測試樹) ==========
    public void insert(int val) { root = insert(root, val); }
    private Node insert(Node node, int val) {
        if (node == null) return new Node(val);
        if (val < node.data) node.left = insert(node.left, val);
        else if (val > node.data) node.right = insert(node.right, val);
        else return node;
        updateHeight(node);
        return rebalance(node, val);
    }

    // ========== 範圍查詢 ==========
    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryHelper(root, min, max, result);
        return result;
    }

    private void rangeQueryHelper(Node node, int min, int max, List<Integer> result) {
        if (node == null) return;

        // BST 剪枝優化：
        // 如果當前節點值大於 min，才需要遍歷左子樹
        if (node.data > min) {
            rangeQueryHelper(node.left, min, max, result);
        }

        // 如果當前節點在範圍內，加入結果
        if (node.data >= min && node.data <= max) {
            result.add(node.data);
        }

        // 如果當前節點值小於 max，才需要遍歷右子樹
        if (node.data < max) {
            rangeQueryHelper(node.right, min, max, result);
        }
    }

    // ========== AVL 平衡處理 ==========
    private Node rebalance(Node node, int val) {
        int bal = getBalance(node);
        // LL
        if (bal > 1 && val < node.left.data) return rightRotate(node);
        // RR
        if (bal < -1 && val > node.right.data) return leftRotate(node);
        // LR
        if (bal > 1 && val > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if (bal < -1 && val < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    // ========== 工具方法 ==========
    private int getHeight(Node n) { return n == null ? 0 : n.height; }
    private void updateHeight(Node n) { 
        n.height = Math.max(getHeight(n.left), getHeight(n.right)) + 1; 
    }
    private int getBalance(Node n) { 
        return n == null ? 0 : getHeight(n.left) - getHeight(n.right); 
    }
    
    private Node leftRotate(Node x) {
        Node y = x.right, T2 = y.left;
        y.left = x; x.right = T2;
        updateHeight(x); updateHeight(y); 
        return y;
    }
    
    private Node rightRotate(Node y) {
        Node x = y.left, T2 = x.right;
        x.right = y; y.left = T2;
        updateHeight(y); updateHeight(x); 
        return x;
    }

    // ========== 輔助方法 ==========
    public void printInOrder() { 
        printInOrder(root); 
        System.out.println(); 
    }
    
    private void printInOrder(Node n) {
        if (n != null) {
            printInOrder(n.left);
            System.out.print(n.data + " ");
            printInOrder(n.right);
        }
    }

    // ========== 測試主程式 ==========
    public static void main(String[] args) {
        AVLRangeQueryExercise tree = new AVLRangeQueryExercise();
        
        // 建立測試樹
        int[] values = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45};
        for (int val : values) {
            tree.insert(val);
        }
        
        System.out.print("完整樹的中序遍歷: ");
        tree.printInOrder();
        
        // 測試範圍查詢
        System.out.println("\n=== 範圍查詢測試 ===");
        
        // 測試案例 1
        List<Integer> result1 = tree.rangeQuery(25, 45);
        System.out.println("範圍 [25, 45]: " + result1);
        
        // 測試案例 2
        List<Integer> result2 = tree.rangeQuery(15, 35);
        System.out.println("範圍 [15, 35]: " + result2);
        
        // 測試案例 3
        List<Integer> result3 = tree.rangeQuery(60, 90);
        System.out.println("範圍 [60, 90]: " + result3);
        
        // 測試邊界情況
        List<Integer> result4 = tree.rangeQuery(100, 200);
        System.out.println("範圍 [100, 200] (無元素): " + result4);
        
        // 測試單點查詢
        List<Integer> result5 = tree.rangeQuery(50, 50);
        System.out.println("範圍 [50, 50] (單點): " + result5);
        
        System.out.println("\n時間複雜度: O(log n + k)");
        System.out.println("其中 n 為樹中節點總數，k 為範圍內元素個數");
    }
}
