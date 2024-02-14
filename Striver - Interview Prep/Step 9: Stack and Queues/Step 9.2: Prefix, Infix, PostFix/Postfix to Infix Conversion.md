# Postfix to Infix Conversion

- https://www.geeksforgeeks.org/problems/postfix-to-infix-conversion/1
- technique already discussed [here](./Prefix,%20Infix,%20PostFix.md)

```java
class Solution {
    
    static String postToInfix(String exp) {
        
        char[] tokens = exp.toCharArray();
        Deque<String> stack = new ArrayDeque<>();
        
        for (int i = 0; i < tokens.length; i++) {
            if (isAlphaNumeric(tokens[i])) {
                stack.add(String.valueOf(tokens[i]));
            } else {
                String operand2 = stack.removeLast();
                String operand1 = stack.removeLast();
                stack.addLast('(' + operand1 + tokens[i] + operand2 + ')');
            }
        }

        return stack.removeLast();
    }

    private static boolean isAlphaNumeric(char c) {
        return isAlphabetic(c) || isNumeric(c);
    }

    private static boolean isAlphabetic(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
    
    private static boolean isNumeric(char c) {
        return (c >= '0' && c <= '9');
    }
}
```
