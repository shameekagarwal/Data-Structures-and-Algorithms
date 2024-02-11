# Number of Inversions

- https://www.codingninjas.com/studio/problems/number-of-inversions_6840276
- brute force - O(n^2)
- heart of the problem is in `countInversions`
- if we have two sorted arrays, and we want to count number of inversions such that left element comes from the left array, and the right inversions from the right
- we use a pointer for both arrays
  - do `r++` till `rightArray[r] < leftArray[l]`
  - now, number of inversions with l at left = r
- corner case - when the while loop terminates, either l or r reaches full length. but if elements are still left for l, we simply add it - we could have also changed the loop's `&&` to `||` and then for all elements on the left, r would have been added
- i think this solution can be reused in multiple places - if we can solve for smaller chunks and then solve for the bigger chunk
- create array copy to avoid mutating inputs

```java
import java.util.Arrays;

public class Solution {
    
    public static int numberOfInversions(int []aRaw, int n) {
        int[] a = Arrays.copyOfRange(aRaw, 0, n);
        return numberOfInversions(a, 0, n - 1);
    }

    private static int numberOfInversions(int[] a, int l, int r) {

        if (l == r) return 0;
                
        int leftStart = getLeftStart(l, r);
        int leftEnd = getLeftEnd(l, r);
        int rightStart = getRightStart(l, r);
        int rightEnd = getRightEnd(l, r);

        int leftInversions = numberOfInversions(a, leftStart, leftEnd);
        int rightInversions = numberOfInversions(a, rightStart, rightEnd);
        int mergedInversions = countInversions(a, leftStart, leftEnd, rightStart, rightEnd);
        merge(a, leftStart, leftEnd, rightStart, rightEnd);

        return leftInversions + rightInversions + mergedInversions;
    }

    private static int countInversions(int[] a, int leftStart, int leftEnd, int rightStart, int rightEnd) {
        
        int leftPointer = leftStart;
        int rightPointer = rightStart;

        int inversions = 0;

        while (leftPointer <= leftEnd && rightPointer <= rightEnd) {
            while (rightPointer <= rightEnd && a[rightPointer] < a[leftPointer]) {
                rightPointer += 1;
            }
            inversions += (rightPointer - rightStart);
            leftPointer += 1;
        }

        inversions += (rightPointer - rightStart) * (leftEnd - leftPointer + 1);

        // System.out.printf("inversions for [%d, %d, %d, %d] = %d\n", leftStart, leftEnd, rightStart, rightEnd, inversions);

        return inversions;
    }

    private static void merge(int[] a, int leftStart, int leftEnd, int rightStart, int rightEnd) {

        int merged[] = new int[rightEnd - leftStart + 1];
        int leftPointer = leftStart;
        int rightPointer = rightStart;
        int mergedPointer = 0;

        while (leftPointer <= leftEnd && rightPointer <= rightEnd) {
            if (a[leftPointer] < a[rightPointer]) {
                merged[mergedPointer] = a[leftPointer];
                mergedPointer += 1;
                leftPointer += 1;
            } else {
                merged[mergedPointer] = a[rightPointer];
                mergedPointer += 1;
                rightPointer += 1;
            }
        }

        while (leftPointer <= leftEnd) {
            merged[mergedPointer] = a[leftPointer];
            mergedPointer += 1;
            leftPointer += 1;
        }

        while (rightPointer <= rightEnd) {
            merged[mergedPointer] = a[rightPointer];
            mergedPointer += 1;
            rightPointer += 1;
        }

        for (int i = 0; i < mergedPointer; i++) {
            a[i + leftStart] = merged[i];
        }
    }

    private static int getLeftStart(int l, int r) {
        return l;
    }

    private static int getLeftEnd(int l, int r) {
        return (l + r) / 2;
    }

    private static int getRightStart(int l, int r) {
        return getLeftEnd(l, r) + 1;
    }

    private static int getRightEnd(int l, int r) {
        return r;
    }
}
```
