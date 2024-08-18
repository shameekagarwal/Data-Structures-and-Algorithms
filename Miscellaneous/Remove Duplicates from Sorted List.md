# Remove Duplicates from Sorted List

- https://leetcode.com/problems/remove-duplicates-from-sorted-list/
- maintain a current tail initialized to head, current initialized to head.next
- if current.val != currentTail.val, update currentTail.next to point to current, and move currentTail to current
- after the loop, do not forget to initialize currentTail.next to null
- important - null check for head at the beginning

```java
class Solution {

    public ListNode deleteDuplicates(ListNode head) {

        if (head == null) return null;

        ListNode currentTail = head;
        ListNode current = head.next;

        while (current != null) {

            if (current.val != currentTail.val) {
                currentTail.next = current;
                currentTail = currentTail.next;
            }

            current = current.next;
        }

        currentTail.next = null;

        return head;
    }
}
```
