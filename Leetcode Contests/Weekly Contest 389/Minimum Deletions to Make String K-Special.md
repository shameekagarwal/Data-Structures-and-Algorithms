# Minimum Deletions to Make String K-Special

- https://leetcode.com/problems/minimum-deletions-to-make-string-k-special/
- populate frequency for each character
- for all i from 0 to character length
- try making all characters go up to frequency i
  - if frequency of character is less than i, simply add all of its frequency - we have to delete all of it, because we can only delete characters
  - if frequency of character is more than i, add surplus characters i.e. anything beyond i + k

```java
class Solution {
    
    private static final int NO_OF_CHARS = 'z' - 'a' + 1;
    
    public int minimumDeletions(String word, int k) {
        
        char[] characters = word.toCharArray();
        int[] frequency = new int[NO_OF_CHARS];
        
        for (int i = 0; i < characters.length; i++) {
            frequency[characters[i] - 'a'] += 1;
        }
        
        int minDeletions = characters.length;
        
        for (int i = 0; i <= characters.length; i++) {
            
            int currentDeletions = 0;
            
            for (int j = 0; j < NO_OF_CHARS; j++) {
                if (frequency[j] < i) {
                    currentDeletions += frequency[j];
                } else if (frequency[j] > i + k) {
                    currentDeletions += frequency[j] - (i + k);
                }
            }
            
            minDeletions = Math.min(minDeletions, currentDeletions);
        }
        
        return minDeletions;
    }
}
```
