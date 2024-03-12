# Palindrome Linked List

- https://leetcode.com/problems/palindrome-linked-list/

## Brute

- insert into stack and compare one by one

```java
import java.util.ArrayDeque;

class Solution {

    public boolean isPalindrome(ListNode head) {
        
        ArrayDeque<ListNode> deque = new ArrayDeque<>();
        ListNode current = head;
        while (current != null) {
            deque.addLast(current);
            current = current.next;
        }

        current = head;
        while (current != null) {
            if (deque.removeLast().val != current.val) {
                return false;
            }
            current = current.next;
        }

        return true;
    }
}
```

## O(1) space

- revers 2nd half of list
- check if palindrome by iterating through both halves
  - note - ending condition is iterator of second half does not touch null
  - because first half is pointing to head of second half (tail of second half after it has been reversed)
- reverse it back to original - so that original list is not changed, best practice
- notice how end node changes based on odd or even

```java
class Solution {

    public boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) return true;

        // calculate mid
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // reverse and calculate is palindrome
        boolean isOddLength = fast != null;
        ListNode startNode = head;
        ListNode endNode = isOddLength ? reverse(slow.next) : reverse(slow);
        boolean isPalindrome = getIsPalindrome(startNode, endNode);

        // reverse back to original format
        reverse(endNode);
        // printList(head);

        return isPalindrome;
    }

    private void printList(ListNode head) {
        while (head != null) {
            System.out.printf("%d -> ", head.val);
            head = head.next;
        }
        System.out.printf("\n");        
    }

    private boolean getIsPalindrome(ListNode startNode, ListNode endNode) {
        while (endNode != null) {
            if (startNode.val != endNode.val) {
                return false;
            }
            startNode = startNode.next;
            endNode = endNode.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }
}
```
