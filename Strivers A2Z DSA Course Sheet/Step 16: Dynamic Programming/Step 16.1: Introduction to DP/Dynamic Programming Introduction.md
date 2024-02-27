# Dynamic Programming Introduction

- two methods - 
  - tabulation - bottom up
  - memoization - top down
- general pattern - memoization -> tabulation -> tabulation with space optimization
- https://www.codingninjas.com/studio/problems/nth-fibonacci-number_74156
- f(n) = f(n - 1) + f(n - 2)
- memoization - store the values of sub problems in array / map to avoid recomputation
- time complexity - O(N), space complexity - O(N) and auxiliary stack space O(N) as well

```java
import java.util.*;

public class Solution {
import java.util.*;

public class Solution {

	private static int[] memoized;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		memoized = new int[n + 1];
		Arrays.fill(memoized, -1);
		memoized[0] = 0;
		memoized[1] = 1;

		System.out.println(fibonacci(n));
	}

	private static int fibonacci(int n) {

		if (memoized[n] != -1) return memoized[n];

		memoized[n] = fibonacci(n - 1) + fibonacci(n - 2);
		return memoized[n];
	}
}

	private static int[] memoized;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		memoized = new int[n + 1];
		Arrays.fill(memoized, -1);
		memoized[0] = 0;
		memoized[1] = 1;

		System.out.println(fibonacci(n));
	}

	private static int fibonacci(int n) {

		if (memoized[n] != -1) return memoized[n];

		memoized[n] = fibonacci(n - 1) + fibonacci(n - 2);
		return memoized[n];
	}
}
```

- tabulation approach - 
- time complexity - O(N), space complexity - O(N)
- so, space complexity is better than memoization

```java
import java.util.*;

public class Solution {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;

		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		System.out.println(dp[n]);
	}
}
```

- space optimization
- only last two are needed

```java
import java.util.*;

public class Solution {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int previous = 1;
		int beforePrevious = 0;

		for (int i = 2; i <= n; i++) {
			int earlierPrevious = previous;
			previous = previous + beforePrevious;
			beforePrevious = earlierPrevious;
		}

		System.out.println(previous);
	}
}
```
