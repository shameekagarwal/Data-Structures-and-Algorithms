# Assign Cookies

- https://leetcode.com/problems/assign-cookies/
- why sort the cookie array - ensure that a child is assigned the smallest possible cookie. e.g. we have greed 1,2 and cookie 1,2. if greed 1 is assigned cookie 2, we can only satisfy 1 child, but answer possible is 2
- why sort the greedy array - we try assigning cookies in ascending order i.e. to less greedy children first. this way, we have more chances of increasing content children
- time complexity - O(NlogN + MlogM)

```java
class Solution {

    public int findContentChildren(int[] g, int[] s) {

        g = Arrays.copyOfRange(g, 0, g.length); Arrays.sort(g);
        s = Arrays.copyOfRange(s, 0, s.length); Arrays.sort(s);

        int childPtr = 0;

        for (int i = 0; i < s.length && childPtr < g.length; i++) {
            if (s[i] >= g[childPtr]) {
                childPtr += 1;
            }
        }

        return childPtr;
    }
}
```
