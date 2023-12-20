# Points

- question - sort stack using an additional stack only
- complexity - o(n^2)

# Dry Run

- [], [5, 1, 3, 4, 2]
- [2], [5, 1, 3, 4]
- [2, 4], [5, 1, 3]
- [2, 3], [5, 1, 4]
- [2, 3, 4], [5, 1]
- [1], [5, 4, 3, 2]
- [1, 2], [5, 4, 3]
- [1, 2, 3], [5, 4]
- [1, 2, 3, 4], [5]
- [1, 2, 3, 4, 5], []

# Solution

```java
public static void sortStack(Stack<Integer> stack) {

    Stack<Integer> sortedStack = new Stack<>();

    while (!stack.isEmpty()) {
        Integer top = stack.pop();
        while (!sortedStack.isEmpty() && (sortedStack.peek() > top)) {
            stack.push(sortedStack.pop());
        }
        sortedStack.push(top);
    }

    while (!sortedStack.isEmpty()) {
      stack.push(sortedStack.pop());
    }
}
```
