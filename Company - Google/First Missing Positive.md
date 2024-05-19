# First Missing Positive

- https://leetcode.com/problems/first-missing-positive/

## Brute

- use an extra seen boolean array
- question says O(1) space

## Approach 1

- our missing number would have to be from 1 to nums.length + 1
- we check if all numbers from 1 to nums.length are present. if they are, we return nums.length + 1
- step 1 - mark all negative numbers and 0 as any big positive value, e.g. nums.length + 1
  - because they do not contribute anything, and we only check if numbers between 1 to nums.length are present
- step 2 - make the `nums[nums[i] - 1]` negative, provided `nums[i]` is between 1 to nums.length
- step 3 - for every index, check if it is negative. if not, return i + 1

```java
class Solution {

    public int firstMissingPositive(int[] nums) {

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] <= 0) {
                nums[i] = nums.length + 1;
            }
        }

        for (int i = 0; i < nums.length; i++) {

            int element = Math.abs(nums[i]);
            int idx = element - 1;

            if (element <= nums.length) {
                nums[idx] = nums[idx] < 0 ? nums[idx] : -nums[idx];
            }
        }

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] > 0) return i + 1;
        }

        return nums.length + 1;
    }
}
```

## Approach 2

- iterate through each element
- if `nums[i]` >= 1 and <= nums.length
  - if `nums[i] == nums[nums[i] - 1]`, increment i
  - else swap it with `nums[nums[i] - 1]` - do not increment i, we need to again adjust element at ith index
- else, increment i

```java
class Solution {

    public int firstMissingPositive(int[] nums) {

        for (int i = 0; i < nums.length;) {

            if (nums[i] >= 1 && nums[i] <= nums.length) {

                if (nums[nums[i] - 1] == nums[i]) {
                    i += 1;
                } else {
                    swap(nums, nums[i] - 1, i);
                }
            } else {
                i += 1;
            }
        }

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
```
