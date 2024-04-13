# Sum of Subarray Minimums

- https://leetcode.com/problems/sum-of-subarray-minimums/
- brute - O(n^2)
- optimal - for every element, get the previous smallest element and next smallest element - this is a typical "monotonic stack" problem
- e.g. [Next Greater Element](./Next%20Greater%20Element.md)
- now, we have the index of previous smallest element (-1 if current element is the smallest till everything before it)
- now, we have the index of next smallest element (array's length if current element is the smallest from everything after it)
- so, total sub arrays possible - `(i - pse[i]) * (nse[i] - i)`
- e.g. for `[3,1,2,4]`, for 1, pse is -1 and nse is 4
- count is `(1 - -1) * (4 - 1) = 6`
  - `3,1`, `3,1,2`, `3,1,2,4`
  - `1`, `1,2`, `1,2,4`
- contribution to result = `count * arr[i]`
- corner case - to solve for cases where `=`, i was looking for strictly `<` in pse but `<=` for nse

```java
class Solution {

    private static final int MOD = 1000000007;

    public int sumSubarrayMins(int[] arr) {

        int[] pse = constructPse(arr);
        int[] nse = constructNse(arr);

        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            int count = ((nse[i] - i) * (i - pse[i])) % MOD;
            int contribution = (int) ((1L * count * arr[i]) % MOD);
            result = (result + contribution) % MOD;
        }

        return result;
    }

    private int[] constructPse(int[] arr) {

        int[] pse = new int[arr.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peekLast()] >= arr[i]) {
                stack.removeLast();
            }
            pse[i] = stack.isEmpty() ? -1 : stack.peekLast();
            stack.addLast(i);
        }

        return pse;
    }

    private int[] constructNse(int[] arr) {

        int[] nse = new int[arr.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = arr.length - 1; i > -1; i--) {
            while (!stack.isEmpty() && arr[stack.peekLast()] > arr[i]) {
                stack.removeLast();
            }
            nse[i] = stack.isEmpty() ? arr.length : stack.peekLast();
            stack.addLast(i);
        }

        return nse;
    }
}
```

## Optimal 

- refer [](./Largest%20Rectangle%20in%20Histogram.md)

```java
class Solution {

    private static final int MOD = 1000000007;

    public int sumSubarrayMins(int[] arr) {

        int n = arr.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0;

        for (int i = 0; i <= n; i++) {

            while (!stack.isEmpty() && ((i == n) || (arr[stack.peekLast()] >= arr[i]))) {

                int idx = stack.removeLast();
                int left = stack.isEmpty() ? -1 : stack.peekLast();
                int right = i;
                int noOfSubArrays = mul(idx - left, right - idx);
                result = add(result, mul(noOfSubArrays, arr[idx]));
            }

            stack.addLast(i);
        }

        return result;
    }

    private int mul(int a, int b) {
        return (int)((a * 1L * b) % MOD);
    }

    private int add(int a, int b) {
        return (int)((a + 0L + b) % MOD);
    }
}
```
