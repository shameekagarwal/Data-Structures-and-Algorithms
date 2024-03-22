# Distribute Elements Into Two Arrays I

- https://leetcode.com/problems/distribute-elements-into-two-arrays-i/
- just brute forced my way

```java
class Solution {

    public int[] resultArray(int[] nums) {
        
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        
        int n = nums.length;
        
        a.add(nums[0]);
        b.add(nums[1]);
        
        for (int i = 2; i < n; i++) {
            if (getLast(a) > getLast(b)) {
                a.add(nums[i]);
            } else {
                b.add(nums[i]);
            }
        }
        
        int ptr = 0;
        int[] result = new int[n];
        
        for (int i : a) {
            result[ptr] = i;
            ptr += 1;
        }
        
        for (int i : b) {
            result[ptr] = i;
            ptr += 1;
        }
        
        return result;
    }
    
    private int getLast(List<Integer> list) {
        return list.get(list.size() - 1);
    }
}
```
