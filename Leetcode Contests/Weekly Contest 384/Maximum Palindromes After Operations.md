# Maximum Palindromes After Operations

- https://leetcode.com/problems/maximum-palindromes-after-operations/description/
- what is given - any pairs of characters can be swapped
- just used greedy
- count number of characters having odd occurrence and number of characters having even occurrence
- sort the array based on length - smallest to biggest
- start converting to palindrome - pick from even characters
- if word is odd in length
  - first try picking from odd characters
  - if not possible, pick from even characters

```java
class Solution {
    
    private static final int NO_OF_CHARS = 'z' - 'a' + 1;
    
    public int maxPalindromesAfterOperations(String[] words) {
        
        int n = words.length;
        
        words = Arrays.copyOfRange(words, 0, n);
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        
        int[] frequencyLookup = new int[NO_OF_CHARS];
        for (String word : words) {
            for (char c : word.toCharArray()) {
                frequencyLookup[c - 'a'] += 1;
            }
        }
        
        int oddFrequencyChars = 0;
        int evenFrequencyChars = 0;
        for (int frequency : frequencyLookup) {
            oddFrequencyChars += ((frequency % 2 == 0) ? 0 : 1);
            evenFrequencyChars += ((frequency % 2 == 0) ? frequency : frequency - 1);
        }
        
        int ans = 0;
        
        for (String word : words) {
            
            int evenCharactersToPick = ((word.length() % 2 == 0) ? word.length() : word.length() - 1);
            int oddCharactersToPick = ((word.length() % 2 == 0) ? 0 : 1);
            
            if (evenFrequencyChars >= evenCharactersToPick) {
                evenFrequencyChars -= evenCharactersToPick;
                evenCharactersToPick = 0;
            }
            
            if (oddCharactersToPick == 1) {
                if (oddFrequencyChars > 0) {
                    oddFrequencyChars -= 1;
                    oddCharactersToPick = 0;
                } else if (evenFrequencyChars > 0) {
                    evenFrequencyChars -= 1;
                    oddCharactersToPick = 0;
                }
            }
            
            if (oddCharactersToPick == 0 && evenCharactersToPick == 0) {
                ans += 1;
            }
            
        }
        
        return ans;
    }
}
```
