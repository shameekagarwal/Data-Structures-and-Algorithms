# Points

- https://leetcode.com/problems/linked-list-cycle/
- slow and fast pointer (also known as floyd's tortoise and hare algorithm)
- if there is a loop in the list, the fast pointer will eventually meet the slow pointer

# Solution

```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && slow != fast) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) fast = fast.next;
        }
        return slow == fast;
    }
}
```
