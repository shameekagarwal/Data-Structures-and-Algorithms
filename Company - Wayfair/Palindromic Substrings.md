# Palindromic Substrings

- https://leetcode.com/problems/palindromic-substrings/
- idea is to implement [Longest Palindromic Substring](./Longest%20Palindromic%20Substring.md) with slight modifications - instead of keeping track of longest and start of longes, add (end - center) / (center - start) to a global result - these are the total palindromes possible when expanding around center

```java
class Solution {

    public int countSubstrings(String s) {

        char[] arr = s.toCharArray();
        int n = arr.length;

        int result = 0;

        for (int center = 0; center < n; center++) {

            int start = center;
            int end = center;

            while (start > -1 && end < n && arr[start] == arr[end]) {
                start -= 1;
                end += 1;
            }

            result += (center - start);
        }

        for (int center = 0; center < n - 1; center++) {

            int start = center;
            int end = center + 1;

            while (start > -1 && end < n && arr[start] == arr[end]) {
                start -= 1;
                end += 1;
            }

            result += (center - start);
        }

        return result;
    }
}
```
