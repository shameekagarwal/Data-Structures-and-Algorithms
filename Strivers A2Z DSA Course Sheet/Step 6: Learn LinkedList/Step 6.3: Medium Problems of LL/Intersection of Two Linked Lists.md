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
- assume smaller list is x uncommon, longer has y uncommon, and both have z common
- one pointer would move x + z + y, other would move y + z + x, after which both will meet
  - if same length both would move the uncommon equal length part x / y
- if no part is common, one would move a + b, other would move b + a, after which both would be null
  - if same length both would move just move once a / b

```java
public class Solution {
    
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode currA = headA;
        ListNode currB = headB;
        
        while (true) {

            if (currA == currB) return currA;

            if (currA == null) currA = headB;
            else currA = currA.next;

            if (currB == null) currB = headA;
            else currB = currB.next;
        }
    }
}
```
