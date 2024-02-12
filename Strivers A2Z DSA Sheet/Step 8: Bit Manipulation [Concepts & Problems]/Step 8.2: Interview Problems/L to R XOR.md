# L to R XOR

- https://www.codingninjas.com/studio/problems/l-to-r-xor_8160412
- naive - O(n) - tle

```java
public class Solution {
    
    public static int findXOR(int L, int R) {
    
        int result = 0;
    
        for (int i = L; i <= R; i++) {
            result ^= i;
        }
    
        return result;
    }
}
```

- optimal - O(1) xor up to x follows a pattern - 
  ```
  0000 -  0 - 0
  0001 -  1 - 1
  0010 -  2 - 3
  0011 -  3 - 0
  
  0100 -  4 - 4
  0101 -  5 - 1
  0110 -  6 - 7
  0111 -  7 - 0
  
  1000 -  8 - 8
  1001 -  9 - 1
  1010 - 10 - 11
  1011 - 11 - 0
  
  1100 - 12 - 12
  1101 - 13 - 1
  1110 - 14 - 15
  1111 - 15 - 0
  ```

```java
public class Solution {
    
    public static int findXOR(int L, int R) {
        return xorUptoX(R) ^ xorUptoX(L - 1);
    }

    private static int xorUptoX(int x) {
        switch (x % 4) {
            case 0: return x;
            case 1: return 1;
            case 2: return x + 1;
            case 3: return 0;
        }
        return -1;
    }
}
```
