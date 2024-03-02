# Best Time to Buy and Sell Stock

- https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
- for selling on ith day, buy on the day when the price is minimum from 0 to i-1

```java
class Solution {

    public int maxProfit(int[] prices) {

        int maxProfit = 0;
        int minPrice = prices[0];

        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }

        return maxProfit;
    }
}
```
