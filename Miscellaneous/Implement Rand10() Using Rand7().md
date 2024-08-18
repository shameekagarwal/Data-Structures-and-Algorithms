# Implement Rand10() Using Rand7()

- https://leetcode.com/problems/implement-rand10-using-rand7/
- rand7 once is not enough so we run it twice
- now, we have 49 possibilities to choose from - 1,1, 1,2, 1,3...4,1, 4,2, 4,3....7,6 7,7
- so, we convert it into the range 1-49 -> ((a-1)*7) + b
- now, we reject it if its in the range 41-49, otherwise we use (sum % 10) + 1
- imp note we are cycling our ranges - this ensures uniform distribution
  ```
   1  2  3  4  5  6  7
   8  9 10 11 12 13 14
  15 16 17 18 19 20 21
  22 23 24 25 26 27 28
  29 30 31 32 33 34 35
  36 37 38 39 40 41 42
  43 44 45 46 47 48 49
  ```
- when i was trying to do below - it was not working - 
  ```java
  if (idx >= 40) return rand10();
  return idx / 4 + 1;
  ```
- this is because this is not uniform - 
  ```
  1-3 is for 1
  4-7 is for 2
  ...
  36-39 is for 10
  ```
- time complexity - might feel infinite, but asymptotic - 
  ```
  let x be time taken for rand7
  T = (40/49)*2x + (9/49)*(2x+T)
  T = 80x/49 + 18x/49 + 9T/49
  T = 98x/40 ~ 3x
  ```

```java
class Solution extends SolBase {

    public int rand10() {

        int a = rand7();
        int b = rand7();

        int idx = ((a - 1) * 7) + b;

        if (idx > 40) return rand10();

        return (idx % 10) + 1;
    }
}
```
