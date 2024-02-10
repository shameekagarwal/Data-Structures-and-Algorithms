# Rotate List

- https://leetcode.com/problems/rotate-list/
- we can merge any two loops - get length and tail in same loop / change pointers of old and new tail in same loop etc, thus effectively making it faster. but below felt more readable to me

```java
class Solution {
 
    public ListNode rotateRight(ListNode head, int k) {
        
        int length = getLength(head);
        if (length == 0) return head;
        k = k % length;
        if (k == 0) return head;
        
        ListNode prevTail = getNode(head, length);
        prevTail.next = head;

        ListNode newTail = getNode(head, length - k);
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }

    private ListNode getNode(ListNode current, int l) {
        l -= 1;
        while (l > 0) {
            current = current.next;
            l -= 1;
        }
        return current;
    }

    private int getLength(ListNode current) {
        int length = 0;
        while (current != null) {
            length += 1;
            current = current.next;
        }
        return length;
    }
}
```
