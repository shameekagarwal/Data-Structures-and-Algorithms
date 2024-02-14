# Infix to Postfix Conversion

- https://www.codingninjas.com/studio/problems/day-23-:-infix-to-postfix-_1382146
- algorithm - 
  - if `(`, push to stack
  - if `)`, pop from stack and add to output till `(` is encountered
  - if an operator is encountered, pop from stack and add to output till 
    - stack is not empty and
    - top of stack has precedence >= than incoming operator
  - if operand add to output

```java 
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Map;

public class Solution {

    private static final Map<Character, Integer> precedence = Map.of(
        '^', 3,
        '*', 2,
        '/', 2,
        '+', 1,
        '-', 1,
        '(', 0
    );

    public static String infixToPostfix(String exp) {

        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder postfix = new StringBuilder();

        for (char c : exp.toCharArray()) {
            if (c == '(') {
                stack.addLast(c);
            } else if (c == ')') {
                while (stack.peekLast() != '(') {
                    postfix.append(stack.removeLast());
                }
                stack.removeLast();
            } else if (precedence.containsKey(c)) {
                while (!stack.isEmpty() && (precedence.get(stack.peekLast()) >= precedence.get(c))) {
                    postfix.append(stack.removeLast());
                }
                stack.addLast(c);   
            } else {
                postfix.append(c);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.removeLast());
        }

        return postfix.toString();
    }
}
```
