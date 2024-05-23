# Queue Reconstruction by Height

- https://leetcode.com/problems/queue-reconstruction-by-height/
- we can be sure that tallest person will have 0 people in front
  - note - if we have multiple tallest people - then they can have 0, 1, and so on people in front
  - this is because same height in front adds to count as well
- so, we start adding from tallest people to smallest people
- now, if we consider adding in decreasing order to list - the second value is basically the index to insert at!

```java
class Solution {

    public int[][] reconstructQueue(int[][] people) {

        Arrays.sort(people, (a, b) -> b[0] != a[0] ? 
            b[0] - a[0] : 
            a[1] - b[1]);

        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < people.length; i++) {
            list.add(people[i][1], people[i]);
        }

        int[][] result = new int[people.length][];

        for (int i = 0; i < people.length; i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}
```
