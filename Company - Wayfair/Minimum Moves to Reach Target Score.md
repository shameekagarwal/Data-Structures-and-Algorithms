# Minimum Moves to Reach Target Score

- https://leetcode.com/problems/minimum-moves-to-reach-target-score/
- go backward i.e. reach 1 from target
- greedy - while doubles are left, go from from target to target / 2
- if max doubles % 2 = 0, we need one double operation to go from target / 2 to target
- else, we need one double operation to get to target - 1 and then an increment operation to get to target

```java
class Solution {

    public int minMoves(int target, int maxDoubles) {

        int moves = 0;

        while (target / 2 >= 1 && maxDoubles > 0) {
            moves += 1;
            moves += (target % 2);
            target /= 2;
            maxDoubles -= 1;
        }

        moves += (target - 1);

        return moves;
    }
}
```
