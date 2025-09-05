import java.util.Scanner;

public class LC21_MergeTwoLists_Clinics {
    // 鏈結節點定義
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        ListNode head1 = buildList(sc, n);
        ListNode head2 = buildList(sc, m);

        sc.close();

        ListNode merged = mergeTwoLists(head1, head2);
        printList(merged);
    }

    // 建立鏈結串列
    private static ListNode buildList(Scanner sc, int size) {
        if (size == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int i = 0; i < size; i++) {
            tail.next = new ListNode(sc.nextInt());
            tail = tail.next;
        }
        return dummy.next;
    }

    // 合併兩條已排序鏈結串列
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        // 將剩餘節點接上
        tail.next = (l1 != null) ? l1 : l2;

        return dummy.next;
    }

    // 輸出鏈表節點值
    private static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val).append(" ");
            head = head.next;
        }
        System.out.print(sb.toString().trim());
    }
}
