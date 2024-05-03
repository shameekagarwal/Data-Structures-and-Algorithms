# Minimum Operations

- https://www.codingninjas.com/studio/problems/minimum-operations_8360665
- simple bfs (with level number) 
- i think we can only visit at most 1000 unique numbers
- after that visited will start becoming true for new numbers, and we would not run the algorithm for them

```java
import java.util.*;

public class Solution {

    private static final int MOD = 1000;

    public static int minimumOperations(int n, int start, int end, int []a) {

        boolean[] seen = new boolean[MOD + 1];
        seen[start] = true;

        Deque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{start, 0});

        while (!queue.isEmpty()) {

            int[] node = queue.removeFirst();

            if (node[0] == end) {
                return node[1];
            }

            for (int i : a) {

                int product = (node[0] * i) % MOD;

                if (!seen[product]) {
                    seen[product] = true;
                    queue.addLast(new int[]{product, node[1] + 1});
                }
            }
        }

        return -1;
    }
}
```
