# Rotate String

- https://leetcode.com/problems/rotate-string/

## Brute Force

- complexity - O(size * (2 * size)) - both creating new substrings and comparing the strings require O(n) so thats where the 2*n comes from
- another brute force technique, not discussed - make `s = s + s`
- now, we just have to search for goal in s - we do not have to every time create new strings by breaking into substrings
- i feel this newer one might run better, but not bothering myself

```java
class Solution {

    public boolean rotateString(String s, String goal) {

        if (s.length() != goal.length()) {
            return false;
        }
        
        for (int i = 0; i < s.length(); i++) {
            if (s.equals(goal.substring(i, s.length()) + goal.substring(0, i))) {
                return true;
            }
        }

        return false;
    }
}
```

## Optimal

- use the same concept from brute force but optimal - 
- make `s = s + s`
- then use kmp (discussed [here](/Strivers%20A2Z%20DSA%20Sheet/Step%2018:%20Strings/Step%2018.1/KMP%20algo%20LPS(pi)%20array.md))
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
