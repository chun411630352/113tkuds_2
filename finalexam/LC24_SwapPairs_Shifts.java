import java.util.Scanner;

public class LC24_SwapPairs_Shifts {
    // 鏈結節點定義
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // 讀取節點數
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (int i = 0; i < n; i++) {
            tail.next = new ListNode(sc.nextInt());
            tail = tail.next;
        }
        sc.close();

        ListNode newHead = swapPairs(dummy.next);
        printList(newHead);
    }

    // 兩兩交換相鄰節點
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = a.next;

            // 交換節點
            prev.next = b;
            a.next = b.next;
            b.next = a;

            // prev 向前跳兩格繼續處理
            prev = a;
        }

        return dummy.next;
    }

    // 輸出鏈結串列節點值
    public static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode curr = head;
        while (curr != null) {
            sb.append(curr.val).append(" ");
            curr = curr.next;
        }
        System.out.print(sb.toString().trim());
    }
}
