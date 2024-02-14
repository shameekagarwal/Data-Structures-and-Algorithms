# Prefix to Infix Conversion

- https://www.codingninjas.com/studio/problems/prefix-to-infix_1215000
- technique already discussed [here](./Prefix,%20Infix,%20PostFix.md)

```java
import java.util.Deque;
import java.util.ArrayDeque;

public class Solution {

    public static String prefixToInfixConversion(String exp) {

        Deque<String> stack = new ArrayDeque<>();
        char[] tokens = exp.toCharArray();

        for (int i = tokens.length - 1; i > -1; i--) {
            if (tokens[i] == '/' || tokens[i] == '*' || tokens[i] == '+' || tokens[i] == '-') {
                stack.addLast('(' + stack.removeLast() + tokens[i] + stack.removeLast() + ')');
            } else {
                stack.addLast(String.valueOf(tokens[i]));
            }
        }
        return stack.removeLast().toString();
    }
}
```
