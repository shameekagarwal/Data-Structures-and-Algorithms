# Wildcard Matching

- https://leetcode.com/problems/wildcard-matching/
- `?` - just take it to match
- `*` - is the tricky part
- think case - `ab*cd` and `abxyzcd`
- for * - we can either match nothing i.e. f(i + 1, j), or any number of any characters f(i, j + 1)
- base cases -
  - if we reach target length
    - if all remaining characters in source are * return true
    - else return false
  - if we each source length return false
- recursion time complexity - O(2^n*2^m)
- memoized + recursion - O(m*n)

```java
class Solution {

    private char[] pattern;
    private char[] string;

    private boolean[][] memo;
    private boolean[][] seen;

    public boolean isMatch(String s, String p) {
        
        pattern = p.toCharArray();
        string = s.toCharArray();

        seen = new boolean[pattern.length + 1][string.length + 1];
        memo = new boolean[pattern.length + 1][string.length + 1];
        
        return isMatch(0, 0);
    }

    private boolean isMatch(int idxPattern, int idxString) {

        if (seen[idxPattern][idxString]) {
            return memo[idxPattern][idxString];
        }

        if (idxString == string.length) {
            memo[idxPattern][idxString] = true;
            for (int i = idxPattern; i < pattern.length; i++) {
                if (pattern[i] != '*') {
                    memo[idxPattern][idxString] = false;
                }
            }
        } else if (idxPattern == pattern.length) {
            memo[idxPattern][idxString] = false;
        } else {
            if (pattern[idxPattern] == string[idxString]) {
                memo[idxPattern][idxString] = isMatch(idxPattern + 1, idxString + 1);
            } else if (pattern[idxPattern] == '?') {
                memo[idxPattern][idxString] = isMatch(idxPattern + 1, idxString + 1);
            } else if (pattern[idxPattern] == '*') {
                memo[idxPattern][idxString] = isMatch(idxPattern + 1, idxString) || isMatch(idxPattern, idxString + 1);
            } else {
                memo[idxPattern][idxString] = false;
            }
        }

        seen[idxPattern][idxString] = true;
        return memo[idxPattern][idxString];
    }
}
```

- tabular + space optimized
- look at base cases carefully

```java
class Solution {

    //     * x
    //   t t t
    // a f t f
    // b f t f
    // x f t t

    public boolean isMatch(String s, String p) {

        char[] string = s.toCharArray();
        char[] pattern = p.toCharArray();

        boolean[] previous = new boolean[pattern.length + 1];
        
        // empty pattern matches empty string
        previous[0] = true;
        
        // pattern with *(s) in prefix matches empty string
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] != '*') break;
            previous[i + 1] = true;
        }

        for (int i = 0; i < string.length; i++) {

            boolean[] current = new boolean[pattern.length + 1];

            // empty pattern does not match non empty string
            current[0] = false;

            for (int j = 0; j < pattern.length; j++) {

                if (string[i] == pattern[j]) {
                    current[j + 1] = previous[j];
                } else if (pattern[j] == '?') {
                    current[j + 1] = previous[j];
                } else if (pattern[j] == '*') {
                    current[j + 1] = previous[j + 1] || current[j];
                } else {
                    current[j + 1] = false;
                }
            }

            previous = current;
        }

        return previous[pattern.length];
    }
}
```
