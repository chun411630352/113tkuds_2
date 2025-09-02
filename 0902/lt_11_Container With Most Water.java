class Solution {
    public int maxArea(int[] height) {
        // 左指標初始位置在陣列起點
        int left = 0;
        // 右指標初始位置在陣列終點
        int right = height.length - 1;
        // 用來記錄目前找到的最大面積
        int maxArea = 0;
        
        // 當左指標小於右指標時持續進行
        while (left < right) {
            // 計算兩指標間的寬度
            int width = right - left;
            // 容器高度為左右指標高度中較小者
            int currentHeight = Math.min(height[left], height[right]);
            // 計算當前容器面積
            int currentArea = width * currentHeight;
            // 更新最大面積
            maxArea = Math.max(maxArea, currentArea);
            
            // 移動較短邊的指標，嘗試尋找更高的線以增大面積
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        // 回傳找到的最大容積面積
        return maxArea;
    }
}
