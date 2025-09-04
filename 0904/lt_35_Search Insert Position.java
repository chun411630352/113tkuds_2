class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        // 二分搜尋
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;  // 找到目標回傳索引
            } else if (nums[mid] < target) {
                left = mid + 1;  //目標在右半區
            } else {
                right = mid - 1; //目標在左半區
            }
        }
        
        // 若目標不存在，left 為插入位置
        return left;
    }
}
