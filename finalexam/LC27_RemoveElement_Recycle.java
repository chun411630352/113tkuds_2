import java.util.Scanner;

public class LC27_RemoveElement_Recycle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int val = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        sc.close();

        int newLength = removeElement(nums, val);

        System.out.println(newLength);
        for (int i = 0; i < newLength; i++) {
            System.out.print(nums[i] + (i == newLength - 1 ? "\n" : " "));
        }
    }

    // 單指針覆寫所有不等於 val 的元素
    public static int removeElement(int[] nums, int val) {
        int write = 0;
        for (int x : nums) {
            if (x != val) {
                nums[write++] = x;
            }
        }
        return write;
    }
}
