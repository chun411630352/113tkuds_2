import java.util.Scanner;

public class LC34_SearchRange_DelaySpan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        sc.close();

        int[] res = searchRange(nums, target);
        System.out.println(res[0] + " " + res[1]);
    }

    // 找出 target 在陣列中起始與結束位置
    public static int[] searchRange(int[] nums, int target) {
        int left = lowerBound(nums, target);
        int right = lowerBound(nums, target + 1) - 1;

        // 確認區間有效且元素為 target
        if (left <= right && right < nums.length && nums[left] == target && nums[right] == target) {
            return new int[] {left, right};
        }
        return new int[] {-1, -1};
    }

    // 二分尋找最左邊等於或大於 target 的索引
    private static int lowerBound(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
