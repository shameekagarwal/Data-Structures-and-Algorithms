# Sort a stack using recursion

- https://www.codingninjas.com/studio/problems/sort-stack_1229505
- not mutating the original input
- brute / naive - store in an array, sort it and return
- insert sorted
  - insert an element in sorted position
  - keep popping till stack isn't empty / stack's top does become less than element to insert
  - push the element and return
  - start adding back all the intermediate popped elements
- call insert sorted for every element of the stack one by one
- i also try and ensure that the input remains unchanged
- time complexity - O(n^2)

```java
import java.util.* ;
import java.io.*;

public class Solution {

	public static Stack<Integer> sortStack(Stack<Integer> s) {
		Stack<Integer> result = new Stack<>();
		sortStack(result, s);
		return result;
	}

	private static void sortStack(Stack<Integer> sorted, Stack<Integer> input) {
		if (input.isEmpty()) return;
		int top = input.pop();
		sortStack(sorted, input);
		insertSorted(sorted, top);
		input.push(top);
	}

	private static void insertSorted(Stack<Integer> sorted, int element) {
		if (sorted.isEmpty() || sorted.peek() <= element) {
			sorted.push(element);
			return;
		}
		int top = sorted.pop();
		insertSorted(sorted, element);
		sorted.push(top);
	}
}
```
