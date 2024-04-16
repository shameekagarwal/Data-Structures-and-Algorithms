# Maximum Length Substring With Two Occurrences

- https://leetcode.com/problems/maximum-length-substring-with-two-occurrences/
- sliding window - if count of character ending at current index i.e. r becomes 3, increment l till this count goes below 3
- window is from l to r, so window length is r - l + 1

```java
class Solution {

    public int maximumLengthSubstring(String s) {
        
        int l = 0;
        int[] frequency = new int['z' - 'a' + 1];
        char[] str = s.toCharArray();
        int n = str.length;
        
        int result = 0;
        
        for (int r = 0; r < n; r++) {

            frequency[str[r] - 'a'] += 1;

            while (frequency[str[r] - 'a'] == 3) {
                frequency[str[l] - 'a'] -= 1;
                l += 1;
            }
            
            result = Math.max(r - l + 1, result); 
        }
        
        return result;
    }
}
```
