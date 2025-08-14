// 節點類別
class AVLNode {
    int key;          // 節點值
    AVLNode left;     // 左子樹
    AVLNode right;    // 右子樹
    int height;       // 節點高度

    public AVLNode(int key) {
        this.key = key;
        this.height = 1; // 新節點的初始高度為 1
    }

    // 更新節點高度
    public void updateHeight() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        this.height = Math.max(leftHeight, rightHeight) + 1;
    }
}

// AVL 旋轉操作類別
public class AVLRotations {

    // 右旋操作
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // 執行旋轉
        x.right = y;
        y.left = T2;

        // 更新高度
        y.updateHeight();
        x.updateHeight();

        return x; // 新的根節點
    }

    // 左旋操作
    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // 執行旋轉
        y.left = x;
        x.right = T2;

        // 更新高度
        x.updateHeight();
        y.updateHeight();

        return y; // 新的根節點
    }

    // 測試用 main
    public static void main(String[] args) {
        // 範例樹
        AVLNode root = new AVLNode(30);
        root.left = new AVLNode(20);
        root.left.left = new AVLNode(10);

        // 執行右旋
        System.out.println("旋轉前根節點: " + root.key);
        root = rightRotate(root);
        System.out.println("右旋後根節點: " + root.key);

        // 執行左旋
        root.right = new AVLNode(40);
        root.right.right = new AVLNode(50);
        System.out.println("旋轉前根節點: " + root.key);
        root = leftRotate(root);
        System.out.println("左旋後根節點: " + root.key);
    }
}
