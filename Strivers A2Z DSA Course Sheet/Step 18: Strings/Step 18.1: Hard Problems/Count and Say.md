# Count and Say

- https://leetcode.com/problems/count-and-say/
- doing exactly what the question says
- complexity - O(n * m), where m is the size of the string that we reach

```java
class Solution {

    public String countAndSay(int n) {
        
        List<Character> result = recurse(n);
        
        StringBuilder sb = new StringBuilder();
        
        for (char c : result) {
            sb.append(c);
        }

        return sb.toString();
    }

    private List<Character> recurse(int n) {
        
        if (n == 1) return List.of('1');
        
        List<Character> list = recurse(n - 1);
        List<Character> result = new ArrayList<>();
        int size = list.size();

        for (int i = 0; i < size;) {

            int j = i;

            while (j < size && list.get(i) == list.get(j)) {
                j += 1;
            }

            result.add((char)(j - i + '0'));
            result.add(list.get(i));
            i = j;
        }

        return result;
    }
}
```
