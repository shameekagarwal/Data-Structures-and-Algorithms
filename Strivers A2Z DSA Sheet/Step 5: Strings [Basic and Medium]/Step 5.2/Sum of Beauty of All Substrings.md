# Sum of Beauty of All Substrings

- pretty brute force
- O(n^2) for all substrings
- keep incrementing count of characters in a map
- https://leetcode.com/problems/sum-of-beauty-of-all-substrings

```java
class Solution {

    private static final Integer NO_OF_LOWERCASE_CHARS = 'z' - 'a' + 1;

    public int beautySum(String s) {
        
        char[] arr = s.toCharArray();

        int result = 0;

        for (int i = 0; i < arr.length; i++) {

            int[] map = new int[NO_OF_LOWERCASE_CHARS];

            for (int j = i; j < arr.length; j++) {

                map[arr[j] - 'a'] += 1;
                int minFreq = Integer.MAX_VALUE;
                int maxFreq = Integer.MIN_VALUE;

                for (int k = 0; k < NO_OF_LOWERCASE_CHARS; k++) {
                    if (map[k] != 0) {
                        minFreq = Math.min(minFreq, map[k]);
                        maxFreq = Math.max(maxFreq, map[k]);
                    }
                }
                // System.out.printf("for substring %s = max: %d, min: %d\n", s.substring(i, j + 1), maxFreq, minFreq);
                result += (maxFreq - minFreq);
            }
        }

        return result;
    }
}
```
