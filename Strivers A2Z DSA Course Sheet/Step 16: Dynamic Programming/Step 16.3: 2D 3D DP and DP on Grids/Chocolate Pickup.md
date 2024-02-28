# Chocolate Pickup

- https://www.codingninjas.com/studio/problems/chocolate-pickup_3125885
- to avoid issues related to removing the common cell, we write the recursion for both of them together
- there are 3 variables, so we need 3 parts to memoize - `dp[n][m][m]`
- time complexity of memoized solution - `O(n*m*m)`

```java
import java.util.*;
import java.io.*;

public class Solution {

	private static int rows;
	private static int cols;
	private static int[][] grid;

	public static int maximumChocolates(int r, int c, int[][] grid) {

		Solution.rows = r;
		Solution.cols = c;
		Solution.grid = grid;

		return maximumChocolates(0, 0, cols - 1);
	}

	private static int maximumChocolates(int row, int aliceCol, int bobCol) {
		
		if (row == rows) return 0;

		int max = Integer.MIN_VALUE;

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				
				int newAliceCol = aliceCol + i;
				int newBobCol = bobCol + j;

				if (newAliceCol > -1 && newAliceCol < cols && newBobCol > -1 && newBobCol < cols) {
					int currentChocolates = maximumChocolates(row + 1, newAliceCol, newBobCol) + grid[row][aliceCol] + grid[row][bobCol];
					if (newAliceCol == newBobCol) {
						currentChocolates -= grid[row][bobCol];
					}
					// System.out.printf("%d, %d: %d\n", newAliceCol, newBobCol, currentChocolates);
					max = Math.max(max, currentChocolates);
				}
			}
		}

		return max;
	}
}
```

- tabulation + space optimized - we will need a 2d array, of size `O(m*m)`

```java
import java.util.* ;
import java.io.*; 

public class Solution {
	
	public static int maximumChocolates(int r, int c, int[][] grid) {

		int rows = grid.length;
		int cols = grid[0].length;

		int[][] prevRow = new int[cols][cols];
		for (int[] row : prevRow) {
			Arrays.fill(row, Integer.MIN_VALUE);
		}
		prevRow[0][cols - 1] = grid[0][0] + grid[0][cols - 1];
		if (cols - 1 == 0) {
			prevRow[0][cols - 1] -= grid[0][cols - 1];
		}


		for (int i = 1; i < rows; i++) {

			int[][] currentRow = new int[cols][cols];
			for (int[] row : currentRow) {
				Arrays.fill(row, Integer.MIN_VALUE);
			}

			for (int aliceCol = 0; aliceCol < cols; aliceCol++) {
				for (int bobCol = 0; bobCol < cols; bobCol++) {
					for (int aliceDy = -1; aliceDy <= 1; aliceDy++) {
						for (int bobDy = -1; bobDy <= 1; bobDy++) {

							int newAliceCol = aliceCol + aliceDy;
							int newBobCol = bobCol + bobDy;

							if (newAliceCol > -1 && newAliceCol < cols && newBobCol > -1 && newBobCol < cols) {

								if (prevRow[aliceCol][bobCol] == Integer.MIN_VALUE) {
									continue;
								} else {
									int currentValue = prevRow[aliceCol][bobCol] + grid[i][newAliceCol] + grid[i][newBobCol];
									if (newAliceCol == newBobCol) {
										currentValue -= grid[i][newBobCol];
									}
									currentRow[newAliceCol][newBobCol] = Math.max(currentRow[newAliceCol][newBobCol], currentValue);
									// System.out.printf("from [%d, %d] to [%d, %d]: %d\n", aliceCol, bobCol, newAliceCol, newBobCol, currentRow[newAliceCol][newBobCol]);
								}
							}
						}
					}
				}
			}

			prevRow = currentRow;
		}

		int max = Integer.MIN_VALUE;
		for (int[] x : prevRow) {
			for (int y : x) {
				max = Math.max(max, y);
			}
		}
		return max;
	}
}
```
