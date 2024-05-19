# Happy Number

- https://leetcode.com/problems/happy-number/
- a number should not grow indefinitely - we just add squares of digits - so biggest number can be 9^2 some t times, suh that `9^ * t` fits in an integer
- now there can be cycles, e.g. 2 - 
  ```
  2 4 16 37 58 89 145 42 20 4 ... (and repeats from this point on)
  ```
- so, we can use a hash set to detect cycles and break out of the loop
- time complexity - calculation of the next number takes log(n) time. number of digits in a number is also log(n), so the next time, n is same as log(n). so, total complexity is  - 
  ```
  log(n) + log(log(n)) + log(log(log(n))) + log(log(log(log(n)))) + ...
  ```
- note - we can also use floyd's cycle detection algorithm, by re imagining this problem like a lined list!
  - this solution might be useful if the interviewer wants "constant space"

```java
class Solution {

    public boolean isHappy(int n) {

        Set<Integer> seen = new HashSet<>();

        while (!seen.contains(n)) {

            seen.add(n);

            int result = 0;

            while (n > 0) {
                result += (int) Math.pow(n % 10, 2);
                n /= 10;
            }

            if (result == 1) {
                return true;
            }

            // System.out.printf("%d ", result);

            n = result;
        }

        // System.out.println();

        return false;
    }
}
```
