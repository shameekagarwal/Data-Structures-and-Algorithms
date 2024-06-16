# Longest Palindromic Substring

- https://leetcode.com/problems/longest-palindromic-substring/

## O(N^2) Time, O(N^2) Space

```java
class Solution {

    public String longestPalindrome(String s) {

        char[] arr = s.toCharArray();
        int n = arr.length;

        boolean[][] isPalindrome = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            isPalindrome[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            isPalindrome[i][i + 1] = arr[i] == arr[i + 1];
        }

        for (int length = 3; length <= n; length++) {

            for (int start = 0; start <= n - length; start++) {
                isPalindrome[start][start + length - 1] = 
                    isPalindrome[start + 1][start + length - 2] &&
                    (arr[start] == arr[start + length - 1]);
            }
        }

        for (int length = n; length >= 1; length--) {

            for (int start = 0; start <= n - length; start++) {

                if (isPalindrome[start][start + length - 1]) {
                    return s.substring(start, start + length);
                }
            }
        }

        return "";
    }
}
```

## O(N^2) Time, O(1) Space

```java
class Solution {

    public String longestPalindrome(String s) {

        char[] arr = s.toCharArray();
        int n = arr.length;

        int palindromeStart = -1;
        int maxPalindromeLength = 0;

        for (int center = 0; center < n; center++) {

            int start = center;
            int end = center;

            while (start > -1 && end < n && arr[start] == arr[end]) {
                start -= 1;
                end += 1;
            }

            if (end - start - 1 > maxPalindromeLength) {
                maxPalindromeLength = end - start - 1;
                palindromeStart = start + 1;
            }
        }

        for (int center = 0; center < n - 1; center++) {

            int start = center;
            int end = center + 1;

            while (start > -1 && end < n && arr[start] == arr[end]) {
                start -= 1;
                end += 1;
            }

            if (end - start - 1 > maxPalindromeLength) {
                maxPalindromeLength = end - start - 1;
                palindromeStart = start + 1;
            }
        }

        if (maxPalindromeLength == 0) {
            return "";
        }

        return s.substring(palindromeStart, palindromeStart + maxPalindromeLength);
    }
}
```
