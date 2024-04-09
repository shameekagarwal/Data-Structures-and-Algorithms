# Longest Happy Prefix

- https://leetcode.com/problems/longest-happy-prefix/
- calculate the lps in [kmp](./KMP%20algo%20LPS(pi)%20array.md)
- then, return `s.substring(0, lps[n - 1])`;

```java
class Solution {

    public String longestPrefix(String s) {
        
        int n = s.length();
        int[] lps = new int[n];
        int i = 1;
        int prevLps = 0;
        char[] arr = s.toCharArray();

        while (i < n) {
            if (arr[i] == arr[prevLps]) {
                lps[i] = prevLps + 1;
                i += 1;
                prevLps += 1;
            } else if (prevLps == 0) {
                lps[i] = 0;
                i += 1;
            } else {
                prevLps = lps[prevLps - 1];
            }
        }

        return s.substring(0, lps[n - 1]);
    }
}
```
