# Lexicographically Smallest String After Operations With Constraint

- https://leetcode.com/problems/lexicographically-smallest-string-after-operations-with-constraint/
- observation 1 - the only reason to increase a character is if we can cycle and get to a -
  - no beyond of increasing beyond a
  - no point increasing but never even reaching a
- observation 2 - always makes sense to do greedy - make first character as small as possible, then focus on next character and so on
- so, first, calculate minimum cost of reaching a -
  - can be reached by decreasing
  - can be reached by increasing then cycling
- if this cost is <= k - convert current character to a, and decrease k by this cost above
- else, make current character go k steps back, and make k as 0 - if there would have been more k left than distance from a, we would have already used it above to reach a

```java
class Solution {

    public String getSmallestString(String s, int k) {

        char[] arr = s.toCharArray();
        int n = arr.length;

        for (int i = 0; i < n && k > 0; i++) {

            int opsRequiredForA = Math.min(arr[i] - 'a', 'z' - arr[i] + 1);
            
            if (opsRequiredForA > k) {
                arr[i] = (char) (arr[i] - k);
                k = 0;
            } else {
                arr[i] = 'a';
                k -= opsRequiredForA;
            }
        }
        
        return new String(arr);
    }
}
```
