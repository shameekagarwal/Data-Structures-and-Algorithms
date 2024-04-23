# Median of 2 sorted arrays

- https://leetcode.com/problems/median-of-two-sorted-arrays/

## Brute Force

- merge them, calculate median - O(n)
- remember - however, notice the optimization below - no need to calculate the entire merged array, use extra space, etc - just keep track of two elements - last and second last - till we reach the median position
- if array is even - use both, else just use last
- time complexity - O(n)

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int nums1Ptr = 0;
        int nums2Ptr = 0;
        int totalLength = nums1.length + nums2.length;
        int medianElement = (totalLength / 2) + 1;
        int medianPtr = 0;
        int lastEle = -1;
        int secondLastEle = -1;

        while (nums1Ptr < nums1.length && nums2Ptr < nums2.length && medianPtr < medianElement) {
            if (nums1[nums1Ptr] < nums2[nums2Ptr]) {
                secondLastEle = lastEle;
                lastEle = nums1[nums1Ptr];
                medianPtr += 1;
                nums1Ptr += 1;
            } else {
                secondLastEle = lastEle;
                lastEle = nums2[nums2Ptr];
                medianPtr += 1;
                nums2Ptr += 1;
            }
        }

        while (nums1Ptr < nums1.length && medianPtr < medianElement) {
            secondLastEle = lastEle;
            lastEle = nums1[nums1Ptr];
            medianPtr += 1;
            nums1Ptr += 1;
        }
        
        while (nums2Ptr < nums2.length && medianPtr < medianElement) {
            secondLastEle = lastEle;
            lastEle = nums2[nums2Ptr];
            medianPtr += 1;
            nums2Ptr += 1;
        }

        if (totalLength % 2 == 0) {
            return ((lastEle + secondLastEle) / 2.0);
        } else {
            return lastEle;
        }
    }
}
```

## Optimal

- imagine we pick up x elements from array 1
- say we need to pick up y elements from array 2
- we can easily calculate y based on lengths of array 1 and array 2
- since both arrays are already sorted
- we just need to ensure
  - last element of left half of array 1 > first element of right half of array 2
  - last element of left half of array 2 > first element of right half of array 2

![Median of 2 sorted arrays](./Median%20of%202%20sorted%20arrays.png)

- conditions - 
  - if l1 > r2 - we need to decrease elements picked up from first array
- actual median 
  - (max(l1, l2) + min(r1, r2)) / 2 - if total elements are even
  - max(l1, l2) - if total elements are odd
- time complexity - O(log(smaller array length))?
- important, something i realized when revising - setting the bounds properly is also important - then we would not have to check for 0 length individually
  - if we need to pick 5 elements for left, and 2nd array has length 1, we cannot pick < 4 elements from 1st array
  - if we need to pick 5 elements for left, and 1nd array has length 10, we can pick at max 5, and not 10 elements from 1st array

```java
class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;

        int sz = m + n;
        
        int left = (sz + 1) / 2;
        int right = sz - left;

        int l = Math.max(0, left - n);
        int r = Math.min(m, left);

        while (l <= r) {

            int x = (l + r) / 2;
            int y = left - x;

            if (x != 0 && y != n && nums1[x - 1] > nums2[y]) {
                r = x - 1;
            } else if (y != 0 && x != m && nums2[y - 1] > nums1[x]) {
                l = x + 1;
            } else {
                if (sz % 2 == 0) {

                    int max, min;

                    if (x != 0 && y != 0) {
                        max = Math.max(nums1[x - 1], nums2[y - 1]);
                    } else if (x != 0) {
                        max = nums1[x - 1];
                    } else {
                        max = nums2[y - 1];
                    }

                    if (x != m && y != n) {
                        min = Math.min(nums1[x], nums2[y]);
                    } else if (x != m) {
                        min = nums1[x];
                    } else {
                        min = nums2[y];
                    }

                    return (max + min) / 2.0;

                } else {
                    // max from left
                    if (x != 0 && y != 0) {
                        return Math.max(nums1[x - 1], nums2[y - 1]);
                    } else if (x != 0) {
                        return nums1[x - 1];
                    } else {
                        return nums2[y - 1];
                    }
                }
            }
        }

        return -1;
    }
}
```
