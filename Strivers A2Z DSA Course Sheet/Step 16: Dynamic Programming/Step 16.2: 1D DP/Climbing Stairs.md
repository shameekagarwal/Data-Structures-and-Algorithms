# Climbing Stairs

- https://leetcode.com/problems/climbing-stairs/
- like fibonacci, but 0th term is 1 and 1st term is 1 (in fibonacci 0th term is 0 and 1st term is 1)
  ```
  1 1 2 3 4 5
  0 1 2 3 5 6
  ```
- all possible ways / best way - recursion / dp

```java
class Solution {

    public int climbStairs(int n) {
        
        int beforePrevious = 1;
        int previous = 1;

        for (int i = 2; i <= n; i++) {
            int earlierPrevious = previous;
            previous = previous + beforePrevious;
            beforePrevious = earlierPrevious;
        }

        return previous;
    }
}
```
