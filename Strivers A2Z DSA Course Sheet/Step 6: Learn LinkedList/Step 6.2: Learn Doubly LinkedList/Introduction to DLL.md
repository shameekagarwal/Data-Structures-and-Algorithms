# Introduction to DLL

- stores previous pointer as well so that e can go back as well
- https://www.codingninjas.com/studio/problems/introduction-to-doubly-linked-list_8160413

```java
public class Solution {
    public static Node constructDLL(int []arr) {
        
        Node head = new Node(arr[0]);
        Node prevNode = head;

        for (int i = 1; i < arr.length; i++) {
            Node newNode = new Node(arr[i]);
            newNode.prev = prevNode;
            prevNode.next = newNode;
            prevNode = newNode;
        }
        
        return head;
    }
}
```
