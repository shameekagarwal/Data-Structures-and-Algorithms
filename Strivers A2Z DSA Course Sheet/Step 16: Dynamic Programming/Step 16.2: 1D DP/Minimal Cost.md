# Minimal Cost

- https://www.codingninjas.com/studio/problems/minimal-cost_8180930
- extension of [this](./Frog%20Jump.md)
- we can take from 1 up to k jumps here, in that question, k was always 2
- time complexity - O(N * k), space complexity - O(N)

```java
public class Solution {

    public static int minimizeCost(int n, int k, int[] height) {

        int[] dp = new int[n];

        for (int i = 1; i < n; i++) {

            dp[i] = dp[i - 1] + Math.abs(height[i] - height[i - 1]);

            for (int j = 2; j <= Math.min(i, k); j++) {
                dp[i] = Math.min(dp[i], dp[i - j] + Math.abs(height[i] - height[i - j]));
            }
        }

        return dp[n - 1];
    }
}
```

- space optimize to O(K)
- we just need the k last values to compute a state
- finally, after calculation, overwrite the element k steps behind with current value

```java
public class Solution {

    public static int minimizeCost(int n, int k, int[] height) {

        int[] dp = new int[k];
        int currentAnswer = 0;

        for (int i = 1; i < n; i++) {

            currentAnswer = dp[(i - 1) % k] + Math.abs(height[i] - height[i - 1]);

            for (int j = 2; j <= Math.min(i, k); j++) {
                currentAnswer = Math.min(currentAnswer, dp[(i - j) % k] + Math.abs(height[i] - height[i - j]));
            }

            dp[i % k] = currentAnswer;
        }

        return currentAnswer;
    }
}
```
