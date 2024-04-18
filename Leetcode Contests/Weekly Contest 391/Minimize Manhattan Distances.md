# Minimize Manhattan Distances

- https://leetcode.com/problems/minimize-manhattan-distances/
- manhattan distance between two points - a,b and c,d - either a-b or b-a, either c-d or d-c. so, 4 combinations are possible. we rewrite the 4 combinations as follows
  - (c-a)+(d-b) -> (c+d)-(a+b)
  - (c-a)+(b-d) -> (c-d)-(a-b)
  - (a-c)+(d-b) -> (a-b)-(c-d)
  - (a-c)+(b-d) -> (a+b)-(c+d)
- so basically, it can be
  - the difference between sums of x,y
  - the difference between differences of x,y
- this way, if we construct the sums and differences array and sort them O(n*log(n))
- we get the maximum distance in O(1) now
- now, we have to check the maximum manhattan distance after removing the points one by one
- imagine sums array (the index part) after sorting looks like this - `[i,p.....q,j]`
- imagine differences array (the index part) after sorting looks like this - `[x,u.....v,y]`
- so, our answer can be - 
  - after excluding x
  - after excluding y
  - after excluding i
  - after excluding j
  - after excluding anything but these 4 - answer will be manhattan distance of the array
- one example - if excluding i - 
  - for sums consider p and j
  - for differences - 
    - consider u and y if x == i
    - consider x and v if y == i
    - else consider x and y

```java
class Solution {

    public int minimumDistance(int[][] points) {
        
        int n = points.length;
        int[][] sums = new int[n][];
        int[][] differences = new int[n][];
        
        for (int i = 0; i < n; i++) {
            sums[i] = new int[]{points[i][0] + points[i][1], i};
            differences[i] = new int[]{points[i][0] - points[i][1], i};
        }
        
        Arrays.sort(sums, (a, b) -> a[0] - b[0]);
        Arrays.sort(differences, (a, b) -> a[0] - b[0]);
        
        int val1 = Math.max(excludeIdx(sums, n, -1), excludeIdx(differences, n, -1));
        int val2 = Math.max(excludeIdx(sums, n, sums[0][1]), excludeIdx(differences, n, sums[0][1]));
        int val3 = Math.max(excludeIdx(sums, n, sums[n - 1][1]), excludeIdx(differences, n, sums[n - 1][1]));
        int val4 = Math.max(excludeIdx(sums, n, differences[0][1]), excludeIdx(differences, n, differences[0][1]));
        int val5 = Math.max(excludeIdx(sums, n, differences[n - 1][1]), excludeIdx(differences, n, differences[n - 1][1]));
        
        return min(val1, val2, val3, val4, val5);
    }
    
    private int excludeIdx(int[][] a, int n, int i) {
        int x = a[0][1] == i ? a[1][0] : a[0][0];
        int y = a[n - 1][1] == i ? a[n - 2][0] : a[n - 1][0];
        return y - x;
    }
    
    private int min(int... nums) {
        
        int mimimum = nums[0];
        
        for (int num : nums) {
            mimimum = Math.min(mimimum, num);
        }
        
        return mimimum;
    }
}
```
