# Minimum Operations

- https://www.codingninjas.com/studio/problems/minimum-operations_8360665
- simple bfs (with level number) 

```java
import java.util.*;

public class Solution {

    private static final int MOD = 1000;

    public static int minimumOperations(int n, int start, int end, int[] multipliers) {

        int numberOfOperations = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        
        queue.addLast(start);
        Set<Integer> visited = new HashSet<>();
        if (start == end) return 0;
        
        while (!queue.isEmpty()) {

            int currentLevelSize = queue.size();

            numberOfOperations += 1;

            for (int i = 0; i < currentLevelSize; i++) {

                int number = queue.removeFirst();

                for (int multiplier : multipliers) {

                    int result = (number * multiplier) % MOD;

                    if (visited.contains(result)) continue;
                    if (result == end) return numberOfOperations;

                    visited.add(result);
                    queue.addLast(result);
                }
            }
        }

        return -1;
    }
}
```
