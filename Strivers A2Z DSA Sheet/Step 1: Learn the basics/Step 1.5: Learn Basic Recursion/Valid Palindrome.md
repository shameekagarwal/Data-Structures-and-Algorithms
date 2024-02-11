# Valid Palindrome

- https://leetcode.com/problems/valid-palindrome/
- remember to break is alpha numeric into reusable pieces, if cannot recall stl

```java
class Solution {
    public boolean isPalindrome(String s) {
        return isPalindromeRecursive(new StringBuilder(s), 0, s.length() - 1);
    }

    private boolean isPalindromeRecursive(StringBuilder s, int l, int r) {
        if (l >= r) return true;
        if (!isAlphaNumeric(s.charAt(l))) return isPalindromeRecursive(s, l + 1, r);
        if (!isAlphaNumeric(s.charAt(r))) return isPalindromeRecursive(s, l, r - 1);
        if (isCapital(s.charAt(l))) s.setCharAt(l, (char)(s.charAt(l) + 32));
        if (isCapital(s.charAt(r))) s.setCharAt(r, (char)(s.charAt(r) + 32));
        System.out.println("comparing: " + s.charAt(l) + ", " + s.charAt(r));
        if (s.charAt(l) != s.charAt(r)) return false;
        return isPalindromeRecursive(s, l + 1, r - 1);
    }

    private boolean isAlphaNumeric(char c) {
        return isNumeric(c) || isCapital(c) || isSmall(c);
    }

    private boolean isNumeric(char c) {
        return (c <= '9' && c >= '0');
    }

    private boolean isCapital(char c) {
        return (c <= 'Z' && c >= 'A');
    }

    private boolean isSmall(char c) {
        return (c <= 'z' && c >= 'a');
    }
}
```
