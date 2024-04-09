#  First Occurrence of a Pattern in a Text

- https://www.naukri.com/code360/problems/first-occurence-of-a-pattern-in-a-text_8416393
- method 1 - kmp can be used

```java
public class Solution {

    public static int firstOccurence(String text, String pat) {
        return firstOccurence(text.toCharArray(), pat.toCharArray());
    }

    private static int firstOccurence(char[] text, char[] pat) {

        int n = text.length;
        int m = pat.length;

        int[] lps = getLps(text);

        int i = 0;
        int j = 0;

        while (i < n) {

            if (text[i] == pat[j]) {

                i += 1;
                j += 1;

                if (j == m) return i - m;
            } else if (j == 0) {
                i += 1;
            } else {
                j = lps[j - 1];
            }
        }

        return -1;
    }

    private static int[] getLps(char[] str) {

        int n = str.length;
        int[] lps = new int[n];
        int i = 1;
        int prevLps = 0;

        while (i < n) {

            if (str[i] == str[prevLps]) {
                lps[i] = prevLps + 1;
                i += 1;
                prevLps += 1;
            } else if (prevLps == 0) {
                i += 1;
            } else {
                prevLps = lps[prevLps - 1];
            }
        }

        return lps;
    }
}
```
