# Koko Eating Bananas

- https://leetcode.com/problems/koko-eating-bananas/
- max - eat number of bananas = maximum pile size in array
- min - 1
- brute - iterate 1 to max, find which min value gives the min answer
- optimal - use binary search in this range
- complexity - `O(piles.length * log(max piles[i]))`

```java
class Solution {

    private int[] piles;
    private int h;
    
    public int minEatingSpeed(int[] piles, int h) {
        
        this.piles = piles;
        this.h = h;
        
        int maxSpeed = calculateMaxSpeed();
        int minSpeed = 1;
        int optimalSpeed = maxSpeed;

        while (minSpeed <= maxSpeed) {
            
            int currentSpeed = minSpeed + ((maxSpeed - minSpeed) / 2);
            if (canEatWithinHHours(currentSpeed)) {
                optimalSpeed = currentSpeed;
                maxSpeed = currentSpeed - 1;
            } else {
                minSpeed = currentSpeed + 1;
            }
        }

        return optimalSpeed;
    }

    private boolean canEatWithinHHours(int speed) {
        int requiredHours = 0;
        for (int i = 0; i < piles.length; i++) {
            requiredHours += Math.ceil((double) piles[i] / speed);
        }
        return requiredHours <= h;
    }

    private int calculateMaxSpeed() {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < piles.length; i++) {
            max = Math.max(max, piles[i]);
        }
        return max;
    }
}
```
