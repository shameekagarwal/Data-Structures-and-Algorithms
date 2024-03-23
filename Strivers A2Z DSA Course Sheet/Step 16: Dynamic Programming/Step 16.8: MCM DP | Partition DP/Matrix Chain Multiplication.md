# Matrix Chain Multiplication

- https://www.codingninjas.com/studio/problems/matrix-chain-multiplication_624880
- partition dp is not easy - first problem is mcm or matrix chain multiplication
- "no of operations" when multiplying two matrices - `a*b`, and `b*c` - no of operations are `a*b*c`
- now assume we multiply 3 matrices - x (a,b) y (b,c) and z (c,d). number of operations can be - 
  - `(x * y) * z` - `(a * b * c) + (a * c * d)`
  - `x * (y * z)` - `(b * c * d) + (a * b * d)`
- now, the input format is also strange
- e.g. we have the following array to solve for - 1 2 3 4. we can break it into -
  - 1 2 3, 3 4 - `(a * b) * c` - `f(1, 3) + f(3, 4) + p1 * p3 * p4`
  - 1 2, 2 3 4 - `a * (b * c)` - `f(1, 2) + f(2, 4) + p1 * p2 * p4` 
- time complexity - O(N^3) - loop of n runs, 2 states

```java
public class Solution {

	public static int mcm(int[] p) {
		return recurse(p, 0, p.length - 1);
	}

	private static int recurse(int[] p, int start, int end) {

		if (start == end - 1) return 0;

		int steps = Integer.MAX_VALUE;

		for (int i = start + 1; i < end; i++) {
			steps = Math.min(
				steps,
				recurse(p, start, i) + recurse(p, i, end) + (p[start] * p[i] * p[end])
			);
		}

		return steps;
	}
}
```

- memoized - complexity should be O(n^2)

```java
public class Solution {

	public static int mcm(int[] p) {

		int n = p.length;

		int[][] memo = new int[n][n];
		boolean[][] seen = new boolean[n][n];

		return recurse(p, 0, p.length - 1, memo, seen);
	}

	private static int recurse(int[] p, int start, int end, int[][] memo, boolean[][] seen) {

		if (start == end - 1) return 0;

		int steps = Integer.MAX_VALUE;

		if (seen[start][end]) {
			return memo[start][end];
		}

		for (int i = start + 1; i < end; i++) {
			steps = Math.min(
				steps,
				recurse(p, start, i, memo, seen) + recurse(p, i, end, memo, seen) + (p[start] * p[i] * p[end])
			);
		}

		memo[start][end] = steps;
		seen[start][end] = true;

		return steps;
	}
}
```

- tabulation - calculate for lengths 2, 3, ... n - so like a top down approach
- why - for length 3, 2s should already be calculated

```java
public class Solution {

	public static int mcm(int[] p) {

		int n = p.length;

		int[][] dp = new int[n][n];

		for (int len = 2; len <= n; len++) {

			for (int start = 0; start < n - len; start++) {

				int end = start + len;

				dp[start][end] = Integer.MAX_VALUE;

				for (int partition = start + 1; partition < end; partition++) {

					dp[start][end] = Math.min(
						dp[start][end],
						dp[start][partition] + dp[partition][end] + (p[start] * p[partition] * p[end])
					);
				}

				// System.out.printf("%d, %d: %d\n", start, end, dp[start][end]);
			}
		}

		return dp[0][n - 1];
	}
}
```
