import java.util.*;

public class AVLLeaderboardSystem {
    // 玩家節點類
    static class PlayerNode {
        String playerName;
        int score;
        PlayerNode left, right;
        int height = 1;
        int size = 1; // 子樹大小（包含自己）
        
        PlayerNode(String name, int score) {
            this.playerName = name;
            this.score = score;
        }
    }
    
    private PlayerNode root;
    
    // ========== 添加/更新玩家分數 ==========
    public void addOrUpdatePlayer(String playerName, int score) {
        // 先刪除舊記錄（如果存在）
        root = deletePlayer(root, playerName);
        // 插入新記錄
        root = insert(root, playerName, score);
    }
    
    private PlayerNode insert(PlayerNode node, String playerName, int score) {
        if (node == null) {
            return new PlayerNode(playerName, score);
        }
        
        // 按分數降序排列，分數相同按名稱字典序
        if (score > node.score || (score == node.score && playerName.compareTo(node.playerName) < 0)) {
            node.left = insert(node.left, playerName, score);
        } else {
            node.right = insert(node.right, playerName, score);
        }
        
        updateNode(node);
        return rebalance(node);
    }
    
    private PlayerNode deletePlayer(PlayerNode node, String playerName) {
        if (node == null) return null;
        
        if (playerName.equals(node.playerName)) {
            // 找到要刪除的節點
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                // 找右子樹最小節點作為替代
                PlayerNode successor = findMin(node.right);
                node.playerName = successor.playerName;
                node.score = successor.score;
                node.right = deletePlayer(node.right, successor.playerName);
            }
        } else {
            // 繼續搜尋
            node.left = deletePlayer(node.left, playerName);
            node.right = deletePlayer(node.right, playerName);
        }
        
        if (node != null) {
            updateNode(node);
            node = rebalance(node);
        }
        return node;
    }
    
    // ========== 查詢玩家排名 ==========
    public int getPlayerRank(String playerName) {
        return getRank(root, playerName);
    }
    
    private int getRank(PlayerNode node, String playerName) {
        if (node == null) return -1; // 玩家不存在
        
        if (playerName.equals(node.playerName)) {
            return getSize(node.left) + 1; // 左子樹大小 + 1
        }
        
        // 判斷目標玩家的分數和位置
        PlayerNode target = findPlayer(root, playerName);
        if (target == null) return -1;
        
        if (target.score > node.score || 
            (target.score == node.score && target.playerName.compareTo(node.playerName) < 0)) {
            // 在左子樹
            return getRank(node.left, playerName);
        } else {
            // 在右子樹
            int rightRank = getRank(node.right, playerName);
            return rightRank == -1 ? -1 : getSize(node.left) + 1 + rightRank;
        }
    }
    
    // ========== 查詢第 K 名玩家 ==========
    public String getKthPlayer(int k) {
        PlayerNode result = select(root, k);
        return result != null ? result.playerName + "(" + result.score + ")" : null;
    }
    
    private PlayerNode select(PlayerNode node, int k) {
        if (node == null) return null;
        
        int leftSize = getSize(node.left);
        
        if (k == leftSize + 1) {
            return node;
        } else if (k <= leftSize) {
            return select(node.left, k);
        } else {
            return select(node.right, k - leftSize - 1);
        }
    }
    
    // ========== 查詢前 K 名玩家 ==========
    public List<String> getTopKPlayers(int k) {
        List<String> result = new ArrayList<>();
        getTopK(root, k, result);
        return result;
    }
    
    private void getTopK(PlayerNode node, int k, List<String> result) {
        if (node == null || result.size() >= k) return;
        
        // 中序遍歷：左 -> 根 -> 右（分數從高到低）
        getTopK(node.left, k, result);
        
        if (result.size() < k) {
            result.add(node.playerName + "(" + node.score + ")");
        }
        
        getTopK(node.right, k, result);
    }
    
    // ========== 輔助方法 ==========
    private PlayerNode findPlayer(PlayerNode node, String playerName) {
        if (node == null) return null;
        if (playerName.equals(node.playerName)) return node;
        
        PlayerNode left = findPlayer(node.left, playerName);
        return left != null ? left : findPlayer(node.right, playerName);
    }
    
    private PlayerNode findMin(PlayerNode node) {
        while (node.left != null) node = node.left;
        return node;
    }
    
    private void updateNode(PlayerNode node) {
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        node.size = getSize(node.left) + getSize(node.right) + 1;
    }
    
    private int getHeight(PlayerNode node) { return node == null ? 0 : node.height; }
    private int getSize(PlayerNode node) { return node == null ? 0 : node.size; }
    private int getBalance(PlayerNode node) { 
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right); 
    }
    
    private PlayerNode rebalance(PlayerNode node) {
        int balance = getBalance(node);
        if (balance > 1 && getBalance(node.left) >= 0) return rightRotate(node);
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0) return leftRotate(node);
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }
    
    private PlayerNode leftRotate(PlayerNode x) {
        PlayerNode y = x.right, T2 = y.left;
        y.left = x; x.right = T2;
        updateNode(x); updateNode(y);
        return y;
    }
    
    private PlayerNode rightRotate(PlayerNode y) {
        PlayerNode x = y.left, T2 = x.right;
        x.right = y; y.left = T2;
        updateNode(y); updateNode(x);
        return x;
    }
    
    // ========== 顯示排行榜 ==========
    public void printLeaderboard() {
        System.out.println("=== 完整排行榜 ===");
        printInOrder(root, new int[]{1});
    }
    
    private void printInOrder(PlayerNode node, int[] rank) {
        if (node != null) {
            printInOrder(node.left, rank);
            System.out.println(rank[0] + ". " + node.playerName + ": " + node.score);
          
            printInOrder(node.right, rank);
        }
    }
    
    // ========== 測試主程式 ==========
    public static void main(String[] args) {
        AVLLeaderboardSystem leaderboard = new AVLLeaderboardSystem();
        
        System.out.println("=== 添加玩家分數 ===");
        leaderboard.addOrUpdatePlayer("Alice", 1200);
        leaderboard.addOrUpdatePlayer("Bob", 1500);
        leaderboard.addOrUpdatePlayer("Charlie", 1000);
        leaderboard.addOrUpdatePlayer("David", 1800);
        leaderboard.addOrUpdatePlayer("Eve", 1350);
        
        leaderboard.printLeaderboard();
        
        System.out.println("\n=== 查詢排名 ===");
        System.out.println("Alice 排名: " + leaderboard.getPlayerRank("Alice"));
        System.out.println("Bob 排名: " + leaderboard.getPlayerRank("Bob"));
        System.out.println("David 排名: " + leaderboard.getPlayerRank("David"));
        
        System.out.println("\n=== 查詢第 K 名 ===");
        System.out.println("第1名: " + leaderboard.getKthPlayer(1));
        System.out.println("第3名: " + leaderboard.getKthPlayer(3));
        
        System.out.println("\n=== 前3名玩家 ===");
        System.out.println(leaderboard.getTopKPlayers(3));
        
        System.out.println("\n=== 更新 Alice 分數為 2000 ===");
        leaderboard.addOrUpdatePlayer("Alice", 2000);
        leaderboard.printLeaderboard();
        
        System.out.println("\nAlice 新排名: " + leaderboard.getPlayerRank("Alice"));
        System.out.println("前3名: " + leaderboard.getTopKPlayers(3));
    }
}
