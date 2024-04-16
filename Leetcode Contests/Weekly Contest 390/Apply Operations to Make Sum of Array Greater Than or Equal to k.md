# Apply Operations to Make Sum of Array Greater Than or Equal to k

- https://leetcode.com/problems/apply-operations-to-make-sum-of-array-greater-than-or-equal-to-k/
- just by observation - basically, first increment 1 up to ceil(square root)
- then, duplicate this till we cross k
- e.g. to get to 8
  - ceil(square root) is 3
  - to get to 3, we need 3 - 1 = 2 operations
  - now, we need to duplicate this 3 (9 / 3 - 1) "more" times = 2 operations
  - so, total = 4 operations
- actual proof behind it - 
  - k <= (1 + increments) * (duplications + 1)
  - k <= 1 + (increments * duplications) + (increments + duplications)
  - our job - minimizing (a + b) (given in question), and maximizing (a * b) (as a conclusion of above expression)
  - this can be done by bringing a and b as close to each other as possible
- e.g. - 4 x 4 = 16, 3 x 5 = 15, 2 x 6 = 12, 1 x 7 = 7

```java
class Solution {

    public int minOperations(int k) {

        int numberToForm = (int) Math.ceil(Math.sqrt(k));

        int increments = numberToForm - 1;
        int duplications = (int) Math.ceil((double) k / numberToForm) - 1;

        return increments + duplications;
    }
}
```
