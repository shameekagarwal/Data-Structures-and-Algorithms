# Find the Integer Added to Array II

- https://leetcode.com/problems/find-the-integer-added-to-array-ii/
- question states answer exists
- we can skip two elements in nums1
- so, the result would have to be one of the three below - 
  - `nums2[0] - nums1[0]`
  - `nums2[0] - nums1[1]`
  - `nums2[0] - nums1[2]`
- for each of the possibilities, we see if answer is possible - 
  - iterate using two different pointers for both arrays
  - if not matched, move only nums1 pointer forward, not nums2 pointer
  - finally, return true if nums2 pointer reaches the end
- finally, minimize result using the three possibilities "as long as they are valid" i.e. nums2 pointer reaches end for that possibility

```java
class Solution {

    public int minimumAddedInteger(int[] nums1, int[] nums2) {
        
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        int diffA = nums2[0] - nums1[0];
        int diffB = nums2[0] - nums1[1];
        int diffC = nums2[0] - nums1[2];
        
        boolean isPossibleA = isPossible(nums1, nums2, diffA);
        boolean isPossibleB = isPossible(nums1, nums2, diffB);
        boolean isPossibleC = isPossible(nums1, nums2, diffC);
        
        int result = Integer.MAX_VALUE;
        
        if (isPossibleA) {
            result = Math.min(result, diffA);
        }
        
        if (isPossibleB) {
            result = Math.min(result, diffB);
        }
        
        if (isPossibleC) {
            result = Math.min(result, diffC);
        }
        
        return result;
    }
    
    private boolean isPossible(int[] nums1, int[] nums2, int diff) {
        
        int j = 0;
        
        int n = nums1.length;
        
        for (int i = 0; i < n && j < n - 2; i++) {

            if (nums2[j] - nums1[i] == diff) {
                j += 1;
            }
        }
        
        return j == n - 2;
    }
}
```
