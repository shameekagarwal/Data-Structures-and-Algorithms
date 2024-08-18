# Maximum Product Subarray

- https://leetcode.com/problems/maximum-product-subarray/
- was getting numeric overflow when using [this](../Strivers%20A2Z%20DSA%20Course%20Sheet/Step%203:%20Solve%20Problems%20on%20Arrays/Step%203.3:%20Hard/Maximum%20Product%20Subarray.md)
- fix - no point resetting when running product is positive, it is a viable candidate
- however if running product becomes negative
  - if we are doing prefix and there are no negative numbers after i, reset product to 1
  - if we are doing suffix and there are no negative numbers before i, reset product to 1

```java
class Solution {

    public int maxProduct(int[] nums) {

        int maxProduct = Integer.MIN_VALUE;
        int runningProduct = 1;

        int firstNegative = -1;
        int lastNegative = nums.length;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] < 0) {
                firstNegative = i;
                break;
            }
        }


        for (int i = nums.length - 1; i > -1; i--) {

            if (nums[i] < 0) {
                lastNegative = i;
                break;
            }
        }

        for (int i = 0; i < nums.length; i++) {

            runningProduct *= nums[i];
            maxProduct = Math.max(maxProduct, runningProduct);

            if (runningProduct == 0) {
                runningProduct = 1;
            } else if (runningProduct < 0 && lastNegative <= i) {
                runningProduct = 1;
            }
        }

        runningProduct = 1;

        for (int i = nums.length - 1; i > -1; i--) {

            runningProduct *= nums[i];
            maxProduct = Math.max(maxProduct, runningProduct);

            if (runningProduct == 0) {
                runningProduct = 1;
            } else if (runningProduct < 0 && firstNegative >= i) {
                runningProduct = 1;
            }
        }

        return maxProduct;
    }
}
```
