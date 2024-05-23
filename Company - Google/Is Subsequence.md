# Is Subsequence

- https://leetcode.com/problems/is-subsequence/
- interviewer mana greedy ka proof example se to ok, else give up

```java
class Solution {

    public boolean isSubsequence(String s, String t) {
        return isSubsequence(s.toCharArray(), t.toCharArray());
    }

    public boolean isSubsequence(char[] s, char[] t) {

        int sPtr = 0;

        for (int tPtr = 0; tPtr < t.length && sPtr < s.length; tPtr++) {

            if (t[tPtr] == s[sPtr]) {
                sPtr += 1;
            }
        }

        return sPtr == s.length;
    }
}
```
