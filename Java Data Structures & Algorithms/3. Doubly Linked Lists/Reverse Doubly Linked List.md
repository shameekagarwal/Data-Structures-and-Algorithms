# Points

- after every loop iteration, prev will point to the nodes in order, e.g. 1 then 2 then 3
- make 1 prev point to current (2), make 2 prev point to current (3) and make 3 prev point to current (null)

# Solution

```java
public void reverse() {
    if (head == null || head.next == null) return;
    
    Node prev = null;
    Node current = head;
    while (current != null) {
        Node nextCurrent = current.next;
        current.next = prev;
        prev = current;
        current = nextCurrent;
        prev.prev = current;
    }
    tail = head;
    head = prev;
}
```
