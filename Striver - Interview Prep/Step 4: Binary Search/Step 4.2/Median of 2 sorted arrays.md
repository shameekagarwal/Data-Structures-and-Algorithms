# Median of 2 sorted arrays

- https://leetcode.com/problems/median-of-two-sorted-arrays/

## Brute Force

- merge them, calculate median - O(n)
- however, notice the optimization below - no need to calculate the entire merged array, use extra space, etc - just keep track of two elements - last and second last - till we reach the median position
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

```java
class Solution {
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // ensure nums1.length < nums2.length
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        // handle boundary cases
        if (nums1.length == 0 && nums2.length == 0) {
            return -1;
        } else if (nums1.length == 0 && nums2.length == 1) {
            return nums2[0];
        }

        // elements on left from first array + second array
        int totalElementsOnLeft = (nums1.length + nums2.length) / 2;

        int low = 0;
        int high = nums1.length;

        while (low <= high) {
            
            int l1 = low + ((high - low) / 2); // elements from first array on left
            int l2 = totalElementsOnLeft - l1; // elements from second array on left

            // decrease elements from first array on left
            if (l1 != 0 && l2 != nums2.length && nums1[l1 - 1] > nums2[l2]) {
                high = l1 - 1;
            }

            // increase elements from first array on left
            else if (l2 != 0 && l1 != nums1.length && nums2[l2 - 1] > nums1[l1]) {
                low = l1 + 1;
            }

            else {
                
                int maxNumFromLeft = -1;
                int minNumFromRight = -1;

                if (l1 == 0) {
                    maxNumFromLeft = nums2[l2 - 1];
                } else if (l2 == 0) {
                    maxNumFromLeft = nums1[l1 - 1];
                } else {
                    maxNumFromLeft = Math.max(nums1[l1 - 1], nums2[l2 - 1]);
                }

                if (l1 == nums1.length) {
                    minNumFromRight = nums2[l2];
                } else if (l2 == nums2.length) {
                    minNumFromRight = nums1[l1];
                } else {
                    minNumFromRight = Math.min(nums1[l1], nums2[l2]);
                }

                if ((nums1.length + nums2.length) % 2 == 0) {
                    return (maxNumFromLeft + minNumFromRight) / 2.0;
                } else {
                    return minNumFromRight;
                }
            }
        }

        return -1;
    }
}
```
