# Prefix to Postfix Conversion

- https://www.codingninjas.com/studio/problems/convert-prefix-to-postfix_8391014

```java
import java.util.Deque;
import java.util.ArrayDeque;

public class Solution {
    
    public static String preToPost(String s) {
        
        char[] tokens = s.toCharArray();
        Deque<String> stack = new ArrayDeque<>();

        for (int i = tokens.length - 1; i > -1; i--) {
            if (isOperator(tokens[i])) {
                String operand1 = stack.removeLast();
                String operand2 = stack.removeLast();
                stack.addLast(operand1 + operand2 + tokens[i]);
            } else {
                stack.addLast(String.valueOf(tokens[i]));
            }
        }

        return stack.removeLast();
    }

    private static boolean isOperator(char c) {
        return (c == '/') || (c == '*') || (c == '+') || (c == '-');
    }
}
```
