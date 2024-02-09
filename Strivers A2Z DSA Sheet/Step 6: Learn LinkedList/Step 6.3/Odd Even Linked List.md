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
        
        if (head == null || head.next == null) return head;

        ListNode oddNodeStart = head;
        ListNode evenNodeStart = head.next;

        ListNode currentOddNode = oddNodeStart;
        ListNode currentEvenNode = evenNodeStart;
        while (currentEvenNode != null && currentEvenNode.next != null) {
            currentOddNode.next = currentOddNode.next.next;
            currentEvenNode.next = currentEvenNode.next.next;
            currentOddNode = currentOddNode.next;
            currentEvenNode = currentEvenNode.next;
        }

        currentOddNode.next = evenNodeStart;
        return head;
    }
}
```
