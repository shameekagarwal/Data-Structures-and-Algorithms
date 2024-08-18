# Apply Operations to Make Two Strings Equal

- https://leetcode.com/problems/apply-operations-to-make-two-strings-equal/
- three pieces of state - idx, was adjacent flipped and the earlier index that was flipped
- if adjacent was flipped, we have to flip as well
- at this point, if bits are same - great, continue
- else
  - try by flipping adjacent
  - if earlier distant was flipped, flip current and turn "earlier index flipped" back to -1
  - if no earlier distant has been currently flipped, and set "earlier index flipped" to current index
- at the end - adjacent should be false and "earlier index flipped" should be -1 - otherwise, our series of operations were invalid

```java
class Solution {

    public int minOperations(String s1, String s2, int x) {

        int[][][] dp = new int[s1.length()][2][s1.length()];
        boolean[][][] seen = new boolean[s1.length()][2][s1.length() + 1];

        return minOperations(s1.toCharArray(), s2.toCharArray(), 0, false, -1, x, dp, seen);
    }

    public int minOperations(char[] s1, char[] s2, int idx, boolean flipWithAdjacent, 
        int flipWithDistant, int x, int[][][] dp, boolean[][][] seen) {

        if (idx == s1.length) {

            if (flipWithAdjacent || flipWithDistant != -1) {
                return -1;
            }

            return 0;
        }

        int flipWithAdjacentIdx = flipWithAdjacent ? 1 : 0;
        int flipWithDistantIdx = flipWithDistant + 1;

        if (seen[idx][flipWithAdjacentIdx][flipWithDistantIdx]) {
            return dp[idx][flipWithAdjacentIdx][flipWithDistantIdx];
        }

        int b1 = s1[idx] - '0';
        int b2 = s2[idx] - '0';

        if (flipWithAdjacent) {
            b1 = 1 - b1;
        }

        int result = -1;

        if (b1 == b2) {
            result = minOperations(s1, s2, idx + 1, false, flipWithDistant, x, dp, seen);
        } else {

            int possiblity1 = minOperations(s1, s2, idx + 1, true, flipWithDistant, x, dp, seen);

            if (possiblity1 != -1) {

                if (result == -1 || result > possiblity1 + 1) {
                    result = possiblity1 + 1;
                }
            }

            if (flipWithDistant != -1) {

                int possiblity2 = minOperations(s1, s2, idx + 1, false, -1, x, dp, seen);

                if (possiblity2 != -1) {

                    if (result == -1 || result > possiblity2 + x) {
                        result = possiblity2 + x;
                    }
                }

            } else {

                int possiblity2 = minOperations(s1, s2, idx + 1, false, idx, x, dp, seen);

                if (possiblity2 != -1) {

                    if (result == -1 || result > possiblity2) {
                        result = possiblity2;
                    }
                }
            }
        }

        seen[idx][flipWithAdjacentIdx][flipWithDistantIdx] = true;
        dp[idx][flipWithAdjacentIdx][flipWithDistantIdx] = result;

        return result;
    }
}
```
