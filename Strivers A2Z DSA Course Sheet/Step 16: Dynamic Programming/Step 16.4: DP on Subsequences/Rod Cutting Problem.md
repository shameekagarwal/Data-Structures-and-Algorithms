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

## Solution 2

- recursive - initialize result(i) as price(i)
- then, try to maximize it using
  - result(1) + result(i - 1)
  - result(2) + result(i - 2)
  - result(3) + result(i - 3)

```java
import java.util.Arrays;

public class Solution {

	public static int cutRod(int price[], int n) {
		int[] dp = new int[n + 1];
		Arrays.fill(dp, -1);
		return recurse(n, price, dp);
	}

	private static int recurse(int length, int[] price, int[] dp) {

		if (dp[length] != -1) {
			return dp[length];
		}

		int result = price[length - 1];

		for (int i = 1; i <= length / 2; i++) {
			result = Math.max(result, recurse(i, price, dp) + recurse(length - i, price, dp));
		}

		dp[length] = result;

		return result;
	}
}
```

now, converted to iterative - 

```java
import java.util.Arrays;

public class Solution {

	public static int cutRod(int price[], int n) {

		int[] dp = new int[n + 1];

		for (int len = 1; len <= n; len++) {

			dp[len] = price[len - 1];

			for (int i = 1; i <= len / 2; i++) {
				dp[len] = Math.max(dp[len], dp[len - i] + dp[i]);
			}
		}

		return dp[n];
	}
}
```
