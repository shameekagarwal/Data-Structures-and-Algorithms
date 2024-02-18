# Minimum number of Coins

- https://www.geeksforgeeks.org/problems/-minimum-number-of-coins4426/1
- pick largest possible value of largest denomination and so on
- note why greedy works - because sum of any two coins does not exceed a third coin
- e.g. imagine we had denominations of size 1, 5, 6, 9. to form 11, greedy gives us the answer 9, 1, 1, but the actual answer in this case would be 5, 6
- so, we will use dp in this case
- e.g. question is for indian currency, where dp works

```java
class Solution {
    
    private static final List<Integer> indianCurrencies = List.of(1, 2, 5, 10, 20, 50, 100, 200, 500, 2000);
    
    static List<Integer> minPartition(int N) {
        
        List<Integer> result = new ArrayList<>();
        
        for (int i = indianCurrencies.size() - 1; i > -1; i--) {
            while (N >= indianCurrencies.get(i)) {
                N -= indianCurrencies.get(i);
                result.add(indianCurrencies.get(i));
            }
        }
        
        return result;
    }
}
```
