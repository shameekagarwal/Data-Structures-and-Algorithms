# House Robber

- https://leetcode.com/problems/house-robber/
- try all subsequences - pick or not pick the element
- first, make the recursive call - 

```java
class Solution {

    private int n;
    private int[] nums;

    public int rob(int[] nums) {
        
        this.nums = nums;
        n = nums.length;
        
        return Math.max(rob(n - 2), nums[n - 1] + rob(n - 3));
    }

    private int rob(int idx) {

        if (idx < 0) return 0;

        return Math.max(rob(idx - 1),  nums[idx] + rob(idx - 2));
    }
}
```

- idea is max at current index - max((max up to index - 2 + value at index), (max up to index - 1))
- now, converting it into equivalent of tabular format with optimized space
- note ,y mistake - i took previous as `nums[1]`. it would be `Math.max(nums[1], nums[0])`

```java
class Solution {

    private int n;
    private int[] nums;

    public int rob(int[] nums) {

        if (nums.length == 1) return nums[0];

        int beforePrevious = nums[0];
        int previous = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            int earlierPrevious = previous;
            previous = Math.max(previous, nums[i] + beforePrevious);
            beforePrevious = earlierPrevious;
        }
        
        return previous;
    }
}

```
