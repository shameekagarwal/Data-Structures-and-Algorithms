# Word Break

- https://leetcode.com/problems/word-break/
- important - was getting tle for the case - 
  ```
  "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"
  ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
  ```
- at this point i realized - if i can break a sentence up to x into words - does not matter how - i do not need to care about all other solutions for it, given that question asks for true false, not for all possible ways
- so, i used a `isBreakableUpto` boolean array to help achieve this

```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] isBreakableUpto = new boolean[s.length()];
        return recurse(s, wordDict, 0, isBreakableUpto);
    }

    private boolean recurse(String s, List<String> wordDict, int idx, boolean[] isBreakableUpto) {

        if (idx == s.length()) return true;

        for (int i = idx; i < s.length(); i++) {
            if (!isBreakableUpto[i] && wordDict.contains(s.substring(idx, i + 1))) {
                isBreakableUpto[i] = true;
                // System.out.printf("found %s at %d\n", s.substring(idx, i + 1), idx);
                if (recurse(s, wordDict, i + 1, isBreakableUpto)) {
                    return true;
                }
            }
        }

        return false;
    }
}
```
