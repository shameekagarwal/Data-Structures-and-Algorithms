# Shortest Subarray With OR at Least K II

- https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-ii/
- using two pointer approach
- the bitmap contains frequency for a bit
- if it is > 1, that bit is set, else not
- if current window's value reaches >= k, keep increasing l till either l = r+1 (empty array) or value goes below k
- time complexity - 32 * 2N probably?

```java
class Solution {

    public int minimumSubarrayLength(int[] nums, int k) {

        int n = nums.length;
        int or = 0;
        int l = 0;
        int result = n + 1;
        int[] bitmap = new int[31];

        for (int r = 0; r < n; r++) {

            fill(bitmap, nums[r]);

            while (l <= r && valueOfBitmap(bitmap) >= k) {
                result = Math.min(r - l + 1, result);
                remove(bitmap, nums[l]);
                l += 1;
            }
        }

        return result == n + 1 ? -1 : result;
    }

    private void remove(int[] bitmap, int n) {

        for (int i = 0; i < 31; i++) {
            bitmap[i] -= ((n >> i) & 1);
        }
    }

    private int valueOfBitmap(int[] bitmap) {

        int value = 0;

        for (int i = 0; i < 31; i++) {
            int bit = bitmap[i] >= 1 ? 1 : 0;
            value += (bit << i);
        }

        return value;
    }

    private void fill(int[] bitmap, int n) {

        for (int i = 0; i < 31; i++) {
            bitmap[i] += ((n >> i) & 1);
        }
    }
}
```
