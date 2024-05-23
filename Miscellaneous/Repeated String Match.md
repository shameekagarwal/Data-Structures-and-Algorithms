# Repeated String Match

- https://leetcode.com/problems/repeated-string-match/
- observation - basically, b has to be of the following form - 
  - (some suffix of a) + (n * a) + (some prefix of a)
- so, there are three parts
- what is n? - floor(b.length / a.length)
- case 1 - if some suffix of a is empty - answer is n + 1
  ```
     d abcd abcd
  abcd abcd abcd
  ```
- case 2 - similarly, if some prefix of a is empty - answer is n + 1 again
  ```
  abcd abcd a
  abcd abcd abcd
  ```
- case 3 - if some prefix of a + some suffix of a together form a - answer is n + 1
  ```
    cd abcd abcd ab
       abcd abcd abcd (ye last ka cd starting ka match karega)
  ```
- case 4 - if both are partial - answer is n + 2
  ```
     d abcd abcd a
  abcd abcd abcd abcd
  ```

```java
class Solution {

    public int repeatedStringMatch(String a, String b) {
        return repeatedStringMatch(a.toCharArray(), b.toCharArray());
    }

    public int repeatedStringMatch(char[] a, char[] b) {

        int m = a.length;
        int n = b.length;
        int times = n / m;

        for (int i = times; i <= times + 2; i++) {

            char[] aRepeated = repeat(a, i);

            if (kmp(aRepeated, b)) {
                return i;
            }
        }

        return -1;
    }

    private boolean kmp(char[] a, char[] b) {

        int[] lps = constructLps(b);
        int i = 0;
        int j = 0;

        while (i < a.length) {
            
            if (a[i] == b[j]) {

                i += 1;
                j += 1;

                if (j == b.length) {
                    return true;
                }

            } else if (j != 0) {
                j = lps[j - 1];
            } else {
                i += 1;
            }
        }

        return false;
    }

    private int[] constructLps(char[] b) {

        int[] lps = new int[b.length];
        int i = 1;
        int j = 0;

        while (i < b.length) {
            if (b[i] == b[j]) {
                lps[i] = j + 1;
                j += 1;
                i += 1;
            } else if (j != 0) {
                j = lps[j - 1];
            } else {
                i += 1;
            }
        }

        return lps;
    }

    private char[] repeat(char[] a, int times) {

        char[] aRepeated = new char[a.length * times];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < times; j++) {
                aRepeated[a.length * j + i] = a[i];
            }
        }

        return aRepeated;
    }
}
```
