# Insert a node in DLL

- if i calculate `nodeBeforeNewNode` using `nodeAfterNewNode.prev`, it would not work if we have to insert at the end of the dll - `nodeAfterNewNode` would be null and we would get an NPE
- https://www.geeksforgeeks.org/problems/insert-a-node-in-doubly-linked-list/
- seems like in question head cannot change, but we can return `newNode` as the new head if `nodeBeforeNewNode` is null

```java
class GfG {
    
    void addNode(Node head, int pos, int data) {
        
        Node nodeAfterNewNode = head;
        Node nodeBeforeNewNode = null;
        for (int i = 0; i <= pos; i++) {
            nodeBeforeNewNode = nodeAfterNewNode;
            nodeAfterNewNode = nodeAfterNewNode.next;
        }

        Node newNode = new Node(data);
        
        if (nodeBeforeNewNode != null) nodeBeforeNewNode.next = newNode;
        if (nodeAfterNewNode != null) nodeAfterNewNode.prev = newNode;
        
        newNode.prev = nodeBeforeNewNode;
        newNode.next = nodeAfterNewNode;
	}
}
```
