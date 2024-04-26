# Latest Time You Can Obtain After Replacing Characters

- https://leetcode.com/problems/latest-time-you-can-obtain-after-replacing-characters/

```java
class Solution {

    public String findLatestTime(String s) {
        
        char[] arr = s.toCharArray();
        
        if (arr[0] == '?') {
            if (arr[1] == '?' || arr[1] == '1'  || arr[1] == '0') {
                arr[0] = '1';
            } else {
                arr[0] = '0';
            }
        }
        
        if (arr[1] == '?') {
            if (arr[0] == '1') {
                arr[1] = '1';
            } else {
                arr[1] = '9';
            }
        }
        
        if (arr[3] == '?') {
            arr[3] = '5';
        }
        
        if (arr[4] == '?') {
            arr[4] = '9';
        }
        
        return new String(arr);
    }
}
```
