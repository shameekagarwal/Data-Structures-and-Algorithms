# Best Time to Buy and Sell Stock II

- https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
- recursion - we need to carry a boolean "buy"
- if bought, we can either sell or not sell
- if not bought, we can either not buy or buy
- note - both have a case of "do nothing" - we can make it common
- when we buy, we essentially subtract `prices[i]` from profit
- when we sell, we essentially add `prices[i]` to profit
- time complexity - O(2^n * 2)? - 2 possibilities for every index

```java
class Solution {

    private int[] prices;

    public int maxProfit(int[] prices) {
        this.prices = prices;
        return maxProfit(0, false);
    }

    private int maxProfit(int idx, boolean alreadyBought) {

        if (idx == prices.length) return 0;

        int profit = maxProfit(idx + 1, alreadyBought);

        if (alreadyBought) {
            profit = Math.max(profit, maxProfit(idx + 1, false) + prices[idx]);
        } else {
            profit = Math.max(profit, maxProfit(idx + 1, true) - prices[idx]);
        }

        return profit;
    }
}
```

```
          0,F
    
     1,F           1,T

  2,F   2,T      2,T   2,F
```

- memoization - O(n * 2), space - O(2 * n) + auxiliary of O(N) for stack

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

        if (idx == prices.length) return 0;

        int alreadyBoughtIdx = alreadyBought ? 1 : 0;

        if (seen[idx][alreadyBoughtIdx]) {
            return memo[idx][alreadyBoughtIdx];
        }

        int profit = maxProfit(idx + 1, alreadyBought);

        if (alreadyBought) {
            profit = Math.max(profit, maxProfit(idx + 1, false) + prices[idx]);
        } else {
            profit = Math.max(profit, maxProfit(idx + 1, true) - prices[idx]);
        }

        seen[idx][alreadyBoughtIdx] = true;
        memo[idx][alreadyBoughtIdx] = profit;

        return profit;
    }
}
```

- tabular - 

```java
class Solution {

    public int maxProfit(int[] prices) {

        int[][] dp = new int[prices.length][2];
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return Math.max(dp[prices.length - 1][0], dp[prices.length - 1][1]);
    }
}
```

- space optimized - 

```java
class Solution {

    public int maxProfit(int[] prices) {

        int[][] dp = new int[prices.length][2];
        
        int buyState = -prices[0];
        int canBuyState = 0;

        for (int i = 1; i < prices.length; i++) {
            
            int newBuyState = Math.max(buyState, canBuyState - prices[i]);
            int newCanBuyState = Math.max(canBuyState, buyState + prices[i]);

            buyState = newBuyState;
            canBuyState = newCanBuyState;
        }

        return Math.max(buyState, canBuyState);
    }
}
```

- my solution (not dp) - if i think of it like a graph, it is easy to see - buy at all minima and sell at all maxima
- e.g. i have a slope - 1,3,7 - best i can do is buy at 1 and sell at 7 - i cannot do better than this
- so, we have a maxima if `prices[i] < prices[i - 1]`
- so, `prices[i - 1]` becomes the maxima for its earlier minima, and `prices[i]` becomes the minima
- the last profit calculation outside the loop is needed for cases when there is a maxima at end, e.g. `3,1,4`
- dry run for examples like this - `7,6,5,1,2,5,3,10,7,5,1`

```java
class Solution {

    public int maxProfit(int[] prices) {

        int profit = 0;

        int minOfCurrentSlope = prices[0];

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < prices[i - 1]) {
                profit += (prices[i - 1] - minOfCurrentSlope);
                minOfCurrentSlope =  prices[i];
            }
        }
        profit += (prices[prices.length - 1] - minOfCurrentSlope);

        return profit;
    }
}
```
