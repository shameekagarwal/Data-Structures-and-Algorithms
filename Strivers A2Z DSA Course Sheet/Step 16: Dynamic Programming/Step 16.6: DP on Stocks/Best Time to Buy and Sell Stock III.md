# Best Time to Buy and Sell Stock III

- https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
- think like [Best Time to Buy and Sell Stock II](./Best%20Time%20to%20Buy%20and%20Sell%20Stock%20II.md)
- but, introduce a third counter variable, which can go up to 2 only
- so, memoized + recursive solution - O(3 * 2 * N) - 3 because number of transactions have three possible values, 2 because of 2 possible states of already bought

```java
class Solution {

    private static final int NO_OF_TRANSACTIONS_PERMITTED = 2;

    private int[] prices;
    private int[][][] memo;
    private boolean[][][] seen;

    public int maxProfit(int[] prices) {
        this.prices = prices;
        seen = new boolean[prices.length][2][NO_OF_TRANSACTIONS_PERMITTED + 1];
        memo = new int[prices.length][2][NO_OF_TRANSACTIONS_PERMITTED + 1];
        return maxProfit(0, false, NO_OF_TRANSACTIONS_PERMITTED);
    }

    private int maxProfit(int idx, boolean alreadyBought, int transactionsRemaining) {
        
        if (idx == prices.length) return 0;

        int alreadyBoughtIdx = alreadyBought ? 1 : 0;

        if (seen[idx][alreadyBoughtIdx][transactionsRemaining]) {
            return memo[idx][alreadyBoughtIdx][transactionsRemaining];
        }

        int profit = maxProfit(idx + 1, alreadyBought, transactionsRemaining);

        if (alreadyBought) {
            profit = Math.max(profit, maxProfit(idx + 1, false, transactionsRemaining) + prices[idx]);
        } else {
            if (transactionsRemaining > 0) {
                profit = Math.max(profit, maxProfit(idx + 1, true, transactionsRemaining - 1) - prices[idx]);
            }
        }

        memo[idx][alreadyBoughtIdx][transactionsRemaining] = profit;
        seen[idx][alreadyBoughtIdx][transactionsRemaining] = true;

        return profit;
    }
}
```

- unable to convert to tabular. another solution - 
- calculate max profit we can make in terms of suffix and prefix
- final answer is max possible `pre[i] + suf[i + 1]`
- edge case - 1 transaction, e.g. 1 2 3 4 5. for this, only use last element of pre / first element of suf

```java
class Solution {

    public int maxProfit(int[] prices) {

        int[] prefixProfit = new int[prices.length];
        int minTillNow = prices[0];
        for (int i = 1; i < prices.length; i++) {
            prefixProfit[i] = Math.max(prefixProfit[i - 1], prices[i] - minTillNow);
            minTillNow = Math.min(minTillNow, prices[i]);
        }

        int[] suffixProfit = new int[prices.length];
        int maxTillNow = prices[prices.length - 1];
        for (int i = prices.length - 2; i > -1; i--) {
            suffixProfit[i] = Math.max(suffixProfit[i + 1], maxTillNow - prices[i]);
            maxTillNow = Math.max(maxTillNow, prices[i]);
        }

        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            profit = Math.max(profit, suffixProfit[i + 1] + prefixProfit[i]);
        }

        profit = Math.max(profit, prefixProfit[prices.length - 1]);
        return profit;
    }
}
```
