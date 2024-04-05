# Fruits and Baskets

- https://www.codingninjas.com/studio/problems/fruits-and-baskets_985356
- similar optimization to [Longest Substring Without Repeating Characters](./Longest%20Substring%20Without%20Repeating%20Characters.md)
- we are basically trying to find the length of maximum consecutive trees we can pick ending at r
- if neither fruit1 and fruit2 have been assigned a value, and both are not equal to current element, increment l to the "minimum possible"
- minimum possible - (whichever of fruit1 and fruit2 had the "prior last occurrence") + 1
- if fruit1 has not been assigned a value, assign it the current value
- if fruit2 has not been assigned a value, and value assigned to fruit1 is not the same as the current value, assign it the current value
- finally, if current fruit is same as fruit1 / fruit2, update their respective "prior last occurrence"

```java
import java.util.Arrays;

public class Solution {

    public static int findMaxFruits(int []arr, int n) {

        int fruit1LastIdx = -1;
        int fruit1 = -1;

        int fruit2LastIdx = -1;
        int fruit2 = -1;

        int l = 0;
        int result = 0;

        for (int r = 0; r < n; r++) {

            if ((arr[r] != fruit1 && fruit1LastIdx != -1) && (arr[r] != fruit2 && fruit2LastIdx != -1)) {
                if (fruit1LastIdx < fruit2LastIdx) {
                    l = fruit1LastIdx + 1;
                    fruit1LastIdx = r;
                    fruit1 = arr[r];
                } else {
                    l = fruit2LastIdx + 1;
                    fruit2LastIdx = r;
                    fruit2 = arr[r];
                }
            } else if (fruit1LastIdx == -1) {
                fruit1LastIdx = r;
                fruit1 = arr[r];
            } else if (fruit2LastIdx == -1 && fruit1 != arr[r]) {
                fruit2LastIdx = r;
                fruit2 = arr[r];
            } else if (fruit1 == arr[r]) {
                fruit1LastIdx = r;
            } else if (fruit2 == arr[r]) {
                fruit2LastIdx = r;
            }

            // System.out.println(Arrays.toString(Arrays.copyOfRange(arr, l, r + 1)) + ": " + "fruit1: [" + fruit1LastIdx + "," + fruit1 + "], fruit2: [" + fruit2LastIdx + "," + fruit2 + "]");

            result = Math.max(r - l + 1, result);
        }

        return result;
    }
}
```
