# Implement Stack using Queue

- https://leetcode.com/problems/implement-stack-using-queues/
- push -
  - add to q2
  - move all elements of q1 to q2
  - swap q1 and q2
- pop - 
  - remove top of q1
- time complexity - push - O(n), pop - O(1)
- space complexity - O(n)

```java
import java.util.Deque;
import java.util.ArrayDeque;

class MyStack {

    private Deque<Integer> queue1;
    private Deque<Integer> queue2;

    public MyStack() {
        queue1 = new ArrayDeque<>();
        queue2 = new ArrayDeque<>();
    }
    
    public void push(int x) {
        queue2.addLast(x);
        while (!queue1.isEmpty()) {
            queue2.addLast(queue1.removeFirst());
        }
        
        Deque<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }
    
    public int pop() {
        return queue1.removeFirst();
    }
    
    public int top() {
        return queue1.peekFirst();
    }
    
    public boolean empty() {
        return queue1.isEmpty();
    }
}
```

- implementing using one queue - 
  - add to queue
  - dequeue size - 1 elements and push them back
  - this way, the element that was added last comes to the top of the queue

```java
import java.util.Deque;
import java.util.ArrayDeque;

class MyStack {

    private Deque<Integer> queue;

    public MyStack() {
        queue = new ArrayDeque<>();
    }
    
    public void push(int x) {
        queue.addLast(x);
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.addLast(queue.removeFirst());
        }
    }
    
    public int pop() {
        return queue.removeFirst();
    }
    
    public int top() {
        return queue.peekFirst();
    }
    
    public boolean empty() {
        return queue.isEmpty();
    }
}
```
