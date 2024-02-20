# Isomorphic Strings

- https://leetcode.com/problems/isomorphic-strings/
- we know `s[i]` is mapped to `t[i]`
- if we encounter an x such that `s[x]` = `s[i]`, then `t[x]` should also be = `t[i]`
- case i missed - if `s[i]` has not been already mapped, `t[i]` should not be mapped to something else

```java
class Solution {

    public boolean isIsomorphic(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        int map[] = new int[65536];
        boolean alreadyMapped[] = new boolean[65536];

        for (int i = 0; i < s.length(); i++) {
            if (map[s.charAt(i)] == 0) {
                if (alreadyMapped[t.charAt(i)]) {
                    return false;
                }
                map[s.charAt(i)] = t.charAt(i);
                alreadyMapped[t.charAt(i)] = true;
            } else if (map[s.charAt(i)] != t.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
```
