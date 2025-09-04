import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);  // 對陣列排序，方便使用雙指標
        
        // 初始化最近和為前三個數字的和
        int closestSum = nums[0] + nums[1] + nums[2];
        
        // 遍歷陣列每個元素作為第一個數字
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;             // 第二個數字的起始指標
            int right = nums.length - 1;  // 第三個數字的起始指標
            
            // 使用雙指標移動尋找最接近目標的和
            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                
                // 若目前和比之前更接近目標，更新最近和
                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }
                
                // 根據目前和與目標值比較，調整指標
                if (currentSum < target) {
                    left++;   // 和過小，左指標右移增大總和
                } else if (currentSum > target) {
                    right--;  // 和過大，右指標左移減小總和
                } else {
                    // 當和正好等於目標，直接回傳結果
                    return currentSum;
                }
            }
        }
        
        return closestSum;  // 回傳最接近目標的三數和
    }
}
