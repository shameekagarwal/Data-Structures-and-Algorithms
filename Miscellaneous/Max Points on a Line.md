# Max Points on a Line

- https://leetcode.com/problems/max-points-on-a-line/
- we run two for loops
- for every i - generate all the lines it can be a part of
- for every i, we maintain a slope frequency, which we increment if the same slope is found
- finally, we update the max frequency
- note - we do +1 for the current point

```java
class Solution {

    public int maxPoints(int[][] points) {

        if (points.length == 1) return 1;

        int maxValue = 1;

        for (int i = 0; i < points.length; i++) {

            Map<Double, Integer> slopeLookup = new HashMap<>();

            for (int j = 0; j < points.length; j++) {

                if (i == j) continue;

                double numerator = points[j][1] - points[i][1];
                double denominator = points[j][0] - points[i][0];

                if (numerator == 0) {
                    slopeLookup.put(0.0, slopeLookup.getOrDefault(0.0, 0) + 1);
                } else if (denominator == 0) {
                    slopeLookup.put(Double.MAX_VALUE, slopeLookup.getOrDefault(Double.MAX_VALUE, 0) + 1);
                } else {
                    double slope = numerator / denominator;
                    slopeLookup.put(slope, slopeLookup.getOrDefault(slope, 0) + 1);
                }
            }

            maxValue = Math.max(maxValue, Collections.max(slopeLookup.values()) + 1);
        }

        return maxValue;
    }
}
```
