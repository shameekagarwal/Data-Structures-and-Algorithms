# Row with max 1s

- https://www.codingninjas.com/studio/problems/row-of-a-matrix-with-maximum-ones_982768
- lower bound search on every row for position of one

```java
import java.util.ArrayList;

public class Solution {

    public static int maximumOnesRow(ArrayList<ArrayList<Integer>> matrix, int n, int m) {

        int rowWithEarliestPositionOfOne = -1;
        int earliestPositionOfOne = matrix.get(0).size();

        for (int i = 0; i < matrix.size(); i++) {
            int positionOfOne = findLowerBound(matrix.get(i), 1);
            if (positionOfOne < earliestPositionOfOne) {
                earliestPositionOfOne = positionOfOne;
                rowWithEarliestPositionOfOne = i;
            }
        }

        return rowWithEarliestPositionOfOne;
    }

    private static int findLowerBound(ArrayList<Integer> list, int element) {

        int low = 0;
        int high = list.size() - 1;
        int position = list.size();

        while (low <= high) {

            int mid = (low + high) / 2;

            if (list.get(mid) == element) {
                position = mid;
                high = mid - 1;
            } else if (list.get(mid) > element) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return position;
    }
}
```
