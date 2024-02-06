# Roman to Integer

- "Roman numerals are usually written largest to smallest from left to right" is mentioned in the problem
- https://leetcode.com/problems/roman-to-integer/
- i do `toCharArray` everywhere because it is more performant in leetcode

```java
class Solution {

    private static final Map<Character, Integer> romanToInt = Map.of(
        'I', 1,
        'V', 5,
        'X', 10,
        'L', 50,
        'C', 100,
        'D', 500,
        'M', 1000
    );

    public int romanToInt(String s) {
        
        int value = 0;
        char[] romanChars = s.toCharArray();
        int i = 0;
        
        while (i < romanChars.length) {
            if (i + 1 < romanChars.length && romanToInt.get(romanChars[i + 1]) > romanToInt.get(romanChars[i])) {
                value += (romanToInt.get(romanChars[i + 1]) - romanToInt.get(romanChars[i]));
                i += 2;
            } else {
                value += romanToInt.get(romanChars[i]);
                i += 1;
            }
        }
        
        return value;
    }
}
```
