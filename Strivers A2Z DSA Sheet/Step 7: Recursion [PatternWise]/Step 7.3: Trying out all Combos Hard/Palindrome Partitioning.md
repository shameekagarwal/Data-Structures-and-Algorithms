# Palindrome Partitioning

- optimization - pre compute is palindrome
- note - `toCharArray` was not very useful for the recursive call, since we needed substrings
- https://leetcode.com/problems/palindrome-partitioning/

```java
class Solution {
    
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        boolean[][] memoizedPalindrome = getMemoizedPalindrome(s);
        recurse(result, s, 0, new ArrayList<>(), memoizedPalindrome);
        return result;
    }

    private boolean[][] getMemoizedPalindrome(String s) {
        
        char[] str = s.toCharArray();

        boolean[][] dp = new boolean[str.length][str.length];

        for (int i = 0; i < str.length; i++) {
            dp[i][i] = true;
        }

        for (int i = 0; i < str.length - 1; i++) {
            dp[i][i + 1] = (str[i] == str[i + 1]);
        }

        for (int len = 3; len <= str.length; len++) {
            for (int i = 0; i <= str.length - len; i++) {
                dp[i][i + len - 1] = (dp[i + 1][i + len - 2] && (str[i] == str[i + len - 1]));
            }
        }

        return dp;
    }

    private void recurse(List<List<String>> result, String s, int startIdx, List<String> currentPick, boolean[][] memoizedPalindrome) {
        
        if (startIdx == s.length()) {
            result.add(new ArrayList<>(currentPick));
            return;
        }
        
        for (int i = startIdx; i < s.length(); i++) {
            if (memoizedPalindrome[startIdx][i]) {
                String currentPalindrome = s.substring(startIdx, i + 1);
                currentPick.add(currentPalindrome);
                recurse(result, s, i + 1, currentPick, memoizedPalindrome);
                currentPick.remove(currentPick.size() - 1);
            }
        }
    }
}
```
