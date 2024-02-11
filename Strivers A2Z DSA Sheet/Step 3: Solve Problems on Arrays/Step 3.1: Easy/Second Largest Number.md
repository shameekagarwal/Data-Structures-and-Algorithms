# Second Largest Number

- https://www.codingninjas.com/studio/problems/ninja-and-the-second-order-elements_6581960

```java
public class Solution {

    public static int[] getSecondOrderElements(int n, int []a) {

        int largestElement = Integer.MIN_VALUE;
        int secondLargestElement = Integer.MIN_VALUE;

        int smallestElement = Integer.MAX_VALUE;
        int secondSmallestElement = Integer.MAX_VALUE;

        for (int i = 0; i < a.length; i++) {
 
            if (a[i] > largestElement) {
                secondLargestElement = largestElement;
                largestElement = a[i];
            } else if (a[i] > secondLargestElement) {
                secondLargestElement = a[i];
            }

            if (a[i] < smallestElement) {
                secondSmallestElement = smallestElement;
                smallestElement = a[i];
            } else if (a[i] < secondSmallestElement) {
                secondSmallestElement = a[i];
            }
        }

        return new int[]{secondLargestElement, secondSmallestElement};
    }
}
```
