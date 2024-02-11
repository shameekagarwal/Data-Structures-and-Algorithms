# Subarray with given XOR

- https://www.interviewbit.com/problems/subarray-with-given-xor/

```java
public class Solution {
    public int solve(ArrayList<Integer> A, int B) {
        
        Map<Integer, Integer> prefixXorLookup = new HashMap<>();
        prefixXorLookup.put(0, 1);
        int currentXor = 0;
        int result = 0;
        
        for (int i = 0; i < A.size(); i++) {
            currentXor ^= A.get(i);
            result += prefixXorLookup.getOrDefault(currentXor ^ B, 0);
            prefixXorLookup.put(currentXor, prefixXorLookup.getOrDefault(currentXor, 0) + 1);
        }
        
        return result;
    }
}
```
