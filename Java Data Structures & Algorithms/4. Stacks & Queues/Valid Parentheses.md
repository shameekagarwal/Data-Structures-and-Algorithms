# Points

- https://leetcode.com/problems/valid-parentheses/

# Solution

```java
class Solution {

    public boolean isValid(String s) {

        Deque<Character> deque = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                deque.addLast(s.charAt(i));
            } else if (s.charAt(i) == ')') {
                if (deque.isEmpty() || deque.peekLast() != '(') return false;
                deque.removeLast();
            } else if (s.charAt(i) == '}') {
                if (deque.isEmpty() || deque.peekLast() != '{') return false;
                deque.removeLast();
            } else if (s.charAt(i) == ']') {
                if (deque.isEmpty() || deque.peekLast() != '[') return false;
                deque.removeLast();
            }
        }

        return deque.isEmpty();
    }
}
```
