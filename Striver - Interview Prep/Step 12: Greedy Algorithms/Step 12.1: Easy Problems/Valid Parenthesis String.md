# Valid Parenthesis String

- https://leetcode.com/problems/valid-parenthesis-string/
- there is another greedy approach, which will work with O(1) space, skipping for now

## Based on Valid Parentheses

- we will use stack like in [Valid Parentheses](../../Step%209:%20Stack%20and%20Queues/Step%209.1:%20Learning/Valid%20Parentheses.md)
- note - "the position is important for opening brackets" unlike in valid parentheses
- we should try and exhaust the actual `(` using `)` first because `*` can be converted to anything
- recall we stored character and not indices in valid parentheses
- we need to store indices because when we are iterating normally and exhausting `(` or `*` using `)`, we can be sure that both `(` and `*` occur before the current encountered `)` in the string
- however, the same is not true when converting `*` to `)` - we do not know if `*` is before or after `(`, but we need the `*` to come after so that it can act like a `)`

```java
class Solution {

    public boolean checkValidString(String s) {
        
        Deque<Integer> opening = new ArrayDeque<>();
        Deque<Integer> stars = new ArrayDeque<>();

        char[] characters = s.toCharArray();

        for (int i = 0; i < characters.length; i++) {
            if (characters[i] == '(') {
                opening.addLast(i);
            } else if (characters[i] == '*') {
                stars.addLast(i);
            } else {
                if (!opening.isEmpty()) {
                    opening.removeLast();
                } else if (!stars.isEmpty()) {
                    stars.removeLast();
                } else {
                    return false;
                }
            }
        }

        while (!opening.isEmpty()) {
            if (stars.isEmpty() || stars.peekLast() < opening.peekLast()) {
                return false;
            }
            stars.removeLast();
            opening.removeLast();
        }

        return true;
    }
}
```
