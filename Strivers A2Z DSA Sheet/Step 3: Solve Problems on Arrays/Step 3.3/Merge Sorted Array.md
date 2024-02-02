# Merge Sorted Array

## Variation 1

- we need to fill nums1
- brute - use the usual merge algorithm by using an extra O(m + n) space, and then copy this temporary array as is into nums1
- optimal - iterate from back, fill from back - dry run to understand how because of this, nums1 would never be overwritten
- i think time complexity of optimal is same (maybe we are saved from an extra iteration of copying), but space complexity reduces to constant
- https://leetcode.com/problems/merge-sorted-array/

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        
        int nums1Pointer = m - 1;
        int nums2Pointer = n - 1;
        int sortedNums1Pointer = m + n - 1;
        
        while (nums1Pointer > -1 && nums2Pointer > -1) {
            if (nums1[nums1Pointer] > nums2[nums2Pointer]) {
                nums1[sortedNums1Pointer] = nums1[nums1Pointer];
                nums1Pointer -= 1;
                sortedNums1Pointer -= 1;
            } else {
                nums1[sortedNums1Pointer] = nums2[nums2Pointer];
                nums2Pointer -= 1;
                sortedNums1Pointer -= 1;
            }
        }
        
        while (nums1Pointer > -1) {
            nums1[sortedNums1Pointer] = nums1[nums1Pointer];
            nums1Pointer -= 1;
            sortedNums1Pointer -= 1;
        }

        while (nums2Pointer > -1) {
            nums1[sortedNums1Pointer] = nums2[nums2Pointer];
            nums2Pointer -= 1;
            sortedNums1Pointer -= 1;
        }
    }
}
```

## Variation 2

- https://www.codingninjas.com/studio/problems/merge-two-sorted-arrays-without-extra-space_6898839
- we just have to fill the two arrays
- we can easily do it in time O(m + n) by using extra space of O(m + n)
- but question clearly states - no extra space
- my understanding - time complexity will go for a toss but we are saved from space complexity
- space complexity below is constant, time complexity is O(m + n) + O(m logm) + O(n logn)
- we go back from a and forward from b
- swap while currentPointerA > currentPointerB and both do not exceed bounds
- intuition? - 
  - a -> -------- x-1, x ----------
  - b -> -------- y, y+1 ----------
- first we compare `a[x]` and `b[y]`. if `a[x] > b[y]`, swap. assume this is not the case i.e. `a[x] < b[y]`
- when we compare `a[x-1]` and `b[y+1]`, no way `a[x-1] > b[y+1]` because `a[x] < b[y]`

```java
import java.util.Arrays;

public class Solution {
    public static void mergeTwoSortedArraysWithoutExtraSpace(long []a, long []b) {
        
        int pointerA = a.length - 1;
        int pointerB = 0;
        
        while (pointerA > -1 && pointerB < b.length && a[pointerA] > b[pointerB]) {
            swap(a, pointerA, b, pointerB);
            pointerA -= 1;
            pointerB += 1;
        }

        Arrays.sort(a);
        Arrays.sort(b);
    }

    private static void swap(long[] a, int x, long[] b, int y) {
        long temp = a[x];
        a[x] = b[y];
        b[y] = temp;
    }
}
```
