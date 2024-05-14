# Count and Say

- https://leetcode.com/problems/count-and-say/
- doing exactly what the question says
- complexity - O(n * m), where m is the size of the string that we reach

```java
class Solution {

    public String countAndSay(int n) {

        char[] arr = new char[]{'1'};
        
        for (int i = 0; i < n - 1; i++) {

            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < arr.length;) {

                int start = j;

                while (j < arr.length && arr[j] == arr[start]) {
                    j += 1;
                }

                sb.append(Integer.toString(j - start));
                sb.append(arr[start]);
            }

            arr = sb.toString().toCharArray();
        }

        return new String(arr);
    }
}
```
