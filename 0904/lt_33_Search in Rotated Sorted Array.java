class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid; // 找到目標直接返回索引
            }
            
            // 判斷左半區是否有序
            if (nums[left] <= nums[mid]) {
                // 目標是否位於左半有序區間
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // 縮小搜尋範圍為左半區
                } else {
                    left = mid + 1;  // 目標位於右半區
                }
            } else {
                // 右半區有序
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;  // 目標位於右半區
                } else {
                    right = mid - 1; // 目標位於左半區
                }
            }
        }
        
        return -1; // 未找到返回-1
    }
}
