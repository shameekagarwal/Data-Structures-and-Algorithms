# Mark Elements on Array by Performing Queries

- https://leetcode.com/problems/mark-elements-on-array-by-performing-queries/
- just did what the question asked
- sort the array - first based on value, then based on index - because question says when marking elements, use smallest value and then the smallest index
- calculate sum of array
- for the query, first, mark the element if unmarked, and subtract from sum
- then, mark k smallest remaining elements from the sorted array and subtract the ones that were unmarked
- time complexity - n log n, for sorting, processing queries takes - q + n

```java
class Solution {

    public long[] unmarkedSumArray(int[] nums, int[][] queries) {
        
        int[][] sortedLookup = new int[nums.length][];
        for (int i = 0; i < nums.length; i++) {
            sortedLookup[i] = new int[]{nums[i], i};
        }
        Arrays.sort(sortedLookup, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int sortedLookupPointer = 0;
        
        long[] result = new long[queries.length];
        
        Set<Integer> marked = new HashSet<>();

        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        
        for (int i = 0; i < queries.length; i++) {
            
            if (!marked.contains(queries[i][0])) {
                marked.add(queries[i][0]);
                sum -= nums[queries[i][0]];
            }
            
            int toUnmark = queries[i][1];
            
            while ((toUnmark > 0) && (sortedLookupPointer < sortedLookup.length)) {
                if (!marked.contains(sortedLookup[sortedLookupPointer][1])) {
                    marked.add(sortedLookup[sortedLookupPointer][1]);
                    sum -= sortedLookup[sortedLookupPointer][0];
                    toUnmark -= 1;
                }
                sortedLookupPointer += 1;
            }

            result[i] = sum;
        }
        
        return result;
    }
}
```
