# Minimum Levels to Gain More Points

- https://leetcode.com/problems/minimum-levels-to-gain-more-points/
- first, make bob play all the levels
- then, from 0 to n - 2, one by one add the score to alice and remove it from bob. if score of alice is more than bob, return i + 1 (number of levels alice played from the beginning)

```java
class Solution {

    public int minimumLevels(int[] possible) {

        int n = possible.length;
        int alice = 0;
        int bob = 0;

        for (int i = 0; i < n; i++) {
            bob += (possible[i] == 0 ? -1 : 1);
        }

        for (int i = 0; i < n - 1; i++) {

            alice += (possible[i] == 0 ? -1 : 1);
            bob -= (possible[i] == 0 ? -1 : 1);

            if (alice > bob) return i + 1;
        }

        return -1;
    }
}
```
