# Valid Parentheses

- https://leetcode.com/problems/valid-parentheses/
- stack should be empty at end, otherwise a case like `([]` will pass
- stack's top should be its corresponding 

```java
class Solution {

    public boolean isValid(String s) {

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.addLast(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.removeLast() != '(') return false;
            } else if (c == '}') {
                if (stack.isEmpty() || stack.removeLast() != '{') return false;
            } else if (c == ']') {
                if (stack.isEmpty() || stack.removeLast() != '[') return false;
            }
        }

        return stack.isEmpty();
    }
}
```
