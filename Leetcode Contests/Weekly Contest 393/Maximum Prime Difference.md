# Maximum Prime Difference

- https://leetcode.com/problems/maximum-prime-difference/
- apply sieve of eratosthenes from numbers from 0 to max in array
- then, update min for the first prime encountered in the array
- update max every time a prime is encountered in the array

```java
class Solution {

    public int maximumPrimeDifference(int[] nums) {
        
        int mx = findMax(nums);
        boolean[] sieve = findSieve(nums, mx);
        int n = nums.length;
        int result = 0;
        
        int min = -1;
        int max = -1;
        
        for (int i = 0; i < n; i++) {
            if (sieve[nums[i]]) {
                if (min == -1) {
                    min = i;
                    max = i;
                } else {
                    max = i;
                }
            }
        }
        
        return max - min;
    }
    
    private boolean[] findSieve(int[] nums, int sz) {
        
        boolean[] sieve = new boolean[sz + 1];
        
        Arrays.fill(sieve, true);
        sieve[0] = false;
        sieve[1] = false;
        
        for (int i = 2; i * i <= sz; i++) {
            
            if (sieve[i]) {
                
                for (int j = i * i; j <= sz; j += i) {
                    sieve[j] = false;
                }
            }
        }
        
        return sieve;
    }
    
    private int findMax(int[] nums) {
        
        int n = nums.length;
        
        int result = nums[0];
        
        for (int i = 1; i < n; i++) {
            result = Math.max(result, nums[i]);
        }
        
        return result;
    }
}
```
