public class AVLTree {
    // === 內部類: 節點類 ===
    static class AVLNode {
        int data;
        AVLNode left, right;
        int height;

        AVLNode(int data) {
            this.data = data;
            this.height = 1;
        }

        void updateHeight() {
            int lh = (left != null) ? left.height : 0;
            int rh = (right != null) ? right.height : 0;
            this.height = Math.max(lh, rh) + 1;
        }

        int getBalance() {
            int lh = (left != null) ? left.height : 0;
            int rh = (right != null) ? right.height : 0;
            return lh - rh;
        }
    }

    // === 內部類: 旋轉工具 ===
    static class AVLRotations {
        static AVLNode rightRotate(AVLNode y) {
            AVLNode x = y.left;
            AVLNode T2 = (x != null) ? x.right : null;
            x.right = y;
            y.left = T2;

            y.updateHeight();
            x.updateHeight();
            return x;
        }
        static AVLNode leftRotate(AVLNode x) {
            AVLNode y = x.right;
            AVLNode T2 = (y != null) ? y.left : null;
            y.left = x;
            x.right = T2;

            x.updateHeight();
            y.updateHeight();
            return y;
        }
    }

    // === AVLTree主體 ===
    private AVLNode root;

    // 插入
    public void insert(int data) {
        root = insertNode(root, data);
    }
    private AVLNode insertNode(AVLNode node, int data) {
        if (node == null) return new AVLNode(data);

        if (data < node.data)
            node.left = insertNode(node.left, data);
        else if (data > node.data)
            node.right = insertNode(node.right, data);
        else
            return node; // 不允許重複

        node.updateHeight();
        int balance = node.getBalance();

        // LL
        if (balance > 1 && data < node.left.data)
            return AVLRotations.rightRotate(node);
        // RR
        if (balance < -1 && data > node.right.data)
            return AVLRotations.leftRotate(node);
        // LR
        if (balance > 1 && data > node.left.data) {
            node.left = AVLRotations.leftRotate(node.left);
            return AVLRotations.rightRotate(node);
        }
        // RL
        if (balance < -1 && data < node.right.data) {
            node.right = AVLRotations.rightRotate(node.right);
            return AVLRotations.leftRotate(node);
        }
        return node;
    }

    // 搜尋
    public boolean search(int data) {
        return searchNode(root, data);
    }
    private boolean searchNode(AVLNode node, int data) {
        if (node == null) return false;
        if (data == node.data) return true;
        if (data < node.data) return searchNode(node.left, data);
        return searchNode(node.right, data);
    }

    // 找最小
    private AVLNode findMin(AVLNode node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    // 刪除
    public void delete(int data) {
        root = deleteNode(root, data);
    }
    private AVLNode deleteNode(AVLNode node, int data) {
        if (node == null) return null;

        if (data < node.data) {
            node.left = deleteNode(node.left, data);
        } else if (data > node.data) {
            node.right = deleteNode(node.right, data);
        } else {
            if (node.left == null || node.right == null) {
                AVLNode temp = (node.left != null) ? node.left : node.right;
                if (temp == null) {
                    node = null;
                } else {
                    node.data = temp.data;
                    node.left = temp.left;
                    node.right = temp.right;
                    node.height = temp.height;
                }
            } else {
                AVLNode temp = findMin(node.right);
                node.data = temp.data;
                node.right = deleteNode(node.right, temp.data);
            }
        }

        if (node == null) return node;

        node.updateHeight();
        int balance = node.getBalance();

        // LL
        if (balance > 1 && node.left.getBalance() >= 0)
            return AVLRotations.rightRotate(node);
        // LR
        if (balance > 1 && node.left.getBalance() < 0) {
            node.left = AVLRotations.leftRotate(node.left);
            return AVLRotations.rightRotate(node);
        }
        // RR
        if (balance < -1 && node.right.getBalance() <= 0)
            return AVLRotations.leftRotate(node);
        // RL
        if (balance < -1 && node.right.getBalance() > 0) {
            node.right = AVLRotations.rightRotate(node.right);
            return AVLRotations.leftRotate(node);
        }
        return node;
    }

    // 檢查AVL正確性
    public boolean isValidAVL() {
        return checkAVL(root) != -1;
    }
    private int checkAVL(AVLNode node) {
        if (node == null) return 0;
        int l = checkAVL(node.left);
        int r = checkAVL(node.right);
        if (l == -1 || r == -1) return -1;
        if (Math.abs(l - r) > 1) return -1;
        return Math.max(l, r) + 1;
    }

    // 印中序+平衡因子
    public void printTree() {
        printInOrder(root);
        System.out.println();
    }
    private void printInOrder(AVLNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.data + "(" + node.getBalance() + ") ");
            printInOrder(node.right);
        }
    }

    // 主程式示範
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        int[] values = {10, 20, 30, 40, 50, 25};
        for (int v : values) tree.insert(v);

        System.out.print("插入後樹: ");
        tree.printTree();

        System.out.println("搜尋 30: " + tree.search(30));
        System.out.println("搜尋 99: " + tree.search(99));

        tree.delete(40);
        System.out.print("刪除40後樹: ");
        tree.printTree();

        System.out.println("是否合法AVL: " + tree.isValidAVL());
    }
}
