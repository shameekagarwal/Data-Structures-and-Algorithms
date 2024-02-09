# Middle of the Linked List

- https://leetcode.com/problems/middle-of-the-linked-list/
- brute - calculate length in first traversal, go up to length / 2 in second traversal
- optimal - tortoise and hare algorithm

```java
class Solution {
    
    public ListNode middleNode(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }
}
```
