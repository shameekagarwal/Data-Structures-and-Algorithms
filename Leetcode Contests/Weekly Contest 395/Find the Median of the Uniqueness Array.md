# Find the Median of the Uniqueness Array

- https://leetcode.com/problems/find-the-median-of-the-uniqueness-array/
- binary search on answer
- bounds for this binary search - l = 1, r = total unique elements
- let us our current possible median is m
  - we try to find all elements in the "uniqueness array" less than or equal to m
    - we can only allow windows with m or lesser unique elements
    - we use frequency map to maintain the unique elements count in current window
    - for any r, we can chose r - l + 1 starting points
  - we want this number to be >= half of (n+1)C(2) (definition of median)
  - if it is, then update median but try looking for a smaller value
  - else, we have to look for a larger value

```java
class Solution {

    public int medianOfUniquenessArray(int[] nums) {

        int l = 1;
        int r = getUniqueNoOfElements(nums);
        int median = -1;

        int n = nums.length;

        long totalUniquenessElements = n * 1L * (n + 1) / 2;
        long required = (totalUniquenessElements + 1) / 2;

        while (l <= r) {

            int m = (l + r) / 2;

            long count = getUniquenessElementsLE(nums, m);

            if (count >= required) {
                median = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return median;
    }

    private long getUniquenessElementsLE(int[] nums, int median) {

        Map<Integer, Integer> frequencyLookup = new HashMap<>();

        long result = 0;

        int l = 0;

        for (int r = 0; r < nums.length; r++) {

            frequencyLookup.put(nums[r], frequencyLookup.getOrDefault(nums[r], 0) + 1);

            while (frequencyLookup.size() > median) {

                frequencyLookup.put(nums[l], frequencyLookup.get(nums[l]) - 1);

                if (frequencyLookup.get(nums[l]) == 0) {
                    frequencyLookup.remove(nums[l]);
                }

                l += 1;
            }

            result += (r - l + 1);
        }

        return result;
    }

    private int getUniqueNoOfElements(int[] nums) {

        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        return set.size();
    }
}
```
