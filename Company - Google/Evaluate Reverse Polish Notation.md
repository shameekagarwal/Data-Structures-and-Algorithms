# Evaluate Reverse Polish Notation

- https://leetcode.com/problems/evaluate-reverse-polish-notation/

```java
class Solution {

    private static final Set<String> operators = Set.of("/", "*", "+", "-");

    public int evalRPN(String[] tokens) {

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < tokens.length; i++) {

            if (operators.contains(tokens[i])) {
                int a = stack.removeLast();
                int b = stack.removeLast();
                stack.addLast(perform(b, a, tokens[i]));
            } else {
                stack.addLast(Integer.parseInt(tokens[i]));
            }
        }

        return stack.removeLast();
    }

    private int perform(int a, int b, String operator) {

        switch (operator) {
            case "/":
                return a / b;
            case "*":
                return a * b;
            case "+":
                return a + b;
            case "-":
                return a - b;
        }

        throw new RuntimeException("unsupported operator");
    }
}
```
