# Kth element of 2 sorted arrays

- replica of [Median of 2 sorted arrays](./Median%20of%202%20sorted%20arrays.md)
- https://www.codingninjas.com/studio/problems/k-th-element-of-2-sorted-array_1164159
- vvimp - was getting index out of bounds - low and high cannot be 0 and n - 
  - min has to be `Math.max(0, k - n)`
    - if array 2 has 3 elements, and k is 4
    - we will have to pick up at least 1 element from array 1 because even all elements of array 2 on left are not enough
  - max has to be `Math.min(k, n)`
    - if array 1 has 10 elements, and k is 4
    - we cannot pick 10 elements from array 1 ever, we can only pick at most 4 elements
- cannot understand why this issue never happened in the median question?

## Brute

```java
import java.util.ArrayList;

public class Solution {
    
    public static int kthElement(ArrayList<Integer> arr1, ArrayList<Integer> arr2, int n, int m, int k) {

        int ptr1 = 0;
        int ptr2 = 0;
        int ptrMerged = 0;
        int lastEle = -1;

        while (ptr1 < arr1.size() && ptr2 < arr2.size() && ptrMerged < k) {
            if (arr1.get(ptr1) < arr2.get(ptr2)) {
                lastEle = arr1.get(ptr1);
                ptr1 += 1;
                ptrMerged += 1;
            } else {
                lastEle = arr2.get(ptr2);
                ptr2 += 1;
                ptrMerged += 1;
            }
        }

        while (ptr1 < arr1.size() && ptrMerged < k) {
            lastEle = arr1.get(ptr1);
            ptr1 += 1;
            ptrMerged += 1;
        }
        
        while (ptr1 < arr1.size() && ptrMerged < k) {
            lastEle = arr2.get(ptr2);
            ptr2 += 1;
            ptrMerged += 1;
        }

        return lastEle;
    }
}
```

## Optimal

```java
import java.util.ArrayList;
public class Solution {
    
    public static int kthElement(ArrayList<Integer> arr1, ArrayList<Integer> arr2, int n, int m, int k) {

        if (arr1.size() > arr2.size()) {
            ArrayList<Integer> temp = arr1;
            arr1 = arr2;
            arr2 = temp;
        }

        if ((arr1.size() == 0 && arr2.size() == 0) || (k > arr1.size() + arr2.size())) {
            return -1;
        } else if (arr1.size() == 0 && arr2.size() == 1) {
            return arr2.get(0);
        }

        int low = Math.max(0, k - arr2.size());
        int high = Math.min(arr1.size(), k);

        while (low <= high) {
            
            int l1 = (low + high) / 2;
            int l2 = k - l1;

            // decrease elements of first array on left
            if (l1 != 0 && l2 != arr2.size() && arr1.get(l1 - 1) > arr2.get(l2)) {
                high = l1 - 1;
            }

            // increase elements of first array on left
            else if (l2 != 0 && l1 != arr1.size() && arr2.get(l2 - 1) > arr1.get(l1)) {
                low = l1 + 1;
            }

            else {
                if (l1 == 0) return arr2.get(l2 - 1);
                else if (l2 == 0) return arr1.get(l1 - 1);
                else return Math.max(arr1.get(l1 - 1), arr2.get(l2 - 1));
            }
        }

        return -1;
    }
}
```
