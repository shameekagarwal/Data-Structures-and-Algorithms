# Shortest Palindrome

- https://leetcode.com/problems/shortest-palindrome/
- rephrasing the question - the longest prefix palindrome can be used
- the remaining suffix can be reversed and added at the beginning
- time complexity of below - O(n^2)

```java
class Solution {

    public String shortestPalindrome(String s) {

        String rev = reverse(s);
        int n = s.length();

        for (int i = n; i >= 1; i--) {

            String revSuffix = rev.substring(n - i, n);
            String strPrefix = s.substring(0, i);

            if (strPrefix.equals(revSuffix)) {
                return rev.substring(0, n - i) + s;
            }
        }

        return rev;
    }

    private String reverse(String str) {
        
        int n = str.length();

        String result = "";

        for (int i = n - 1; i > -1; i--) {
            result += str.charAt(i);
        }

        return result;
    }
}
```

- optimized - use kmp
- if we construct a string like this - s + "#" + rev
- we only need to find the lps for this array
- then, once we find the longest part that is a palindrome, we just need to add the remaining characters of s in reversed fashion at the beginning

```java
class Solution {

    public String shortestPalindrome(String s) {

        String reversed = reverse(s);
        String concatenatedString = s + "#" + reversed;

        int n = s.length();
        int m = concatenatedString.length();

        int[] lps = new int[m];
        int prevLps = 0;
        int i = 1;

        while (i < m) {

            if (concatenatedString.charAt(i) == concatenatedString.charAt(prevLps)) {
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

        String palindrome = s.substring(0, lps[m - 1]);
        String start = reversed.substring(0, n - lps[m - 1]);
        String end = s.substring(lps[m - 1], n);

        // System.out.println(start + ", " + palindrome + ", " + end);

        return start + palindrome + end;
    }

    private String reverse(String s) {

        StringBuilder sb = new StringBuilder();

        int n = s.length();

        for (int i = n - 1; i > -1; i--) {
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }
}
```
