import java.util.HashMap;
import java.util.Scanner;

public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] seats = new int[n];
        for (int i = 0; i < n; i++) {
            seats[i] = sc.nextInt();
        }
        sc.close();

        int[] res = twoSum(seats, target);
        System.out.println(res[0] + " " + res[1]);
    }

    public static int[] twoSum(int[] seats, int target) {
        // key: 需要的座位數 (target - 座位數)
        // value: 座位數索引
        HashMap<Integer, Integer> needMap = new HashMap<>();

        for (int i = 0; i < seats.length; i++) {
            int seat = seats[i];
            if (needMap.containsKey(seat)) {
                // 找到之前需求 target - seat 的索引，現在 seat 補足目標
                return new int[]{needMap.get(seat), i};
            } else {
                // 記錄還需要的座位數，等別的班次補齊
                needMap.put(target - seat, i);
            }
        }
        // 找不到符合組合，返回 -1 -1
        return new int[]{-1, -1};
    }
}
