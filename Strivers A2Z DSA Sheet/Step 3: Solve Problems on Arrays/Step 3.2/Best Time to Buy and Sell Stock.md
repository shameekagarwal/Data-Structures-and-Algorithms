# Best Time to Buy and Sell Stock

- https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
- if we sell on day i, we should buy on the day minimum between 0 to i - 1

```java
class Solution {
    public int maxProfit(int[] prices) {
        int minSeenTillNow = Integer.MAX_VALUE;
        int maximumProfitTillNow = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            maximumProfitTillNow = Math.max(maximumProfitTillNow, prices[i] - minSeenTillNow);
            minSeenTillNow = Math.min(minSeenTillNow, prices[i]);
        }
        return maximumProfitTillNow < 0 ? 0 : maximumProfitTillNow;
    }
}
```
