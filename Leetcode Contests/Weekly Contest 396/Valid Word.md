# Valid Word

- https://leetcode.com/contest/weekly-contest-396/problems/valid-word/

```java
class Solution {

    private static final Set<Character> vowels = Set.of(
        'a', 'e', 'i', 'o', 'u',
        'A', 'E', 'I', 'O', 'U'
    );

    public boolean isValid(String word) {

        if (word.length() < 3) return false;

        boolean hasVowel = false;
        boolean hasConsonant = false;

        for (char c : word.toCharArray()) {
            
            if (!isUpperCase(c) && !isLowerCase(c) && !isDigit(c)) {
                return false;
            }

            if (isUpperCase(c) || isLowerCase(c)) {
                hasVowel |= vowels.contains(c);
                hasConsonant |= (!vowels.contains(c));
            }
        }

        return hasVowel && hasConsonant;
    }

    private boolean isUpperCase(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private boolean isLowerCase(char c) {
        return c >= 'a' && c <= 'z';
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
```
