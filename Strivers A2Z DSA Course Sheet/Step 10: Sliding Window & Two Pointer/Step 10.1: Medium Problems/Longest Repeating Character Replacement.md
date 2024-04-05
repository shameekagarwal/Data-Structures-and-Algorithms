# Longest Repeating Character Replacement

- https://leetcode.com/problems/longest-repeating-character-replacement/
- say character c has the maximum frequency of x in our current window
- greedy - of course it would make sense to convert the remaining characters in the window to this character
- how many conversions needed in the current window? -
  - r - l + 1 is the size of the window
  - x of them are equal to the most frequently occurring character
  - so, r - l + 1 - x of them need to be converted
- if number of these conversions needed are greater than k, then we move the left pointer forward
- why two pointer works - if x conversions are needed for a window l to r, no way lesser than x conversions would be needed for a window l to r + 1
- so, we should be fine with incrementing l for r, since r + 1 would be able to "reuse" this
- time complexity - 26 * 2 * N? (assuming each l movement has time complexity of 26, both l and r contribute n each)

```java
class Solution {

    private static final int NO_OF_UPPERCASE_CHARACTERS = 'Z' - 'A' + 1;

    public int characterReplacement(String s, int k) {
        
        int[] frequency = new int[NO_OF_UPPERCASE_CHARACTERS];
        char[] characters = s.toCharArray();
        int l = 0;
        int result = 0;

        for (int r = 0; r < characters.length; r++) {
            frequency[characters[r] - 'A'] += 1;
            while (r - l + 1 - maxElement(frequency) > k) {
                frequency[characters[l] - 'A'] -= 1;
                l += 1;
            }
            result = Math.max(r - l + 1, result);
        }

        return result;
    }

    private int maxElement(int[] frequency) {
        int max = 0;
        for (int count : frequency) {
            max = Math.max(max, count);
        }
        return max;
    }
}
```
