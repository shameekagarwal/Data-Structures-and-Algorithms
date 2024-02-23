# Expression Add Operators

- https://leetcode.com/problems/expression-add-operators/
- if idx reaches end of string and current computation = target, add it to list of results
- from idx to end of string, try taking the entire thing as a number
- case 1 - if this is the first number, we do not add any operator before it (refer condition idx == 0)
- case 2 - otherwise, we try adding all three operators - +, - and * one by one
- case 3 - finally, if idx holds '0' - do not add more digits - question states that `+ 07`, `* 008`, etc would not be allowed - so we break out of loop just after one iteration
- now, case 2 elaboration
- case 2.1 - addition - straightforward
- case 2.2 - subtraction - note - previous number is current number but -current number - this helps in cases like `5-4*3` (explained later)
- case 2.3 - multiplication - because of bodmas
  - previous number becomes previous number * current number
  - result becomes result - previous number + new previous number
- `5-4*3` - 5-4 will give result 1, but when 3 is found, we do `1-(-4)+(-4*3)` = `-7`, which looks right - this was possible because we took previous number as -7 and not just 7

```java
class Solution {

    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        recurse(result, num.toCharArray(), target, 0, new StringBuilder(), 0L, 0L);
        return result;
    }

    private void recurse(List<String> result, char[] num, int target, int idx, StringBuilder expression, long previousNumber, long currentResult) {

        if (idx == num.length) {
            if (currentResult == target) {
                result.add(expression.toString());
            }
            return;
        }

        long currentNumber = 0;
        for (int i = idx; i < num.length; i++) {

            int noOfDigits = i - idx + 1;
            currentNumber = (currentNumber * 10) + (num[i] - '0');

            if (idx == 0) {
                expression.append(currentNumber);
                recurse(result, num, target, i + 1, expression, currentNumber, currentResult + currentNumber);
                expression.delete(expression.length() - noOfDigits, expression.length());
            } else {
                expression.append('+');
                expression.append(currentNumber);
                recurse(result, num, target, i + 1, expression, currentNumber, currentResult + currentNumber);
                expression.delete(expression.length() - noOfDigits - 1, expression.length());

                expression.append('-');
                expression.append(currentNumber);
                recurse(result, num, target, i + 1, expression, -currentNumber, currentResult - currentNumber);
                expression.delete(expression.length() - noOfDigits - 1, expression.length());

                expression.append('*');
                expression.append(currentNumber);
                long newPreviousNumber = previousNumber * currentNumber;
                recurse(result, num, target, i + 1, expression, newPreviousNumber, currentResult - previousNumber + newPreviousNumber);
                expression.delete(expression.length() - noOfDigits - 1, expression.length());
            }

            if (num[idx] == '0') break;
        }
    }
}
```
