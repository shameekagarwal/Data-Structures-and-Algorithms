# Number of Substrings Containing All Three Characters

- https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
- iterate from 0 to n
- assume we want all substrings ending at i
- we would first need the minimum of ("last seen a", "last seen b", "last seen c") - one of them would be i
- assume the value of above is x. we can use any substring starting from 0 to x and ending at i
- so, we add x + 1 to the result 

```java
class Solution {

    public int numberOfSubstrings(String s) {

        char[] characters = s.toCharArray();
        
        int lastASeen = -1;
        int lastBSeen = -1;
        int lastCSeen = -1;
        int result = 0;
        
        for (int i = 0; i < characters.length; i++) {
            
            if (characters[i] == 'a') {
                lastASeen = i;
            } else if (characters[i] == 'b') {
                lastBSeen = i;
            } else if (characters[i] == 'c') {
                lastCSeen = i;
            }

            result += Math.min(lastASeen, Math.min(lastBSeen, lastCSeen)) + 1;
        }

        return result;
    }
}
```
