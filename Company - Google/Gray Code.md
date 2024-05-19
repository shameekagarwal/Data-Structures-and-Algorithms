# Gray Code

- for 1 - solution is 0, 1
- for 2 - 
  - we can rewrite 0, 1 as 00, 01
  - we can write remaining numbers - 2, 3 as 10, 11
- so basically, we have to have twice as many numbers for n + 1 as numbers for n
- so, divide result into two halves - first and second half
- 1st half of n is same as result of n-1 - 00, 01
- now
  - take the first half
  - set the msb
  - reverse it
- this results in 11, 10
- this can be the second half
- now, we result is just first half + second half

```java
class Solution {

    public List<Integer> grayCode(int n) {

        List<Integer> result = new ArrayList<>();

        result.add(0);
        result.add(1);

        for (int i = 2; i <= n; i++) {

            List<Integer> secondHalf = new ArrayList<>();

            for (int num : result) {
                secondHalf.add(num | (1 << (i - 1)));
            }

            Collections.reverse(secondHalf);

            result.addAll(secondHalf);
        }

        return result;
    }
}
```
