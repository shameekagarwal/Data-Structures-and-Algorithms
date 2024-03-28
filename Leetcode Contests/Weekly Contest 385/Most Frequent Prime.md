# Most Frequent Prime

- https://leetcode.com/problems/most-frequent-prime/
- just do what question asks
- we move in one direction using dfs
- along the way, we append all the intermediate results, obtained using `(running * 10) + current`
- then, we check which of these is prime using sieve of eratosthenes
- finally, we find their count and consider the one with the largest count, and largest value if multiple of them have the largest count

```java
class Solution {
    
    private static final int[][] directions = new int[][]{
        new int[]{1, 0},
        new int[]{-1, 0},
        new int[]{0, 1},
        new int[]{0, -1},
        new int[]{1, -1},
        new int[]{1, 1},
        new int[]{-1, 1},
        new int[]{-1, -1},
    };

    
    public int mostFrequentPrime(int[][] mat) {
        
        int m = mat.length;
        int n = mat[0].length;
        
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            
            for (int j = 0; j < n; j++) {
                
                for (int[] direction : directions) {
                    dfs(i, j, direction[0], direction[1], m, n, mat[i][j], mat, result);
                }
            }
        }
        
        int largest = 1;
        
        for (int i : result) {
            largest = Math.max(i, largest);
        }
        
        boolean[] isNotPrime = new boolean[largest + 1];
        isNotPrime[0] = true;
        isNotPrime[1] = true;
        
        for (int i = 2; i * i <= largest; i++) {
            for (int j = i * i; j <= largest; j += i) {
                isNotPrime[j] = true;
            }
        }
        
        int[] count = new int[largest + 1];
        
        for (int i : result) {
            if (!isNotPrime[i]) {
                count[i] += 1;
            }
        }
        
        int maxPrimeCount = 0;
        int prime = 0;
        
        for (int i : result) {
            if (count[i] > maxPrimeCount) {
                maxPrimeCount = count[i];
                prime = i;
            } else if (count[i] == maxPrimeCount) {
                prime = Math.max(i, prime);
            }
        }
        
        return count[prime] == 0 ? -1 : prime;
    }
    
    private void dfs(int x, int y, int dx, int dy, int m, int n, int current, int[][] mat, List<Integer> result) {
        
        int newX = x + dx;
        int newY = y + dy;
        
        if (newX < 0 || newX > m - 1 || newY < 0 || newY > n - 1) return;
        
        current = (current * 10) + mat[newX][newY];
        result.add(current);
        
        dfs(newX, newY, dx, dy, m, n, current, mat, result);
    }
}
```
