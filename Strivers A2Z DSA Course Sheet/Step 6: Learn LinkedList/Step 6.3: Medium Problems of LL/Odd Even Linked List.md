# Odd Even Linked List

- https://leetcode.com/problems/odd-even-linked-list/
- brute - add data to list, then start replacing values
- question - use O(1) space
- even is faster, so just check for even in the while loop
  - if length is even, even.next would be null
  - if length is odd, even would be null
- time complexity - O(n / 2) ish

```java
class Solution {

    public ListNode oddEvenList(ListNode head) {
        
        if (head == null || head.next == null) {
            return head;
        }

        ListNode currentOdd = head;
        ListNode currentEven = head.next;
        ListNode evenHead = currentEven;

        ListNode current = head.next.next;

        while (current != null) {

            currentOdd.next = current;
            currentEven.next = current.next;

            currentOdd = currentOdd.next;
            currentEven = currentEven.next;

            current = current.next;

            if (current != null) {
                current = current.next;
            }
        }

        currentOdd.next = evenHead;

        return head;
    }
}
```
