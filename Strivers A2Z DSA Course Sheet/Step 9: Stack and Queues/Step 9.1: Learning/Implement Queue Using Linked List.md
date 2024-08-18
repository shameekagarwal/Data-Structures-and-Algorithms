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

- https://www.geeksforgeeks.org/problems/implement-queue-using-linked-list/

```java
class QueueNode {

    int data;
    QueueNode next;
    
    QueueNode(int a) {
        data = a;
        next = null;
    }
}

class MyQueue {

    QueueNode front, rear;

    void push(int a) {

        QueueNode newNode = new QueueNode(a);

        if (front == null) {
            front = new QueueNode(a);
            rear = front;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    int pop() {

        if (front == null) {
            return -1;
        }

        int data = front.data;

        if (front == rear) {
            rear = null;
        }

        front = front.next;

        return data;
    }
}
```
