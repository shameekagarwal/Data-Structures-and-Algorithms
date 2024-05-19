# Permutation Sequence

- https://leetcode.com/problems/permutation-sequence/

## Approach 1 - Brute

- call next permutation k times
- complexity - k * n

```java
class Solution {

    public String getPermutation(int n, int k) {
        
        int[] arr = new int[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        for (int i = 1; i < k; i++) {
            nextPermutation(arr, n);
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < n; i++) {
            result.append(Integer.toString(arr[i]));
        }

        return result.toString();
    }

    private void nextPermutation(int[] arr, int n) {

        int pivot = n - 1;

        while (pivot > 0 && arr[pivot] < arr[pivot - 1]) {
            pivot -= 1;
        }

        int idx = justGreaterBinarySearch(arr, pivot, n - 1, arr[pivot - 1]);

        swap(arr, pivot - 1, idx);
        reverse(arr, pivot, n - 1);
    }

    private int justGreaterBinarySearch(int[] arr, int l, int r, int element) {

        int result = l;

        while (l <= r) {

            int m = (l + r) / 2;

            if (arr[m] > element) {
                result = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return result;
    }

    private void reverse(int[] arr, int l, int r) {

        while (l < r) {
            swap(arr, l, r);
            l += 1;
            r -= 1;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
```

## Approach 2 - Optimal

- recall how for generating subsets, we can break into 2^n-1 numbers
- we do similar thing here

| Bin   | K | Breakdown          | Factor | Permutation |
|-------|---|--------------------|--------|-------------|
| 0 0 0 | 0 | 2!x0 + 1!x0 + 0!x0 | 0 0 0  | 1 2 3       |
| 0 0 1 | 1 | 2!x0 + 1!x1 + 0!x0 | 0 1 0  | 1 3 2       |
| 0 1 0 | 2 | 2!x1 + 1!x0 + 0!x0 | 1 0 0  | 2 1 3       |
| 0 1 1 | 3 | 2!x1 + 1!x1 + 0!x0 | 1 1 0  | 2 3 1       |
| 1 1 0 | 4 | 2!x2 + 1!x0 + 0!x0 | 2 0 0  | 3 1 2       |
| 1 0 1 | 5 | 2!x2 + 1!x1 + 0!x0 | 2 1 0  | 3 2 1       |

- now for k, find the factors one by one
- e.g. a factor is 1 - pick the first element from `[1,2,3,4]` = 2
- then, remove 2 from this list - `[1,3,4]`
- continue the process till n
- time complexity - n^2 - extra n due to the .remove called on list, so should be lesser than n^2, since the list itself becomes smaller every time

```java
class Solution {

    public String getPermutation(int n, int k) {

        k -= 1;

        int[] factorial = generateFactorials(n);

        List<Integer> pick = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            pick.add(i + 1);
        }

        List<Integer> permutation = new ArrayList<>();

        for (int i = n - 1; i > -1; i--) {

            int factor = k / factorial[i];

            permutation.add(pick.get(factor));
            pick.remove(factor);

            k -= (factor * factorial[i]);
        }

        StringBuilder result = new StringBuilder();

        for (int number : permutation) {
            result.append(Integer.toString(number));
        }

        return result.toString();
    }

    private int[] generateFactorials(int n) {

        int[] factorial = new int[n + 1];
        factorial[0] = 1;

        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        return factorial;
    }
}
```
