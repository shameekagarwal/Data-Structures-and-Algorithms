# Points

- https://leetcode.com/problems/first-unique-character-in-a-string/
- using array of length 26 as hash map when problems inolve only for e.g. lowercase alphabets

# Solution

```java
class Solution {
    public int firstUniqChar(String s) {
        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 97] += 1;
        }
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map[s.charAt(i) - 97] == 1) return i;
        }
        return -1;
    }
}
```
