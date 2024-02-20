# Kth Missing Positive Number

- https://leetcode.com/problems/kth-missing-positive-number/
- the brute force itself is not that straight forward, remember
- logic for brute force - if array was empty, answer would have been the number itself
- however, we need to increment possible value of k till numbers smaller than it
- if k is 6, array is `[5, 7, 9]`, answer would be 8 since two numbers - 5 and 7 are smaller than it

```java
class Solution {
    public int findKthPositive(int[] arr, int k) {
        int result = k;
        for (int i = 0; i < arr.length; i++) {
            if (i + k + 1 > arr[i]) {
                result = k + i + 1;
            }
        }
        return result;
    }
}
```

- now, implement the above but use binary search

```java
class Solution {
    public int findKthPositive(int[] arr, int k) {
        
        int low = 0;
        int high = arr.length - 1;
        int idx = -1; // e.g. arr = [4], k = 3

        while (low <= high) {
            
            int mid = low + ((high - low) / 2);

            if (arr[mid] - (mid + 1) < k) {
                idx = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.printf("idx = %d\n", idx);

        if (idx == -1) {
            return k;
        } else {
            return k + idx + 1;
        }
    }
}

```
