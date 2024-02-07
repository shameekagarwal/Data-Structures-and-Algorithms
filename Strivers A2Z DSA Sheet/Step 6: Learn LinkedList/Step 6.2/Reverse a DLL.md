# Reverse a DLL

- brute force like solution - use stack and push everything into it, and probably "mutate" the node by replacing it data etc
- optimal - swap one by one
- https://www.geeksforgeeks.org/problems/reverse-a-doubly-linked-list/1
- `beforeCurrent` - points to the node before the current node (technically becomes `afterCurrent` after the swap)
- keeping it outside the loop and not inside helps us return the head at the end

```java
public static Node reverseDLL(Node  head) {
    
    Node current = head;
    Node beforeCurrent = null;

    while (current != null) {
        current.prev = current.next;
        current.next = beforeCurrent;
        beforeCurrent = current;
        current = current.prev;
    }
    
    return beforeCurrent;
}
```
