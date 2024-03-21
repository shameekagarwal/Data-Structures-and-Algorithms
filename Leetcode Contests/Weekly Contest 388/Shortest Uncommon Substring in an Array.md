# Shortest Uncommon Substring in an Array

- https://leetcode.com/problems/shortest-uncommon-substring-in-an-array/
- its just an implementation heavy problem - generate all substrings - takes O(k*2)
- iterate over all strings
- generate all substrings of these strings
- for each substring, determine how many strings contain this substring
- complexity - O(n * k^2 * n * k)

```java
class Solution {

    public String[] shortestSubstrings(String[] arr) {
        
        String[] result = new String[arr.length];
        
        for (int i = 0; i < arr.length; i++) {
            
            String word = arr[i];
            String shortestSubstring = "";

            for (int length = 1; length <= word.length(); length++) {

                for (int start = 0; start <= word.length() - length; start++) {
                    
                    String currentSubstring = word.substring(start, start + length);
                    boolean possible = true;
                    
                    for (int j = 0; j < arr.length; j++) {
                        if (i == j) continue;
                        if (arr[j].contains(currentSubstring)) {
                            possible = false;
                            break;
                        }
                    }
                    
                    if (possible) {
                        if (shortestSubstring.equals("")) {
                            shortestSubstring = currentSubstring;
                        } else {
                            shortestSubstring = shortestSubstring.compareTo(currentSubstring) < 0 ? shortestSubstring : currentSubstring;
                        }
                    }
                }
                
                if (!shortestSubstring.equals("")) {
                    break;
                }
            }
            
            result[i] = shortestSubstring;
        }
        
        return result;
    }
}
```
