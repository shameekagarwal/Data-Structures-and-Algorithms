# Sort linked list of 0s 1s 2s

- brute - iterate one by one, increase counts of three variables. finally, overwrite the nodes with counts for 0, 1 and 2 respectively
- optimal - have a dummy node for all three
- keep appending to the right list
- join them together by discarding the dummies - the last three lines before return should cover all cases - 0 is not there but 1 and 2 are there, only 0 and 2 is there, etc

```java
public class Solution {

    public static Node sortList(Node head) {
        
        Node zeroHead = new Node(-1);
        Node oneHead = new Node(-1);
        Node twoHead = new Node(-1);

        Node zeroTail = zeroHead;
        Node oneTail = oneHead;
        Node twoTail = twoHead;
        
        Node current = head;

        while (current != null) {
            if (current.data == 0) {
                zeroTail.next = current;
                zeroTail = zeroTail.next;
            } else if (current.data == 1) {
                oneTail.next = current;
                oneTail = oneTail.next;
            } else {
                twoTail.next = current;
                twoTail = twoTail.next;
            }
            current = current.next;
        }

        zeroTail.next = oneHead.next == null ? twoHead.next : oneHead.next;
        oneTail.next = twoHead.next;
        twoTail.next = null;

        return zeroHead.next;
    }
}
```
