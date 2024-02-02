# Missing And Repeating Numbers

- https://www.codingninjas.com/studio/problems/missing-and-repeating-numbers_6828164

## Brute Force

- time - O(2 * n)
- space - O(n)

```java
public class Solution {
    public static int[] findMissingRepeatingNumbers(int []a) {
        
        short[] lookup = new short[a.length + 1];

        for (int i = 0; i < a.length; i++) {
            lookup[a[i]] += 1;
        }

        int repeatingNumber = Integer.MIN_VALUE;
        int missingNumber = Integer.MAX_VALUE;
        
        for (int i = 1; i <= a.length; i++) {
            if (lookup[i] == 2) {
                repeatingNumber = i;
            } else if (lookup[i] == 0) {
                missingNumber = i;
            }
        }

        return new int[]{repeatingNumber, missingNumber};
    }
}
```

## Optimal

```java
public class Solution {
    public static int[] findMissingRepeatingNumbers(int []a) {

        long diffOfMissingAndRepeating = 0L;
        long diffOfSquareOfMissingAndRepeating = 0L;
        for (int i = 0; i < a.length; i++) {
            diffOfMissingAndRepeating += a[i] - (i + 1);
            diffOfSquareOfMissingAndRepeating += ((long) a[i] * a[i]) - ((long) (i + 1) * (i + 1));
        }

        long sumOfMissingAndRepeating = diffOfSquareOfMissingAndRepeating / diffOfMissingAndRepeating;
        int repeating = (int) (sumOfMissingAndRepeating + diffOfMissingAndRepeating) / 2;
        int missing = (int) (sumOfMissingAndRepeating - diffOfMissingAndRepeating) / 2;

        return new int[]{repeating, missing};
    }
}
```
