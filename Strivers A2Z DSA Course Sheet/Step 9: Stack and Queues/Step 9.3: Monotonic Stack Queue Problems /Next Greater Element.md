# Next Greater Element

- https://leetcode.com/problems/next-greater-element-i/
- naive - 
  - for all elements i in nums2
  - go from i + 1 to nums2.length - 1 looking for the next greatest element
  - put it in a map
  - then construct the result from this map
- question here is
  - calculate next greater element for all elements in nums2
  - then, construct result such that `result[i] = nge[nums1[i]]`
- for every element, to calculate next greater element - traverse from back using a stack
- intuition - if `a[x] > a[y]` and x < y, for any element from 0 to x-1, `a[y]` can never be an answer, because `a[x]` is a better candidate
- we just store the result for every element in a map
- this way, we can easily construct the result by iterating through nums1

```java
class Solution {
    
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        
        Map<Integer, Integer> nge = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int i = nums2.length - 1; i > -1; i--) {
            while (!stack.isEmpty() && stack.peekLast() < nums2[i]) {
                stack.removeLast();
            }
            nge.put(nums2[i], stack.isEmpty() ? -1 : stack.peekLast());
            stack.addLast(nums2[i]);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nge.get(nums1[i]);
        }
        return result;
    }
}
```
