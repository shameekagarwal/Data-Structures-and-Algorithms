# Longest Palindromic Substring

## Brute Force

- pure brute - generate all palindromic strings and check - O(n^3)
- better - O(n^2) - consider all centers and expand around it
- n centers for odd length substrings
- n - 1 centers for even length substrings
- expand on left and right of it
- another brute force using dp -
  - fill `dp[i][i]` with 1 and `dp[i][i + 1]` with 2 if `arr[i] == arr[i + 1]`
  - `dp[i][j] = dp[i + 1][j - 1] + 1 if arr[i] == arr[j] else 0`
- the most optimal solution of this problem comes from manacher's algorithm, skipping for now

```java
class Solution {

    public String longestPalindrome(String s) {
        
        char[] characters = s.toCharArray();
        int n = characters.length;
        int result = 0;

        int start = 0;
        int end = 0;

        for (int i = 0; i < n; i++) {

            int equal = totalEqual(characters, n, i - 1, i + 1);
            if (result < 2 * equal + 1) {
                result = 2 * equal + 1;
                start = i - equal;
                end = i + equal;
            }
        }

        for (int i = 0; i + 1 < n; i++) {

            int equal = totalEqual(characters, n, i, i + 1);
            if (result < 2 * equal) {
                result = 2 * equal;
                start = i - equal + 1;
                end = i + equal;
            }
        }

        return s.substring(start, end + 1);
    }

    private int totalEqual(char[] characters, int n, int l, int r) {

        int count = 0;

        while (l > -1 && r < n && (characters[l] == characters[r])) {
            l -= 1;
            r += 1;
            count += 1;
        }

        return count;
    }
}
```
