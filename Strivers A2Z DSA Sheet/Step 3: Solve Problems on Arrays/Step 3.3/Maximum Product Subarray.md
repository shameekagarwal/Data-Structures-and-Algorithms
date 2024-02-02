# Maximum Product Subarray

- brute - O(n^2)
- optimal - the current solution is intuition based. there is another kadane's algorithm based solution, but it is not intuitive, so intuitive one is discussed here
- case 1 - if array contains all positive numbers, result = product of all numbers
- case 2 - if array contains even number of negative numbers, result = product of all numbers
- case 3 - if array contains odd number of positive numbers, result = 
  - consider that pivot is at index x, where `a[x] < 0`
  - answer will either be product of array up to before x or from after x
  - so ultimately, it will either be `product[0...x-1]` or `product[x+1...n-1]`
  - x here is for all negative integers
- if we just calculate max(all prefix products, all suffix products), it covers all 3 cases except if array contains a 0 are covered
- basically understand that if the array had no 0s, only positive or negative integers, the subarray with maximum product would only be either be some prefix or some suffix
- case 4 - if array has a 0 - we need to reset the product to 1 and start again from 0
- basically, the crux of the solution is, if we only calculate prefix and suffix products, and we reset to 1 every time we encounter a 0, we cover all cases

```java
class Solution {
    public int maxProduct(int[] nums) {
        int result = 0;
        int prefix = 1;
        for (int i = 0; i < nums.length; i++) {
            if (prefix == 0) prefix = 1;
            prefix *= nums[i];
            result = Math.max(prefix, result);
        }
        int suffix = 1;
        for (int i = nums.length - 1; i > -1; i--) {
            if (suffix == 0) suffix = 1;
            suffix *= nums[i];
            result = Math.max(suffix, result);
        }
        return result;
    }
}
```
- now, if we look above, the entire thing can actually be solved inside one loop

```java
class Solution {
    public int maxProduct(int[] nums) {
        int result = Integer.MIN_VALUE;
        int prefix = 1;
        int suffix = 1;
        for (int i = 0; i < nums.length; i++) {
            if (prefix == 0) prefix = 1;
            if (suffix == 0) suffix = 1;
            prefix *= nums[i];
            suffix *= nums[nums.length - 1 - i];
            result = Math.max(prefix, Math.max(suffix, result));
        }
        return result;
    }
}
```
