# Number of Subarrays That Match a Pattern II

- https://leetcode.com/problems/number-of-subarrays-that-match-a-pattern-ii/
- convert source array to the 1,0,-1 equivalent
  - if `i + 1 > i`, set to 1
  - if `i + 1 < i`, set to -1
  - else set to 0
- convert elements of both - pattern and generated compressed array above to positive - add 2 to all elements, so -1,0,1 become 1,2,3
- this is to help with rabin karp hashing
- multiplier of rabin karp can be 3 now
- finally, perform [rabin karp](../../Strivers%20A2Z%20DSA%20Course%20Sheet/Step%2018:%20Strings/Step%2018.1:%20Hard%20Problems/Rabin%20Karp.md). every time the hash matches, add 1 to result
- imp for interviews - see the use of "modulo utils" - add, sub, mul and pow, and how they use each other. makes the code cleaner

```java
class Solution {

    private static final int MOD = 1000000009;
    private static final int MUL = 3;

    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        
        int n = nums.length;
        int m = pattern.length;

        int[] compressed = generateCompressed(nums, n);
        pattern = Arrays.copyOfRange(pattern, 0, m);

        convertToPositive(compressed, n - 1);
        convertToPositive(pattern, m);

        return rabinKarp(compressed, n - 1, pattern, m);
    }

    private int rabinKarp(int[] source, int n, int[] pattern, int m) {
        
        int patternHash = calculateHash(pattern, m);
        int numsHash = calculateHash(source, m);
        int result = 0;

        for (int i = m - 1; i < n; i++) {
           
            if (i != m - 1) {
                int toSubtract = mul(pow(MUL, m - 1), source[i - m]);
                numsHash = sub(numsHash, toSubtract);
                numsHash = mul(numsHash, MUL);
                numsHash = add(numsHash, source[i]);
            }

            // System.out.printf("%d, %d\n", numsHash, patternHash);
           
            if (numsHash == patternHash) result += 1;
        }

        return result;
    }

    private int calculateHash(int[] a, int n) {

        int hash = 0;

        for (int i = 0; i < n; i++) {
            hash = add(hash, mul(pow(MUL, n - 1 - i), a[i]));
        }

        return hash;
    }

    private int pow(int a, int b) {
        
        int res = 1;

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

    private int mul(int a, int b) {
        return (int)(((long) a * b) % MOD);
    }

    private int add(int a, int b) {
        return (int)(((long) a + b) % MOD);
    }

    private int sub(int a, int b) {
        return add((int)(((long) a - b) % MOD), MOD);
    }

    private void convertToPositive(int[] a, int n) {
        for (int i = 0; i < n; i++) {
            a[i] += 2;
        }
    }

    private int[] generateCompressed(int[] nums, int n) {
        
        int[] compressed = new int[n - 1];
        
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                compressed[i] = 1;
            } else if (nums[i] == nums[i + 1]) {
                compressed[i] = 0;
            } else {
                compressed[i] = -1;
            }
        }

        // System.out.println(Arrays.toString(compressed));
        return compressed;
    }
}
```
