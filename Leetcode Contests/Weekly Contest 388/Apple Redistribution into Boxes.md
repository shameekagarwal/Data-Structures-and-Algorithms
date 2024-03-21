# Apple Redistribution into Boxes

- https://leetcode.com/problems/apple-redistribution-into-boxes/
- greedy - calculate total no of apples, then start filling from biggest to smallest box
- important - apples from the same pack can be distributed into different boxes

```java
class Solution {
    
    public int minimumBoxes(int[] apples, int[] capacity) {
        
        Arrays.sort(capacity);
        
        int totalApples = 0;
        for (int apple : apples) {
            totalApples += apple;
        }
        
        int totalBoxesCapacity = 0;
        int totalBoxes = 0;
        for (int i = capacity.length - 1; i > -1; i--) {
            totalBoxesCapacity += capacity[i];
            totalBoxes += 1;
            if (totalBoxesCapacity >= totalApples) break;
        }
        
        return totalBoxes;
    }
}
```
