import java.util.Scanner;

public class LC26_RemoveDuplicates_Scores {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] scores = new int[n];
        for (int i = 0; i < n; i++) {
            scores[i] = sc.nextInt();
        }
        sc.close();

        int newLength = removeDuplicates(scores);
        System.out.println(newLength);
        for (int i = 0; i < newLength; i++) {
            System.out.print(scores[i] + (i == newLength -1 ? "\n" : " "));
        }
    }

    // 就地去除重複元素，返回新長度
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int write = 1; // 寫入指標，指向下一個可放置的位置
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[write] = nums[i];
                write++;
            }
        }
        return write;
    }
}

