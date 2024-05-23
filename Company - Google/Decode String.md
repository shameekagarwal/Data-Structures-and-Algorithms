# Decode String

- https://leetcode.com/problems/decode-string/
- my solution - felt cleaner than the editorial tbh
- add an empty string to stack - `[""]`
- process from back
  - why? before replicating a string n times, i should have the actual string right
- case 1 - when we encounter a `]`, add a new empty string to the stack
- case 2 - when we encounter something not a bracket, add the character to the top string of the stack
- case 3 - when we encounter a `[`
  - construct the number before it
  - reverse it and parse it into an integer
  - remove the top of the stack
  - add it to the next top of the stack n number of times
- the final answer is in reversed form at the top of the stack

```java
class Solution {

    public String decodeString(String s) {

        char[] arr = s.toCharArray();

        Deque<StringBuilder> stack = new ArrayDeque<>();
        stack.addLast(new StringBuilder());

        int i = arr.length - 1;

        while (i > -1) {

            if (arr[i] == ']') {
                stack.addLast(new StringBuilder());
                i -= 1;
            } else if (arr[i] == '[') {

                i -= 1;

                StringBuilder timesSerialized = new StringBuilder();

                while (i > -1 && arr[i] >= '0' && arr[i] <= '9') {
                    timesSerialized.append(arr[i]);
                    i -= 1;
                }

                timesSerialized.reverse();

                int times = Integer.parseInt(timesSerialized.toString());
                StringBuilder top = stack.removeLast();

                for (int count = 0; count < times; count++) {
                    stack.peekLast().append(top);
                }
            } else {
                stack.peekLast().append(arr[i]);
                i -= 1;
            }
        }

        StringBuilder top = stack.removeLast();
        top.reverse();
        return top.toString();
    }
}
```
