# Count Prefix and Suffix Pairs I

- https://leetcode.com/problems/count-prefix-and-suffix-pairs-i/
- for all pairs i, j where i < j
- see if `str[i]` is both a prefix and a suffix of `str[j]`
- complexity - O(n^2 * word_length)

```java
class Solution {

    public int countPrefixSuffixPairs(String[] words) {
        
        int n = words.length;
        
        int result = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                
                int x = words[i].length();
                int y = words[j].length();
                
                if (x > y) continue;
                
                boolean valid = true;
                
                for (int k = 0; k < x; k++) {
                    if ((words[i].charAt(k) != words[j].charAt(k)) || (words[i].charAt(k) != words[j].charAt(y - x + k))) {
                        valid = false;
                        break;
                    }
                }
                
                if (valid) result += 1;
            }
        }
        
        return result;
    }
}
```
