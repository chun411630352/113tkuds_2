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
    public ListNode reverseKGroup(ListNode head, int k) {
        // 建立虛擬頭節點，方便操作
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode groupPrev = dummy;
        
        while (true) {
            // 找到第 k 個節點
            ListNode kth = getKthNode(groupPrev, k);
            if (kth == null) break;  // 若不足 k 個節點，結束迴圈
            
            ListNode groupNext = kth.next; // 下一組起點
            
            // 反轉該區段
            ListNode prev = groupNext;
            ListNode curr = groupPrev.next;
            while (curr != groupNext) {
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }
            
            // 調整指標以連接反轉後區段
            ListNode temp = groupPrev.next; // 原區段起點為反轉後尾節點
            groupPrev.next = kth;  // 連接反轉後的頭節點
            groupPrev = temp;      // 移動至區段尾，為下一區段前一節點
        }
        
        // 回傳反轉後的鏈結串列頭
        return dummy.next;
    }
    
    // 輔助函式：從起點開始前進 k 個節點，回傳該節點，數量不足時回傳 null
    private ListNode getKthNode(ListNode start, int k) {
        while (start != null && k > 0) {
            start = start.next;
            k--;
        }
        return start;
    }
}
