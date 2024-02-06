# Count With K Different Characters

- https://www.codingninjas.com/studio/problems/count-with-k-different-characters_1214627
- at every step, we find no. of substrings with length <= k starting ending at i
- so, we increase left till number of unique characters seen are less than or equal to k
- this value is i + 1 - left
- we do this for both k and k - 1 to get the actual value for k
- time complexity - O(2n) + O(2n) = O(n)

```java
import java.util.Map;
import java.util.HashMap;

public class Solution {

  public static int countSubStrings(String str, int k) {
    return findNoOfSubstringsLessThanOrEqualToK(str, k) - findNoOfSubstringsLessThanOrEqualToK(str, k - 1);
  }

  private static int findNoOfSubstringsLessThanOrEqualToK(String s, int k) {

    if (k <= 0)
      return 0;

    char[] arr = s.toCharArray();
    Map<Character, Integer> frequency = new HashMap<>();
    int result = 0;
    int left = 0;

    for (int i = 0; i < arr.length; i++) {
      frequency.put(arr[i], frequency.getOrDefault(arr[i], 0) + 1);
      while (frequency.size() > k) {
        if (frequency.get(arr[left]) == 1) {
          frequency.remove(arr[left]);
        } else {
          frequency.put(arr[left], frequency.get(arr[left]) - 1);
        }
        left += 1;
      }
      int windowLength = i - left + 1;
      // System.out.printf("for %s: %d\n", s.substring(left, i + 1), windowLength);
      result += windowLength;
    }

    return result;
  }

}

```
