# Points

- https://leetcode.com/problems/swap-nodes-in-pairs/
- swap in pairs - first swap 1,2 then 3,4 then 5,6 and so on
- say a is first element of pair and b second element of pair
- a.next would point to b.next.next, while b.next would point to a
- edge case - if odd number of nodes are there, e.g. 1 -> 2 -> 3 -> 4 -> 5, 2nd pair's a (3) would not point to b.next.next, but b.next

# Solution

```java
class Solution {
    public ListNode swapPairs(ListNode head) {
        
        if (head == null || head.next == null) return head; 

        ListNode a = head;
        ListNode b = head.next;
        ListNode newHead = b;

        while (b != null) {
            
            ListNode originalBNext = b.next;
            
            ListNode newBNext = a;
            ListNode newANext = (b.next == null ? null : (b.next.next == null ? b.next : b.next.next));

            a.next = newANext;
            b.next = newBNext;
            
            a = originalBNext;
            b = (originalBNext == null ? null : originalBNext.next);
        }

        return newHead;
    }
}
```
