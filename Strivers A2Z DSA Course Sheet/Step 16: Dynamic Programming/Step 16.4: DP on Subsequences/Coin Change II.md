# Coin Change II

- https://leetcode.com/problems/coin-change-ii/
- inspired by [this](./Coin%20Change.md)
- recursive - 

```java
class Solution {

    public int change(int amount, int[] coins) {
        return change(0, amount, coins);
    }

    private int change(int idx, int amount, int[] coins) {
        if (idx == coins.length) {
            return amount == 0 ? 1 : 0;
        }
        
        int notTake = change(idx + 1, amount, coins);
        int take = amount >= coins[idx] ? change(idx, amount - coins[idx], coins) : 0;
        return take + notTake;
    }
}
```

- tabular + space optimized -

```java
class Solution {

    public int change(int amount, int[] coins) {
        
        int[] previous = new int[amount + 1];
        previous[0] = 1;

        for (int coin : coins) {

            for (int j = 0; j <= amount; j++) {
                if (coin <= j && previous[j - coin] != -1) {
                    previous[j] += previous[j - coin];
                }
            }
        }

        return previous[amount];
    }
}
```
