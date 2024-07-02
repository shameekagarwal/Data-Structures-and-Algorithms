# Number of Longest Increasing Subsequence

- https://leetcode.com/problems/number-of-longest-increasing-subsequence/
- thought process - for lis ending at i, we do not care how - we want the "count" of all "max lis lengths" possible which "end at i"
- [Longest Increasing Subsequence](./Longest%20Increasing%20Subsequence.md) with maintaining counts - 
  - if larger, reset count to `count[j]`
  - if equal, add `count[j]`
- for dry runs in interview, think of good examples like - 1 5 3 2 6 - for 6, we can use
  - 1 5 6
  - 1 3 6
  - 1 2 6

```java
class Solution {

    public int findNumberOfLIS(int[] nums) {

        int n = nums.length;

        int[] lisSize = new int[n];
        int[] lisCount = new int[n];

        for (int i = 0; i < n; i++) {

            lisSize[i] = 1;
            lisCount[i] = 1;

            for (int j = 0; j < i; j++) {

                if (nums[i] > nums[j]) {

                    if (lisSize[j] + 1 > lisSize[i]) {
                        lisSize[i] = lisSize[j] + 1;
                        lisCount[i] = lisCount[j];
                    } else if (lisSize[j] + 1 == lisSize[i]) {
                        lisCount[i] += lisCount[j];
                    }
                }
            }
        }

        // System.out.println(Arrays.toString(lisSize));
        // System.out.println(Arrays.toString(lisCount));

        int maxLisSize = 0;
        int count = 0;

        for (int i = 0; i < n; i++) {

            if (maxLisSize < lisSize[i]) {
                maxLisSize = lisSize[i];
                count = lisCount[i];
            } else if (maxLisSize == lisSize[i]) {
                count += lisCount[i];
            }
        }

        return count;
    }
}
```
