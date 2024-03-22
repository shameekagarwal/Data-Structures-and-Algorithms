# Printing Longest Increasing Subsequence

- https://www.codingninjas.com/studio/problems/printing-longest-increasing-subsequence_8360670
- use the better version described [here](./Longest%20Increasing%20Subsequence.md)

```java
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {

    public static List<Integer> printingLongestIncreasingSubsequence(int []arr, int x) {

        int n = arr.length;

        int[] dp = new int[n];
        int[] prevLisIdx = new int[n];

        for (int i = 0; i < n; i++) {

            dp[i] = 1;
            prevLisIdx[i] = -1;

            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prevLisIdx[i] = j;
                }
            }
        }

        int lisLen = 0;
        int lisEndIdx = -1;

        for (int i = 0; i < n; i++) {
            if (dp[i] > lisLen) {
                lisLen = dp[i];
                lisEndIdx = i;
            }
        }

        List<Integer> lis = new ArrayList<>();
        while (lisEndIdx != -1) {
            lis.add(arr[lisEndIdx]);
            lisEndIdx = prevLisIdx[lisEndIdx];
        }

        Collections.reverse(lis);
        
        return lis;
    }
}
```
