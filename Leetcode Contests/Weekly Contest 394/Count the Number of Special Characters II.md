# Count the Number of Special Characters II

- https://leetcode.com/problems/count-the-number-of-special-characters-ii/

```java
class Solution {
    
    private static final int NO_OF_CHARS = 'z' - 'a' + 1;

    public int numberOfSpecialChars(String word) {
                
        char[] arr = word.toCharArray();
        int n = arr.length;
        
        int[] lowerCaseLookup = new int[NO_OF_CHARS];
        int[] upperCaseLookup = new int[NO_OF_CHARS];
        
        Arrays.fill(lowerCaseLookup, -1);
        Arrays.fill(upperCaseLookup, -1);
        
        for (int i = 0; i < n; i++) {
            if (arr[i] >= 'a' && arr[i] <= 'z') {
                lowerCaseLookup[arr[i] - 'a'] = i;
            } else {
                if (upperCaseLookup[arr[i] - 'A'] == -1) {
                    upperCaseLookup[arr[i] - 'A'] = i;
                }
            }
        }
        
        int result = 0;
        
        for (int i = 0; i < NO_OF_CHARS; i++) {
            if (lowerCaseLookup[i] != -1 && upperCaseLookup[i] != -1) {
                result += (lowerCaseLookup[i] < upperCaseLookup[i]) ? 1 : 0;
            }
        }
        
        return result;
    }
}
```
