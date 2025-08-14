public class AVLBasicExercise {
    // 節點類別（內部靜態類）
    static class Node {
        int data;
        Node left, right;
        int height;
        Node(int data) {
            this.data = data;
            this.height = 1;
        }
    }

    private Node root;

    // ====== 插入 ======
    public void insert(int val) {
        root = insert(root, val);
    }
    private Node insert(Node node, int val) {
        if (node == null) return new Node(val);
        if (val < node.data) node.left = insert(node.left, val);
        else if (val > node.data) node.right = insert(node.right, val);
        else return node; // 不插重複

        updateHeight(node);
        int balance = getBalance(node);
        // LL
        if (balance > 1 && val < node.left.data)
            return rightRotate(node);
        // RR
        if (balance < -1 && val > node.right.data)
            return leftRotate(node);
        // LR
        if (balance > 1 && val > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if (balance < -1 && val < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    // ====== 搜尋 ======
    public boolean search(int val) { return search(root, val); }
    private boolean search(Node node, int val) {
        if (node == null) return false;
        if (val == node.data) return true;
        if (val < node.data) return search(node.left, val);
        return search(node.right, val);
    }

    // ====== 計算高度 ======
    public int height() { return getHeight(root); }
    private int getHeight(Node node) {
        return (node == null) ? 0 : node.height;
    }

    // ====== 是否有效AVL ======
    public boolean isValidAVL() { return checkAVL(root) != -1; }
    // 檢查是否每個子樹平衡
    private int checkAVL(Node node) {
        if (node == null) return 0;
        int leftH = checkAVL(node.left);
        int rightH = checkAVL(node.right);
        if (leftH == -1 || rightH == -1) return -1;
        if (Math.abs(leftH - rightH) > 1) return -1;
        return Math.max(leftH, rightH) + 1;
    }

    // ====== 必要的小工具 ======
    private int getBalance(Node node) {
        return (node == null) ? 0 : getHeight(node.left) - getHeight(node.right);
    }
    private void updateHeight(Node node) {
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }
    private Node leftRotate(Node x) {
        Node y = x.right, T2 = y.left;
        y.left = x;
        x.right = T2;
        updateHeight(x); updateHeight(y);
        return y;
    }
    private Node rightRotate(Node y) {
        Node x = y.left, T2 = x.right;
        x.right = y;
        y.left = T2;
        updateHeight(y); updateHeight(x);
        return x;
    }

    // ====== 印tree（中序）方便測試 ======
    public void printInOrder() {
        printInOrder(root); System.out.println();
    }
    private void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.data + " ");
            printInOrder(node.right);
        }
    }
    // ====== main 測試 ======
    public static void main(String[] args) {
        AVLBasicExercise tree = new AVLBasicExercise();
        int[] vals = {10, 20, 30, 40, 50, 25};
        for (int v : vals) tree.insert(v);

        System.out.print("中序: "); tree.printInOrder();
        System.out.println("搜尋 25: " + tree.search(25));
        System.out.println("搜尋 60: " + tree.search(60));
        System.out.println("樹高: " + tree.height());
        System.out.println("是否合法AVL: " + tree.isValidAVL());
    }
}
