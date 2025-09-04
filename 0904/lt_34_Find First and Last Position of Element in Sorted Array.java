class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums == null || nums.length == 0) return result;

        // 找左邊界
        int leftIndex = binarySearch(nums, target, true);
        if (leftIndex == nums.length || nums[leftIndex] != target) {
            return result; // 目標不存在
        }

        // 找右邊界
        int rightIndex = binarySearch(nums, target, false) - 1;

        result[0] = leftIndex;
        result[1] = rightIndex;
        return result;
    }

    // findFirst == true：找第一個大於等於 target 的索引
    // findFirst == false：找第一個大於 target 的索引
    private int binarySearch(int[] nums, int target, boolean findFirst) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target || (findFirst && nums[mid] == target)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
