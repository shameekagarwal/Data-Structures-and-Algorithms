# Generate Parentheses

- https://leetcode.com/problems/generate-parentheses/
- generate `(` if count of all `(` in current string is < n
- generate `)` if current count is > 0

```java
class Solution {
    
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        char[] str = new char[2 * n];
        rec(result, str, 0, n, 0);
        return result;
    }

    private void rec(List<String> result, char[] str, int idx, int totalPossibleOpens, int currentOpens) {
        if (idx == str.length) {
            result.add(new String(str));
            return;
        }
        if (totalPossibleOpens > 0) {
            str[idx] = '(';
            rec(result, str, idx + 1, totalPossibleOpens - 1, currentOpens + 1);
        }
        if (currentOpens > 0) {
            str[idx] = ')';
            rec(result, str, idx + 1, totalPossibleOpens, currentOpens - 1);
        }
    }
}
```
