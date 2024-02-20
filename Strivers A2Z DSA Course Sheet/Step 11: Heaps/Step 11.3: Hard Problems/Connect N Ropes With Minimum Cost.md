# Connect N Ropes With Minimum Cost

- https://www.codingninjas.com/studio/problems/connect-n-ropes-with-minimum-cost_625783
- if i try to dry run different permutations of combining 4 ropes - 
  ```
  a + b
  a + b + c
  a + b + c + d
  3a + 3b + 2c + d
  
  a + b
  c + d
  a + b + c + d
  2a + 2b + 2c + 2d
  
  a + c
  a + c + b
  a + c + b + d
  3a + 3c + 2b + d
  ```
- the ropes picker earlier are picked more - so, we would like to pick smaller ropes earlier and bigger ropes later
- so we use greedy approach
- remove 2 ropes from pq and keep adding the combined rope to the pq back again, till pq size becomes 1
- return the final cost

```java
import java.util.Scanner;
import java.util.PriorityQueue;

public class solution {

    static int minCost(int arr[], int n) {

        if (arr.length == 0) return 0;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i : arr) {
            minHeap.add(i);
        }

        int result = 0;
        while (minHeap.size() > 1) {
            int ropeA = minHeap.remove();
            int ropeB = minHeap.remove();
            result += ropeA + ropeB;
            minHeap.add(ropeA + ropeB);
        }

        return result;
    }
}
```
