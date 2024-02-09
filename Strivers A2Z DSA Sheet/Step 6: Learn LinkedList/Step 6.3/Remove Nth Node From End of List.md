# Remove Nth Node From End of List

- https://leetcode.com/problems/remove-nth-node-from-end-of-list/
- brute force - find length of list, remove (length - n + 1)th node
- optimal - move fast n times
- now, move fast and slow together till fast.next is not null
- this way, slow will point to the node before the node to be deleted
- so, we can do `slow.next = slow.next.next` (actual `slow.next` should be automatically deleted later on due to garbage collection)
- note - fast will be null if n = length of list. so, if fast is null, just return `head.next`
- complexity - `O(n)` - but it is a single iteration only

```java
class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // remove first node
        if (fast == null) {
            return head.next;
        }

        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return head;
    }
}
```
