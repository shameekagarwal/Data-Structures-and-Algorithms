# Fruits and Baskets

- https://www.codingninjas.com/studio/problems/fruits-and-baskets_985356
- similar optimization to [Longest Substring Without Repeating Characters](./Longest%20Substring%20Without%20Repeating%20Characters.md)
- we are basically trying to find the length of maximum consecutive trees we can pick ending at r
- if neither fruit1 and fruit2 have been assigned a value, and both are not equal to current element, increment l to the "minimum possible"
- minimum possible - (whichever of fruit1 and fruit2 occurs first) + 1
- if fruit1 has not been assigned a value, assign it the current value
- if fruit2 has not been assigned a value, and value assigned to fruit1 is not the same as the current value, assign it the current value

```java
import java.util.Map;
import java.util.HashMap;

public class Solution {

    public static int findMaxFruits(int []arr, int n) {

        Map<Integer, Integer> lastSeen = new HashMap<>();
        int fruit1 = -1;
        int fruit2 = -1;
        int l = 0;
        int maxFruitsPicked = 0;

        for (int r = 0; r < arr.length; r++) {

            if ((fruit1 != -1 && arr[r] != fruit1) && (fruit2 != -1 && arr[r] != fruit2)) {
                if (lastSeen.get(fruit1) < lastSeen.get(fruit2)) {
                    l = lastSeen.get(fruit1) + 1;
                    fruit1 = arr[r];
                } else {
                    l = lastSeen.get(fruit2) + 1;
                    fruit2 = arr[r];
                }
            } else if (fruit1 == -1) {
                fruit1 = arr[r];
            } else if (fruit2 == -1 && arr[r] != fruit1) {
                fruit2 = arr[r];
            }
            lastSeen.put(arr[r], r);

            // System.out.printf("fruit 1: %d, fruit 2: %d, l: %d\n", fruit1, fruit2, l);

            maxFruitsPicked = Math.max(maxFruitsPicked, r - l + 1);
        }

        return maxFruitsPicked;
    }
}
```
