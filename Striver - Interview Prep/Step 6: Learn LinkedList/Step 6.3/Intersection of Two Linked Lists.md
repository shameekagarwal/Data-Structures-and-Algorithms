# Intersection of Two Linked Lists

- https://leetcode.com/problems/intersection-of-two-linked-lists/

## Brute

- absolute brute - iterate through one list and store it in hash set. check for all nodes in either list to see if a node exists in the hash set
- better - calculate length of both. then, move the longer one by `|L1 - L2|`. finally, move both of them simultaneously one by one. where both are equal is where the intersection happens
- note how we use `findIntersection` intelligently - it can assume that `headA` is longer than `headB`

```java
public class Solution {
    
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        
        int length1 = findLength(headA);
        int length2 = findLength(headB);

        if (length1 > length2) {
            return findIntersection(headA, headB, length1 - length2);
        } else {
            return findIntersection(headB, headA, length2 - length1);
        }
    }

    private ListNode findIntersection(ListNode headA, ListNode headB, int l) {
        while (l > 0) {
            l -= 1;
            headA = headA.next;
        }
        while (headA != null && headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    private int findLength(ListNode head) {
        int length = 0;
        while (head != null) {
            head = head.next;
            length += 1;
        }
        return length;
    }
}
```

## O(1) space

- move both to end
- if one reaches end, reset it to beginning but of other list
- when the other reaches end, reset it to beginning of other list as well
- now both are "aligned" i.e. they will both meet at intersection point
- note - if they do not have an intersection point, both reach null at the same time
- important for implementation - why are two `if (currA == currB)` needed?
  - the second one helps cover cases like the following 
    - list 1 - `[1,3]`, list 2 - `[3]`
    - currA = 1, currB = 3
    - currA = 3, curB = null
    - currA = null, currB = 1
    - currA = 3, currB = 3 - this was after currA became headB - so, we need to again check instead of moving them to next
  - the first one helps cover cases like the following
    - list 1 - `[4,1,8,4,5]`, list 2 - `[5,6,1,8,4,5]` (the one in the leetcode example)
    - currA = 4, currB = 5
    - currA = 1, currB = 6
    - currA = 8, currB = 1
    - currA = 4, currB = 8
    - currA = 5, currB = 4
    - currA = null, currB = 5
    - currA = 5, currB = 5 -> currA = 6, currB = null
    - currA = 6, currB = 4 -> currA = 1, currB = 1
    - currA = 8, currB = 8

```java
public class Solution {
    
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode currA = headA;
        ListNode currB = headB;
        
        while (true) {

            if (currA == currB) return currA;
            if (currA == null) currA = headB;
            if (currB == null) currB = headA;
            if (currA == currB) return currA;

            currA = currA.next;
            currB = currB.next;
        }
    }
}
```
