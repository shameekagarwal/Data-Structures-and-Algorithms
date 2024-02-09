# Length of Loop in LL

- https://www.codingninjas.com/studio/problems/find-length-of-loop_8160455

## Brute

- keep a counter in a map
- once we encounter the same node again, subtract current count from stored count

```java
import java.util.Map;
import java.util.HashMap;

public class Solution {

    public static int lengthOfLoop(Node head) {
        
        Map<Node, Integer> visited = new HashMap<>();
        Node current = head;
        int count = 1;
        
        while (current != null) {
            if (visited.containsKey(current)) {
                return count - visited.get(current);
            }
            visited.put(current, count);
            count += 1;
            current = current.next;
        }

        return 0;
    }
}
```

## Optimal

- first [Detect a loop in LL](./Detect%20a%20loop%20in%20LL.md)
- then leave the slow there and move fast one by one till it reaches the fast again

```java
public class Solution {
    
    public static int lengthOfLoop(Node head) {
        
        if (head == null || head.next == null) return 0;

        Node fast = head;
        Node slow = head;

        do {
            fast = fast.next.next;
            slow = slow.next;
        } while (fast != null && fast.next != null && fast != slow);

        if (fast == null || fast.next == null) return 0;

        int count = 0;
        do {
            fast = fast.next;
            count += 1;
        } while (slow != fast);

        return count;
    }
}
```
