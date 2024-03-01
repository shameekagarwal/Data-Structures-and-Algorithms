# Print Longest Common Subsequence

- https://www.geeksforgeeks.org/printing-longest-common-subsequence/
- use full dp matrix (not space optimized) of [this](./Longest%20Common%20Subsequence.md)
- then, start backtracking from n-1,m-1 -
  - if characters are same, go to x-1,y-1
  - else go to x-1,y or x,y-1 whichever one x,y matches
- reverse the string builder at end

```java
public class Solution {

  public static String findLCS(int n, int m, String s1, String s2) {

    char[] s1Arr = s1.toCharArray();
    char[] s2Arr = s2.toCharArray();

    int len1 = s1Arr.length;
    int len2 = s2Arr.length;

    int[][] dp = new int[len1][len2];

    for (int i = 0; i < len1; i++) {
      for (int j = 0; j < len2; j++) {
        if (s1Arr[i] == s2Arr[j]) {
          dp[i][j] = ((i == 0 || j == 0) ? 0 : dp[i - 1][j - 1]) + 1;
        } else {
          dp[i][j] = Math.max(
              i == 0 ? 0 : dp[i - 1][j],
              j == 0 ? 0 : dp[i][j - 1]);
        }
      }
    }

    int x = len1 - 1;
    int y = len2 - 1;

    StringBuilder lcs = new StringBuilder();

    while (x > -1 && y > -1) {
      if (s1Arr[x] == s2Arr[y]) {
        lcs.append(s1Arr[x]);
        x -= 1;
        y -= 1;
      } else {
        if (x > 0 && dp[x][y] == dp[x - 1][y]) {
          x -= 1;
        } else if (y > 0 && dp[x][y] == dp[x][y - 1]) {
          y -= 1;
        } else {
          break;
        }
      }
    }

    lcs.reverse();

    return lcs.toString();
  }
}
```
