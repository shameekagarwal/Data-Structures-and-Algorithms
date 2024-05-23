# Longest Palindrome

- https://leetcode.com/problems/longest-palindrome/

```java
class Solution {

    public int longestPalindrome(String s) {

        Map<Character, Integer> lookup = new HashMap<>();
        char[] arr = s.toCharArray();

        for (char c : arr) {
            lookup.put(c, lookup.getOrDefault(c, 0) + 1);
        }

        int total = 0;
        boolean foundOdd = false;

        for (Integer frequency : lookup.values()) {

            if (frequency % 2 == 0) {
                total += frequency;
            } else {
                total += (frequency - 1);
                foundOdd = true;
            }
        }

        return total + (foundOdd ? 1 : 0);
    }
}
```
