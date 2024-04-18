# Water Bottles II

- https://leetcode.com/problems/water-bottles-ii/

```java
class Solution {

    public int maxBottlesDrunk(int numBottles, int numExchange) {
        
        int bottlesDrunk = numBottles;
        int emptyBottles = numBottles;
        
        while (emptyBottles >= numExchange) {
            bottlesDrunk += 1;
            emptyBottles -= numExchange;
            emptyBottles += 1;
            numExchange += 1;
        }
        
        return bottlesDrunk;
    }
}
```
