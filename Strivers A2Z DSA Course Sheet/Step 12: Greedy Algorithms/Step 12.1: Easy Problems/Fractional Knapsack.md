# Fractional Knapsack

- https://www.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1
- we can pick fractions of weights
- to maximize value, pick weights with maximum value per unit weight first
- so, we sort the array first (custom comparator)
- we pick the entire weight and add the entire value if possible
- else, we pick the remaining weight and multiply it by value per unit weight to get the increase in value

```java
class Solution {

    double fractionalKnapsack(int W, Item arr[], int n) {
        
        arr = Arrays.copyOfRange(arr, 0, arr.length);
        
        Arrays.sort(arr, (itemA, itemB) -> {
            double vpw1 = ((double) itemA.value) / itemA.weight;
            double vpw2 = ((double) itemB.value) / itemB.weight;
            if (vpw1 > vpw2) return -1;
            else if (vpw1 < vpw2) return 1;
            return 0;
        });
        
        double finalValue = 0;
        for (int i = 0; i < arr.length; i++) {
            if (W > arr[i].weight) {
                W -= arr[i].weight;
                finalValue += arr[i].value;
            } else {
                finalValue += ((((double) arr[i].value) / arr[i].weight) * W);
                W = 0;
            }
            // System.out.printf("adding weight: %d, value: %d, so final value: %f and final weight: %d\n", arr[i].weight, arr[i].value, finalValue, W);
        }
        return finalValue;
    }
}
```
