# Find the Number of Subarrays Where Boundary Elements Are Maximum

- https://leetcode.com/problems/find-the-number-of-subarrays-where-boundary-elements-are-maximum/
- assume we want all boundary subarrays ending at i
- our start can be anything from 0 to i - i inclusive, because one element is also a subarray
- two conditions are required for this start - 
  - `nums[start]` = `nums[i]`
  - everything between start and i should be <= `nums[i]`
- so, first we calculate [previous greater element](../../Strivers%20A2Z%20DSA%20Course%20Sheet/Step%209:%20Stack%20and%20Queues/Step%209.3:%20Monotonic%20Stack%20Queue%20Problems%20/Next%20Greater%20Element.md)
- then, at each `map[element]`, we store all the indices having `map[element]`
- note - this array will always be sorted
- now, for each i - we want to perform binary search
  - find end such that - `map[nums[i]][end] = i`
  - find smallest start such that - `map[nums[i]][start] > pge[i]`
- now, we can pick all subarrays (start to end, end)

```java
class Solution {

    public long numberOfSubarrays(int[] nums) {
        
        int n = nums.length;
        
        Map<Integer, List<Integer>> lookup = new HashMap<>();
        int[] pge = getPreviousGreaterElements(nums);
        
        long result = 0;
        
        for (int i = 0; i < n; i++) {

            if (!lookup.containsKey(nums[i])) {
                lookup.put(nums[i], new ArrayList<>());
            }

            lookup.get(nums[i]).add(i);
            
            int l = 0;
            int r = lookup.get(nums[i]).size() - 1;
            int start = i;
            
            while (l <= r) {
                
                int m = (l + r) / 2;
                
                if (lookup.get(nums[i]).get(m) > pge[i]) {
                    start = m;
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            
            int end = lookup.get(nums[i]).size() - 1;

            result += (end - start + 1);
        }
        
        return result;
    }

    private int[] getPreviousGreaterElements(int[] nums) {

        int n = nums.length;
        
        Deque<Integer> stack = new ArrayDeque<>();
        int[] pge = new int[n];
        
        for (int i = 0; i < n; i++) {
            
            while (!stack.isEmpty() && nums[stack.peekLast()] <= nums[i]) {
                stack.removeLast();
            }
            
            pge[i] = stack.isEmpty() ? -1 : stack.peekLast();
            
            stack.addLast(i);
        }

        return pge;
    }
}
```
