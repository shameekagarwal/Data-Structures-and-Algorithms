# Best Time to Buy and Sell Stock IV

- https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
- the same solution for dp + memoization works

```java
class Solution {

    public int maxProfit(int k, int[] prices) {
        
        int n = prices.length;

        int[][][] memo = new int[n][2][k + 1];
        boolean[][][] seen = new boolean[n][2][k + 1];

        return recurse(0, false, k, prices, n, memo, seen);
    }

    private int recurse(int idx, boolean buyState, int transactionsRemaining, int[] prices, int n, int[][][] memo, boolean[][][] seen) {

        if (idx == n) return 0;

        int buyStateIdx = buyState ? 1 : 0;

        if (seen[idx][buyStateIdx][transactionsRemaining]) {
            return memo[idx][buyStateIdx][transactionsRemaining];
        }

        int profit = recurse(idx + 1, buyState, transactionsRemaining, prices, n, memo, seen);

        if (buyState) {
            profit = Math.max(profit, recurse(idx + 1, false, transactionsRemaining, prices, n, memo, seen) + prices[idx]);
        } else {
            if (transactionsRemaining > 0) {
                profit = Math.max(profit, recurse(idx + 1, true, transactionsRemaining - 1, prices, n, memo, seen) - prices[idx]);
            }
        }

        seen[idx][buyStateIdx][transactionsRemaining] = true;
        memo[idx][buyStateIdx][transactionsRemaining] = profit;

        return profit;
    }
}
```
