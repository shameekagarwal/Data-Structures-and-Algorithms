# Simplify Path

- https://leetcode.com/problems/simplify-path/

```java
class Solution {

    private static final String GO_UP = "..";
    private static final String STAY = ".";

    public String simplifyPath(String path) {

        char[] arr = path.toCharArray();

        int i = 0;

        Deque<String> deque = new ArrayDeque<>();

        while (i < arr.length) {

            StringBuilder currentDirectory = new StringBuilder();

            while (i < arr.length && arr[i] == '/') {
                i += 1;
            }

            while (i < arr.length && arr[i] != '/') {
                currentDirectory.append(arr[i]);
                i += 1;
            }

            if (GO_UP.equals(currentDirectory.toString())) {
                if (!deque.isEmpty()) {
                    deque.removeLast();
                }
            } else if (!STAY.equals(currentDirectory.toString())) {
                deque.addLast(currentDirectory.toString());
            }

            while (i < arr.length && arr[i] == '/') {
                i += 1;
            }
        }

        StringBuilder result = new StringBuilder();
        result.append('/');

        while (!deque.isEmpty()) {

            result.append(deque.removeFirst());

            if (!deque.isEmpty()) {
                result.append('/');
            }
        }

        return result.toString();
    }
}
```
