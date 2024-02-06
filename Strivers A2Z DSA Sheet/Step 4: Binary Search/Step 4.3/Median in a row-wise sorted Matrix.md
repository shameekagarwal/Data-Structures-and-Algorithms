# Median in a row-wise sorted Matrix

- https://www.codingninjas.com/studio/problems/median-of-a-row-wise-sorted-matrix_1115473
- brute force - O(mn) (for iterating through matrix) + O(mn * logmn) (for sorting) + O(mn) (for iterating through sorted flattened array)
- challenge - can have duplicate elements
- imagine we have an array like this - `[..... 9 9 9 9 9 .....]`
- the median is 9 - but it is the 9 such that there are 9s on the left half, and 9s on the right half
- solution - we just calculate no of elements <= the number we are looking for
- e.g. if median says that there should be 5 elements should be on the left
- whenever we find a number that has "at least" 5 elements smaller than itself (it can have more elements), we consider it as an answer,but we change high to mid - 1 in hopes of finding better answers
- so, for every row, we will be calculating upper bound
- min = min of all elements in 1st column
- max = max of all elements in last column
- note - since we wanted to find number of elements smaller than or equal to the current element - we use upper bound there as well

```java
public final class Solution {
    
    public static int findMedian(int matrix[][], int m, int n) {
        
        int min = findMin(matrix);
        int max = findMax(matrix);
        int median = max;
        int nosSmallerThanMedian = (m * n) / 2;

        while (min <= max) {
            
            int mid = min + ((max - min) / 2);
            int countOfSmallerNos = 0;
            
            for (int i = 0; i < matrix.length; i++) {
                countOfSmallerNos += upperBound(matrix[i], mid);
            }
            
            if (countOfSmallerNos > nosSmallerThanMedian) {
                max = mid - 1;
                median = mid;
            } else {
                min = mid + 1;
            }
        }

        return median;
    }

    private static int upperBound(int[] arr, int val) {

        int low = 0;
        int high = arr.length - 1;
        int upperBound = arr.length;

        while (low <= high) {

            int mid = low + ((high - low) / 2);

            if (arr[mid] > val) {
                upperBound = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return upperBound;
    }

    private static int findMin(int[][] mat) {

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < mat.length; i++) {
            min = Math.min(min, mat[i][0]);
        }

        return min;
    }

    private static int findMax(int[][] mat) {

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < mat.length; i++) {
            max = Math.max(max, mat[i][mat[0].length - 1]);
        }

        return max;
    }
}
```
