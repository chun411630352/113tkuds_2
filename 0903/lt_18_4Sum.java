import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) return res;  // 特殊情況檢查
        
        Arrays.sort(nums);  // 排序，有利於後續雙指標尋找與去重
        
        int n = nums.length;
        // 第一層迴圈，選擇第一個數
        for (int i = 0; i < n - 3; i++) {
            // 跳過重複的第一個數
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            // 第二層迴圈，選擇第二個數
            for (int j = i + 1; j < n - 2; j++) {
                // 跳過重複的第二個數
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                
                int left = j + 1;       // 左指標，指向第三個數
                int right = n - 1;      // 右指標，指向第四個數
                
                // 使用雙指標在剩餘區間尋找符合條件的兩數
                while (left < right) {
                    // 利用long避免整數溢位
                    long sum = (long)nums[i] + nums[j] + nums[left] + nums[right];
                    
                    if (sum == target) {
                        // 找到一組符合條件的四元組，加入結果
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        
                        // 跳過重複的第三個數
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        // 跳過重複的第四個數
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;   // 總和小於目標，左指標右移增加sum
                    } else {
                        right--;  // 總和大於目標，右指標左移減少sum
                    }
                }
            }
        }
        
        return res;
    }
}
