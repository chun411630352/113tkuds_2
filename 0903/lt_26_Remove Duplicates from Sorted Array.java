class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0; // 空陣列直接回傳0
        
        int uniquePos = 0; // 指向唯一元素最後位置
        
        // 從第二個元素開始遍歷
        for (int i = 1; i < nums.length; i++) {
            // 若遇到新元素，將 uniquePos 往前移並更新該位置元素
            if (nums[i] != nums[uniquePos]) {
                uniquePos++;
                nums[uniquePos] = nums[i];
            }
        }
        
        // 回傳唯一元素長度
        return uniquePos + 1;
    }
}
