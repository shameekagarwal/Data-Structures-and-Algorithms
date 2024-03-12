# Search in a Linked List

- https://www.codingninjas.com/studio/problems/search-in-a-linked-list_975381

```java
public class Solution {
    public static int searchInLinkedList(Node head, int k) {
        
        while (head != null) {
            if (head.data == k) return 1;
            head = head.next;
        }
        
        return 0;
    }
}
```
