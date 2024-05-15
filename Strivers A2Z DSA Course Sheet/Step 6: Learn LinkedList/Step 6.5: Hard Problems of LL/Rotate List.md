# Rotate List

- https://leetcode.com/problems/rotate-list/
- we can merge any two loops - get length and tail in same loop / change pointers of old and new tail in same loop etc, thus effectively making it faster. but below felt more readable to me

```java
class Solution {

    public ListNode rotateRight(ListNode head, int k) {
        
        int length = getLength(head);

        if (length == 0) return head;

        k = k % length;

        if (k == 0) {
            return head;
        }

        ListNode current = head;

        for (int i = 0; i < k; i++) {
            current = current.next;
        }

        ListNode nodeBeforeKthNodeFromEnd = head;
        ListNode lastNode = current;

        current = current.next;

        while (current != null) {
            nodeBeforeKthNodeFromEnd = nodeBeforeKthNodeFromEnd.next;
            lastNode = current;
            current = current.next;
        }

        lastNode.next = head;
        ListNode newHead = nodeBeforeKthNodeFromEnd.next;
        nodeBeforeKthNodeFromEnd.next = null;

        return newHead;
    }

    private int getLength(ListNode head) {
        
        int length = 0;

        while (head != null) {
            head = head.next;
            length += 1;
        }

        return length;
    }
}
```
