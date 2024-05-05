# Find All Possible Stable Binary Arrays I

- https://leetcode.com/problems/find-all-possible-stable-binary-arrays-i/
- array length will be zero + one
- if the consecutive 0s has not reached limit, we can place 0 at current index
- same for 1
- so, changing parameters - 
  - idx
  - number placed at last index
  - count of consecutive number placed at last index
  - zeroes consumed
- using all above, we can easily calculate ones consumed, ones remaining and zeros remaining

```java
class Solution {

    private static final int MOD = 1000000007;

    public int numberOfStableArrays(int zero, int one, int limit) {

        int total = zero + one;

        int[][][][] dp = new int[total][2][limit + 1][zero + 1];

        for (int i = 0; i < total; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < limit + 1; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }

        return recurse(0, 0, 0, 0, limit, zero, one, dp);
    }

    private int recurse(int idx, int lastPlaced, int consecutive, int zerosConsumed, int limit, int zero, int one, int[][][][] dp) {

        if (idx == zero + one) {
            return 1;
        }

        if (dp[idx][lastPlaced][consecutive][zerosConsumed] != -1) {
            return dp[idx][lastPlaced][consecutive][zerosConsumed];
        }

        int onesConsumed = idx - zerosConsumed;
        int zerosRemaining = zero - zerosConsumed;
        int onesRemaining = one - onesConsumed;

        int result = 0;

        if (zerosRemaining > 0 && !(consecutive == limit && lastPlaced == 0)) {
            int nextConsecutive = lastPlaced == 0 ? consecutive + 1 : 1;
            result += recurse(idx + 1, 0, nextConsecutive, zerosConsumed + 1, limit, zero, one, dp);
        }

        if (onesRemaining > 0 && !(consecutive == limit && lastPlaced == 1)) {
            int nextConsecutive = lastPlaced == 1 ? consecutive + 1 : 1;
            result = (result + recurse(idx + 1, 1, nextConsecutive, zerosConsumed, limit, zero, one, dp)) % MOD;
        }

        dp[idx][lastPlaced][consecutive][zerosConsumed] = result;

        return result;
    }
}
```
