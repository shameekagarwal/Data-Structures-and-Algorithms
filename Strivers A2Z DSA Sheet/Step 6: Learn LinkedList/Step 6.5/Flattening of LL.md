# Flattening of LL

- brute - put everything into an array, sort it and then return
- https://www.codingninjas.com/studio/problems/flatten-a-linked-list_1112655
- assume list is `1 -> 2 -> 3 ...`. however, each of 1, 2, etc have children
- logic - `merge(merge(merge(dummy, 1), 2), 3)....`
- note - before merging, make the current's next as null, so that when the final list is calculated, all nodes of it have next as null
- however, we need to store it first to move the current pointer

```java
public class Solution {
    
    public static Node flattenLinkedList(Node head) {

        if (head == null || head.next == null) return head;

        Node mergedCurrent = new Node(Integer.MIN_VALUE);
        Node current = head;

        while (current != null) {
            Node nextCurrent = current.next;
            current.next = null;
            mergedCurrent = merge(mergedCurrent, current);
            current = nextCurrent;
        }

        return mergedCurrent.child;
    }

    private static Node merge(Node a, Node b) {
        
        Node mergedHead = new Node(Integer.MIN_VALUE);
        Node mergedCurrent = mergedHead;
        
        while (a != null && b != null) {
            if (a.data < b.data) {
                mergedCurrent.child = a;
                mergedCurrent = a;
                a = a.child;
            } else {
                mergedCurrent.child = b;
                mergedCurrent = b;
                b = b.child;
            }
        }

        if (a != null) {
            mergedCurrent.child = a;
        }

        if (b != null) {
            mergedCurrent.child = b;
        }

        return mergedHead.child;
    }
}
```
