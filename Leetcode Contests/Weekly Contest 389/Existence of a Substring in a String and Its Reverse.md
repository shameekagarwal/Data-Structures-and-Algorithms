# Existence of a Substring in a String and Its Reverse

- https://leetcode.com/problems/existence-of-a-substring-in-a-string-and-its-reverse/

```java
class Solution {
    public boolean isSubstringPresent(String s) {
        
        Set<List<Character>> substrings = new HashSet<>();
        
        char[] chars = s.toCharArray();
        
        for (int i = 0; i < chars.length - 1; i++) {
            substrings.add(List.of(chars[i], chars[i + 1]));
        }
        
        for (int i = chars.length - 1; i > 0; i--) {
            if (substrings.contains(List.of(chars[i], chars[i - 1]))) {
                return true;
            }
        }
        
        return false;
    }
}
```
