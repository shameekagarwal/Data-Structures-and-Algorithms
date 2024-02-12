# Set The Rightmost Unset Bit

- https://www.codingninjas.com/studio/problems/set-the-rightmost-unset-bit_8160456
- e.g. 7 should stay 7, 5 should become 7 

```java
public class Solution {

    public static int setBits(int N) {

        int smallestUnsetBit = -1;
        int currentBit = 0;
        int temp = N;
        while (temp > 0) {
            if ((temp & 1) == 0) {
                smallestUnsetBit = currentBit;
                break;
            }
            currentBit += 1;
            temp /= 2;
        }

        if (smallestUnsetBit != -1) {
            N = (N | (1 << smallestUnsetBit));
        }

        return N;
    }
}

```
