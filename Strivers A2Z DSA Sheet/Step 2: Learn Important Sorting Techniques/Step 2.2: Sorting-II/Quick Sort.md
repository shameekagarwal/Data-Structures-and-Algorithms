# Quick Sort

- https://www.codingninjas.com/studio/problems/quick-sort_5844
- pick a pivot
- place it in the right spot - 
	- place the smaller elements to the left of pivot
	- place the larger elements to the right of pivot
- then, recursively sort the right and left parts of the pivot
- another optimization here - to determine the left and right parts of the pivot, we do not use auxiliary arrays - we use O(1) space using pointers (unlike merge sort?)
- time complexity - O(n * log n)

```java
public class Solution {
	
	public static void quickSort(int[] input, int startIndex, int endIndex) {

		if (startIndex >= endIndex) return;
		
		int pivot = input[startIndex];
		
		int low = startIndex;
		int high = endIndex;

		while (low < high) {
			while ((low < endIndex) && (input[low] <= pivot)) {
				low += 1;
			}
			while ((high > startIndex) && (input[high] > pivot)) {
				high -= 1;
			}
			if (low < high) {
				swap(input, low, high);
			}
		}

		swap(input, high, startIndex);

		quickSort(input, startIndex, high - 1);
		quickSort(input, high + 1, endIndex);
	}

	private static void swap(int[] input, int low, int high) {
		int temp = input[low];
		input[low] = input[high];
		input[high] = temp;
	}
}
```
