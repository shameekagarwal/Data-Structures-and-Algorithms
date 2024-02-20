# Maximum Nesting Depth of the Parentheses

- https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/

```java
class Solution {
    public int maxDepth(String s) {
        int maxCount = 0;
        int currentCount = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                currentCount += 1;
                maxCount = Math.max(currentCount, maxCount);
            } else if (c == ')') {
                currentCount -= 1;
            }
        }
        return maxCount;
    }
}
```
