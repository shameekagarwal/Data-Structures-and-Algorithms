# Delete all occurrences of a key in DLL

- https://www.codingninjas.com/studio/problems/delete-all-occurrences-of-a-given-key-in-a-doubly-linked-list_8160461
- first, update new head
- now, previous will point to last non k node
- every time we encounter a non k node, make previous.next = current and current.prev = previous and update previous to current
- at the end, remember to set previous's next to null - to remove trailing ks in list

```java
public class Solution {

    public static Node deleteAllOccurrences(Node head, int k) {
        
        Node current = head;
        while (current != null && current.data == k) {
            current = current.next;
        }

        if (current == null) return null;

        Node newHead = current;

        Node previous = current;
        current = current.next;

        while (current != null) {
            if (current.data != k) {
                previous.next = current;
                current.prev = previous;
                previous = current;
            }
            current = current.next;
        }
        previous.next = null;

        return newHead;
    }
}
```
