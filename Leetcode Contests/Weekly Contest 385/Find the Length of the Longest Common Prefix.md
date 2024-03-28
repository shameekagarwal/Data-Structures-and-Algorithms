# Find the Length of the Longest Common Prefix

- https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix/
- convert all integers to strings
- run a merge sort like algorithm - if `arr1[ptr1] > arr2[ptr2]`, increment ptr2, else increment ptr1
- this way, we always end up comparing the two closest strings from the two arrays

```java
class Solution {

    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        
        List<String> list1 = new ArrayList<>();
        
        for (int i : arr1) {
            list1.add(Integer.toString(i));
        }
        
        List<String> list2 = new ArrayList<>();
        
        for (int i : arr2) {
            list2.add(Integer.toString(i));
        }
        
        // -----------------------------------
        
        Collections.sort(list1);
        Collections.sort(list2);
        
        int result = 0;
        
        int ptr1 = 0;
        int ptr2 = 0;
        
        while (ptr1 < list1.size() && ptr2 < list2.size()) {
            
            char[] one = list1.get(ptr1).toCharArray();
            char[] two = list2.get(ptr2).toCharArray();
            
            for (int i = 0; i < Math.min(one.length, two.length); i++) {
                if (one[i] != two[i]) break;
                result = Math.max(result, i + 1);
            }
            
            if (list1.get(ptr1).compareTo(list2.get(ptr2)) > 0) {
                ptr2 += 1;
            } else {
                ptr1 += 1;
            }
        }
        
        return result;
    }
}
```
