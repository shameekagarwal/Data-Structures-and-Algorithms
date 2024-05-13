# Find the Integer Added to Array I

- https://leetcode.com/problems/find-the-integer-added-to-array-i/
- question already says answer exists 

```java
class Solution {

  public int addedInteger(int[] nums1, int[] nums2) {
      
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    
    return nums2[0] - nums1[0];
  }
}
```
