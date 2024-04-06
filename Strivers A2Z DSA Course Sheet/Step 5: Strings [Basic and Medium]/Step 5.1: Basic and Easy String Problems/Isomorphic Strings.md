# Isomorphic Strings

- https://leetcode.com/problems/isomorphic-strings/
- we know `s[i]` is mapped to `t[i]`
- if we encounter an x such that `s[x]` = `s[i]`, then `t[x]` should also be = `t[i]`
- case i missed - if `s[i]` has not been already mapped, `t[i]` should not be mapped to something else

```java
class Solution {

    public boolean isIsomorphic(String s, String t) {

        char[] source = s.toCharArray();
        char[] target = t.toCharArray();

        int m = source.length;
        int n = target.length;

        if (m != n) return false;

        int[] map = new int[256];
        Arrays.fill(map, -1);
        boolean[] alreadyMapped = new boolean[256];

        for (int i = 0; i < n; i++) {
            if (map[source[i]] == -1) {
                if (alreadyMapped[target[i]]) {
                    return false;
                }
                map[source[i]] = target[i];
                alreadyMapped[target[i]] = true;
            } else if (map[source[i]] != target[i]) {
                return false;
            }
        }

        return true;
    }
}
```
