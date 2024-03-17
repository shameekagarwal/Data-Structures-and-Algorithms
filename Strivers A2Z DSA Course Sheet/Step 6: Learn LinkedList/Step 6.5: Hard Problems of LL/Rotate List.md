# Rotate List

- https://leetcode.com/problems/rotate-list/
- we can merge any two loops - get length and tail in same loop / change pointers of old and new tail in same loop etc, thus effectively making it faster. but below felt more readable to me

```java
class Solution {

    public ListNode rotateRight(ListNode head, int k) {

        if (head == null || head.next == null) return head;

        int length = findLength(head);
        k = k % length;

        if (k == 0) return head;
        
        ListNode fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        ListNode current = head;
        ListNode newHead = null;
        ListNode newTail = null;

        while (true) {

            if (fast != null) {
                if (fast.next == null) {
                    newTail = current;
                }
                fast = fast.next;
                if (fast == null) {
                    newHead = current.next;                    
                }
            }

            if (current.next == null) {
                current.next = head;
                break;
            }

            current = current.next;
        }

        newTail.next = null;

        return newHead;
    }

    private int findLength(ListNode node) {

        int length = 0;

        while (node != null) {
            node = node.next;
            length += 1;
        }

        return length;
    }
}
```
