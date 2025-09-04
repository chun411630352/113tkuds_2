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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 建立虛擬頭節點，方便刪除頭節點的情況
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // 設置兩指標，皆指向虛擬頭節點
        ListNode first = dummy;
        ListNode second = dummy;
        
        // 讓第一個指標先前進 n 步，使兩指標間距離為 n
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        
        // 同時移動兩指標直到第一個指標到尾端
        while (first.next != null) {
            first = first.next;
            second = second.next;
        }
        
        // 此時第二指標的下一節點即為需刪除節點，修改指標跳過該節點
        second.next = second.next.next;
        
        // 傳回去掉節點後的鏈結串列頭節點
        return dummy.next;
    }
}
