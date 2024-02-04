# Painter's partition

- https://www.codingninjas.com/studio/problems/painter-s-partition-problem_1089557

```java
import java.util.ArrayList;

public class Solution {

    public static int findLargestMinDistance(ArrayList<Integer> boards, int k) {

        if (k > boards.size()) {
            return -1;
        }
        
        long minPossibleTime = Integer.MIN_VALUE;
        for (int board : boards) {
            minPossibleTime = Math.max(minPossibleTime, board);
        }

        long maxPossibleTime = 0;
        for (int board : boards) {
            maxPossibleTime += board;
        }

        long result = maxPossibleTime;
        while (minPossibleTime <= maxPossibleTime) {
            
            long possibleResult = minPossibleTime + ((maxPossibleTime - minPossibleTime) / 2);

            int paintersRequired = 0;
            long currentPainterBoards = 0;
            for (int board : boards) {
                if (currentPainterBoards + board > possibleResult) {
                    paintersRequired += 1;
                    currentPainterBoards = 0;
                }
                currentPainterBoards += board;
            }
            paintersRequired += 1;

            if (paintersRequired <= k) {
                result = possibleResult;
                maxPossibleTime = possibleResult - 1;
            } else {
                minPossibleTime = possibleResult + 1;
            }
        }

        return (int) result;
    }
}
```
