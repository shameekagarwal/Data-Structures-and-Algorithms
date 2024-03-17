# Remove duplicates from sorted DLL

- https://www.codingninjas.com/studio/problems/remove-duplicates-from-a-sorted-doubly-linked-list_2420283
- bug in the problem - says dll but question is of ll
- to confirm - i do not think much changes should be needed, commented the line
- no reason for head to change, just return head as is - we retain the first element if duplicates exist

```java
public class Solution {
    
    public static Node uniqueSortedList(Node head) {
        
        Node current = head;
        
        while (current != null) {

            Node nextUnique = current.next;
            while (nextUnique != null && nextUnique.data == current.data) {
                nextUnique = nextUnique.next;
            }

            current.next = nextUnique;
            // if (nextUnique != null) nextUnique.prev = current;
            current = nextUnique;
        }

        return head;
    }
}
```
