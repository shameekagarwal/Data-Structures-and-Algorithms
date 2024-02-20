# Implement Queue Using Linked List

- https://www.codingninjas.com/studio/problems/implement-queue-using-linked-list_8161235
- `LinkedList` of java already has handy methods for deque like functionality - `addFirst`, `addLast`, `removeFirst`, `removeLast`, `getFirst`, `getLast`

```java
import java.util.LinkedList;

public class Solution extends Queue {

    LinkedList<Integer> list = new LinkedList<>();

    public void push(int x) {
        list.addLast(x);
    }

    public int pop() {
        return list.isEmpty() ? -1 : list.removeFirst();
    }
}
```
