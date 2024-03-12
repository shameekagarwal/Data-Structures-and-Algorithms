# Kth Missing Positive Number

- https://leetcode.com/problems/kth-missing-positive-number/
- the brute force itself is not that straight forward, remember
- logic for brute force - if array was empty, answer would have been the number itself
- however, we need to increment possible value of k till numbers smaller than it
- if k is 6, array is `[5, 7, 9]`, answer would be 8 since two numbers - 5 and 7 are smaller than it

```java
class Solution {
    public int findKthPositive(int[] arr, int k) {
        
        int n = arr.length;
        
        for (int i = 0; i < n; i++) {
            if (arr[i] > k + i) {
                return k + i;
            }
        }

        return k + n;
    }
}
```

- now, implement the above but use binary search

```java
class Solution {

    public int findKthPositive(int[] arr, int k) {

        int n = arr.length;
        int low = 0;
        int high = n - 1;
        int result = n + k;

        while (low <= high) {
            
            int mid = low + ((high - low) / 2);

            if (arr[mid] > k + mid) {
                result = k + mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }
}
```
