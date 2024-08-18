# Add Binary

- https://leetcode.com/problems/add-binary/

## Approach 1 - Works if Interviewer allows '+'

- typical pointer approach, seen in linked lists etc already
- remember to use string builder's reverse etc to advantage

```java
class Solution {

    public String addBinary(String a, String b) {
        return addBinary(a.toCharArray(), b.toCharArray());
    }

    public String addBinary(char[] a, char[] b) {
        
        StringBuilder result = new StringBuilder();

        int carry = 0;

        int i = a.length - 1;
        int j = b.length - 1;

        while (i > -1 || j > -1) {

            int digitA = i > -1 ? (a[i] - '0') : 0;
            int digitB = j > -1 ? (b[j] - '0') : 0;
            int total = digitA + digitB + carry;
            int sum = total % 2;
            carry = total / 2;

            result.append((char)(sum + '0'));

            i -= 1;
            j -= 1;
        }

        if (carry != 0) {
            result.append((char)(carry + '0'));
        }

        result.reverse();

        return result.toString();
    }
}
```

## Approach 2 - If Interviewer Does not Allow '+'

- carry = rightShift(and(a, b))
- sum = xor(a, b)
- if carry is 0, return xor output
- else, again call sum on the computer carry and sum
- implementation -
  - either you remember big integer stl
  - or just do the basic implementation of the binary operations using strings
