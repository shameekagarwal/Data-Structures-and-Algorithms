# Reverse LL in group of given size K

- https://leetcode.com/problems/reverse-nodes-in-k-group/
- find head and tail of current group
- if tail is null - attach head to previous node, break out of loop
- if tail is present - store its next and make it next null
- reverse current group - note how its isolated i.e. it is not attached to remaining list
- now, head of current group is return value of reverse, tail is no current
- update previous group tail's next to current group head
- update current to earlier stored next

```java
class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode prevGroupTail = null;
        ListNode current = head;

        while (current != null) {

            int currentGroupLength = 1;
            ListNode currentGroupTail = findKthNode(current, k);

            if (currentGroupTail == null) { // do not reverse
                if (prevGroupTail != null) prevGroupTail.next = current;
                current = null;
            }

            else { // reverse

                ListNode nextCurrent = currentGroupTail.next;
                currentGroupTail.next = null;

                ListNode currentGroupHead = reverse(current);
                currentGroupTail = current;
                
                if (prevGroupTail == null) head = currentGroupHead; // 1st group
                else prevGroupTail.next = currentGroupHead; // any group but 1st
                
                current = nextCurrent;
                prevGroupTail = currentGroupTail;
            }
        }

        return head;
    }

    private ListNode findKthNode(ListNode current, int k) {
        int currentLength = 1;
        while (currentLength < k && current != null) {
            current = current.next;
            currentLength += 1;
        }
        return current;
    }

    private ListNode reverse(ListNode head) {
        
        ListNode current = head;
        ListNode prev = null;

        while (current != null) {
            
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }

        return prev;
    }
}
```
