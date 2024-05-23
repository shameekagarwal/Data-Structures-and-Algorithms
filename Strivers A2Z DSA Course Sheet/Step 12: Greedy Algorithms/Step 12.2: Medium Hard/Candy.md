# Candy

- https://leetcode.com/problems/candy/
- brute force - sort ratings, but remember indices
- now, start assigning candies from lowest to highest
- while assigning, check if rating of left is smaller - if yes, candies for current index should be greater than, so use max(candies, candies_left + 1)
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
- intuition - try thinking of array as mountains
- this is just my thought, to validate
  - all trenches can be 1?
  - all peaks will have to consider their left and right trenches - which ever has more elements
  - all elements "on the slopes" only need to consider their left or right minima, based on if they are on increasing or decreasing slope

```java
class Solution {

    public int candy(int[] ratings) {

        int[] result = new int[ratings.length];
        result[0] = 1;

        for (int i = 1; i < ratings.length; i++) {

            if (ratings[i] > ratings[i - 1]) {
                result[i] = result[i - 1] + 1;
            } else {
                result[i] = 1;
            }
        }

        for (int i = ratings.length - 2; i > -1; i--) {

            if (ratings[i] > ratings[i + 1]) {
                result[i] = Math.max(result[i], result[i + 1] + 1);
            }
        }

        int sum = 0;

        for (int i : result) {
            sum += i;
        }

        return sum;
    }
}
```

## Topological Sort

- topological sort came to my mind first, and it does solve the problem
- assume there is an edge from the larger numbers to their smaller adjacent numbers
- now, we add nodes with indegree 0 to the queue i.e. nodes having no smaller adjacent elements
- finally, we follow kahn's algorithm
- we are automatically able to add elements whose "smaller adjacent elements" have been consumed

```java
class Solution {

    public int candy(int[] ratings) {

        int n = ratings.length;
        int[] indegree = new int[n];

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {

            if (ratings[i] < ratings[i + 1]) {
                indegree[i + 1] += 1;
                graph.get(i).add(i + 1);
            }
        }

        for (int i = 1; i < n; i++) {

            if (ratings[i] < ratings[i - 1]) {
                indegree[i - 1] += 1;
                graph.get(i).add(i - 1);
            }
        }

        Deque<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {

            if (indegree[i] == 0) {
                queue.addLast(new int[]{i, 1});
            }
        }

        int result = 0;

        while (!queue.isEmpty()) {

            int[] node = queue.removeFirst();
            result += node[1];

            for (int neighbor : graph.get(node[0])) {

                indegree[neighbor] -= 1;

                if (indegree[neighbor] == 0) {
                    queue.addLast(new int[]{neighbor, node[1] + 1});
                }
            }
        }

        return result;
    }
}
```
