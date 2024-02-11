# Sort Colors

- https://leetcode.com/problems/sort-colors/
- naive - count all three and overwrite. complexity - O(3*n)
- optimal - dutch national flag algorithm
- intuition - 
  ```
  0, 0, 0, 1, 1, 1, s, t, u, v,  x,  y,  z,  2,  2,  2
  0, 1, 2, 3, 4, 5, 6, 7, 7, 9, 10, 11, 12, 13, 14, 15
  ```
- endOfZeroes = 2, afterEndOfOnes = 6, beginningOfTwos = 13
- array is unsorted between afterEndOfOnes to (beginningOfTwos - 1)
- now, how to maintain these three pointers, while keep the array sorted from 0 to afterEndOfOnes - 1 and from beginningOfTwos to end is the solution
- complexity - O(n) (notice how it is lesser than naive O(3*n))

```java
class Solution {
    public void sortColors(int[] nums) {

        int endOfZeroes = -1;
        int afterEndOfOnes = 0;
        int endOfTwos = nums.length;

        while (afterEndOfOnes < endOfTwos) {
            if (nums[afterEndOfOnes] == 0) {
                endOfZeroes += 1;
                swap(nums, endOfZeroes, afterEndOfOnes);
                afterEndOfOnes += 1;
            } else if (nums[afterEndOfOnes] == 1) {
                afterEndOfOnes += 1;
            } else {
                endOfTwos -= 1;
                swap(nums, endOfTwos, afterEndOfOnes);
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}

```
