class Solution {
    public int removeElement(int[] nums, int val) {
        int insertPos = 0; // 指向下一個非 val 元素應該放置的位置
        
        // 遍歷陣列中所有元素
        for (int num : nums) {
            // 若元素不等於 val，將其放到 insertPos 位置，並將 insertPos 往前移
            if (num != val) {
                nums[insertPos] = num;
                insertPos++;
            }
        }
        
        // 回傳陣列中非 val 元素的數量
        return insertPos;
    }
}
