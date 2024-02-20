# Delete a node in DLL

- https://www.geeksforgeeks.org/problems/delete-node-in-doubly-linked-list/
- 1 based indexing so i ran loop up to x - 1

```java
class Solution {

    Node deleteNode(Node head, int x) {
        
        Node nodeToDelete = head;
        for (int i = 0; i < x - 1; i++){
            nodeToDelete = nodeToDelete.next;
        }
        
        Node nodeBeforeNodeToDelete = nodeToDelete.prev;
        Node nodeAfterNodeToDelete = nodeToDelete.next;
        
        if (nodeBeforeNodeToDelete != null) nodeBeforeNodeToDelete.next = nodeAfterNodeToDelete;
        if (nodeAfterNodeToDelete != null) nodeAfterNodeToDelete.prev = nodeBeforeNodeToDelete;
        
        nodeToDelete.prev = null;
        nodeToDelete.next = null;
        
        if (nodeBeforeNodeToDelete == null) return nodeAfterNodeToDelete;
        return head;
    }
}
```
