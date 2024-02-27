# Frog Jump

- https://www.codingninjas.com/studio/problems/frog-jump_3621012
- why greedy will not work - assume this example - 30 10 60 10 60 50
- greedily - 30 -> 10 -> 10 -> 50 = 60
- however, correct answer is 30 -> 60 -> 60 -> 50 = 40
- so, we use dp
- we can also space optimize this, i have not done that here

```java
import java.util.*;
import java.io.*;

public class Solution {
    
    public static int frogJump(int n, int heights[]) {
        
        int[] dp = new int[n];
        
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + Math.abs(heights[i] - heights[i - 1]);
            if (i > 1) {
                dp[i] = Math.min(dp[i], dp[i - 2] + Math.abs(heights[i] - heights[i - 2]));
            }
        }

        return dp[n - 1];
    }

}
```
