# Ninja’s Training

- https://www.codingninjas.com/studio/problems/ninja%E2%80%99s-training_3621003
- explain why greedy would not work - if we pick 50 first day, we cannot pick 100 on second day
  ```
  1 50 1
  1 100 1
  ```
- recursive call - (index, lastTaskPerformed)
- note - lastTaskPerformed can be 0 1 or 2. for starting off the recursion, we use 3

```java
public class Solution {

    public static int ninjaTraining(int n, int points[][]) {
        return ninjaTraining(n - 1, 3, points);
    }

    private static int ninjaTraining(int idx, int lastTaskPerformed, int points[][]) {

        if (idx == -1) return 0;

        int max = 0;

        for (int i = 0; i < 3; i++) {
            if (lastTaskPerformed != i) {
                max = Math.max(max, points[idx][i] + ninjaTraining(idx - 1, i, points));
            }
        }

        return max;
    }
}
```

- in interview, indicate the repeating tasks above
  ```
                   3,3
    
     2,0           2,1           2,2
  
  1,1   1,2     1,0   1,2     1,0   1,1
  ```
- tabular + space optimized solution - 

```java
import java.util.Arrays;

public class Solution {

    public static int ninjaTraining(int n, int points[][]) {

        int[] prevMax = Arrays.copyOfRange(points[0], 0, 3);

        for (int i = 1; i < n; i++) {

            int[] earlierPrevMax = Arrays.copyOfRange(prevMax, 0, 3);

            prevMax[0] = Math.max(earlierPrevMax[1], earlierPrevMax[2]) + points[i][0];
            prevMax[1] = Math.max(earlierPrevMax[0], earlierPrevMax[2]) + points[i][1];
            prevMax[2] = Math.max(earlierPrevMax[0], earlierPrevMax[1]) + points[i][2];

            // System.out.println(Arrays.toString(prevMax));
        }

        return Math.max(prevMax[0], Math.max(prevMax[1], prevMax[2]));
    }
}

```
