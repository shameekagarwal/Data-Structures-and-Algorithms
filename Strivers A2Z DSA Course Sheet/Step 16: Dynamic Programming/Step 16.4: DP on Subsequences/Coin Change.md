# Coin Change

- https://leetcode.com/problems/coin-change/
- greedy will fail - 
  ```
  coins - 6 2 3
  amount - 7
  ```
- greedy says not possible after picking 6, but it is - 2, 2, 3 so total is 3 coins
- question states infinite supply of coins
- recursion solution - since multiple coins of same kind can be picked, we use idx for pick case, not idx + 1
- so, remember this recursion for infinite supply / multiple uses
- for time complexity, just say exponential - more than 2^n, as we can pick once, twice, thrice, and so on

```java
class Solution {

    public int coinChange(int[] coins, int amount) {
        return coinChange(0, coins, amount);
    }

    private int coinChange(int idx, int[] coins, int amount) {

        if (idx == coins.length) {
            if (amount == 0) return 0;
            return -1;
        }

        int pick = coins[idx] <= amount ? coinChange(idx, coins, amount - coins[idx]) : -1;
        int nonPick = coinChange(idx + 1, coins, amount);

        if (pick == -1) {
            return nonPick;
        } else if (nonPick == -1) {
            return pick + 1;
        } else {
            return Math.min(pick + 1, nonPick);
        }
    }
}
```

- memoized - 

```java
class Solution {

    public int coinChange(int[] coins, int amount) {
        boolean[][] seen = new boolean[coins.length][amount + 1];
        int[][] memo = new int[coins.length][amount + 1];
        return coinChange(0, coins, amount, seen, memo);
    }

    private int coinChange(int idx, int[] coins, int amount, boolean[][] seen, int[][] memo) {

        if (idx == coins.length) {
            if (amount == 0) return 0;
            return -1;
        }

        if (seen[idx][amount]) return memo[idx][amount];

        int pick = coins[idx] <= amount ? coinChange(idx, coins, amount - coins[idx], seen, memo) : -1;
        int nonPick = coinChange(idx + 1, coins, amount, seen, memo);

        if (pick == -1) {
            memo[idx][amount] = nonPick;
        } else if (nonPick == -1) {
            memo[idx][amount] = pick + 1;
        } else {
            memo[idx][amount] = Math.min(pick + 1, nonPick);
        }
        seen[idx][amount] = true;

        return memo[idx][amount];
    }
}
```

- tabular + space optimized -
- note how unlike other problems in this section which required a previous and current, we only use a previous for this
- vvimp - this is again because we can pick as many coins as we want - aka "unbounded knapsack problem"
- e.g. if we can form 4 using a coin 4, we can also form 8 "using the same coin"

```java
class Solution {

    public int coinChange(int[] coins, int amount) {
        
        int[] previous = new int[amount + 1];
        Arrays.fill(previous, -1);
        previous[0] = 0;

        for (int coin : coins) {
            for (int j = 0; j <= amount; j++) {
                if (coin <= j && previous[j - coin] != -1) {
                    if (previous[j] == -1 || previous[j] > previous[j - coin] + 1) {
                        previous[j] = previous[j - coin] + 1;
                    }
                }
            }
            // System.out.println(Arrays.toString(previous));
        }

        return previous[amount];
    }
}
```
