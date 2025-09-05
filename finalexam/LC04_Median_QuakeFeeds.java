import java.util.Scanner;

public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        double[] A = new double[n];
        double[] B = new double[m];

        for (int i = 0; i < n; i++) {
            A[i] = sc.nextDouble();
        }
        for (int i = 0; i < m; i++) {
            B[i] = sc.nextDouble();
        }
        sc.close();

        double median = findMedianSortedArrays(A, B);
        System.out.printf("%.1f\n", median);
    }

    public static double findMedianSortedArrays(double[] A, double[] B) {
        int n = A.length, m = B.length;

        // 確保 A 是較短的陣列以優化二分
        if (n > m) {
            return findMedianSortedArrays(B, A);
        }

        int left = 0, right = n;
        int halfLen = (n + m + 1) / 2;

        while (left <= right) {
            int i = (left + right) / 2;     // A 切割位置
            int j = halfLen - i;            // B 切割位置

            double Aleft = (i == 0) ? Double.NEGATIVE_INFINITY : A[i - 1];
            double Aright = (i == n) ? Double.POSITIVE_INFINITY : A[i];
            double Bleft = (j == 0) ? Double.NEGATIVE_INFINITY : B[j - 1];
            double Bright = (j == m) ? Double.POSITIVE_INFINITY : B[j];

            if (Aleft <= Bright && Bleft <= Aright) {
                // 找到正確切割點
                if ((n + m) % 2 == 1) {
                    return Math.max(Aleft, Bleft);
                } else {
                    return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                }
            } else if (Aleft > Bright) {
                // A 左邊太大，右移切割點
                right = i - 1;
            } else {
                // A 左邊太小，左移切割點
                left = i + 1;
            }
        }

        // 理論上不可能執行到這
        throw new IllegalArgumentException("Input arrays are not sorted correctly.");
    }
}
