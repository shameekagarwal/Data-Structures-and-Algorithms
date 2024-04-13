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
        int n = asteroids.length;

        for (int i = 0; i < n; i++) {
            if (asteroids[i] > 0) {
                stack.addLast(asteroids[i]);
            } else {

                int value = -asteroids[i];
                boolean destroyed = false;

                while (!stack.isEmpty() && stack.peekLast() > 0 && !destroyed) {
                    if (stack.peekLast() < value) {
                        stack.removeLast();
                    } else if (stack.peekLast() == value) {
                        stack.removeLast();
                        destroyed = true;
                    } else {
                        destroyed = true;
                    }
                }

                if (!destroyed) {
                    stack.addLast(asteroids[i]);
                }
            }
        }

        int m = stack.size();
        int[] result = new int[m];
        for (int i = 0; i < m; i++) {
            result[i] = stack.removeFirst();
        }
        return result;
    }
}
```
