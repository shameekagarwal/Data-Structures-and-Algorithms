# Longest Uncommon Subsequence II

- https://leetcode.com/problems/longest-uncommon-subsequence-ii/
- observation - answer will have to be one of the strings itself - we are looking for longest, and its harder to match longer subsequences than shorter ones
- now, for each string, try if any other string is a super sequence of the current string
- if yes, it cannot be the answer. else, maximize the result using the length of this string

```java
class Solution {

    public int findLUSlength(String[] strs) {

        int result = -1;

        for (int i = 0; i < strs.length; i++) {

            boolean possible = true;

            for (int j = 0; j < strs.length; j++) {
                
                if (i == j) continue;

                // System.out.println(strs[i] + ", " + strs[j] + ": " + strs[j].indexOf(strs[i]));

                if (isSubsequene(strs[j], strs[i])) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                result = Math.max(result, strs[i].length());
            }
        }

        return result;
    }

    private boolean isSubsequene(String a, String b) {

        int ptrA = 0;
        int ptrB = 0;

        while (ptrA < a.length() && ptrB < b.length()) {

            if (a.charAt(ptrA) == b.charAt(ptrB)) {
                ptrB += 1;
            }

            ptrA += 1;
        }

        return ptrB == b.length();
    }
}
```
