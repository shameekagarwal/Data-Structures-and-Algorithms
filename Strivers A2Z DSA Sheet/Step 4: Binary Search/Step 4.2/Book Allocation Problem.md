# Book Allocation Problem

- minimum number = max of array - at least some student will have to read the book completely, and that means at least max of array pages will be read at minimum
- maximum number = sum of array
- brute force - run a loop between minimum to maximum. the moment it is possible, break the loop and return the answer
- so, we perform binary search between this
- https://www.codingninjas.com/studio/problems/allocate-books_1090540
- for interview etc - remember the check - if `m > books.size() return -1`

```java
import java.util.ArrayList;

public class Solution {

    public static int findPages(ArrayList<Integer> books, int n, int m) {

        if (m > books.size()) {
            return -1;
        }

        long minPossiblePages = Long.MIN_VALUE;
        for (int book : books) {
            minPossiblePages = Math.max(minPossiblePages, book);
        }

        long maxPossiblePages = 0;
        for (int book : books) {
            maxPossiblePages += book;
        }

        long pages = maxPossiblePages;

        while (minPossiblePages <= maxPossiblePages) {

            long currentPages = (minPossiblePages + maxPossiblePages) / 2;

            int currentNumberOfStudents = 0;
            int pagesReadByCurrentStudent = 0;
            for (int book : books) {
                if (pagesReadByCurrentStudent + book > currentPages) {
                    currentNumberOfStudents += 1;
                    pagesReadByCurrentStudent = 0;
                }
                pagesReadByCurrentStudent += book;
            }
            currentNumberOfStudents += 1;

            // System.out.printf("%d students needed for %d pages\n", currentNumberOfStudents, currentPages);

            if (currentNumberOfStudents > m) {
                minPossiblePages = currentPages + 1;
            } else {
                pages = currentPages;
                maxPossiblePages = currentPages - 1;
            }
        }

        return (int) pages;
    }
}
```
