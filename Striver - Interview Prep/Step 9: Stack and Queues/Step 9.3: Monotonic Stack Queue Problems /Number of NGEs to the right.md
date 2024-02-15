# Number of NGEs to the right

- https://www.codingninjas.com/studio/problems/count-of-greater-elements-to-the-right_8365436
- nothing to do with stack - think of it like [](/Striver%20-%20Interview%20Prep/Step%203:%20Solve%20Problems%20on%20Arrays/Step%203.3:%20Hard/Number%20of%20Inversions.md), but here we do not need just the total count, but we want it separately for each index
- while both left and right halves have elements
- every time an element gets from picked from the left
- number of elements on the right half are all the elements that are greater

```java
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Solution {

    static class Pair {

        int val;
        int idx;

        Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "(" + val + ", " + idx + ")";
        }
    }

    public static int[] countGreater(int []arr, int []query) {
        
        int[] ngeCount = new int[arr.length];
        Pair[] pairs = constructPairs(arr);
        mergeSort(pairs, 0, arr.length - 1, ngeCount);
        int[] result = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            result[i] = ngeCount[query[i]];
        }
        return result;
    }

    private static Pair[] constructPairs(int []arr) {
        Pair[] pairs = new Pair[arr.length];
        for (int i = 0; i < arr.length; i++) {
            pairs[i] = new Pair(arr[i], i);
        }
        return pairs;
    }

    private static void mergeSort(Pair[] pairs, int l, int r, int[] ngeCount) {

        if (l == r) return;

        int leftStart = l;
        int leftEnd = (l + r) / 2;
        int rightStart = leftEnd + 1;
        int rightEnd = r;

        mergeSort(pairs, leftStart, leftEnd, ngeCount);
        mergeSort(pairs, rightStart, rightEnd, ngeCount);
        merge(pairs, leftStart, leftEnd, rightStart, rightEnd, ngeCount);
    }

    private static void merge(Pair[] pairs, int leftStart, int leftEnd, int rightStart, int rightEnd, int[] ngeCount) {

        int leftPtr = leftStart;
        int rightPtr = rightStart;
        Pair[] merged = new Pair[rightEnd - leftStart + 1];
        int mergedPtr = 0;

        while (leftPtr <= leftEnd && rightPtr <= rightEnd) {
            if (pairs[leftPtr].val < pairs[rightPtr].val) {
                ngeCount[pairs[leftPtr].idx] += (rightEnd - rightPtr + 1);
                merged[mergedPtr] = pairs[leftPtr];
                leftPtr += 1;
                mergedPtr += 1;
            } else {
                merged[mergedPtr] = pairs[rightPtr];
                rightPtr += 1;
                mergedPtr += 1;
            }
        }

        while (leftPtr <= leftEnd) {
            merged[mergedPtr] = pairs[leftPtr];
            leftPtr += 1;
            mergedPtr += 1;
        }
        
        while (rightPtr <= rightEnd) {
            merged[mergedPtr] = pairs[rightPtr];
            rightPtr += 1;
            mergedPtr += 1;
        }

        for (int i = 0; i < mergedPtr; i++) {
            pairs[i + leftStart] = merged[i];
        }
    }
}
```
