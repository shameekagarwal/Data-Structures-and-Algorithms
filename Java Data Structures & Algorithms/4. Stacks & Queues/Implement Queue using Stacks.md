# Points

- https://leetcode.com/problems/implement-queue-using-stacks/
- solution 1 is naive, push is o(n) and pop is o(1)
- solution 2 is optimized, push is o(1) and pop is amortized o(1)

# Solution 1

```java
class MyQueue {

    Deque<Integer> deque1 = new ArrayDeque<>();
    Deque<Integer> deque2 = new ArrayDeque<>();

    public MyQueue() {
        
    }

    public void push(int x) {
        while (!deque1.isEmpty()) {
            deque2.addLast(deque1.removeLast());
        }
        deque1.addLast(x);
        while (!deque2.isEmpty()) {
            deque1.addLast(deque2.removeLast());
        }
    }

    public int pop() {
        return deque1.isEmpty() ? -1 : deque1.removeLast();
    }

    public int peek() {
        return deque1.isEmpty() ? -1 : deque1.peekLast();
    }

    public boolean empty() {
        return deque1.isEmpty();
    }
}
```

# Solution 2

```java
class MyQueue {

    Deque<Integer> inDeque = new ArrayDeque<>();
    Deque<Integer> outDeque = new ArrayDeque<>();

    public MyQueue() {
        
    }

    public void push(int x) {
        inDeque.addLast(x);
    }
    
    public int pop() {
        if (outDeque.isEmpty()) {
            while (!inDeque.isEmpty()) {
                outDeque.addLast(inDeque.removeLast());
            }
        }
        return outDeque.isEmpty() ? -1 : outDeque.removeLast();
    }

    public int peek() {
        if (outDeque.isEmpty()) {
            while (!inDeque.isEmpty()) {
                outDeque.addLast(inDeque.removeLast());
            }
        }
        return outDeque.isEmpty() ? -1 : outDeque.peekLast();
    }

    public boolean empty() {
        return inDeque.isEmpty() && outDeque.isEmpty();
    }
}
```
