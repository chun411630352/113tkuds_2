import java.util.*;

public class M10_RBPropertiesCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] vals = new int[n];
        char[] colors = new char[n];

        for (int i = 0; i < n; i++) {
            vals[i] = sc.nextInt();
            String c = sc.next();
            // 空節點 (-1) 視為黑色
            colors[i] = (vals[i] == -1) ? 'B' : c.charAt(0);
        }

        // 1. 根節點為黑
        if (n > 0 && colors[0] != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        // 2. 不得有相鄰紅節點 (紅紅相鄰)
        for (int i = 0; i < n; i++) {
            if (vals[i] == -1) continue; // 空節點跳過
            if (colors[i] == 'R') {
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                if (left < n && colors[left] == 'R' && vals[left] != -1) {
                    System.out.println("RedRedViolation at index " + left);
                    return;
                }
                if (right < n && colors[right] == 'R' && vals[right] != -1) {
                    System.out.println("RedRedViolation at index " + right);
                    return;
                }
            }
        }

        // 3. 黑高度檢查
        int blackHeight = checkBlackHeight(vals, colors, 0);
        if (blackHeight == -1) {
            System.out.println("BlackHeightMismatch");
            return;
        }

        System.out.println("RB Valid");
    }

    static int checkBlackHeight(int[] vals, char[] colors, int i) {
        if (i >= vals.length || vals[i] == -1) return 1; // 空節點黑高為1

        int leftBlackHeight = checkBlackHeight(vals, colors, 2 * i + 1);
        if (leftBlackHeight == -1) return -1;

        int rightBlackHeight = checkBlackHeight(vals, colors, 2 * i + 2);
        if (rightBlackHeight == -1) return -1;

        if (leftBlackHeight != rightBlackHeight) return -1;

        int add = (colors[i] == 'B') ? 1 : 0;
        return leftBlackHeight + add;
    }
}

