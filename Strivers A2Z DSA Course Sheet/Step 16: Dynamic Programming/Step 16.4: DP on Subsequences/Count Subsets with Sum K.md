# Count Subsets with Sum K

- https://www.codingninjas.com/studio/problems/count-subsets-with-sum-k_3952532
- time complexity - O(2^n)

```java
import java.util.*;
import java.io.*;

public class Solution {

    public static int findWays(int num[], int tar) {
        return findWays(0, num, tar);
    }

    private static int findWays(int idx, int num[], int tar) {
        
        if (tar == 0) return 1;
        
        if (idx == num.length) return 0;

        return findWays(idx + 1, num, tar) + findWays(idx + 1, num, tar - num[idx]);
    }
}
```

- memoized - i think it works because only positive given in question. if negative was possible as well, we could use a map i think
- a test case i was missing - array - `[0]`, target - 0. output should be 2
- was initially doing 
  ```java
  if (tar == 0) return 1;
  if (idx == num.length) return 0;
  ```
- then, i started doing
  ```java
  if (idx == num.length) {
      if (tar == 0) return 1;
      return 0;
  }
  ```

```java
import java.util.*;
import java.io.*;

public class Solution {

    private static final int MOD = 1000000007;

    public static int findWays(int num[], int tar) {

        int[][] memo = new int[num.length][tar + 1];
        boolean[][] seen = new boolean[num.length][tar + 1];

        return findWays(0, num, tar, memo, seen);
    }

    private static int findWays(int idx, int num[], int tar, int[][] memo, boolean[][] seen) {

        if (idx == num.length) {
            if (tar == 0) return 1;
            return 0;
        }

        if (seen[idx][tar]) return memo[idx][tar];

        memo[idx][tar] = findWays(idx + 1, num, tar, memo, seen);
        if (tar >= num[idx]) {
            memo[idx][tar] = (int) ((memo[idx][tar] + 0L + findWays(idx + 1, num, tar - num[idx], memo, seen)) % MOD);
        }
        seen[idx][tar] = true;

        return memo[idx][tar];
    }
}
```

- tabulation + space optimization

```java
import java.util.*;
import java.io.*;

public class Solution {

    private static final int MOD = 1000000007;

    public static int findWays(int num[], int tar) {

        int[] previous = new int[tar + 1];
        previous[0] = 1;

        for (int i : num) {
            int[] current = new int[tar + 1];
            for (int j = 0; j <= tar; j++) {
                current[j] = previous[j];
                if (j >= i) {
                    current[j] = (current[j] + previous[j - i]) % MOD;
                }
            }
            previous = current;
        }

        return previous[tar];
    }
}
```
