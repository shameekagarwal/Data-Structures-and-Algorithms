# Aggressive Cows

- https://www.codingninjas.com/studio/problems/aggressive-cows_1082559
- this is a variation of bs on answers - min of max / max of min
- bs on answers can either be (min of max / max of min) or just be (min / max)
- first, sort the array
- min distance - min difference of consecutive elements
- max distance - `arr[n - 1] - arr[0]`
- perform binary search between the two values

```java
import java.util.Arrays;

public class Solution {
    
    public static int aggressiveCows(int []stallsRaw, int k) {
        
        int []stalls = Arrays.copyOfRange(stallsRaw, 0, stallsRaw.length);
        Arrays.sort(stalls);

        int minPossibleDistance = Integer.MAX_VALUE;
        for (int i = 0; i < stalls.length - 1; i++) {
            minPossibleDistance = Math.min(minPossibleDistance, stalls[i + 1] - stalls[i]);
        }
        int maxPossibleDistance = stalls[stalls.length - 1] - stalls[0];

        int minFeasibleDistance = minPossibleDistance;

        while (minPossibleDistance <= maxPossibleDistance) {
            
            int currentDistance = minPossibleDistance + ((maxPossibleDistance - minPossibleDistance) / 2);
            
            int lastCowPosition = stalls[0];
            int cowsPlaced = 1;
            for (int i = 1; i < stalls.length; i++) {
                if (stalls[i] - lastCowPosition >= currentDistance) {
                    cowsPlaced += 1;
                    lastCowPosition = stalls[i];
                }
            }

            // System.out.printf("for %d, can place %d cows\n", currentDistance, cowsPlaced);

            if (cowsPlaced >= k) {
                minFeasibleDistance = currentDistance;
                minPossibleDistance = currentDistance + 1;
            } else {
                maxPossibleDistance = currentDistance - 1;
            }
        }

        return minFeasibleDistance;
    }
}
```
