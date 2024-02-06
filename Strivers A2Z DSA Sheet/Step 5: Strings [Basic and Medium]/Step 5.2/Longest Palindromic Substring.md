# Longest Palindromic Substring

## Brute Force

- pure brute - generate all palindromic strings and check - O(n^3)
- better - O(n^2) - consider all centers and expand around it
- n centers for odd length substrings
- n - 1 centers for even length substrings
- expand on left and right of it
- another brute force using dp -
  - fill `dp[i][i]` with 1 and `dp[i][i + 1]` with 2 if `arr[i] == arr[i + 1]`
  - `dp[i][j] = dp[i + 1][j - 1] + 1 if arr[i] == arr[j] else 0`
- the most optimal solution of this problem comes from manacher's algorithm, skipping for now

```java
class Solution {
 
    public String longestPalindrome(String s) {
        
        char[] arr = s.toCharArray();
        
        PalindromeResult oddResult = findMaxOddLengthPalindrome(arr);
        PalindromeResult evenResult = findMaxEvenLengthPalindrome(arr);

        return oddResult.length > evenResult.length ? 
            s.substring(oddResult.start, oddResult.end + 1) :
            s.substring(evenResult.start, evenResult.end + 1);
    }

    private PalindromeResult findMaxOddLengthPalindrome(char[] arr) {

        PalindromeResult result = new PalindromeResult();

        for (int i = 0; i < arr.length; i++) {

            int currentPalindromeLength = 1;
            int left = i - 1;
            int right = i + 1;

            while (left > -1 && right < arr.length && arr[left] == arr[right]) {
                currentPalindromeLength += 2;
                left -= 1;
                right += 1;
            }
            
            if (result.length < currentPalindromeLength) {
                result.set(currentPalindromeLength, left + 1, right - 1);
            }
        }

        return result;
    }

    private PalindromeResult findMaxEvenLengthPalindrome(char[] arr) {

        PalindromeResult result = new PalindromeResult();

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] != arr[i - 1]) continue;

            int currentPalindromeLength = 2;
            int left = i - 2;
            int right = i + 1;

            while (left > -1 && right < arr.length && arr[left] == arr[right]) {
                currentPalindromeLength += 2;
                left -= 1;
                right += 1;
            }

            if (result.length < currentPalindromeLength) {
                result.set(currentPalindromeLength, left + 1, right - 1);
            }
        }

        return result;
    }
}

class PalindromeResult {

    int length = 0;
    int start = -1;
    int end = -1;

    void set(int length, int start, int end) {
        this.length = length;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "(" + length + ", " + start + "," + end + ")";
    }
}
```
