# Rod Cutting Problem

- https://www.codingninjas.com/studio/problems/rod-cutting-problem_800284
- rethink of it like [Unbounded Knapsack](./Unbounded%20Knapsack.md)
- weight are the different lengths of rods, values are the prices given
- we can break into any piece configuration, and want the maximum price

```java
public class Solution {

	public static int cutRod(int price[], int n) {

		int[] dp = new int[n + 1];

		for (int i = 1; i <= n; i++) {

			for (int j = i; j <= n; j++) {
				dp[j] = Math.max(dp[j - i] + price[i - 1], dp[j]);
			}
		}

		return dp[n];
	}
}
```
