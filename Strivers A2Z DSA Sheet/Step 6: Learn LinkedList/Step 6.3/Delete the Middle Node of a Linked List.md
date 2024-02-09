# Delete the Middle Node of a Linked List

- https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
- based on all slow fast problems like [Middle of the Linked List](./Middle%20of%20the%20Linked%20List.md), i knew i just needed the node before the middle - so i keep track of a prev node as well
- remember consistent implementations

```java
class Solution {

    public ListNode deleteMiddle(ListNode head) {
        
        if (head == null || head.next == null) return null;

        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }

        prev.next = prev.next.next;

        return head;
    }
}
```

- if interviewer cries about third variable, "skip a step" - make fast = head.next.next at the very beginning

```java
class Solution {

    public ListNode deleteMiddle(ListNode head) {
        
        if (head == null || head.next == null) return null;

        ListNode fast = head.next.next;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return head;
    }
}
```
