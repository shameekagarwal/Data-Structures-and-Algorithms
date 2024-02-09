# Detect a loop in LL

- https://leetcode.com/problems/linked-list-cycle/
- brute - like a set / map where address is the key
- optimized - hare and tortoise algorithm
- intuition - distance between fast to slow will keep decreasing since fast pointer is moving towards slow pointer
- using do while since before the loop starts, fast and slow are same
- since we use do while and first step is fast.next.next, we do the check for length 0 and 1 beforehand
- note - fast would be null when length of loop is even, fast.next would be null when length of loop is odd 

```java
public class Solution {

    public boolean hasCycle(ListNode head) {

        if (head == null || head.next == null) return false;
        
        ListNode fast = head;
        ListNode slow = head;

        do {
            fast = fast.next.next;
            slow = slow.next;
        } while (fast != null && fast.next != null && fast != slow);

        return fast != null && fast.next != null && fast == slow;
    }
}
```
