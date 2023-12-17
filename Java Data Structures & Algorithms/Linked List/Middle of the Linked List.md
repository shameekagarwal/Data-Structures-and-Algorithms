# Points

- https://leetcode.com/problems/middle-of-the-linked-list/
- slow and fast pointer (also known as floyd's tortoise and hare algorithm)

# Solution

```java
class Solution {
    public ListNode middleNode(ListNode head) {

      if (head == null) return null;

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) fast = fast.next;
        }

        return slow;
    }
}
```
