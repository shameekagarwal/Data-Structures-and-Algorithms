# Infix to Prefix Conversion

- first see [Infix to Postfix Conversion](./Infix%20to%20Postfix%20Conversion.md)
- reverse string
- look at the slight changes in algorithm seen in infix to postfix - 
  - exchange `(` and `)` in the algorithm
  - pop from stack till precedence of operator is `>` not `>=`
- reverse output
- note did not see corresponding question in gfg / coding ninjas, but it works, verified for two inputs

```java 
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
 
public class Solution {

  private static final Map<Character, Integer> precedence = Map.of(
      '^', 3,
      '*', 2,
      '/', 2,
      '+', 1,
      '-', 1,
      ')', 0);

  public static void main(String[] args) {
    new Solution().run("x+y*z/w+u"); // ++x/*yzwu
    new Solution().run("k+l-m*n+(o^p)*w/u/v*t+q"); // ++-+kl*mn*//*^opwuvtq
  }

  private void run(String expr) {

    char[] tokens = expr.toCharArray();
    Deque<Character> stack = new ArrayDeque<>();
    StringBuilder prefix = new StringBuilder();

    for (int i = tokens.length - 1; i > -1; i--) {
      if (tokens[i] == ')') {
        stack.addLast(tokens[i]);
      } else if (tokens[i] == '(') {
        while (stack.peekLast() != ')') {
          prefix.append(stack.removeLast());
        }
        stack.removeLast();
      } else if (precedence.containsKey(tokens[i])) {
        while (!stack.isEmpty() && (precedence.get(stack.peekLast()) > precedence.get(tokens[i]))) {
          prefix.append(stack.removeLast());
        }
        stack.addLast(tokens[i]);
      } else {
        prefix.append(tokens[i]);
      }
    }

    while (!stack.isEmpty()) {
      prefix.append(stack.removeLast());
    }

    prefix = prefix.reverse();

    System.out.println(prefix);
  }
}
```
