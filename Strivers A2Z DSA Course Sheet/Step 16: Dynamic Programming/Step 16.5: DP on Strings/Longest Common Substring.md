# Longest Common Substring

- https://www.codingninjas.com/studio/problems/longest-common-substring_1235207
- like [this](./Longest%20Common%20Subsequence.md), but with minor tweaks
- longest substring ending at i, j - i-1,j-1 + 1 if i and j are same
- longest - maintain a separate variable. understand that this time, `previous[len - 1]` will not have the result like in subsequences

```java
public class Solution {

    public static int lcs(String str1, String str2) {
        
        char[] str1Arr = str1.toCharArray();
        char[] str2Arr = str2.toCharArray();

        int len1 = str1Arr.length;
        int len2 = str2Arr.length;

        int[] previous = new int[len2];
        int result = 0;

        for (int i = 0; i < len1; i++) {
            
            int[] current = new int[len2];
            
            for (int j = 0; j < len2; j++) {
                if (str1Arr[i] == str2Arr[j]) {
                    current[j] = ((i == 0 || j == 0) ? 0 : previous[j - 1]) + 1;
                    result = Math.max(result, current[j]);
                }
            }

            previous = current;
        }

        return result;
    }
}
```
