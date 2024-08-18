# Sentence Screen Fitting

- https://leetcode.com/problems/sentence-screen-fitting/
- step 1 - we first try to fill x...n-1 of sentence
- step 2 - then, we try filling the entire sentence some t times
- step 3 - finally, we try filling 0...y of sentence
- for step 1 and step 2 - remember to account for fitting without requiring an extra space at end if it goes up to the last column

```java
class Solution {

    public int wordsTyping(String[] sentence, int rows, int cols) {

        int[] lengths = new int[sentence.length];
        int length = 0;

        for (int i = 0; i < sentence.length; i++) {
            lengths[i] = sentence[i].length();
            length += lengths[i];
        }

        int currentRow = 0;
        int currentSentenceIdx = 0;
        int fits = 0;

        while (currentRow < rows) {

            int currentCol = 0;

            while (currentSentenceIdx < sentence.length && currentCol + lengths[currentSentenceIdx] < cols) {
                currentCol += lengths[currentSentenceIdx];
                currentCol += 1;
                currentSentenceIdx += 1;
            }

            // fit without an extra space
            if (currentSentenceIdx < sentence.length && currentCol + lengths[currentSentenceIdx] - 1 < cols) {
                currentCol = cols;
                currentSentenceIdx += 1;
            }

            if (currentSentenceIdx == sentence.length) {
                fits += 1;
                currentSentenceIdx = 0;
            }

            // System.out.printf("after remaining suffix => current col: %d, current idx: %d, fits: %d\n", currentCol, currentSentenceIdx, fits);

            int totalSpace = length + sentence.length;

            if (currentCol < cols) {

                int times = (cols - currentCol) / totalSpace;

                currentCol += times * totalSpace;
                fits += times;

                if (times > 0) {
                    currentSentenceIdx = sentence.length;
                }

                // fit without an extra space
                if (currentCol + length + sentence.length - 1 == cols) {
                    fits += 1;
                    currentCol = cols;
                    currentSentenceIdx = sentence.length;
                }

                if (currentSentenceIdx == sentence.length) {
                    currentSentenceIdx = 0;
                }
            }

            // System.out.printf("after some multiples => current col: %d, current idx: %d, fits: %d\n", currentCol, currentSentenceIdx, fits);

            if (currentCol < cols) {

                while (lengths[currentSentenceIdx] + currentCol <= cols) {
                    currentCol += lengths[currentSentenceIdx];
                    currentCol += 1;
                    currentSentenceIdx += 1;
                }
            }

            currentRow += 1;

            // System.out.printf("after some prefix => current col: %d, current idx: %d, fits: %d\n\n", currentCol, currentSentenceIdx, fits);
        }

        return fits;
    }
}
```
