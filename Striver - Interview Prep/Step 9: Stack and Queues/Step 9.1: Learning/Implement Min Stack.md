# Implement Min Stack

- maintain a pair at the top of the stack - one is the actual element, while the other is the minimum
- time complexity for all operations stays O(1)
- space complexity - O(2 * n)
- note a good idea for interviews - do not handle boundary cases like what if we pop when stack is empty etc - leave it initially saying we can either return dummy values or throw runtime exceptions based on requirement

```java
class MinStack {

    private static class Node {
        
        int min;
        int ele;

        Node(int min, int ele) {
            this.min = min;
            this.ele = ele;
        }

        @Override
        public String toString() {
            return "(min: " + min + ", ele: " + ele + ")";
        }
    }

    private Deque<Node> stack;

    public MinStack() {
        stack = new ArrayDeque<>();
    }
    
    public void push(int val) {
        stack.addLast(new Node(
            Math.min(getMin(), val),
            val
        ));
    }
    
    public void pop() {
        stack.removeLast();
    }
    
    public int top() {
        return stack.peekLast().ele;
    }
    
    public int getMin() {
        return stack.isEmpty() ? Integer.MAX_VALUE : stack.peekLast().min;
    }
}
```

- we can get rid of the extra space complexity
- we just maintain one element min
- if the incoming element is < the min
  - min = incoming element
  - pushed value = (2 * incoming element) - previous min
- why?
  ```
  incoming element < previous min
  incoming element - previous min < 0
  (2 * incoming element) - previous min < incoming element
  // or in other words
  (2 * incoming element) - previous min < current min
  ```
- now, if top is < min, we know that it is actually a modified value
  - so, actual top / return value of pop would just be min
  - after this, min would be updated to (2 * current min) + pushed value
- stack is full range of integer, so, use long

```java
class MinStack {

    private Deque<Long> stack;
    private long min;

    public MinStack() {
        stack = new ArrayDeque<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int val) {
        if (getMin() < val) {
            stack.addLast((long) val);
        } else {
            stack.addLast(2L * val - getMin());
            min = val;
        }
    }

    public void pop() {
        if (top() != stack.peekLast()) {
            min = 2 * getMin() - stack.peekLast();
        }
        stack.removeLast();
    }

    public int top() {
        return (int) (stack.peekLast() > min ? stack.peekLast() : min);
    }

    public int getMin() {
        return (int) min;
    }
}

```
