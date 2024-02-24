# Course Schedule

- has two parts - first part asks if possible or not, second part asks the topological sort
- possible or not - two solutions - 
  - [Cycle Detection in Directed Graph (DFS)](../Step%2015.2:%20Problems%20on%20BFS%20DFS/Cycle%20Detection%20in%20Directed%20Graph%20(DFS).md)
  - [./Cycle Detection in Directed Graph (BFS)](./Cycle%20Detection%20in%20Directed%20Graph%20(BFS).md)
- some actual possible order - again two solutions - [Topological Sorting](./Topological%20Sorting.md) - bfs and dfs
- since kahn can do both easily, i am using it for both problems - probably best to use it for interview
- https://leetcode.com/problems/course-schedule/
- https://leetcode.com/problems/course-schedule-ii/
- in general, the bfs / kahn implementation feels much more reliable / easy to explain, so use it for interview

```java
class Solution {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        
        List<List<Integer>> prerequisitesGraph = new ArrayList<>();
        int[] indegree = new int[numCourses];
        
        for (int course = 0; course < numCourses; course++) {
            prerequisitesGraph.add(new ArrayList<>());
        }
        
        for (int[] prerequisite : prerequisites) {
            prerequisitesGraph.get(prerequisite[1]).add(prerequisite[0]);
            indegree[prerequisite[0]] += 1;
        }

        Deque<Integer> queue = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();

        for (int course = 0; course < numCourses; course++) {
            if (indegree[course] == 0) {
                queue.addLast(course);
            }
        }

        while (!queue.isEmpty()) {

            int course = queue.removeFirst();
            result.add(course);

            for (int prerequisite : prerequisitesGraph.get(course)) {
                indegree[prerequisite] -= 1;
                if (indegree[prerequisite] == 0) {
                    queue.addLast(prerequisite);
                }
            }
        }

        int[] resultArr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultArr[i] = result.get(i);
        }

        // only change needed for either parts is the below return statements
        return result.size() == numCourses ? resultArr : new int[]{};
        // return result.size() == numCourses;
    }
}
```
