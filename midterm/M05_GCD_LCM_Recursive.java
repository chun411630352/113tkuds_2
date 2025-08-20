import java.util.Scanner;

public class M05_GCD_LCM_Recursive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();

        long gcd = gcd(a, b);
        long lcm = a / gcd * b;  // 先除後乘避免溢位

        System.out.println("GCD: " + gcd);
        System.out.println("LCM: " + lcm);
    }

    static long gcd(long x, long y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }
}

/*
 * 時間複雜度：O(log(min(a,b)))
 * 說明：
 * 1. 使用遞迴歐幾里得算法計算最大公約數，每次遞迴都使問題規模大幅縮減。
 * 2. 遞迴深度與最小數值的對數成正比，故 GCD 計算的時間複雜度為 O(log(min(a,b)))。
 * 3. LCM 計算為常數時間操作。
 * 整體時間複雜度為 O(log(min(a,b)))。
 */
