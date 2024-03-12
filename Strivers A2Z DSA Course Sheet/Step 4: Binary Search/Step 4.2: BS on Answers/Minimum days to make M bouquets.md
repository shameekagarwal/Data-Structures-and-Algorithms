# Minimum days to make M bouquets

- let x = maximum in array
- all flowers would be bloomed by day x, and we already checked it is possible to make the bouquets, since m * k <= n
- brute - 1 to x
- optimal - binary search between 1 to x
- let n = number of elements in array
- time complexity - O(n * logx)
- https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/

```java
class Solution {

    private int[] bloomDay;
    private int numberOfBouquets;
    private int numberOfAdjacentFlowers;

    public int minDays(int[] bloomDay, int m, int k) {
        
        this.bloomDay = bloomDay;
        this.numberOfBouquets = m;
        this.numberOfAdjacentFlowers = k;

        if (isImpossibleToForm()) {
            return -1;
        }

        int low = 1;
        int high = getMaxBloomDay();
        int minNumberOfDays = high;

        while (low <= high) {
            int currentNumberOfDays = low + ((high - low) / 2);
            // System.out.printf("evaluating for %d...\n", currentNumberOfDays);
            if (isPossibleToForm(currentNumberOfDays)) {
                minNumberOfDays = currentNumberOfDays;
                high = currentNumberOfDays - 1;
            } else {
                low = currentNumberOfDays + 1;
            }
        }

        return minNumberOfDays;
    }

    private boolean isImpossibleToForm() {
        return (long) numberOfBouquets * numberOfAdjacentFlowers > bloomDay.length;
    }

    private boolean isPossibleToForm(int numberOfDays) {
     
        int currentNumberOfBouquets = 0;
        int currentNumberOfAdjacentFlowers = 0;
     
        for (int i = 0; i < bloomDay.length; i++) {

            if (bloomDay[i] <= numberOfDays) {
                currentNumberOfAdjacentFlowers += 1;
            } else {
                currentNumberOfAdjacentFlowers = 0;
            }

            if (currentNumberOfAdjacentFlowers >= numberOfAdjacentFlowers) {
                currentNumberOfAdjacentFlowers -= numberOfAdjacentFlowers;
                currentNumberOfBouquets += 1;
            }
        }

        // System.out.printf("possible for %d: %b\n", numberOfDays, (currentNumberOfBouquets >= numberOfBouquets));

        return currentNumberOfBouquets >= numberOfBouquets;
    }

    private int getMaxBloomDay() {
  
        int max = Integer.MIN_VALUE;
  
        for (int i = 0; i < bloomDay.length; i++) {
            max = Math.max(max, bloomDay[i]);
        }
        return max;
    }
}
```
