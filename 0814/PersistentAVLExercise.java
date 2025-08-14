import java.util.*;

public class PersistentAVLExercise {
    // 不可變節點類
    static class ImmutableNode {
        final int data;
        final ImmutableNode left, right;
        final int height;
        final int size;
        
        ImmutableNode(int data, ImmutableNode left, ImmutableNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.height = Math.max(getHeight(left), getHeight(right)) + 1;
            this.size = getSize(left) + getSize(right) + 1;
        }
        
        ImmutableNode(int data) {
            this(data, null, null);
        }
        
        private static int getHeight(ImmutableNode node) {
            return node == null ? 0 : node.height;
        }
        
        private static int getSize(ImmutableNode node) {
            return node == null ? 0 : node.size;
        }
        
        int getBalance() {
            return getHeight(left) - getHeight(right);
        }
    }
    
    // 版本管理類
    static class Version {
        final ImmutableNode root;
        final int versionNumber;
        final String operation;
        
        Version(ImmutableNode root, int versionNumber, String operation) {
            this.root = root;
            this.versionNumber = versionNumber;
            this.operation = operation;
        }
    }
    
    private List<Version> versions;
    private int currentVersion;
    
    public PersistentAVLExercise() {
        versions = new ArrayList<>();
        versions.add(new Version(null, 0, "初始版本"));
        currentVersion = 0;
    }
    
    // ========== 插入操作（產生新版本）==========
    public int insert(int data) {
        ImmutableNode currentRoot = versions.get(currentVersion).root;
        ImmutableNode newRoot = insert(currentRoot, data);
        
        currentVersion++;
        versions.add(new Version(newRoot, currentVersion, "插入 " + data));
        
        return currentVersion;
    }
    
    private ImmutableNode insert(ImmutableNode node, int data) {
        if (node == null) {
            return new ImmutableNode(data);
        }
        
        if (data < node.data) {
            // 路徑複製：創建新節點，左子樹為新插入結果，右子樹共享原節點
            return rebalance(new ImmutableNode(node.data, 
                insert(node.left, data), node.right));
        } else if (data > node.data) {
            // 路徑複製：創建新節點，右子樹為新插入結果，左子樹共享原節點
            return rebalance(new ImmutableNode(node.data, 
                node.left, insert(node.right, data)));
        } else {
            // 數據已存在，返回原節點（結構共享）
            return node;
        }
    }
    
    // ========== 刪除操作（產生新版本）==========
    public int delete(int data) {
        ImmutableNode currentRoot = versions.get(currentVersion).root;
        ImmutableNode newRoot = delete(currentRoot, data);
        
        currentVersion++;
        versions.add(new Version(newRoot, currentVersion, "刪除 " + data));
        
        return currentVersion;
    }
    
    private ImmutableNode delete(ImmutableNode node, int data) {
        if (node == null) return null;
        
        if (data < node.data) {
            return rebalance(new ImmutableNode(node.data, 
                delete(node.left, data), node.right));
        } else if (data > node.data) {
            return rebalance(new ImmutableNode(node.data, 
                node.left, delete(node.right, data)));
        } else {
            // 找到要刪除的節點
            if (node.left == null || node.right == null) {
                return (node.left != null) ? node.left : node.right;
            } else {
                // 找右子樹最小值作為替代
                ImmutableNode successor = findMin(node.right);
                return rebalance(new ImmutableNode(successor.data, 
                    node.left, delete(node.right, successor.data)));
            }
        }
    }
    
    // ========== AVL 平衡操作 ==========
    private ImmutableNode rebalance(ImmutableNode node) {
        int balance = node.getBalance();
        
        // LL 情況
        if (balance > 1 && node.left.getBalance() >= 0) {
            return rightRotate(node);
        }
        // LR 情況
        if (balance > 1 && node.left.getBalance() < 0) {
            return rightRotate(new ImmutableNode(node.data, 
                leftRotate(node.left), node.right));
        }
        // RR 情況
        if (balance < -1 && node.right.getBalance() <= 0) {
            return leftRotate(node);
        }
        // RL 情況
        if (balance < -1 && node.right.getBalance() > 0) {
            return leftRotate(new ImmutableNode(node.data, 
                node.left, rightRotate(node.right)));
        }
        
        return node;
    }
    
    private ImmutableNode leftRotate(ImmutableNode x) {
        ImmutableNode y = x.right;
        return new ImmutableNode(y.data, 
            new ImmutableNode(x.data, x.left, y.left), y.right);
    }
    
