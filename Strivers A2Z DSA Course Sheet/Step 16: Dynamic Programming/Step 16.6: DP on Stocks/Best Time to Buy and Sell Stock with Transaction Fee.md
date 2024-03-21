# Best Time to Buy and Sell Stock with Transaction Fee

- https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
- inspired buy [Best Time to Buy and Sell Stock II](./Best%20Time%20to%20Buy%20and%20Sell%20Stock%20II.md). just subtract the fee as well for every buy

```java
class Solution {

    public int maxProfit(int[] prices, int fee) {

        int n = prices.length;

        int buyState = - prices[0] - fee;
        int canBuyState = 0;

        for (int i = 1; i < n; i++) {

            int newCanBuyState = Math.max(canBuyState, buyState + prices[i]);
            int newBuyState = Math.max(buyState, canBuyState - prices[i] - fee);

            canBuyState = newCanBuyState;
            buyState = newBuyState;
        }

        return Math.max(buyState, canBuyState);
    }
}

```
