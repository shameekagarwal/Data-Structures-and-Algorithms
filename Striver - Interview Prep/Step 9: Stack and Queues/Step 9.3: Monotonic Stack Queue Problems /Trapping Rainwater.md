# Trapping Rainwater

- https://leetcode.com/problems/trapping-rain-water/
- logic - amount of rainwater we can trap at i = `min(max(0...i), max(i...n - 1)) - arr[i]`
- now all three solutions are an implementation of this

## Brute

- time complexity - o(n^2)

```java
class Solution {
    
    public int trap(int[] height) {
        
        int result = 0;
        
        for (int i = 0; i < height.length; i++) {
            
            int leftMax = height[i];
            for (int j = 0; j < i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }

            int rightMax = height[i];
            for (int j = i + 1; j < height.length; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }

            result += Math.min(leftMax, rightMax) - height[i];
        }

        return result;
    }
}
```

## Optimized

- construct two separate arrays - prefix max and suffix max
- time complexity - 3 * O(n) - for prefix, suffix and finally calculation
- space complexity - O(2 * n) - for the two extra helper arrays

```java
class Solution {
    
    public int trap(int[] height) {

        int[] prefixMx = getPrefixMx(height);
        int[] suffixMx = getSuffixMx(height);
        
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            result += Math.min(prefixMx[i], suffixMx[i]) - height[i];
        }

        return result;
    }

    private int[] getPrefixMx(int[] arr) {
        int[] prefixMx = new int[arr.length];
        prefixMx[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            prefixMx[i] = Math.max(prefixMx[i - 1], arr[i]);
        }
        return prefixMx;
    }

    private int[] getSuffixMx(int[] arr) {
        int[] suffixMx = new int[arr.length];
        suffixMx[arr.length - 1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i > -1; i--) {
            suffixMx[i] = Math.max(suffixMx[i + 1], arr[i]);
        }
        return suffixMx;
    }
}
```

## Most Optimal

- maintain the right max prefix and max suffix on the fly
- let us say that till now, the left max is x and the right max is y
- we can be sure that if x < y, at least x - current_element can be filled!
- because basically, we wanted min of prefix max and suffix max (min of x and y)
- and as long as "we know" that y is larger, even if we do not know the exact value of y, we can say that x - current element is what gets used for calculation
- time complexity - 

```java
class Solution {

    public int trap(int[] height) {

        int leftPtr = 0; int leftMx = 0;
        int rightPtr = height.length - 1; int rightMx = 0;
        int result = 0;

        while (leftPtr <= rightPtr) {
            if (leftMx <= rightMx) {
                leftMx = Math.max(leftMx, height[leftPtr]);
                result += leftMx - height[leftPtr];
                leftPtr += 1;
            } else {
                rightMx = Math.max(rightMx, height[rightPtr]);
                result += rightMx - height[rightPtr];
                rightPtr -= 1;
            }
        }

        return result;
    }
}
```
