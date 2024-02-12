# Count the number of set bits

- https://www.codingninjas.com/studio/problems/count-total-set-bits_784
- naive - log<sub>2</sub>1 + log<sub>2</sub>2 + log<sub>2</sub>3 + log<sub>2</sub>4 ... 
- will give tle

```java
public class Solution{
    
    public static int countSetBits(int n) {
        
        int result = 0;
        
        for (int i = 1; i <= n; i++) {
            int temp = i;
            while (temp > 0) {
                result += (temp & 1);
                temp /= 2;
            }
        }
        
        return result;
    }
}
```

- optimized -
- assume number is 13
  ```
   0 - 0000
   1 - 0001
   2 - 0010
   3 - 0011
   4 - 0100
   5 - 0101
   6 - 0110
   7 - 0111
   8 - 1000
   9 - 1001
  10 - 1010
  11 - 1011
  12 - 1100
  13 - 1101
  ```
- the number thats of the form 2<sup>something</sum> just before it is 8
- note - i have named something as `x`, 8 as `p`
- number of 1s from 0 to 7 (8-1) - 
  - there are 8 numbers from 0 to 7
  - there are 8 * 3 digits in all these 8 numbers - or p * x
  - half of them would be 0, half of them 1
  - so, `(p * x) / 2` 1s are there from 0 to 7 (remember overflow)
- from 13 to 8, 13 - 8 + 1 numbers have 4th bit as 1 - or `n - p + 1` have msb as 1
- finally, finding bits except msb that are 1s for numbers from 13-8 is the same as
- finding bits that are 1s for numbers between 0 to 5

```java
public class Solution{

    public static int countSetBits(int n) {

        if (n == 0) return 0;

        int x = log2(n);
        int p = 1 << x;
        int a = (int) ((1L * p * x) / 2); // no of 1s in numbers < p
        int b = n - p + 1; // no of 1s in msb of numbers >= p and <= n

        return a + b + countSetBits(n - p);
    }

    private static int log2(int n) {
        int ans = 0;
        while (n > 1) {
            n /= 2;
            ans += 1;
        }
        return ans;
    }
}
```
