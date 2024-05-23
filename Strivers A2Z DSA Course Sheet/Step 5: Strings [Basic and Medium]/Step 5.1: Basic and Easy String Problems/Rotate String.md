# Rotate String

- https://leetcode.com/problems/rotate-string/

## Brute Force

- check for all rotations from 0 to n-1
- complexity - O(n^2)

```java
class Solution {

    public boolean rotateString(String s, String goal) {
        return rotateString(s.toCharArray(), goal.toCharArray());
    }

    public boolean rotateString(char[] s, char[] goal) {

        if (s.length != goal.length) {
            return false;
        }

        int n = s.length;

        for (int rotation = 0; rotation < n; rotation++) {

            boolean matches = true;

            for (int i = 0; i < n; i++) {

                if (s[i] != goal[(i + rotation) % n]) {
                    matches = false;
                    break;
                }
            }

            if (matches) {
                return true;
            }
        }

        return false;
    }
}

```

## Optimal

- make `s = s + s`
- then use kmp (discussed [here](../../Step%2018:%20Strings/Step%2018.1/KMP%20algo%20LPS(pi)%20array.md))
- so important? - to check if an array is a rotated version of another array - we use kmp?
- note - place condition of lengths not equal at the beginning - otherwise, a will be found in aa, i.e. aa + aa, but actually its substring not rotated version

```java
class Solution {

    public boolean rotateString(String s, String goal) {

        if (s.length() != goal.length()) return false;

        char[] goalArr = goal.toCharArray();

        int[] lps = constructLps(goalArr);

        char[] sDoubled = (s + s).toCharArray();

        int i = 0;
        int j = 0;
        while (i < sDoubled.length) {

            if (sDoubled[i] == goalArr[j]) {
                i += 1;
                j += 1;
                if (j == goalArr.length) {
                    return true;
                }
            } else if (j == 0) {
                i += 1;
            } else {
                j = lps[j - 1];
            }
        }

        return false;
    }

    private int[] constructLps(char[] sArr) {

        int[] lps = new int[sArr.length];
        int prevLps = 0;
        int i = 1;

        while (i < sArr.length) {
            if (sArr[i] == sArr[prevLps]) {
                lps[i] = prevLps + 1;
                i += 1;
                prevLps += 1;
            } else if (prevLps == 0) {
                lps[i] = 0;
                i += 1;
            } else {
                prevLps = lps[prevLps - 1];
            }
        }

        return lps;
    }
}
```
