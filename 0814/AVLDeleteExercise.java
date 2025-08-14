public class AVLDeleteExercise {
    // 節點類
    static class Node {
        int data;
        Node left, right;
        int height = 1;
        Node(int data) { this.data = data; }
    }

    private Node root;

    // ========== 插入 ==========
    public void insert(int val) { root = insert(root, val); }
    private Node insert(Node node, int val) {
        if (node == null) return new Node(val);
        if (val < node.data) node.left = insert(node.left, val);
        else if (val > node.data) node.right = insert(node.right, val);
        else return node; // 不插重複
        updateHeight(node);
        return rebalance(node, val);
    }

    // ========== 刪除 ==========
    public void delete(int val) { root = delete(root, val); }
    private Node delete(Node node, int val) {
        if (node == null) return null;
        if (val < node.data) node.left = delete(node.left, val);
        else if (val > node.data) node.right = delete(node.right, val);
        else {
            //（1）葉子或單小孩
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            }
            else {
                //（2）兩子樹：找右子樹最小節點 (後繼)
                Node succ = min(node.right);
                node.data = succ.data;
                node.right = delete(node.right, succ.data);
            }
        }
        if (node == null) return null;
        updateHeight(node);
        // **刪除後自動平衡**
        return rebalanceAfterDelete(node);
    }

    // ========== AVL 平衡(插入用) ==========
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

    // ========== AVL 平衡(刪除用) ==========
    private Node rebalanceAfterDelete(Node node) {
        int bal = getBalance(node);
        // LL
        if (bal > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);
        // LR
        if (bal > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RR
        if (bal < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);
        // RL
        if (bal < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    // ========== 小工具 ==========
    private Node min(Node n) { while (n.left != null) n = n.left; return n; }
    private int getHeight(Node n) { return n == null ? 0 : n.height; }
    private void updateHeight(Node n) { n.height = Math.max(getHeight(n.left), getHeight(n.right)) + 1; }
    private int getBalance(Node n) { return n == null ? 0 : getHeight(n.left) - getHeight(n.right); }
    private Node leftRotate(Node x) {
        Node y = x.right, T2 = y.left;
        y.left = x; x.right = T2;
        updateHeight(x); updateHeight(y); return y;
    }
    private Node rightRotate(Node y) {
        Node x = y.left, T2 = x.right;
        x.right = y; y.left = T2;
        updateHeight(y); updateHeight(x); return x;
    }

    // ========== 輸出 ==========
    public void printInOrder() { printInOrder(root); System.out.println(); }
    private void printInOrder(Node n) {
        if (n != null) {
            printInOrder(n.left); 
            System.out.print(n.data + "(" + getBalance(n) + ") ");
            printInOrder(n.right);
        }
    }
    
    // ========== 驗證 AVL ==========
    public boolean isValidAVL() { return checkAVL(root) != -1; }
    private int checkAVL(Node n) {
        if (n == null) return 0;
        int lH = checkAVL(n.left), rH = checkAVL(n.right);
        if (lH == -1 || rH == -1) return -1;
        if (Math.abs(lH - rH) > 1) return -1;
        return Math.max(lH, rH) + 1;
    }

    // ========== main測試 ==========
    public static void main(String[] args) {
        AVLDeleteExercise tree = new AVLDeleteExercise();
        int[] vals = {20, 10, 30, 5, 15, 25, 35};
        for (int v : vals) tree.insert(v);

        System.out.print("中序: "); tree.printInOrder();

        System.out.println(">> 刪除葉子節點 5");
        tree.delete(5); tree.printInOrder();

        System.out.println(">> 刪除僅有一個子節點 30");
        tree.delete(30); tree.printInOrder();

        System.out.println(">> 刪除有兩個子節點 20");
        tree.delete(20); tree.printInOrder();

        System.out.println("是否合法AVL: " + tree.isValidAVL());
    }
}
