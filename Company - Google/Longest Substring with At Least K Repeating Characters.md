# Longest Substring with At Least K Repeating Characters

- https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
- typically smallest wale me two pointer works, this is longest
- idea is to iterate over unique character count - 1,2,3,...26
- at this point - only consider windows with i unique characters
- keep expanding till current window only has i unique characters
- then start contracting
- now, we have successfully converted to sliding window problem
- now for each valid window, just confirm if all characters have frequency at least 26 or not
- note - i did it in `O(26*N*26)`, but i think it is doable to maintain unique characters / satisfied on the fly and change it to `O(26*N)`

```java
class Solution {

    private static final int NO_OF_CHARS = 'z' - 'a' + 1;

    public int longestSubstring(String s, int k) {

        char[] arr = s.toCharArray();
        int result = 0;

        for (int uniqueChars = 1; uniqueChars <= NO_OF_CHARS; uniqueChars++) {

            int[] lookup = new int[NO_OF_CHARS];
            int l = 0;

            for (int r = 0; r < arr.length; r++) {

                lookup[arr[r] - 'a'] += 1;

                while (getUniqueChars(lookup) > uniqueChars) {
                    lookup[arr[l] - 'a'] -= 1;
                    l += 1;
                }

                // System.out.printf("%d: %d-%d\n", uniqueChars, l, r);

                if (satisfies(lookup, k)) {
                    result = Math.max(result, r - l + 1);
                }
            }
        }

        return result;
    }

    private boolean satisfies(int[] lookup, int k) {

        for (int i = 0; i < NO_OF_CHARS; i++) {

            if (lookup[i] > 0 && lookup[i] < k) {
                return false;
            }
        }

        return true;
    }

    private int getUniqueChars(int[] lookup) {

        int uniqueChars = 0;

        for (int i = 0; i < NO_OF_CHARS; i++) {

            if (lookup[i] != 0) {
                uniqueChars += 1;
            }
        }

        return uniqueChars;
    }
}
```
