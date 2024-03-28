# Apply Operations to Make String Empty

- https://leetcode.com/problems/apply-operations-to-make-string-empty/
- find frequency of all characters
- find max among all these frequencies
- last occurrence of all characters having frequency = this max frequency would be used

```java
class Solution {
    
    private static final int NO_OF_CHARS = 'z' - 'a' + 1;

    public String lastNonEmptyString(String s) {
        
        char[] characters = s.toCharArray();
        int n = characters.length;
        
        int[] frequencyLookup = new int[NO_OF_CHARS];
        
        for (int i = 0; i < n; i++) {
            frequencyLookup[characters[i] - 'a'] += 1;
        }
        
        int maxFrequency = 0;
        for (int i = 0; i < NO_OF_CHARS; i++) {
            maxFrequency = Math.max(maxFrequency, frequencyLookup[i]);
        }
        
        StringBuilder result = new StringBuilder();
        Arrays.fill(frequencyLookup, 0);
        
        for (int i = 0; i < n; i++) {
            frequencyLookup[characters[i] - 'a'] += 1;
            if (frequencyLookup[characters[i] - 'a'] == maxFrequency) {
                result.append(characters[i]);
            }
        }
        
        return result.toString();
    }
}
```
