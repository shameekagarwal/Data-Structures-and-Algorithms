# Points

- https://practice.geeksforgeeks.org/problems/operations-on-binary-min-heap
- _steps already present in notes_
- functions to implement - 
  - extract min
  - insert
  - **delete at given index**

# Starter

- parent, left child, right child, swap indices

```java
class MinHeap {
    
    int[] harr;
    int capacity;
    int heap_size;
    
    MinHeap(int cap) {
        heap_size = 0;
        capacity = cap;
        harr = new int[cap];
    }
    
    int parent(int i) { return (i - 1) / 2; }
    int left(int i) { return (2 * i + 1); }
    int right(int i) { return (2 * i + 2); }
    
    void swapIndices(int a, int b) {
        int tmp = harr[a];
        harr[a] = harr[b];
        harr[b] = tmp;
    }
}
```

# Shift Down

```java
void shiftDown(int elementToRePositionIdx) {
    
    while (true) {
        
        int maxOfTheThreeIdx = elementToRePositionIdx;
        
        if (left(elementToRePositionIdx) < heap_size) {
            if (harr[left(elementToRePositionIdx)] < harr[maxOfTheThreeIdx]) {
                maxOfTheThreeIdx = left(elementToRePositionIdx);
            }
        }
        if (right(elementToRePositionIdx) < heap_size) {
            if (harr[right(elementToRePositionIdx)] < harr[maxOfTheThreeIdx]) {
                maxOfTheThreeIdx = right(elementToRePositionIdx);
            }
        }
        
        if (maxOfTheThreeIdx == elementToRePositionIdx) break;
        
        swapIndices(elementToRePositionIdx, maxOfTheThreeIdx);
        elementToRePositionIdx = maxOfTheThreeIdx;
    }
}
```

# Shift Up

```java
void shiftUp(int elementToRePositionIdx) {
    while (elementToRePositionIdx > 0 && harr[parent(elementToRePositionIdx)] > harr[elementToRePositionIdx]) {
        swapIndices(elementToRePositionIdx, parent(elementToRePositionIdx));
        elementToRePositionIdx = parent(elementToRePositionIdx);
    }
}
```

# Extract Min

- make note of the two boundary cases being handled separately
- because even for size 1, there is no setting of last element to 1st position, so three cases are handled separately

```java
int extractMin()
{
    if (heap_size == 0) return -1;
    if (heap_size == 1) {
        int min = harr[0];
        heap_size -= 1;
        return min;
    }
    int min = harr[0];
    harr[0] = harr[heap_size - 1];
    heap_size -= 1;
    shiftDown(0);
    return min;
}
```

# Insert

```java
void insertKey(int k)
{
    heap_size += 1;
    harr[heap_size - 1] = k;
    shiftUp(heap_size - 1);
}
```

# Delete at Given Index

```java
void deleteKey(int i) 
{
    if (i >= heap_size || i < 0) return;
    if (heap_size == 1) {
      heap_size -= 1;
      return;
    }
    harr[i] = harr[heap_size - 1];
    heap_size -= 1;
    shiftDown(i);
    shiftUp(i);
}
```
