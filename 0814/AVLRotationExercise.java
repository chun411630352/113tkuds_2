public class AVLRotationExercise {
    // 基本結點類
    static class Node {
        int data;
        Node left, right;
        int height = 1;
        Node(int data) { this.data = data; }
    }

    // ====== 旋轉 ======
    // 左旋
    static Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        return y;
    }

    // 右旋
    static Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    // 左右旋
    static Node leftRightRotate(Node node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    // 右左旋
    static Node rightLeftRotate(Node node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    // ====== 小工具 ======
    static int getHeight(Node node) {
        return (node == null) ? 0 : node.height;
    }

    static int getBalance(Node node) {
        return (node == null) ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    // ====== 輸出 ======
    static void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.data + "(" + getBalance(node) + ") ");
            printInOrder(node.right);
        }
    }

    // ====== 測試 ======
    public static void main(String[] args) {
        // ----- 測試左旋 RR -----
        Node root = new Node(10);
        root.right = new Node(20);
        root.right.right = new Node(30);
        updateAllHeight(root);
        System.out.println("左旋(插入序列10,20,30產生RR):");
        printInOrder(root);
        System.out.println();
        root = leftRotate(root);
        printInOrder(root); // 20(0) 10(0) 30(0)
        System.out.println("\n");

        // ----- 測試右旋 LL -----
        root = new Node(30);
        root.left = new Node(20);
        root.left.left = new Node(10);
        updateAllHeight(root);
        System.out.println("右旋(插入序列30,20,10產生LL):");
        printInOrder(root);
        System.out.println();
        root = rightRotate(root);
        printInOrder(root); // 20(0) 10(0) 30(0)
        System.out.println("\n");

        // ----- 測試左右旋 LR -----
        root = new Node(30);
        root.left = new Node(10);
        root.left.right = new Node(20);
        updateAllHeight(root);
        System.out.println("左右旋(插入序列30,10,20產生LR):");
        printInOrder(root);
        System.out.println();
        root = leftRightRotate(root);
        printInOrder(root); // 20(0) 10(0) 30(0)
        System.out.println("\n");

        // ----- 測試右左旋 RL -----
        root = new Node(10);
        root.right = new Node(30);
        root.right.left = new Node(20);
        updateAllHeight(root);
        System.out.println("右左旋(插入序列10,30,20產生RL):");
        printInOrder(root);
        System.out.println();
        root = rightLeftRotate(root);
        printInOrder(root); // 20(0) 10(0) 30(0)
        System.out.println();
    }

    // 補高度(僅用於手動建樹測試)
    static void updateAllHeight(Node node) {
        if (node == null) return;
        updateAllHeight(node.left);
        updateAllHeight(node.right);
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }
}
