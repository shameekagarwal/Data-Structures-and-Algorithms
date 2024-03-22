# Minimum Moves to Pick K Ones

- https://leetcode.com/problems/minimum-moves-to-pick-k-ones/
- observation 1 - 
  - total number of 1s we can pick = no of 1s in the array + max changes
  - that is why, question says maxChanges + sum(nums) >= k
- observation 2 - 
  - swap operation is preferred only for adjacent 1s - 1 move
  - otherwise, change operation will always yield better (or same) results - 2 moves - 1 change and 1 swap to dylan index
- observation 3 - 
  - if a number is at distance x from dylan index, x swaps are needed to collect it
- TODO - implementing this
- apparently, this one is similar - https://leetcode.com/problems/allocate-mailboxes/
