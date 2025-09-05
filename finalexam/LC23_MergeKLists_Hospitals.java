import java.util.*;

public class LC23_MergeKLists_Hospitals {
    // 定義鏈結節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        ListNode[] lists = new ListNode[k];
        
        // 讀取 k 條序列，-1 為結尾標記
        for (int i = 0; i < k; i++) {
            lists[i] = buildList(sc);
        }
        sc.close();

        ListNode merged = mergeKLists(lists);
        printList(merged);
    }

    // 建立鏈結串列直到遇到 -1
    private static ListNode buildList(Scanner sc) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (true) {
            int val = sc.nextInt();
            if (val == -1) break;
            tail.next = new ListNode(val);
            tail = tail.next;
        }
        return dummy.next;
    }

    // 利用 PriorityQueue 合併 k 條排序鏈結串列
    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
        // 將非空串列的頭節點加入最小堆
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        // 持續從堆中彈出最小節點，並推進
        while (!pq.isEmpty()) {
            ListNode curr = pq.poll();
            tail.next = curr;
            tail = tail.next;

            if (curr.next != null) {
                pq.offer(curr.next);
            }
        }

        return dummy.next;
    }

    // 輸出鏈結串列節點值
    private static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val).append(" ");
            head = head.next;
        }
        System.out.print(sb.toString().trim());
    }
}
