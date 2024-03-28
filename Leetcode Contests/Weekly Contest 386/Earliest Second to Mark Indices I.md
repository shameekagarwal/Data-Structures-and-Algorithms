# Earliest Second to Mark Indices I

- https://leetcode.com/problems/earliest-second-to-mark-indices-i/
- assume i want to do it in x seconds
- if my changeIndices from 1 to x looks like this - 2,2,2,2,3,2,2,1
- we should take the last possibility for all indices
- assume -1 means decrement, and we want to change to zero based indexing
- so, array becomes -1,-1,-1,-1,2,-1,1,0 - we only do the marking on the latest possible, and we utilize all other seconds to decrement
- after this, we try counting the elements we are able to mark - if it is same as n good or not
- now, we perform binary search on the number of seconds i.e. n to m (at least n seconds are needed to mark - assume n zeroes)
- initialize result as -1, and every time condition is possible, reduce high but update result as well

```java
class Solution {

    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        
        int n = nums.length;
        int m = changeIndices.length;

        int low = n;
        int high = m;

        int result = -1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            int[] operations = getOperations(changeIndices, mid, n);
            int marked = getMarkable(operations, mid, nums);

            if (marked == n) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    private int getMarkable(int[] operations, int noOfOperations, int[] nums) {

        int marked = 0;
        int decrements = 0;

        for (int i = 0; i < noOfOperations; i++) {
            if (operations[i] == -1) {
                decrements += 1;
            } else if (decrements >= nums[operations[i]]) {
                decrements -= nums[operations[i]];
                marked += 1;
            }
        }

        return marked;
    }

    private int[] getOperations(int[] changeIndices, int noOfOperations, int n) {

        int[] operations = Arrays.copyOfRange(changeIndices, 0, noOfOperations);

        int[] markLookup = new int[n];
        Arrays.fill(markLookup, -1);

        for (int i = 0; i < noOfOperations; i++) {
            
            operations[i] -= 1;
            int previousIndex = markLookup[operations[i]];
            
            if (previousIndex != -1) {
                operations[previousIndex] = -1;
            }

            markLookup[operations[i]] = i;
        }

        return operations;
    }
}
```
