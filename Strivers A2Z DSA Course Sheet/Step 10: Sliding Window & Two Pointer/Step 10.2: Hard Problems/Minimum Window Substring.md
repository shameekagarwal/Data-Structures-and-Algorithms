# Minimum Window Substring

- https://leetcode.com/problems/minimum-window-substring/
- brute + sliding window - keep incrementing r one by one
- using an ascii map right now - question states both lowercase and uppercase characters are possible
- keep incrementing l till the current window cannot does not have all characters needed by target
- i had to place the condition `l <= r` in the loop because l will cross r when for e.g. target is "a" and r is pointing at "a" and l is also pointing at "a" - condition is satisfied so l will try to increment itself (to reduce the window size)
- time complexity - 0(256 * 2 * N) - every time l moves, the entire 256 map check is there

```java
class Solution {

    private static final int NO_OF_ASCII_CHARS = 256;

    public String minWindow(String s, String t) {
        
        int[] targetFrequency = new int[NO_OF_ASCII_CHARS];
        int[] sourceFrequency = new int[NO_OF_ASCII_CHARS];

        char[] targetChars = t.toCharArray();
        char[] sourceChars = s.toCharArray();

        for (int i = 0; i < targetChars.length; i++) {
            targetFrequency[targetChars[i]] += 1;
        }

        int l = 0;
        int start = -1;
        int end = -1;

        for (int r = 0; r < sourceChars.length; r++) {
            sourceFrequency[sourceChars[r]] += 1;
            while (l <= r && containsAll(sourceFrequency, targetFrequency)) {
                if ((start == -1) || (end - start + 1 > r - l + 1)) {
                    start = l;
                    end = r;
                }
                sourceFrequency[sourceChars[l]] -= 1;
                l += 1;
            }
        }

        if (start == -1) return "";
        return s.substring(start, end + 1);
    }

    private boolean containsAll(int[] sourceFrequency, int[] targetFrequency) {
        for (int i = 0; i < NO_OF_ASCII_CHARS; i++) {
            if (sourceFrequency[i] < targetFrequency[i]) return false;
        }
        return true;
    }
}
```

## Optimal

- observation - every time the condition is satisfied, count of one of the characters becomes "the same" as that of target
- so, assume we track two different variables - "satisfied" and "to satisfy"
- "to satisfy" - distinct characters in target
- every time count of some character in source becomes equal to count of some character in target, we increment "satisfied" by 1
- the moment "satisfied" becomes equal to "to satisfy", we start decrementing l
- we increment l till count of some character in source becomes less than count of some character in target
- also, notice how start is l - 1 and end is r - because condition is "not satisfied" when window starts from l, but condition was satisfied till window was starting at l - 1

```java
class Solution {
    
    private static final int NO_OF_ASCII_CHARS = 256;
    
    public String minWindow(String s, String t) {

        int[] targetFrequency = new int[NO_OF_ASCII_CHARS];
        int[] sourceFrequency = new int[NO_OF_ASCII_CHARS];

        char[] targetChars = t.toCharArray();
        char[] sourceChars = s.toCharArray();

        for (int i = 0; i < targetChars.length; i++) {
            targetFrequency[targetChars[i]] += 1;
        }

        int needToSatisfy = 0;
        for (int i = 0; i < NO_OF_ASCII_CHARS; i++) {
            needToSatisfy += (targetFrequency[i] > 0 ? 1 : 0);
        }
        int satisfied = 0;

        int start = -1;
        int end = -1;
        int l = 0;

        for (int r = 0; r < sourceChars.length; r++) {
            
            sourceFrequency[sourceChars[r]] += 1;
            
            if (sourceFrequency[sourceChars[r]] == targetFrequency[sourceChars[r]]) {
                
                satisfied += 1;

                if (needToSatisfy == satisfied) {

                    while (l <= r && needToSatisfy == satisfied) {
                        sourceFrequency[sourceChars[l]] -= 1;
                        if (sourceFrequency[sourceChars[l]] < targetFrequency[sourceChars[l]]) {
                            satisfied -= 1;
                        } else {
                            currentStart = l + 1;
                        }
                        l += 1;
                    }

                    int currentStart = l - 1;
                    int currentEnd = r;

                    // System.out.printf("possible: %d-%d\n", currentStart, currentEnd);

                    if ((start == -1) || (end - start + 1 > currentEnd - currentStart + 1)) {
                        end = currentEnd;
                        start = currentStart;
                    }
                }
            }
        }

        if (start == -1) return "";
        return s.substring(start, end + 1);
    }
}
```
