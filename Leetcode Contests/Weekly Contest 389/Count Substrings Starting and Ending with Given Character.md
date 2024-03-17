# Count Substrings Starting and Ending with Given Character

- https://leetcode.com/problems/count-substrings-starting-and-ending-with-given-character/

```java
class Solution {
    public long countSubstrings(String s, char c) {
        
        char[] chars = s.toCharArray();
        long count = 0;
        
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == c) {
                count += 1;
            }
        }
        
        return count * (count + 1) / 2;
    }
}
```
