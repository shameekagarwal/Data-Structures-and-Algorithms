# Postfix to Prefix Conversion

- https://www.codingninjas.com/studio/problems/postfix-to-prefix_1788455

```java
import java.util.Deque;
import java.util.ArrayDeque;

public class Solution {

    public static String postfixToPrefix(String exp) {

        char[] tokens = exp.toCharArray();
        Deque<String> stack = new ArrayDeque<>();

        for (int i = 0; i < tokens.length; i++) {
            if (isOperator(tokens[i])) {
                String operand2 = stack.removeLast();
                String operand1 = stack.removeLast();
                stack.addLast(tokens[i] + operand1 + operand2);
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
