# Distinct Islands

- https://www.codingninjas.com/studio/problems/distinct-islands_630460
- note how to compare if two islands are same, we have to keep track of the entire path - so, we use set of list of list of int - and we subtract source cell coordinates to help dedupe in set
- if we track just the direction - up or down - it might not work - directions can be same for different islands as well - e.g. DL (down left) can be - 
  ```
  0 1
  1 1
  ```
- or it can be
  ```
  1 1
  0 1
  ```

```java
import java.util.*;

public class Solution {

	private static final int[] x = new int[]{-1, 1, 0, 0};
	private static final int[] y = new int[]{0, 0, -1, 1};

	private static Set<List<List<Integer>>> distinctIslands;

	private static int n;
	private static int m;
	private static int arr[][];
	private static boolean[][] visited;

	public static int distinctIsland(int [][] arr, int n, int m)  {

		Solution.n = n;
		Solution.m = m;
		Solution.arr = arr;
		visited = new boolean[n][m];
		distinctIslands = new HashSet<>();
		
		return solve();
	}

	private static int solve() {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 1 && !visited[i][j]) {
					bfs(i, j);
				}
			}
		}

		return distinctIslands.size();
	}

	private static void bfs(int r, int c) {
		
		Deque<int[]> queue = new ArrayDeque<>();
		visited[r][c] = true;
		queue.addLast(new int[]{r, c});
		List<List<Integer>> path = new ArrayList<>();
		path.add(List.of(0, 0));

		while (!queue.isEmpty()) {
			
			int[] cell = queue.removeFirst();

			for (int i = 0; i < 4; i++) {
				
				int[] newCell = new int[]{cell[0] + x[i], cell[1] + y[i]};
				
				if (isValid(newCell[0], newCell[1]) && !visited[newCell[0]][newCell[1]] && arr[newCell[0]][newCell[1]] == 1) {
					path.add(List.of(newCell[0] - r, newCell[1] - c));
					visited[newCell[0]][newCell[1]] = true;
					queue.addLast(newCell);
				}
			}
		}

		// System.out.println(path);

		distinctIslands.add(path);
	}

	private static boolean isValid(int r, int c) {
		return r > -1 && r < n && c > -1 && c < m;
	}
}

```
