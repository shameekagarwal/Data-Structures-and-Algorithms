# Asteroid Collision

- https://leetcode.com/problems/asteroid-collision/
- if positive asteroid is encountered, simply add it
- if negative asteroid is encountered
  - remove all previous smaller positive asteroids
  - if stack is empty add the negative asteroid
  - else
    - if stack top is negative, add the negative asteroid
    - else
      - if stack top contains a positive asteroid of the same power, remove it

```java
class Solution {

    public int[] asteroidCollision(int[] asteroids) {
        
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] < 0) {

                while (!stack.isEmpty() && stack.peekLast() > 0 && stack.peekLast() < Math.abs(asteroids[i])) {
                    stack.removeLast();
                }

                if (!stack.isEmpty()) {
                    if (stack.peekLast() < 0) {
                        stack.addLast(asteroids[i]);
                    } else {
                        if (stack.peekLast() == Math.abs(asteroids[i])) {
                            stack.removeLast();
                        }
                    }
                } else {
                    stack.addLast(asteroids[i]);
                }
            } else {
                stack.addLast(asteroids[i]);
            }
        }

        int[] result = new int[stack.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = stack.removeFirst();
        }
        return result;
    }
}
```
