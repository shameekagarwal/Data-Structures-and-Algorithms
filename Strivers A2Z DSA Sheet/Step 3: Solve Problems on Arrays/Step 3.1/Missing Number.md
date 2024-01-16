# Missing Number

- https://leetcode.com/problems/missing-number/
- brute - int[] map = new map[n], map[arr[i]] += 1, find position in map where value = 0. time complexity - O(2 * n)
- another approach - (n * (n + 1) / 2) - currentArraySum. disadvantage - might require long etc to store, but this one works with int. "xor of multiple numbers never exceeds the largest number"

```java
class Solution {
    public int missingNumber(int[] nums) {
        
        int xorOfNNumbers = 0;
        int currentArrayXor = 0;
        
        for (int i = 0; i < nums.length; i++) {
            xorOfNNumbers ^= (i + 1);
            currentArrayXor ^= nums[i];
        }

        return currentArrayXor ^ xorOfNNumbers;
    }
}
```
