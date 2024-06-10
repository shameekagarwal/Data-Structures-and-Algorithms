# Maximum Swap

- https://leetcode.com/problems/maximum-swap/
- use greedy - always makes sense to maximize the msb
- imp boundary case i missed -
  ```
       2       9       9
  .... i ..... j ..... k
  ```
- swapping with 9 at position j is better than swapping with 9 at position k - having 2 at j is worse than having 2 at k
- time complexity - O(n) not o(n^2) - because the "reconstruction" from array to string to int only happens once - when the condition `arr[pos] != arr[i]` is satisfied

```java
class Solution {

    public int maximumSwap(int num) {

        char[] arr = Integer.toString(num).toCharArray();

        for (int i = 0; i < arr.length; i++) {

            int pos = i;

            for (int j = i + 1; j < arr.length; j++) {
                
                if (arr[pos] <= arr[j]) {
                    pos = j;
                }
            }

            if (arr[pos] != arr[i]) {
                swap(arr, i, pos);
                return Integer.parseInt(new String(arr));
            }
        }

        return num;
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
```
