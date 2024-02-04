# Capacity To Ship Packages Within D Days

- https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
- key point - consecutive, conveyor belt
- low - max element in array
- high - sum of all elements in array

```java
class Solution {

    private int[] weights;
    private int days;
    
    public int shipWithinDays(int[] weights, int days) {

        this.weights = weights;
        this.days = days;

        int low = getMaximumWeight();
        int high = getSumOfWeights();
        int minRequiredShipCapacity = high;

        while (low <= high) {
            int currentShipCapacity = low + ((high - low) / 2);
            if (isPossibleToShipWithCapacity(currentShipCapacity)) {
                minRequiredShipCapacity = currentShipCapacity;
                high = currentShipCapacity - 1;
            } else {
                low = currentShipCapacity + 1;
            }
        }

        return minRequiredShipCapacity;
    }

    private boolean isPossibleToShipWithCapacity(int capacity) {
        int requiredDays = 0;
        int currentRemainingCapacity = capacity;
        for (int i = 0; i < weights.length; i++) {
            if (currentRemainingCapacity < weights[i]) {
                requiredDays += 1;
                currentRemainingCapacity = capacity;
            }
            currentRemainingCapacity -= weights[i];
        }
        requiredDays += 1;
        // System.out.printf("required days for %d = %d\n", capacity, requiredDays);
        return requiredDays <= days;
    }

    private int getMaximumWeight() {
        int maximumWeight = Integer.MIN_VALUE;
        for (int i = 0; i < weights.length; i++) {
            maximumWeight = Math.max(maximumWeight, weights[i]);
        }
        return maximumWeight;
    }

    private int getSumOfWeights() {
        int sumOfWeights = 0;
        for (int i = 0; i < weights.length; i++) {
            sumOfWeights += weights[i];
        }
        return sumOfWeights;
    }
}
```
