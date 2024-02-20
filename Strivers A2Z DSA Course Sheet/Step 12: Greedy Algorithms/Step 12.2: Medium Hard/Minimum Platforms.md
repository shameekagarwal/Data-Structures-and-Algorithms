# Minimum Platforms

- https://www.geeksforgeeks.org/problems/minimum-platforms-1587115620/1
- we sort the starting and ending times separately
- say we have a pointer for the start time and end time
- while the start pointer's time is less than end time, keep incrementing it
- this means till a train leaves, x platforms would already be occupied
- keep repeating this process
- this line in question - "At any given instance of time, same platform can not be used for both departure of a train and arrival of another train" is for equal time - assume this case - 
  ```
  arr - 0800 1000
  dep - 1000 1100
  ```
- we need 2 platforms - train b has to arrive at a different platform and train a has to leave from a different platform - this is why we use `arr[arrPtr] <= dep[depPtr]` and not `arr[arrPtr] < dep[depPtr]`
- complexity - O(NlogN) (for sorting)

```java
class Solution {

    static int findPlatform(int arr[], int dep[], int n) {

        arr = Arrays.copyOfRange(arr, 0, arr.length);
        dep = Arrays.copyOfRange(dep, 0, dep.length);

        Arrays.sort(arr);
        Arrays.sort(dep);

        int arrPtr = 0;
        int depPtr = 0;
        int maxNoOfPlatformsReqd = 0;
        int currentNoOfPlatforms = 0;

        while (arrPtr < arr.length) {

            while (arrPtr < arr.length && arr[arrPtr] <= dep[depPtr]) {
                currentNoOfPlatforms += 1;
                arrPtr += 1;
            }
            maxNoOfPlatformsReqd = Math.max(currentNoOfPlatforms, maxNoOfPlatformsReqd);

            depPtr += 1;
            currentNoOfPlatforms -= 1;
        }
        
        return maxNoOfPlatformsReqd;
    }
}
```
