# Implement Stack With Linked List

- https://www.codingninjas.com/studio/problems/implement-stack-with-linked-list_1279905
- `LinkedList` of java already has handy methods for deque like functionality - `addFirst`, `addLast`, `removeFirst`, `removeLast`, `getFirst`, `getLast`

```java
import java.util.LinkedList;

public class Solution {

    static class Stack {

        private LinkedList<Integer> list;

        Stack() {
            list = new LinkedList<>();
        }

        int getSize() {
            return list.size();
        }

        boolean isEmpty() {
            return list.isEmpty();
        }

        void push(int data) {
            list.addLast(data);
        }

        void pop() {
            if (!isEmpty()) {
                list.removeLast();
            }
        }

        int getTop() {
            return isEmpty() ? -1 : list.getLast();
        }
    }
}
```
