# Insert a node in DLL

- if i calculate `nodeBeforeNewNode` using `nodeAfterNewNode.prev`, it would not work if we have to insert at the end of the dll - `nodeAfterNewNode` would be null and we would get an NPE
- https://www.codingninjas.com/studio/problems/insertion-in-doubly-linked-list_4609682
- seems like in question head cannot change, but we can return `newNode` as the new head if `nodeBeforeNewNode` is null

```java
import java.util.* ;
import java.io.*; 

public class Solution {

    static Node insert(int pos, int data, Node head) {
        
        Node nodeAfterNewNode = head;
        Node nodeBeforeNewNode = null;
        for (int i = 0; i < pos; i++) {
            nodeBeforeNewNode = nodeAfterNewNode;
            nodeAfterNewNode = nodeAfterNewNode.next;
        }

        Node newNode = new Node(data);
        
        if (nodeBeforeNewNode != null) nodeBeforeNewNode.next = newNode;
        if (nodeAfterNewNode != null) nodeAfterNewNode.prev = newNode;
        
        newNode.prev = nodeBeforeNewNode;
        newNode.next = nodeAfterNewNode;

        return nodeBeforeNewNode == null ? newNode : head;
    }
}
```
