# Sort List

- https://leetcode.com/problems/sort-list/
- brute - put all elements into a list
- sort the list
- overwrite the values
- optimal - merge sort
- note - see how tortoise and hare is modified based on [Delete the Middle Node of a Linked List](./Delete%20the%20Middle%20Node%20of%20a%20Linked%20List.md) i.e. fast is initialized to `head.next.next`
- note - just remember that using dummy node for merging makes the code a lot cleaner

```java
class Solution {

    public ListNode sortList(ListNode head) {

        if (head == null || head.next == null) return head;

        ListNode leftEnd = getNodeBeforeMiddle(head);
        ListNode rightStart = leftEnd.next;
        leftEnd.next = null;
        
        rightStart = sortList(rightStart);
        ListNode leftStart = sortList(head);

        return merge(leftStart, rightStart);
    }

    private ListNode merge(ListNode left, ListNode right) {
        
        ListNode mergedHead = new ListNode(Integer.MIN_VALUE);
        ListNode merged = mergedHead;
        
        while (left != null && right != null) {
            if (left.val < right.val) {
                merged.next = left;
                left = left.next;
                merged = merged.next;
            } else {
                merged.next = right;
                right = right.next;
                merged = merged.next;
            }
        }

        if (left != null) {
            merged.next = left;
        }

        if (right != null) {
            merged.next = right;
        }

        return mergedHead.next;
    }

    private ListNode getNodeBeforeMiddle(ListNode head) {
        
        ListNode fast = head.next.next;
        ListNode slow = head;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    private void printList(ListNode head) {
        System.out.printf("[");
        while (head != null) {
            System.out.printf("%d -> ", head.val);
            head = head.next;
        }
        System.out.printf("]\n");
    }
}
```
