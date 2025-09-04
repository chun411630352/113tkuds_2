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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 建立虛擬頭節點，方便串接節點
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // 遍歷兩條串列，挑選較小節點加入結果串列
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;   // 連接list1當前節點
                list1 = list1.next;     // list1指標往後移
            } else {
                current.next = list2;   // 連接list2當前節點
                list2 = list2.next;     // list2指標往後移
            }
            current = current.next;     // 移動結果串列指標
        }

        // 將剩餘未遍歷完的串列連接到結果尾端
        if (list1 != null) current.next = list1;
        if (list2 != null) current.next = list2;

        // 回傳合併後串列的頭節點（跳過虛擬頭）
        return dummy.next;
    }
}
