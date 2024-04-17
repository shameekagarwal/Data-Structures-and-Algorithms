# Valid Anagram

- https://leetcode.com/problems/valid-anagram/
- just construct a map for lowercase characters
- increment by one character for every character in s
- decrement by one character for every character in t
- if any position in map is not 0, return false, else return true

```java
class Solution {

    private static final int NO_OF_LOWERCASE_CHARS = 'z' - 'a' + 1;

    public boolean isAnagram(String s, String t) {
        
        int charMap[] = new int[NO_OF_LOWERCASE_CHARS];

        for (int i = 0; i < s.length(); i++) {
            charMap[s.charAt(i) - 'a'] += 1;
        }

        for (int i = 0; i < t.length(); i++) {
            charMap[t.charAt(i) - 'a'] -= 1;
        }

        for (int i = 0; i < NO_OF_LOWERCASE_CHARS; i++) {
            if (charMap[i] != 0) {
                return false;
            }
        }

        return true;
    }
}
```

## An STL Solution

```java
class Solution {

    public boolean isAnagram(String s, String t) {

        Map<Character, Integer> sFrequency = new HashMap<>();
        Map<Character, Integer> tFrequency = new HashMap<>();

        for (char c : s.toCharArray()) {
            sFrequency.put(c, sFrequency.getOrDefault(c, 0) + 1);
        }

        for (char c : t.toCharArray()) {
            tFrequency.put(c, tFrequency.getOrDefault(c, 0) + 1);
        }

        return sFrequency.equals(tFrequency);
    }
}
```
