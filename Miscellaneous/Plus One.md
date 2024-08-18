# Plus One

- https://leetcode.com/problems/plus-one/
- just initialize assuming we will run out
- while returning, use `Arrays.copyOfRange` depending on whether or not msb is 0

```java
class Solution {

    public int[] plusOne(int[] digits) {

        int n = digits.length;
        int[] result = new int[n + 1];

        int carry = 1;

        for (int i = n - 1; i > -1; i--) {
            result[i + 1] = (carry + digits[i]) % 10;
            carry = (carry + digits[i]) / 10;
        }

        result[0] = carry;

        return result[0] == 0 ? 
            Arrays.copyOfRange(result, 1, result.length) : result;
    }
}
```
