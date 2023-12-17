# Points

- https://leetcode.com/problems/reverse-linked-list/
- prev should point to null and current to head to start with. i was probably doing head and head.next respectively first. however, doing null and head often simplifies linked list problems

# Solution

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode prev = null;
        ListNode iterator = head;

        while (iterator != null) {
            ListNode nextIterator = iterator.next;
            iterator.next = prev;
            prev = iterator;
            iterator = nextIterator;
        }

        return prev;
    }
}
```
