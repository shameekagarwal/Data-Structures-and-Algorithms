# Points

- https://leetcode.com/problems/reverse-string/

# Solution (Using Stacks)

```java
class Solution {
    public void reverseString(char[] s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length; i++) {
            stack.addLast(s[i]);
        }
        for (int i = 0; i < s.length; i++) {
            s[i] = stack.removeLast();
        }
    }
}
```

# Solution

```java
class Solution {
    public void reverseString(char[] s) {
        char tmp;
        int n = s.length;
        for (int i = 0; i < (n / 2); i++) {
            tmp = s[i];
            s[i] = s[n - 1 - i];
            s[n - 1 - i] = tmp;
        }
    }
}

```
