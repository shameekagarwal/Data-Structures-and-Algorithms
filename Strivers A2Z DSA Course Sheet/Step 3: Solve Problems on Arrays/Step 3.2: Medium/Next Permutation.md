# Next Permutation

- https://leetcode.com/problems/next-permutation/
- brute force - generate all permutations, O(n! * n) (n! permutations, n is size of each permutation)
- optimal - lets assume number if - 2 1 5 4 3 0 0
- we if we keep 2 and 1 as is, the remaining is the max possible
- we need to exchange 1 with the "next largest number" after it - 3 in this case
- doubt, can the above bit be "binary searched"? - yes, but it would not affect time complexity, since we anyway have other O(N) operations
- now, we make the number 2 3 [5 4 1 0 0]
- now, 5 4 1 0 0 we need to make smallest possible - just reverse it!
- small edge case i made mistake - when reversing, e.g. assume it is 5 5 5 5
- we need to swap with last 5, not first - otherwise reversing will not make it ascending

```java
class Solution {

    public void nextPermutation(int[] nums) {

        int pivot = nums.length - 2;

        while (pivot > -1 && nums[pivot] >= nums[pivot + 1]) {
            pivot -= 1;
        }

        if (pivot == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        int idx = findUpper(nums, pivot + 1, nums.length - 1, nums[pivot]);
        swap(nums, pivot, idx);
        reverse(nums, pivot + 1, nums.length - 1);
    }

    private int findUpper(int[] arr, int l, int r, int element) {

        int idx = -1;

        while (l <= r) {

            int m = (l + r) / 2;

            if (arr[m] > element) {
                l = m + 1;
                idx = m;
            } else {
                r = m - 1;
            }
        }

        return idx;
    }

    private void reverse(int[] nums, int l, int r) {

        for (int i = l; i <= (l + r) / 2; i++) {
            swap(nums, i, r - (i - l));
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
```
