# Reverse a stack using recursion

- https://www.codingninjas.com/studio/problems/reverse-stack-using-recursion_631875
- question states only extra space = extra space due to recursion
- pop the top
- reverse everything in the stack currently
- insert this top at stack's bottom

```java
import java.util.Stack;

public class Solution {
    
	public static void reverseStack(Stack<Integer> stack) {
		if (stack.isEmpty()) return;
		int element = stack.pop();
		reverseStack(stack);
		insertBottom(stack, element);
	}

	private static void insertBottom(Stack<Integer> stack, int element) {
		if (stack.isEmpty()) {
			stack.push(element);
			return;
		}
		int top = stack.pop();
		insertBottom(stack, element);
		stack.push(top);
	}
}
```
