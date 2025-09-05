import java.util.Scanner;

public class LC19_RemoveNth_Node_Clinic {
    // 鏈結節點定義
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int i = 0; i < n; i++) {
            curr.next = new ListNode(sc.nextInt());
            curr = curr.next;
        }
        int k = sc.nextInt();
        sc.close();

        ListNode head = removeNthFromEnd(dummy.next, k);
        printList(head);
    }

    // 刪除倒數第 k 個節點
    public static ListNode removeNthFromEnd(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        // fast 指標先走 k 步
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        // fast 與 slow 同步向前走，直到 fast 到尾
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // slow.next 即為要刪除節點
        slow.next = slow.next.next;

        return dummy.next;
    }

    // 輸出鏈表所有節點值
    public static void printList(ListNode head) {
        ListNode curr = head;
        StringBuilder sb = new StringBuilder();
        while (curr != null) {
            sb.append(curr.val).append(' ');
            curr = curr.next;
        }
        System.out.print(sb.toString().trim());
    }
}
