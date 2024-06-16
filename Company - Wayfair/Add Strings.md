# Add Strings

- https://leetcode.com/problems/add-strings/
- mistake 1 - update sum first then carry - because carry is used in calculating sum
- mistake 2 - check if carry is 1 after both indices reach end, e.g. 9 + 1

```java
class Solution {

    public String addStrings(String num1, String num2) {
        return new String(addStrings(num1.toCharArray(), num2.toCharArray()));
    }

    public char[] addStrings(char[] num1, char[] num2) {

        int idx1 = num1.length - 1;
        int idx2 = num2.length - 1;

        int carry = 0;

        List<Integer> result = new ArrayList<>();

        while (idx1 > -1 || idx2 > -1) {

            int digit1 = idx1 < 0 ? 0 : (num1[idx1] - '0');
            int digit2 = idx2 < 0 ? 0 : (num2[idx2] - '0');

            int sum = (digit1 + digit2 + carry) % 10;
            carry = (digit1 + digit2 + carry) / 10;

            result.add(sum);

            idx1 -= 1;
            idx2 -= 1;
        }

        if (carry > 0) {
            result.add(carry);
        }

        char[] resultArr = new char[result.size()];

        for (int i = 0; i < result.size(); i++) {
            resultArr[result.size() - 1 - i] = (char) (result.get(i) + '0');
        }

        return resultArr;
    }
}
```
