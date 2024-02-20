# Flattening of LL

- https://www.codingninjas.com/studio/problems/flatten-a-linked-list_1112655

## Brute
 
- brute - put everything into an array, sort it and then return - do not even bother
- assume list is `1 -> 2 -> 3 ...`. however, each of 1, 2, etc have children
- logic - `merge(merge(merge(dummy, 1), 2), 3)....`
- note - before merging, make the current's next as null, so that when the final list is calculated, all nodes of it have next as null
- however, we need to store it first to move the current pointer
- time complexity - 
  - merging first two lists - n1 + n2
  - merging third list - (n1 + n2) + n3
  - merging fourth list - (n1 + n2 + n3) + n4
- so total - (n1 + n2) + (n1 + n2 + n3) + (n1 + n2 + n3 + n4) ... ≈ 2n + 3n + 4n + ... k*n = O(n * (k * (k + 1)) / 2) = cubic complexity

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

## Optimal

- use a min heap
- first, add the heads of all the lists
- then, keep popping from the min heap, and every time we pop, we push the child of this popped node
- main observation is to understand why potential candidates for the second element in the sorted list = 1st element of all lists but 2nd element of the list whose 1st element was used
- time complexity - pq size stays k, so it contributes log<sub>2</sub>k. we try to process ≈ n * k elements, so total = O(n * k * log<sub>2</sub>k)

```java
import java.util.PriorityQueue;

public class Solution {

    public static Node flattenLinkedList(Node head) {

        PriorityQueue<Pair> minHeap = new PriorityQueue<>();

        Node current = head;
        while (current != null) {
            minHeap.add(new Pair(current.data, current));
            Node nextCurrent = current.next;
            current.next = null;
            current = nextCurrent;
        }

        Node dummyHead = new Node(-1);
        current = dummyHead;
        while (!minHeap.isEmpty()) {
            Pair pair = minHeap.remove();
            current.child = pair.node;
            current = current.child;
            if (current.child != null) {
                minHeap.add(new Pair(current.child.data, current.child));
            }
        }

        return dummyHead.child;
    }

    static class Pair implements Comparable<Pair> {

        int data;
        Node node;

        Pair(int data, Node node) {
            this.data = data;
            this.node = node;
        }

        @Override
        public int compareTo(Pair pair) {
            return data - pair.data;
        }
    }
}
```
