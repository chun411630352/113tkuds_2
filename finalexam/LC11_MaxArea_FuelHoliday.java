import java.util.Scanner;

public class LC11_MaxArea_FuelHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextInt();
        }
        sc.close();

        System.out.println(maxArea(heights));
    }

    public static long maxArea(int[] heights) {
        int left = 0, right = heights.length - 1;
        long maxArea = 0;

        while (left < right) {
            int heightLeft = heights[left];
            int heightRight = heights[right];
            int minHeight = Math.min(heightLeft, heightRight);
            long area = (long) minHeight * (right - left);
            if (area > maxArea) {
                maxArea = area;
            }
            // 移動較短邊，尋求可能更大面積
            if (heightLeft < heightRight) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}