    private ImmutableNode rightRotate(ImmutableNode y) {
        ImmutableNode x = y.left;
        return new ImmutableNode(x.data, 
            x.left, new ImmutableNode(y.data, x.right, y.right));
    }
    
    // ========== 查詢操作 ==========
    public boolean search(int data) {
        return search(versions.get(currentVersion).root, data);
    }
    
    public boolean searchInVersion(int versionNumber, int data) {
        if (versionNumber < 0 || versionNumber >= versions.size()) {
            return false;
        }
        return search(versions.get(versionNumber).root, data);
    }
    
    private boolean search(ImmutableNode node, int data) {
        if (node == null) return false;
        if (data == node.data) return true;
        if (data < node.data) return search(node.left, data);
        return search(node.right, data);
    }
    
    // ========== 版本管理 ==========
    public void switchToVersion(int versionNumber) {
        if (versionNumber >= 0 && versionNumber < versions.size()) {
            currentVersion = versionNumber;
            System.out.println("切換到版本 " + versionNumber);
        } else {
            System.out.println("無效的版本號: " + versionNumber);
        }
    }
    
    public void printVersionHistory() {
        System.out.println("=== 版本歷史 ===");
        for (Version v : versions) {
            String current = (v.versionNumber == currentVersion) ? " <- 當前" : "";
            System.out.println("版本 " + v.versionNumber + ": " + v.operation + current);
        }
    }
    
    public void printCurrentVersion() {
        System.out.print("版本 " + currentVersion + " 中序遍歷: ");
        printInOrder(versions.get(currentVersion).root);
        System.out.println();
    }
    
    public void printVersion(int versionNumber) {
        if (versionNumber >= 0 && versionNumber < versions.size()) {
            System.out.print("版本 " + versionNumber + " 中序遍歷: ");
            printInOrder(versions.get(versionNumber).root);
            System.out.println();
        }
    }
    
    private void printInOrder(ImmutableNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.data + " ");
            printInOrder(node.right);
        }
    }
    
    // ========== 輔助方法 ==========
    private ImmutableNode findMin(ImmutableNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    public int getVersionCount() {
        return versions.size();
    }
    
    public int getCurrentVersion() {
        return currentVersion;
    }
    
    // ========== 分析方法 ==========
    public void analyzeSpaceUsage() {
        System.out.println("=== 空間使用分析 ===");
        Set<ImmutableNode> allNodes = new HashSet<>();
        
        for (Version v : versions) {
            collectNodes(v.root, allNodes);
        }
        
        System.out.println("總版本數: " + versions.size());
        System.out.println("唯一節點數: " + allNodes.size());
        System.out.println("結構共享效果: " + 
            String.format("%.2f%%", (1.0 - (double)allNodes.size() / 
            (versions.size() * getMaxNodesInVersion())) * 100));
    }
    
    private void collectNodes(ImmutableNode node, Set<ImmutableNode> nodes) {
        if (node != null) {
            nodes.add(node);
            collectNodes(node.left, nodes);
            collectNodes(node.right, nodes);
        }
    }
    
    private int getMaxNodesInVersion() {
        int max = 0;
        for (Version v : versions) {
            if (v.root != null) {
                max = Math.max(max, v.root.size);
            }
        }
        return max;
    }
    
    // ========== 測試主程式 ==========
    public static void main(String[] args) {
        PersistentAVLExercise tree = new PersistentAVLExercise();
        
        System.out.println("=== 持久化 AVL 樹演示 ===");
        
        // 創建多個版本
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);
        
        tree.printVersionHistory();
        tree.printCurrentVersion();
        
        System.out.println("\n=== 查詢不同版本 ===");
        tree.printVersion(0);  // 空樹
        tree.printVersion(2);  // 10, 5
        tree.printVersion(5);  // 完整樹
        
        System.out.println("\n=== 刪除操作 ===");
        tree.delete(5);
        tree.delete(10);
        
        tree.printVersionHistory();
        tree.printCurrentVersion();
        
        System.out.println("\n=== 版本切換測試 ===");
        tree.switchToVersion(3);
        tree.printCurrentVersion();
        
        System.out.println("在版本3中搜尋7: " + tree.search(7));
        System.out.println("在版本5中搜尋7: " + tree.searchInVersion(5, 7));
        System.out.println("在版本7中搜尋7: " + tree.searchInVersion(7, 7));
        
        System.out.println();
        tree.analyzeSpaceUsage();
    }
}
