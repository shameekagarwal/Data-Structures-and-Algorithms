# Random Pick with Weight

- https://leetcode.com/problems/random-pick-with-weight/
- we calculate prefix sum
- now, assume we pick any random element from 1 to total weight
- now, we just need to find upper bound of prefix sum array - this will automatically give us the answer
- intuition - prefix sum is monotonically increasing, so binary search is possible

```java
class Solution {

    private int sum;
    private int[] prefixSum;

    public Solution(int[] weights) {

        prefixSum = new int[weights.length];
        prefixSum[0] = weights[0];

        for (int i = 1; i < weights.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + weights[i];
        }
    }

    public int pickIndex() {
        int random = ((int) (Math.random() * prefixSum[prefixSum.length - 1])) + 1;
        int idx = binarySearch(prefixSum, random);
        return idx;
    }

    private int binarySearch(int[] arr, int val) {

        int s = 0;
        int e =  arr.length - 1;
        int result = arr.length - 1;

        while (s <= e) {

            int m = (s + e) / 2;

            if (arr[m] > val) {
                result = m;
                e = m - 1;
            } else if (arr[m] == val) {
                return m;
            } else {
                s = m + 1;
            }
        }

        return result;
    }
}
```
