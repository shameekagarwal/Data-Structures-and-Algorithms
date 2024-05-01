# Count the Number of Special Characters I

- https://leetcode.com/problems/count-the-number-of-special-characters-i/

```java
class Solution {
    
    private static final int NO_OF_CHARS = 'z' - 'a' + 1;

    public int numberOfSpecialChars(String word) {
        
        char[] arr = word.toCharArray();
        int n = arr.length;
        
        boolean[] lowerCaseLookup = new boolean[NO_OF_CHARS];
        boolean[] upperCaseLookup = new boolean[NO_OF_CHARS];
        
        for (int i = 0; i < n; i++) {
            if (arr[i] >= 'a' && arr[i] <= 'z') {
                lowerCaseLookup[arr[i] - 'a'] = true;
            } else {
                upperCaseLookup[arr[i] - 'A'] = true;
            }
        }
        
        int result = 0;
        
        for (int i = 0; i < NO_OF_CHARS; i++) {
            result += (lowerCaseLookup[i] && upperCaseLookup[i]) ? 1 : 0;
        }
        
        return result;
    }
}
```
