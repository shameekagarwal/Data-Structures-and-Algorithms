# Find the Union

- https://www.naukri.com/code360/problems/sorted-array_6613259
- made a lot of mistakes in this
- solution - keep advancing the pointer till the element to add is same as element at pointer

```java
import java.util.*;

public class Solution {
    public static List< Integer > sortedArray(int []a, int []b) {

        List<Integer> mergedList = new ArrayList<>();

        int pointerA = 0;
        int pointerB = 0;
        while (pointerA < a.length && pointerB < b.length) {
            int elementToAdd = a[pointerA] < b[pointerB] ? a[pointerA] : b[pointerB];
            mergedList.add(elementToAdd);
            while (pointerB < b.length && b[pointerB] == elementToAdd) {
                pointerB += 1;
            }
            while (pointerA < a.length && a[pointerA] == elementToAdd) {
                pointerA += 1;
            }
        }

        while (pointerA < a.length) {
            int elementToAdd = a[pointerA];
            mergedList.add(elementToAdd);
            while (pointerA < a.length && a[pointerA] == elementToAdd) {
                pointerA += 1;
            }
        }

        while (pointerB < b.length) {
            int elementToAdd = b[pointerB];
            mergedList.add(elementToAdd);
            while (pointerB < b.length && b[pointerB] == elementToAdd) {
                pointerB += 1;
            }
        }

        return mergedList;
    }
}
```
