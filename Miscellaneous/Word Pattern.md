# Word Pattern

- https://leetcode.com/problems/word-pattern/
- use a hash map for word to character mapping
- if a word was seen using a different character, return false
- else, finally return true
- this works for cases like `a a` `dog dog` and `a b` `dog dog`, but fails for `a a` `dog cat`
- so, we need a separate hash set to keep track of used up characters
- we can only map a word to a character if the character has not been seen before

```java
class Solution {

    public boolean wordPattern(String pattern, String s) {
        
        String[] words = s.split(" ");
        char[] arr = pattern.toCharArray();

        if (arr.length != words.length) {
            return false;
        }

        Map<String, Character> wordToChar = new HashMap<>();
        Set<Character> usedChars = new HashSet<>();

        for (int i = 0; i < words.length; i++) {

            if (wordToChar.containsKey(words[i])) {
                if (wordToChar.get(words[i]) != arr[i]) {
                    return false;
                }
            } else {
                if (usedChars.contains(arr[i])) {
                    return false;
                }
                usedChars.add(arr[i]);
                wordToChar.put(words[i], arr[i]);
            }
        }

        return true;
    }
}
```
