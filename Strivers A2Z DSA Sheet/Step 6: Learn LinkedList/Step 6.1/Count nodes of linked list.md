# Count nodes of linked list

- https://www.codingninjas.com/studio/problems/count-nodes-of-linked-list_5884

```java
public class Solution {
    public static int length(Node head){
        
        int length = 0;

        while (head != null) {
            length += 1;
            head = head.next;
        }

        return length;
    }
}
```
