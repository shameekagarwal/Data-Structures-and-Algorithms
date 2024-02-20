# Count Frequency in a range

- https://www.codingninjas.com/studio/problems/count-frequency-in-a-range_8365446

```java
public class Solution {
    public static int[] countFrequency(int n, int x, int []nums){
        int[] map = new int[n];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= n) {
                map[nums[i] - 1] += 1;
            }
        }
        return map;
    }
}
```
