# Reverse a DLL

- brute force like solution - use stack and push everything into it, and probably "mutate" the node by replacing it data etc
- optimal - swap one by one
- https://www.geeksforgeeks.org/problems/reverse-a-doubly-linked-list/1
- keeping it outside the loop and not inside helps us return the head at the end

```java
public class Solution {

    public static Node reverseDLL(Node head) {

        Node nodeCurrent = head;
        Node nodeBefore = null;

        while (nodeCurrent != null) {
            
            // store after
            Node nodeAfter = nodeCurrent.next;

            // change links
            nodeCurrent.next = nodeBefore;
            if (nodeBefore != null) {
                nodeBefore.prev = nodeCurrent;
            }

            // update before and current
            nodeBefore = nodeCurrent;
            nodeCurrent = nodeAfter;
        }

        nodeBefore.prev = null;

        return nodeBefore;
    }
}
```
