# Permutation in String

- naive will be 26*N - check entire character set for every sliding window
- optimized will only take O(N)
- first, make a frequency map for all characters of s1
- how sliding window works
  - case 1 - decrement count in this lookup when adding to this sliding window
  - case 2 - increment count in this lookup when removing from this sliding window
- now, initialize to satisfy with number of unique characters
- in the sliding window, whenever count touches 0, decrement to satisfy
- whenever it goes from 0 to 1 or 0 to -1, increment to satisfy
- we have found our substring if to satisfy becomes 0

```java
class Solution {

    public boolean checkInclusion(String s1, String s2) {
        return checkInclusion(s1.toCharArray(), s2.toCharArray());
    }

    public boolean checkInclusion(char[] s1, char[] s2) {

        if (s1.length > s2.length) return false;

        int[] map = new int[256];
        int toSatisfy = 0;

        for (int i = 0; i < s1.length; i++) {

            map[s1[i]] += 1;

            if (map[s1[i]] == 1) {
                toSatisfy += 1;
            }
        }

        // construct first window
        for (int i = 0; i < s1.length; i++) {

            map[s2[i]] -= 1;

            if (map[s2[i]] == 0) {
                toSatisfy -= 1;
            } else if (map[s2[i]] == -1) {
                toSatisfy += 1;
            }
        }

        if (toSatisfy == 0) {
            return true;
        }

        for (int i = s1.length; i < s2.length; i++) {
            
            // remove last char of window
            map[s2[i - s1.length]] += 1;

            if (map[s2[i - s1.length]] == 0) {
                toSatisfy -= 1;
            } else if (map[s2[i - s1.length]] == 1) {
                toSatisfy += 1;
            }

            // add last char of window
            map[s2[i]] -= 1;

            if (map[s2[i]] == 0) {
                toSatisfy -= 1;
            } else if (map[s2[i]] == -1) {
                toSatisfy += 1;
            }

            if (toSatisfy == 0) {
                return true;
            }
        }

        return false;
    }
}
```
