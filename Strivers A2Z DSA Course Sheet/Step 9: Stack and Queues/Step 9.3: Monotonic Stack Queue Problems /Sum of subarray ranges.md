# Sum of subarray ranges

- https://leetcode.com/problems/sum-of-subarray-ranges/
- naive - O(n^2) - 

```java
class Solution {

    public long subArrayRanges(int[] nums) {
        long result = 0;
        for (int i = 0; i < nums.length; i++) {
            int min = nums[i];
            int max = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                result += (max - min);
            }
        }
        return result;
    }
}
```

- better - inspired by [Sum of Subarray Minimums](./Sum%20of%20Subarray%20Minimums.md)
- for interview, point out this can use "strategy pattern"
- time complexity - O(n)

```java
class Solution {

    public long subArrayRanges(int[] nums) {

        int[] plt = getPlt(nums); // previous less than
        int[] pgt = getPgt(nums); // previous greater than
        int[] nle = getNle(nums); // next less than or equal to
        int[] nge = getNge(nums); // next greater than or equal to

        long result = 0;
        for (int i = 0; i < nums.length; i++) {
            
            long countSmallest = 1L * (i - plt[i]) * (nle[i] - i);
            long contributionSmallest = countSmallest * nums[i];
            result -= contributionSmallest;

            long countLargest = 1L * (i - pgt[i]) * (nge[i] - i);
            long contributionLargest = countLargest * nums[i];
            result += contributionLargest;
        }

        return result;
    }

    private int[] getPlt(int[] nums) {

        Deque<Integer> stack = new ArrayDeque<>();
        int[] plt = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peekLast()] > nums[i]) {
                stack.removeLast();
            }
            plt[i] = stack.isEmpty() ? -1 : stack.peekLast();
            stack.addLast(i);
        }

        return plt;
    }

    private int[] getPgt(int[] nums) {

        Deque<Integer> stack = new ArrayDeque<>();
        int[] pgt = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peekLast()] < nums[i]) {
                stack.removeLast();
            }
            pgt[i] = stack.isEmpty() ? -1 : stack.peekLast();
            stack.addLast(i);
        }

        return pgt;
    }

    private int[] getNle(int[] nums) {

        Deque<Integer> stack = new ArrayDeque<>();
        int[] nle = new int[nums.length];

        for (int i = nums.length - 1; i > -1; i--) {
            while (!stack.isEmpty() && nums[stack.peekLast()] >= nums[i]) {
                stack.removeLast();
            }
            nle[i] = stack.isEmpty() ? nums.length : stack.peekLast();
            stack.addLast(i);
        }

        return nle;
    }

    private int[] getNge(int[] nums) {

        Deque<Integer> stack = new ArrayDeque<>();
        int[] nge = new int[nums.length];

        for (int i = nums.length - 1; i > -1; i--) {
            while (!stack.isEmpty() && nums[stack.peekLast()] <= nums[i]) {
                stack.removeLast();
            }
            nge[i] = stack.isEmpty() ? nums.length : stack.peekLast();
            stack.addLast(i);
        }

        return nge;
    }
}
```

- optimal - just use one stack for each min and max contribution
- reference - [Largest Rectangle in Histogram](./Largest%20Rectangle%20in%20Histogram.md)

```java
class Solution {

    public long subArrayRanges(int[] nums) {

        int n = nums.length;
        return maxContribution(nums, n) - minContribution(nums, n);
    }

    private long maxContribution(int[] nums, int n) {
        
        long result = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i <= n; i++) {

            while (!stack.isEmpty() && ((i == n) || (nums[stack.peekLast()] < nums[i]))) {
                
                int idx = stack.removeLast();
                int left = stack.isEmpty() ? -1 : stack.peekLast();
                int right = i;
                int noOfSubArrays = (idx - left) * (right - idx);
                // System.out.printf("max contrib: [%d to %d]: %d\n", left, right, nums[idx]);

                result += (noOfSubArrays * 1L * nums[idx]);
            }

            stack.addLast(i);
        }

        return result;
    }

    private long minContribution(int[] nums, int n) {

        long result = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i <= n; i++) {

            while (!stack.isEmpty() && ((i == n) || (nums[stack.peekLast()] > nums[i]))) {
                
                int idx = stack.removeLast();
                int left = stack.isEmpty() ? -1 : stack.peekLast();
                int right = i;
                int noOfSubArrays = (idx - left) * (right - idx);
                // System.out.printf("min contrib: [%d to %d]: %d\n", left, right, nums[idx]);

                result += (noOfSubArrays * 1L * nums[idx]);
            }

            stack.addLast(i);
        }

        return result;
    }
}
```
