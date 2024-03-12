# Minimize Max Distance to Gas Station

- https://www.codingninjas.com/studio/problems/minimise-max-distance_7541449
- brute force - look at [Solution 1](#solution-1) below, but use N^2 - iterate through the list of N elements every time to find the minimum and update it. complexity - O(N * K)

## Solution 1

- using priority queues
- always will give us the current max distance
- i also store the number of gas stations, as it helps determine the new distance - 
- e.g. arr = `[1, 10, 11]` and k = 2
- after first station, gaps are - 4.5, 4.5, 1
- after second station, gaps are - 3, 3, 3, 1
- how did we get 3? - 4.5 * 2 / (2 + 1)
- time complexity - O(n * logn) (first loop) + O(K * logn) (second loop)
- space complexity - pq of size n
- note - remember the custom `Comparable` below

```java
import java.util.PriorityQueue;

public class Solution {

    public static double MinimiseMaxDistance(int []arr, int K) {
        
        PriorityQueue<Segment> pq = new PriorityQueue<>();
        for (int i = 0; i < arr.length - 1; i++) {
            pq.add(new Segment((double) arr[i + 1] - arr[i], 1));
        }

        for (int i = 0; i < K; i++) {
            Segment segment = pq.poll();
            segment.currentGap = (segment.currentGap * segment.numberOfGasStationsPlaced) / (segment.numberOfGasStationsPlaced + 1.0);
            segment.numberOfGasStationsPlaced += 1;
            pq.add(segment);
        }

        return pq.poll().currentGap;
    }
}

class Segment implements Comparable<Segment> {

    double currentGap;
    int numberOfGasStationsPlaced;

    public Segment(double currentGap, int numberOfGasStationsPlaced) {
        this.currentGap = currentGap;
        this.numberOfGasStationsPlaced = numberOfGasStationsPlaced;
    }

    @Override
    public int compareTo(Segment segment) {
        if (currentGap > segment.currentGap) {
            return -1;
        } else if (currentGap < segment.currentGap) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "[" + currentGap + ":" + numberOfGasStationsPlaced + "]";
    }
}
```

## Solution 2

- uses binary search
- note this time - we do not use mid + 1 / mid - 1 to update bounds - we use mid itself
- note this time - while loop does not check low <= high - use high - low >= 10^-6
- low - 0 - have to place gas stations, cannot say what the min would end up being
- high - max of current gaps - the largest gap stays as is and no stations are between it
- time complexity - O(N + (log(high * 10^6) * N)) - to confirm

```java
public class Solution {
    
    public static double MinimiseMaxDistance(int []arr, int K) {

        double low = 0;
        
        double high = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            high = Math.max(high, arr[i + 1] - arr[i]);
        }

        double ans = high;

        while (high - low > Math.pow(10, -7)) {

            double currentGap = low + ((high - low) / 2);

            int numberOfStationsToAdd = 0;
            for (int i = 0; i < arr.length - 1; i++) {
                numberOfStationsToAdd += Math.ceil((arr[i + 1] - arr[i]) / currentGap) - 1;
            }

            if (numberOfStationsToAdd <= K) {
                ans = currentGap;
                high = currentGap;
            } else {
                low = currentGap;
            }
        }

        return ans;
    }
}
```
