# Two Numbers With Odd Occurrences

- https://www.codingninjas.com/studio/problems/two-numbers-with-odd-occurrences_8160466
- let the two numbers occurring odd number of times be n1 and n2
- xor of entire array = n1 ^ n2
- now, say p bit of this xor is set
- this means one of n1 has this bit set while the other does not
- so, for all numbers in the array, if its p bit is set, move it to left, else move it to right
- xor of all elements on left = one of the odd numbers
- xor of all elements on the right = the other odd number

```java
public class Solution {

    public static int[] twoOddNum(int []arr) {
        
        int xor = 0;
        for (int i : arr) {
            xor ^= i;
        }

        int p = 0;
        while (true) {
            if ((xor & 1) == 1) {
                break;
            }
            p += 1;
            xor /= 2;
        }

        int left = 0;
        int right = 0;
        for (int i = 0; i < arr.length; i++) {
            if (((arr[i] >> p) & 1) == 1) {
                left ^= arr[i];
            } else {
                right ^= arr[i];
            }
        }

        if (left < right) {
            left = left ^ right;
            right = left ^ right;
            left =  left ^ right;
        }

        return new int[]{left, right};
    }
}
```
