public class AVLNode {
    int data;
    AVLNode left, right;
    int height;
    
    public AVLNode(int data) {
        this.data = data;
        this.height = 1;
    }
    
    // 計算平衡因子
    public int getBalance() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        return leftHeight - rightHeight;
    }
    
    // 更新節點高度
    public void updateHeight() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        this.height = Math.max(leftHeight, rightHeight) + 1;
    }

    // ==== AVL 操作 ====  
    // 左旋
    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = (y != null) ? y.left : null;

        y.left = x;
        x.right = T2;

        x.updateHeight();
        y.updateHeight();

        return y;
    }

    // 右旋
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = (x != null) ? x.right : null;

        x.right = y;
        y.left = T2;

        y.updateHeight();
        x.updateHeight();

        return x;
    }

    // 插入
    public static AVLNode insert(AVLNode node, int key) {
        if (node == null) return new AVLNode(key);

        if (key < node.data)
            node.left = insert(node.left, key);
        else if (key > node.data)
            node.right = insert(node.right, key);
        else // 不插重複
            return node;

        node.updateHeight();
        int balance = node.getBalance();

        // LL
        if (balance > 1 && key < node.left.data)
            return rightRotate(node);
        // RR
        if (balance < -1 && key > node.right.data)
            return leftRotate(node);
        // LR
        if (balance > 1 && key > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if (balance < -1 && key < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    // 搜尋
    public static boolean search(AVLNode node, int key) {
        if (node == null) return false;
        if (key == node.data) return true;
        if (key < node.data) return search(node.left, key);
        return search(node.right, key);
    }

    // 中序
    public static void inorder(AVLNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data + " ");
            inorder(node.right);
        }
    }

    // ====== main ======
    public static void main(String[] args) {
        AVLNode root = null;
        int[] vals = {10, 20, 30, 40, 50, 25};
        for (int v : vals) {
            root = insert(root, v);
        }

        System.out.print("中序遍歷: ");
        inorder(root);
        System.out.println();

        System.out.println("搜尋 30 是否存在: " + (search(root, 30) ? "是" : "否"));
        System.out.println("搜尋 99 是否存在: " + (search(root, 99) ? "是" : "否"));
    }
}
