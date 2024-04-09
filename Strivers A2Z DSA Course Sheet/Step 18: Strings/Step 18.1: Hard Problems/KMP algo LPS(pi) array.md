# KMP algo / LPS(pi) array

- https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/

## Brute Force

- time complexity - O(n * m)
- look at outer loop range carefully

```java
class Solution {
    public int strStr(String haystack, String needle) {
        
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int j = 0;
            while (j < needle.length()) {
                if (needle.charAt(j) != haystack.charAt(i + j)) {
                    break;
                }
                j++;
            }
            if (j == needle.length()) {
                return i;
            }
        }
        return -1;
    }
}
```

## KMP Algorithm

- remember - whenever solving kmp problem, try to come up with example for both lps construction and matching - this will help give intuition to interviewer
- we construct an array such that if for index i, value is x, it means `needle[0...x - 1]` = `needle[i - x - 1...i]`
- this array is called the "longest prefix suffix" - thats why the title has lps
- logic behind if - 
  - e.g. imagine we are at i, and character at i is same as character at prevLps
  - since prefix ending prevLps - 1 has already been checked with suffix ending at i - 1
  - thus `lps[i] = prevLps`
  - then, we increment i and prevLps
- logic behind else if - straight forward, nothing is matching
- logic behind final else - try understanding for last character in below example - last c will be first compared against the middle d and fail. then, it would compared against third c and match - we did not have to compare the a and b before the last c to find that for last c, lps is up to third c
  ```
  a b c a b d a b c a b c
  0 0 0 1 2 0 1 2 3 4 5 3
  ```
- time complexity of building lps - `O(2 * n)`

```java
private int[] getLps(String s) {

    int[] lps = new int[s.length()];
    int prevLps = 0;
    int i = 1;

    while (i < s.length()) {
        if (s.charAt(prevLps) == s.charAt(i)) {
            lps[i] = prevLps + 1;
            prevLps += 1;
            i += 1;
        } else if (prevLps == 0) {
            lps[i] = 0;
            i += 1;
        } else {
            prevLps = lps[prevLps - 1];
        }
    }

    return lps;
}
```

- for matching - 
  ```
  a b c a b c a b x
  a b c a b x
  ```
- after x does not match c, we automatically match third c of pattern with sixth c of source, and then the remaining pattern matches
- we did not have to start matching for pattern from start

```java
private int findFirstIndex(int[] lps, String haystack, String needle) {
    
    int i = 0;
    int j = 0;

    while (i < haystack.length() && j < needle.length()) {
        if (haystack.charAt(i) == needle.charAt(j)) {
            i += 1;
            j += 1;
        } else if (j == 0) {
            i += 1;
        } else {
            j = lps[j - 1];
        }
    }

    return j == needle.length() ? i - j : -1;
}
```
