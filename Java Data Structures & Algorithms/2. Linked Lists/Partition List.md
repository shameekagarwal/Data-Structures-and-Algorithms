# Points

- https://leetcode.com/problems/partition-list/
- my earlier solution was very complex, but when i retried, i did it by maintaining 4 different pointers, which made it very simple
- note how next of tail of both partitions need to be handled outside the loop

# Solution

```java
class Solution {
    public ListNode partition(ListNode head, int x) {
        
        ListNode lessThanHead = null;
        ListNode lessThanTail = null;
        ListNode greaterThanOrEqualToHead = null;
        ListNode greaterThanOrEqualToTail = null;
        ListNode iterator = head;
        
        while (iterator != null) {
            
            ListNode nextIterator = iterator.next;
            
            if (iterator.val < x) {
                if (lessThanHead == null) {
                    lessThanHead = iterator;
                } else {
                    lessThanTail.next = iterator;
                }
                lessThanTail = iterator;
            } else {
                if (greaterThanOrEqualToHead == null) {
                    greaterThanOrEqualToHead = iterator;
                } else {
                    greaterThanOrEqualToTail.next = iterator;
                }
                greaterThanOrEqualToTail = iterator;
            }
            
            iterator = nextIterator;
        }

        if (lessThanTail != null) {
            lessThanTail.next = greaterThanOrEqualToHead;
        }
        if (greaterThanOrEqualToTail != null) {
            greaterThanOrEqualToTail.next = null;
        }

        return ((lessThanHead != null) ? lessThanHead : greaterThanOrEqualToHead);
    }
}
```
