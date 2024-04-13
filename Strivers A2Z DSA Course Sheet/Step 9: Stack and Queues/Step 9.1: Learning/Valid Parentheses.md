# Valid Parentheses

- https://leetcode.com/problems/valid-parentheses/
- stack should be empty at end, otherwise a case like `([]` will pass
- stack's top should be its corresponding 

```java
class Solution {

    private final Map<Character, Character> bracketLookup = Map.of(
        '(', ')',
        '{', '}',
        '[', ']'
    );

    public boolean isValid(String s) {
        
        Deque<Character> stack = new ArrayDeque<>();
        char[] arr = s.toCharArray();
        
        for (char c : arr) {
            if (bracketLookup.containsKey(c)) {
                stack.addLast(c);
            } else {
                if (stack.isEmpty()) return false;
                char topOpening = stack.removeLast();
                if (bracketLookup.get(topOpening) != c) return false;
            }
        }

        return stack.isEmpty();
    }
}
```
