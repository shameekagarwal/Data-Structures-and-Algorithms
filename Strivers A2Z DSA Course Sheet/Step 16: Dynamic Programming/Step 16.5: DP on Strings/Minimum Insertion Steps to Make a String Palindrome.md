# Minimum Insertion Steps to Make a String Palindrome

- https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
- max steps - length of string - make s = s + reverse(s)
- for min steps - 
- first, find [Longest Palindromic Subsequence](./Longest%20Palindromic%20Subsequence.md)
- now, we just need to insert length - above value to get number of insertions to make!
- e.g. - abixyztzyxba
- ab*i*xyz*t*zyxba, we just need to insert t and i somehow, because rest of it is a valid palindrome

```java
class Solution {

    public int minInsertions(String s) {

        char[] s1 = s.toCharArray();
        char[] s2 = s.toCharArray();
        reverse(s2);

        int n = s.length();
        int[] previous = new int[n];

        for (int i = 0; i < n; i++) {

            int[] current = new int[n];

            for (int j = 0; j < n; j++) {
                if (s1[i] == s2[j]) {
                    current[j] = (j == 0 ? 0 : previous[j - 1]) + 1;
                } else {
                    current[j] = Math.max(
                        j == 0 ? 0 : current[j - 1],
                        i == 0 ? 0 : previous[j]
                    );
                }
            }

            previous = current;
            // System.out.println(Arrays.toString(current));
        }

        return n - previous[n  - 1];
    }

    private void reverse(char[] s) {
        
        int n = s.length;
        
        for (int i = 0; i < n / 2; i++) {
            char temp = s[i];
            s[i] = s[n - 1 - i];
            s[n - 1 - i] = temp;
        }
    }
}
```
