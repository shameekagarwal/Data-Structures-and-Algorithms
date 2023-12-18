# Points

- https://leetcode.com/problems/remove-nth-node-from-end-of-list/
- checking for boundary conditions - what if n = size of list etc is key
- remember the two pointer technique used here

# Solution

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode fast = head;
        while ((n >= 0) && (fast != null)) {
            fast = fast.next;
            n--;
        }

        if (n != -1) {
            return head.next;
        }

        ListNode slow = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        
        return head;
    }
}
```

