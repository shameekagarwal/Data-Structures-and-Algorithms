# Repeated String Match

- https://leetcode.com/problems/repeated-string-match/
- b can be of the following forms - 
  - suffix_of_a + (n * a) + prefix_of_a - can be found inside (n + 2) * a
  - suffix_of_a + (n * a) - can be found inside (n + 1) * a
  - (n * a) + prefix_of_a - can be found inside (n + 1) * a
  - (n * a) - can be found inside n * a
- n = floor(b.size() / a.size()) = 3
- then, add a two more times to it
- finally use [rabin karp](./Rabin%20Karp.md) or [kmp](./KMP%20algo%20LPS(pi)%20array.md) to find size

```java
class Solution {

    private static final int MUL = 27;
    private static final int MOD = 1000000007;

    public int repeatedStringMatch(String a, String b) {

        int m = a.length();
        int n = b.length();

        String chargedA = "";
        int times = n / m;
        for (int i = 1; i <= times + 2; i++) {
            chargedA += a;
            if (i >= times) {
                if (rabinKarp(chargedA, b) != -1) {
                    return i;
                }
            }
        }

        return -1;
    }

    private int rabinKarp(String source, String pattern) {

        char[] pat = pattern.toCharArray();
        int m = pat.length;
        
        char[] src = source.toCharArray();
        int n = src.length;

        long patHash = hash(pat);
        long srcHash = hash(Arrays.copyOfRange(src, 0, m));

        for (int i = m - 1; i < n; i++) {

            if (i != m - 1) {
                long toSubtract = mul(getChar(src[i - m]), pow(MUL, m - 1));
                srcHash = sub(srcHash, toSubtract);
                srcHash = mul(srcHash, MUL);
                srcHash = add(srcHash, getChar(src[i]));
            }

            if (patHash == srcHash) return i - m + 1;
        }

        System.out.println();

        return -1;
    }

    private long hash(char[] a) {

        int n = a.length;
        long hash = 0;

        for (int i = n - 1; i > -1; i--) {
            long charHash = mul(getChar(a[i]), pow(MUL, n - 1 - i));
            hash = add(hash, charHash);
        }

        return hash;
    }

    private int getChar(char c) {
        return c - 'a' + 1;
    }

    private long sub(long a, long b) {
        return (((a - b) % MOD) + MOD) % MOD;
    }

    private long add(long a, long b) {
        return (a + b) % MOD;
    }

    private long mul(long a, long b) {
        return (a * b) % MOD;
    }

    private long pow(long a, long b) {
        
        long res = 1;

        while (b > 0) {
            if (b % 2 != 0) {
                res = mul(res, a);
                b -= 1;
            } else {
                a = mul(a, a);
                b /= 2;
            }
        }

        return res;
    }
}
```
