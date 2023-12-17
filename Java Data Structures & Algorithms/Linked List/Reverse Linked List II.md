# Points

- https://leetcode.com/problems/reverse-linked-list-ii/
- **corner cases**
  - left and right is 1 and length. the head returned will change as well
  - left is 1. the 4th point in basic idea below is skipped
- basic idea - 
  - e.g. 1 -> 2 -> 3 -> 4 -> 5 -> 6, left is 3 right is 5
  - remember pointer to 2
  - create reversed list - 5 -> 4 -> 3 -> null
  - make 2 point to reversed list head
  - make reversed list tail point to 6
- _naming of variables is terrible_

# Solution

```java
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {

        left -= 1;
        right -= 1;

        if (left < 0 || left >= right || head.next == null) return head;

        ListNode beforeReversedListHead = null;
        ListNode reversedListHead = head;
        for (int i = 0; i < left; i++) {
            beforeReversedListHead = reversedListHead;
            reversedListHead = reversedListHead.next;
        }
        ListNode reversedListTail = reversedListHead;

        ListNode prev = null;
        for (int i = left; i <= right; i++) {
            ListNode afterReversedListHead = reversedListHead.next;
            reversedListHead.next = prev;
            prev = reversedListHead;
            reversedListHead = afterReversedListHead;
        }

        if (beforeReversedListHead != null) beforeReversedListHead.next = prev;
        reversedListTail.next = reversedListHead;

        return beforeReversedListHead == null ? prev : head;
    }
}
```
