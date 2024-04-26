# Kth Smallest Amount With Single Denomination Combination

- https://leetcode.com/problems/kth-smallest-amount-with-single-denomination-combination/
- we cannot generate a sequence of length k, given limits of k
- if we do not think about deduplication, we can simply solve by binary search
- let `l = min(array)`, `r = k * max(array)`
- easy to see - assume we have denominations of 1 and 2 - 
  ```
  1 2 2 3 4 4 5 6 6 7 8 8 9 10
  ```
- number of possible values we can generate which are <= mid = sum(mid / denomination) for all denominations
- now, if this value is >= k, current value can be our answer - why? because duplicates can make this count more
- however, we try to look for a smaller value, so r = mid - 1
- else, l = mid + 1
- now issue - duplicates
- our strategy of binary search is correct - but how to get that count for mid effectively? - the one we used was when considering duplicates
- solution - inclusion exclusion principle
- a + b + c - (a V b) - (a V c) - (b V c) + (a V b V c) and so on
- here, intersection denotes lcm
- intuition - 
  - when adding for a b and c, we included lcm(a, b), lcm(a, c) and lcm(b, c) twice
  - when removing lcm(a, b), lcm(a, c) and lcm(b, c), i ended up "not including" lcm(a, b, c) at all
  - and so on

```java
class Solution {

    public long findKthSmallest(int[] coins, int k) {
        
        long small = findMin(coins);
        long large = k * 1L * findMax(coins);
        long ans = -1;
        int n = coins.length;
        Map<Integer, List<Long>> lcmLookup = new HashMap<>();
        
        generateAllSubsequences(coins, n, 0, lcmLookup, new ArrayList<>());

        // System.out.println(lcmLookup);
        
        while (small <= large) {
            
            long mid = (small + large) / 2;
            
            long smaller = findDenominationsLE(mid, lcmLookup, n);
            System.out.printf("denominations smaller than %d: %d\n", mid, smaller);
            
            if (smaller >= k) {
                ans = mid;
                large = mid - 1;
            } else {
                small = mid + 1;
            }
        }
        
        return ans;
    }
    
    private void generateAllSubsequences(int[] coins, int n, int idx, Map<Integer, List<Long>> lcmLookup, List<Integer> current) {
        
        if (idx == n) {
            
            int sz = current.size();
            if (sz == 0) return;
            
            long lcm = findLcm(current);
            
            if (!lcmLookup.containsKey(sz)) {
                lcmLookup.put(sz, new ArrayList<>());
            }

            lcmLookup.get(sz).add(lcm);
            
            return;
        }

        generateAllSubsequences(coins, n, idx + 1, lcmLookup, current);
        current.add(coins[idx]);
        generateAllSubsequences(coins, n, idx + 1, lcmLookup, current);
        current.remove(current.size() - 1);
    }
    
    private long findLcm(List<Integer> current) {
        
        int n = current.size();
        long result = current.get(0);

        for (int i = 1; i < n; i++) {
            result = (result * current.get(i)) / gcd(result, current.get(i));
        }
        
        return result;
    }
    
    private long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
    
    private long findDenominationsLE(long mid, Map<Integer, List<Long>> lcmLookup, int n) {

        long result = 0;
        
        for (int i = 1; i <= n; i++) {
            
            int multiplier = i % 2 == 0 ? -1 : 1;
            
            for (long value : lcmLookup.get(i)) {
                result += (multiplier * (mid / value));
            }
        }

        return result;
    }
    
    private long findMin(int[] coins) {
        
        int n = coins.length;
        
        int min = coins[0];
        
        for (int i = 1; i < n; i++) {
            min = Math.min(min, coins[i]);
        }
        
        return min;
    }
    
    private long findMax(int[] coins) {
        
        int n = coins.length;
        
        int max = coins[0];
        
        for (int i = 1; i < n; i++) {
            max = Math.max(max, coins[i]);
        }
        
        return max;
    }
}
```

