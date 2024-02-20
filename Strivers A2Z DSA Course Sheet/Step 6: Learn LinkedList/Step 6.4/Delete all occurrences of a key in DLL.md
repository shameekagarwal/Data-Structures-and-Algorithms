# Delete all occurrences of a key in DLL

- https://www.codingninjas.com/studio/problems/delete-all-occurrences-of-a-given-key-in-a-doubly-linked-list_8160461
- notice the head update check as well
- no need of extra pointers outside the loop, since we can go back and forward

```java
public class Solution {

    public static Node deleteAllOccurrences(Node head, int k) {

        Node curr = head;
        Node newHead = null;

        while (curr != null) {

            if (curr.data == k) {

                Node prev = curr.prev;
                Node next = curr.next;
                if (prev != null) prev.next = next;
                if (next != null) next.prev = prev;
                if (head == curr) {
                    head = next;
                }
            }
            curr = curr.next;
        }

        return head;
    }
}
```

