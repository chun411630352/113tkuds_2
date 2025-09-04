import java.util.PriorityQueue;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // 建立小根堆，根據節點值排序
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
            (a, b) -> a.val - b.val
        );
        
        // 將每個非空鏈結串列的頭節點加入小根堆
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }
        
        // 虛擬頭節點，方便串接結果
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        // 不斷從堆中取出最小節點，串接到結果串列
        while (!minHeap.isEmpty()) {
            ListNode minNode = minHeap.poll();
            current.next = minNode;
            current = current.next;
            
            // 若取出的節點後面還有節點，加入堆中
            if (minNode.next != null) {
                minHeap.offer(minNode.next);
            }
        }
        
        // 回傳合併後的串列頭節點
        return dummy.next;
    }
}
