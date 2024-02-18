# Minimum Window Subsequence

- https://www.codingninjas.com/studio/problems/minimum-window-subsequence_2181133
- my understanding - difference from [Minimum Window Substring](./Minimum%20Window%20Substring.md) - in that problem, order did not matter like here
- assume we have source = ababe and target = abe
- sliding window will stretch from 0 to 4
- but then once we start moving back, we realize we can just use 2 to 4
- j tracks what part of target needs to match next
- r tracks right slider of window
- once j reaches target's length - store current position of r in a temporary variable
- decrement j once to bring it to end of target - remember r is incremented at the very end of outer loop, so it is already at right position
- we start moving r and j back till j reaches -1
- again, both r and j have gone a step too far, so increment r by one
- now, we have the right subsequence - from r to r's initial value
- just compare it to existing stored value - if current length is lesser than current answer / there is no existing answer, update answer
- assume we matched from x. r is pointing to x. now, since we increment r at the end, our next possible candidate also starts from x + 1 - which feels right on observation

```java
public class Solution {
    
    public static String minWindow(String S, String T) {

        int r = 0;
        char[] source = S.toCharArray();
        char[] target = T.toCharArray();
        int j = 0;
        int start = -1;
        int end = -1;

        while (r < source.length) {
            if (source[r] == target[j]) {
                j += 1;
            }
            if (j == target.length) {
                int currentR = r;
                j -= 1;
                while (j > -1) {
                    if (source[r] == target[j]) {
                        j -= 1;
                    }
                    r -= 1;
                }
                r += 1;
                if (start == -1 || end - start + 1 > currentR - r + 1) {
                    start = r;
                    end = currentR;
                }
                j = 0;
            }
            r += 1;
        }

        if (start == -1) return "";
        return S.substring(start, end + 1);
    }
}
```
