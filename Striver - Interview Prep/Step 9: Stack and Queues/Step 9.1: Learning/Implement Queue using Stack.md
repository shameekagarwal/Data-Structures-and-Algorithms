# Implement Queue using Stack

- https://leetcode.com/problems/implement-queue-using-stacks/
- maintain two stacks - input and output
- push - 
  - add to input stack
- pop - 
  - if output is empty, move everything from input to output
  - return output.pop()
- time complexity - push - O(1), pop - O(1) amortized, but worst case is O(n) when output is empty

```java
import java.util.Deque;
import java.util.ArrayDeque;

class MyQueue {

    private Deque<Integer> input;
    private Deque<Integer> output;

    public MyQueue() {
        input = new ArrayDeque<>();
        output = new ArrayDeque<>();
    }

    public void push(int x) {
        input.addLast(x);
    }

    public int pop() {
        replenishOutput();
        return output.removeLast();
    }
    
    public int peek() {
        replenishOutput();
        return output.peekLast();
    }

    private void replenishOutput() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.addLast(input.removeLast());
            }
        }
    }
    
    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }
}
```
