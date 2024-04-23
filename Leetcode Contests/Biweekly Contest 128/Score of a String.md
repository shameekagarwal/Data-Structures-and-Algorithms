# Score of a String

- https://leetcode.com/problems/score-of-a-string/

```java
class Solution {

    public int scoreOfString(String s) {
        
        char[] arr = s.toCharArray();
        int n = arr.length;
        int result = 0;
        
        for (int i = 0; i < n - 1; i++) {
            result += Math.abs(arr[i] - arr[i + 1]);
        }
        
        return result;
    }
}
```
