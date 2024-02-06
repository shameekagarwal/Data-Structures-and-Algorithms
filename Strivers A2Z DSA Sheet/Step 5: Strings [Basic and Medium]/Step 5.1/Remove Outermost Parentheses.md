# Remove Outermost Parentheses

- https://leetcode.com/problems/remove-outermost-parentheses/

```java
class Solution {
    public String removeOuterParentheses(String s) {
        
        StringBuilder sb = new StringBuilder();
        int openCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                openCount += 1;
                if (openCount != 1) {
                    sb.append(s.charAt(i));
                }
            } else {
                openCount -= 1;
                if (openCount != 0) {
                    sb.append(s.charAt(i));
                }
            }
        }
        return sb.toString();
    }
}
```
