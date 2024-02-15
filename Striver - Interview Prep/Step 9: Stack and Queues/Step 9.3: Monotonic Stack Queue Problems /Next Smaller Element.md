# Next Smaller Element

- https://www.interviewbit.com/problems/nearest-smaller-element/

```java
public class Solution {
    
    public int[] prevSmaller(int[] A) {
        
        Deque<Integer> stack = new ArrayDeque<>();
        int[] result = new int[A.length];
        
        for (int i = 0; i < A.length; i++) {
            while (!stack.isEmpty() && stack.peekLast() >= A[i]) {
                stack.removeLast();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peekLast();
            stack.addLast(A[i]);
        }
        
        return result;
    }
}
```
