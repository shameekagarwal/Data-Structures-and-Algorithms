# Rabin Karp

- brute force of finding needle in haystack - O(n * m)
- option 1 - [KMP](./KMP%20algo%20LPS(pi)%20array.md)
- another option - this rabin karp
- sliding window - rolling hash
- a good hash - 26^i, where i is the position
- abc - (26^2 * 1) + (26^1 * 2) + 3
- this value can end up being very large - so, we use modulo - 1000000007
- after modulo - we might have collisions
  - so, compare the pattern again if match is found
  - another solution - take two hashes - (10^9 + 7) (with multiplier 26) and (10^9 + 33) (with multiplier 27)
- remember to write modular versions of addition, subtraction, power, etc
- calculating rolling_hash for next window = 
  - subtract contribution of previous window's 1st character
  - multiple the remaining hash with the multiplier
  - add contribution of current window's last character

```java
import java.util.Arrays;

public class Solution {

    private static int MUL = 27;
    private static int MOD = 1000000007;

    public static int firstOccurence(String text, String pat){
        return firstOccurence(text.toCharArray(), pat.toCharArray());
    }

    private static int firstOccurence(char[] text, char[] pat) {

        int n = text.length;
        int m = pat.length;

        long patternHash = calculateHash(Arrays.copyOfRange(pat, 0, m));
        long textHash = calculateHash(Arrays.copyOfRange(text, 0, m));

        for (int i = m - 1; i < n; i++) {

            if (i != m - 1) {
                
                long multiplier = pow(MUL, m - 1);
                long toSubtract = (getCharValue(text[i - m]) * multiplier) % MOD;
                textHash = (((textHash - toSubtract) % MOD) + MOD) % MOD;
                
                textHash = (textHash * MUL) % MOD;

                int toAdd = getCharValue(text[i]);
                textHash = (textHash + toAdd) % MOD;
            }
            // System.out.printf("%d, %d\n", textHash, patternHash);

            if (textHash == patternHash) return i - m + 1;
        }

        return -1;
    }

    private static int getCharValue(char c) {
        return c - 'a' + 1;
    }

    private static long calculateHash(char[] str) {

        int n = str.length;
        long hash = 0;

        for (int i = 0; i < n; i++) {
            long multiplier = pow(MUL, n - 1 - i);
            int currentValue = getCharValue(str[i]);
            hash = (hash + ((currentValue * multiplier) % MOD)) % MOD;
        }

        return hash;
    }

    private static long pow(long a, long b) {

        long res = 1;

        while (b > 0) {
            if (b % 2 == 0) {
                b /= 2;
                a = (a * a) % MOD;
            } else {
                b -= 1;
                res = (res * a) % MOD;
            }
        }

        return res;
    }
}
```
