# Maximize Happiness of Selected Children

- https://leetcode.com/problems/maximize-happiness-of-selected-children/
- try taking the bigger elements first
- e.g. we have 1 1 1 1 20
- if i took 20 at end, it would actually be 16
- if i take it at beginning, the 1s would not reduce below 0 - after 1st day, all would stay at 0
- so, to take advantage of this, take bigger elements first

```java
class Solution {

    public long maximumHappinessSum(int[] happiness, int k) {
        
        Arrays.sort(happiness);
        
        long totalHappiness = 0;
        
        int n = happiness.length;
        
        for (int i = 0; i < k; i++) {
            totalHappiness += Math.max(happiness[n - i - 1] - i, 0);
        }
        
        return totalHappiness;
    }
}
```
