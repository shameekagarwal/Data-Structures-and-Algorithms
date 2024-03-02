# Best Time to Buy and Sell Stock with Cooldown

- https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
- same as [Best Time to Buy and Sell Stock II](./Best%20Time%20to%20Buy%20and%20Sell%20Stock%20II.md)
- but now, i introduce a third boolean, which indicates if the stock was sold on the last index
- time complexity of memoized solution - O(4*N)

```java
class Solution {

    private int[] prices;

    public int maxProfit(int[] prices) {
        this.prices = prices;
        return maxProfit(0, false, false);
    }

    private int maxProfit(int idx, boolean alreadyBought, boolean soldAtLastIdx) {

        if (idx == prices.length) return 0;

        int profit = maxProfit(idx + 1, alreadyBought, false);

        if (alreadyBought) {
            profit = Math.max(profit, maxProfit(idx + 1, false, true) + prices[idx]);
        } else {
            if (!soldAtLastIdx) {
                profit = Math.max(profit, maxProfit(idx + 1, true, false) - prices[idx]);
            }
        }

        return profit;
    }
}
```

- another solution - jump to idx + 2 when selling

```java
class Solution {

    private int[] prices;
    private int[][] memo;
    private boolean[][] seen;

    public int maxProfit(int[] prices) {
        this.prices = prices;
        memo = new int[prices.length][2];
        seen = new boolean[prices.length][2];
        return maxProfit(0, false);
    }

    private int maxProfit(int idx, boolean alreadyBought) {

        if (idx > prices.length - 1) return 0;

        int alreadyBoughtIdx = alreadyBought ? 1 : 0;

        if (seen[idx][alreadyBoughtIdx]) {
            return memo[idx][alreadyBoughtIdx];
        }

        int profit = maxProfit(idx + 1, alreadyBought);

        if (alreadyBought) {
            profit = Math.max(profit, maxProfit(idx + 2, false) + prices[idx]);
        } else {
            profit = Math.max(profit, maxProfit(idx + 1, true) - prices[idx]);
        }

        memo[idx][alreadyBoughtIdx] = profit;
        seen[idx][alreadyBoughtIdx] = true;

        return profit;
    }
}
```

- tabular + space optimized

```java
class Solution {

    private int[] prices;
    private int[][] memo;
    private boolean[][] seen;

    public int maxProfit(int[] prices) {

        int[][] dp = new int[3][2];

        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {

            int previousIdx = ((i % 3) + 2) % 3;
            int beforePreviousIdx = ((i % 3) + 1) % 3;

            dp[i % 3][1] = Math.max(dp[previousIdx][1], dp[beforePreviousIdx][0] - prices[i]);
            dp[i % 3][0] = Math.max(dp[previousIdx][0], dp[previousIdx][1] + prices[i]);
        }

        return dp[(prices.length - 1) % 3][0];
    }
}
```
