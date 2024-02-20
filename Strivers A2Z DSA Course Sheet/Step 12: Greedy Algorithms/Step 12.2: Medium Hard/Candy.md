# Candy

- https://leetcode.com/problems/candy/
- brute force - sort ratings, but remember indices
- now, start assigning candies from lowest to highest
- while assigning, check if rating of left is smaller - if yes, candies for current index should be at least greater than or equal - add candies = max(candies, candies_left + 1)
- do the same thing for right
- time complexity - O(N*logN)

```java
class Solution {

    public int candy(int[] ratings) {

        int[] candy = new int[ratings.length];

        List<int[]> sortedRatings = new ArrayList<>();
        for (int i = 0; i < ratings.length; i++) {
            sortedRatings.add(new int[]{ratings[i], i});
        }

        Collections.sort(sortedRatings, (a, b) -> a[0] - b[0]);

        int result = 0;
        for (int[] sortedRating : sortedRatings) {
            int candies = 1;
            if (sortedRating[1] > 0 && ratings[sortedRating[1]] > ratings[sortedRating[1] - 1]) {
                candies = Math.max(candies, candy[sortedRating[1] - 1] + 1);
            }
            if (sortedRating[1] < ratings.length - 1 && ratings[sortedRating[1]] > ratings[sortedRating[1] + 1]) {
                candies = Math.max(candies, candy[sortedRating[1] + 1] + 1);
            }
            candy[sortedRating[1]] = candies;
            result += candies;
        }

        return result;
    }
}
```

## Optimal

- try traversing from left to right and then right to left

```java
class Solution {

    public int candy(int[] ratings) {
        
        int[] candies = new int[ratings.length];

        for (int i = 0; i < ratings.length; i++) {
            candies[i] = 1;
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        for (int i = ratings.length - 1; i > -1; i--) {
            if (i < ratings.length - 1 && ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        int result = 0;
        for (int candy : candies) {
            result += candy;
        }

        return result;
    }
}
```
