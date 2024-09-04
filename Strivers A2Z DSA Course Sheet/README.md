# Strivers A2Z DSA Course Sheet

### Random Revision Generation Script

to help with revision, so that the topic etc is not revealed.  
run it inside the console tab after opening the a2z link.

```js
const script = async () => {

  const steps = await fetch("https://backend.takeuforward.org/api/sheets/double/strivers_a2z_sheet")
    .then(res => res.json());
  const sub_steps = steps.flatMap(step => step.sub_steps);
  const topics = sub_steps.flatMap(sub_step => sub_step.topics);
  const links = topics.map(topic => ({ leetcode: topic.lc_link, gfg: topic.gfg_link }));
  const randomized = links.map(value => ({ ...value, sort: Math.random() }))
    .sort((a, b) => a.sort - b.sort);
  
  const rows = randomized.map(link => `| | ${link.leetcode} | ${link.gfg} |`);
  const table = [`| completed | leetcode | gfg |`,
    `| --------- | -------- | -------- |`,
    ...rows];

  const formattedTable = table.reduce((a, b) => `${a}\n${b}`);
  console.log(formattedTable);
}

script();
```

- ✅ - done in 1 attempt
- ⚠️ - took multiple attempts
- ❌ - could not do / could not do optimally
- ❓ - could not think of the other approaches

| completed | leetcode | gfg |
| --------- | -------- | -------- |
| ✅ | null | https://bit.ly/3Np2R2H |
| ✅ | https://leetcode.com/problems/triangle/ | https://bit.ly/3c1H9Uf |
| ⚠️ | null | https://bit.ly/3Cbc5fz |
| ✅ | https://leetcode.com/problems/remove-duplicates-from-sorted-array/#:~:text=Input%3A%20nums%20%3D%20%5B0%2C,%2C%203%2C%20and%204%20respectively. | https://bit.ly/3w7b6ck |
| ✅ | https://leetcode.com/problems/combination-sum/ | https://bit.ly/3C9bJ91 |
| ✅ |  | https://bit.ly/3PA7mY0 |
| ✅ | https://leetcode.com/problems/longest-palindromic-subsequence/ | https://practice.geeksforgeeks.org/problems/longest-palindromic-subsequence-1612327878/1 |
| ✅ | https://leetcode.com/problems/binary-tree-inorder-traversal/ | https://bit.ly/3QzNDbU |
| ✅ | https://leetcode.com/problems/word-break/ | https://bit.ly/3bVT5XN |
| ✅ | null | https://bit.ly/3UVCR1U |
| ✅ | https://leetcode.com/problems/unique-paths-ii/ | https://bit.ly/3tNYlBU |
| ❌ | null | https://bit.ly/3SPGzbK |
| ❌ | https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/ | https://practice.geeksforgeeks.org/problems/preorder-to-postorder4423/1 |
| ✅ | https://leetcode.com/problems/course-schedule-ii/ | https://bit.ly/3w8ZBBl |
| ✅ | null | https://bit.ly/3XiRbmG |
| ✅ | https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/ | https://bit.ly/3Az1OJ3 |
| ✅ | https://leetcode.com/accounts/login/?next=/problems/find-the-celebrity/ | https://bit.ly/3dC9IIB |
| ❓ | https://leetcode.com/problems/candy/ | https://bit.ly/3h14wzm |
| ✅ | https://leetcode.com/problems/sudoku-solver/ | https://bit.ly/3PrA5hs |
| ✅ | https://www.spoj.com/problems/AGGRCOW/ | https://bit.ly/3rBuE5Z |
| ✅ | https://leetcode.com/problems/hand-of-straights/ | null |
| ⚠️ | https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/ | https://bit.ly/3wbFiTI |
| ❌ | null | https://bit.ly/3dsEbIK |
| ✅ | https://leetcode.com/problems/implement-trie-prefix-tree/ | https://bit.ly/3pqacEj |
| ❌ | https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/ | https://practice.geeksforgeeks.org/problems/number-of-ways-to-arrive-at-destination/1 |
| | https://leetcode.com/problems/number-of-enclaves/ | https://practice.geeksforgeeks.org/problems/number-of-enclaves/1 |
| | https://practice.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1 | https://bit.ly/3A6Ob1Y |
| | null | https://bit.ly/3glak75 |
| | null | https://bit.ly/3QJ0vwc |
| | https://leetcode.com/problems/expression-add-operators/ | https://bit.ly/3yPPZN3 |
| | https://leetcode.com/problems/diameter-of-binary-tree/ | https://bit.ly/3wcfG9l |
| | null | https://bit.ly/3Cf398N |
| | https://leetcode.com/problems/minimum-window-subsequence/ | https://bit.ly/3DnB4uZ |
| | https://leetcode.com/problems/number-of-distinct-islands-ii/ | https://practice.geeksforgeeks.org/problems/number-of-distinct-islands/1 |
| | null | https://bit.ly/3QSGvHz |
| | null | https://bit.ly/3D6d94w |
| | https://leetcode.com/problems/unique-paths/ | https://practice.geeksforgeeks.org/problems/number-of-unique-paths/0 |
| | https://leetcode.com/problems/palindrome-partitioning-ii/ | https://bit.ly/3QP6gsc |
| | https://leetcode.com/problems/longest-common-subsequence/ | https://practice.geeksforgeeks.org/problems/longest-common-subsequence-1587115620/1 |
| |  | https://bit.ly/3JWYj1P |
| | null | https://bit.ly/3TSbXXE |
| | null | https://bit.ly/3DqE0ac |
| | https://leetcode.com/problems/intersection-of-two-linked-lists/ | https://bit.ly/3pl5R4W |
| | https://leetcode.com/problems/task-scheduler/ | https://bit.ly/3h2Z92Z |
| | null | https://bit.ly/3gn5Soh |
| | https://leetcode.com/problems/implement-stack-using-queues/ | https://bit.ly/3PrRI0F |
| | null | https://bit.ly/3gn5Soh |
| | https://leetcode.com/problems/subsets-ii/ | https://bit.ly/3MlAvWF |
| | https://leetcode.com/problems/combination-sum-ii/ | https://bit.ly/3VgczZ2 |
| | https://leetcode.com/problems/assign-cookies/ | https://practice.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1 |
| | null | https://bit.ly/3Ceotvr |
| | https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/ | https://practice.geeksforgeeks.org/problems/city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/0/?category[]=Shortest%20Path&category[]=Shortest%20Path&page=1&query=category[]Shortest%20Pathpage1category[]Shortest%20Path |
| | https://leetcode.com/problems/binary-tree-inorder-traversal/ | https://bit.ly/3tJQiGk |
| | https://leetcode.com/problems/boundary-of-binary-tree/ | https://bit.ly/3AsfgOK |
| | https://practice.geeksforgeeks.org/problems/top-view-of-binary-tree/1 | https://bit.ly/3PsvE5T |
| | null | https://www.codingninjas.com/codestudio/problems/ceiling-in-a-sorted-array_1825401 |
| | null | https://bit.ly/3ppA6YJ |
| | https://bit.ly/3ocRQW0 | https://practice.geeksforgeeks.org/problems/count-of-distinct-substrings/1 |
| | https://leetcode.com/problems/maximal-rectangle/ | https://practice.geeksforgeeks.org/problems/max-rectangle/1 |
| | https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/ | https://bit.ly/3K4mwn5 |
| | https://leetcode.com/problems/longest-happy-prefix/ | https://bit.ly/3ptCpKi |
| | https://leetcode.com/problems/rotting-oranges/ | https://bit.ly/3R4htWb |
| | https://leetcode.com/problems/valid-parentheses/ | https://bit.ly/3An2IIo |
| |  | https://bit.ly/3URZnst |
| | null | https://practice.geeksforgeeks.org/problems/java-if-else-decision-making0924/0?category%5B%5D=Java&category%5B%5D=Java&difficulty%5B%5D=-2&page=1&query=category%5B%5DJavadifficulty%5B%5D-2page1category%5B%5DJava |
| | https://leetcode.com/problems/remove-outermost-parentheses/ | https://bit.ly/3rzwRix |
| | https://bit.ly/3qwT4OL | https://bit.ly/3SUAiwJ |
| | null | https://bit.ly/3zWNyrL |
| | https://www.interviewbit.com/problems/subarray-with-given-xor/ | https://bit.ly/3PrvhZs |
| | https://leetcode.com/problems/find-a-peak-element-ii/ | https://bit.ly/3pEovIA |
| | https://leetcode.com/problems/longest-string-chain/ | https://bit.ly/3TSqOkS |
| | https://leetcode.com/problems/copy-list-with-random-pointer/ | https://bit.ly/3PpM6np |
| | https://leetcode.com/problems/n-queens/ | https://bit.ly/3QurLP8 |
| | https://leetcode.com/problems/count-number-of-nice-subarrays/ | https://bit.ly/3xZvUUc |
| | null | https://bit.ly/3Amcomr |
| | https://leetcode.com/problems/coin-change-2/ | https://bit.ly/3SZJWOl |
| | null | https://bit.ly/3zuBr66 |
| | https://leetcode.com/problems/reverse-linked-list/ | https://bit.ly/3zY03mT |
| | https://leetcode.com/problems/edit-distance/ | https://practice.geeksforgeeks.org/problems/edit-distance3702/1 |
| | https://leetcode.com/problems/kth-smallest-element-in-a-bst/ | https://practice.geeksforgeeks.org/problems/find-k-th-smallest-element-in-bst/1#:~:text=Find%20the%20Kth%20Smallest%20element%20in%20the%20BST.&text=Your%20Task%3A,such%20element%20exists%20return%20%2D1. |
| | null | https://bit.ly/3T8xUCf |
| | https://leetcode.com/problems/implement-strstr/ | https://practice.geeksforgeeks.org/problems/search-pattern0205/1 |
| | null | https://bit.ly/3Cgg36D |
| | null | https://bit.ly/3Et6alk |
| | https://leetcode.com/problems/sum-of-subarray-minimums/ | https://bit.ly/3yS5XpZ |
| | https://leetcode.com/problems/next-greater-element-ii/ | https://bit.ly/3DLe7TX |
| | https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/ | https://bit.ly/3Ep4Tfo |
| |  | https://bit.ly/3T0gZ4P |
| | https://leetcode.com/problems/count-good-numbers/ | https://bit.ly/3w6SucH |
| | https://leetcode.com/problems/frequency-of-the-most-frequent-element/ | https://bit.ly/3wcIHla |
| | https://leetcode.com/problems/01-matrix/ | https://practice.geeksforgeeks.org/problems/distance-of-nearest-cell-having-1-1587115620/1 |
| | https://leetcode.com/problems/implement-strstr/ | https://practice.geeksforgeeks.org/problems/8dcd25918295847b4ced54055eae35a8501181c1/1 |
| | https://leetcode.com/problems/armstrong-number/ | https://practice.geeksforgeeks.org/problems/armstrong-numbers2727/1 |
| | null | https://bit.ly/3dEvWJD |
| | null | https://bit.ly/3EsRmTM |
| | null | https://practice.geeksforgeeks.org/problems/while-loop-printtable-java/1 |
| | https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/ | https://bit.ly/3DxaMXw |
| | https://leetcode.com/problems/binary-tree-postorder-traversal/ | https://bit.ly/3Cb5Wjz |
| | https://leetcode.com/problems/split-array-largest-sum/ | https://bit.ly/3Qx0RGs |
| | https://leetcode.com/problems/combination-sum-iii/ | https://bit.ly/3CMZFul |
| | null | https://practice.geeksforgeeks.org/problems/minimum-spanning-tree/1 |
| | https://www.interviewbit.com/problems/maximum-sum-combinations/ | https://bit.ly/3sSXvTT |
| | null | https://practice.geeksforgeeks.org/problems/java-switch-case-statement3529/1 |
| | null | https://bit.ly/3weGgP3 |
| | https://leetcode.com/problems/kth-largest-element-in-a-stream/#:~:text=Implement%20KthLargest%20class%3A,largest%20element%20in%20the%20stream. | https://bit.ly/3JZPqEV |
| | https://practice.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1 | https://practice.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=dfs_of_graph |
| | null | https://bit.ly/3UVQD4C |
| | https://leetcode.com/problems/delete-node-in-a-bst/ | https://practice.geeksforgeeks.org/problems/delete-a-node-from-bst/1 |
| | null | https://bit.ly/3Ap7Onp |
| | https://leetcode.com/problems/path-with-minimum-effort/ | https://bit.ly/3OpIfbh |
| | https://leetcode.com/problems/odd-even-linked-list/ | https://bit.ly/3A3aeqe |
| | https://leetcode.com/problems/subsets/ | https://practice.geeksforgeeks.org/problems/power-set4302/1 |
| | null | https://bit.ly/3wcg7k1 |
| | https://leetcode.com/problems/target-sum/ | https://practice.geeksforgeeks.org/problems/target-sum-1626326450/1 |
| | https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/ | https://practice.geeksforgeeks.org/problems/minimum-sum-partition3317/1 |
| | null | https://bit.ly/3TbvByL |
| | https://leetcode.com/problems/reverse-nodes-in-k-group/ | https://bit.ly/3zWPiBj |
| | https://leetcode.com/problems/count-primes/ | https://bit.ly/3fduYVT |
| | https://leetcode.com/problems/wildcard-matching/ | https://practice.geeksforgeeks.org/problems/wildcard-pattern-matching/1 |
| | null | https://bit.ly/3C9GQRS |
| | null | https://bit.ly/3PtLWLM |
| | https://leetcode.com/problems/rotate-array/ | https://bit.ly/3dnn9vC |
| | https://leetcode.com/problems/delete-operation-for-two-strings/ | https://practice.geeksforgeeks.org/problems/minimum-number-of-deletions-and-insertions0209/1 |
| | https://leetcode.com/problems/single-number/ | https://practice.geeksforgeeks.org/problems/find-the-odd-occurence4820/1 |
| | https://leetcode.com/problems/find-eventual-safe-states/ | https://practice.geeksforgeeks.org/problems/eventual-safe-states/1 |
| | null | https://bit.ly/3CgDDjE |
| | https://leetcode.com/problems/minimum-bit-flips-to-convert-number/ | https://practice.geeksforgeeks.org/problems/bit-difference-1587115620/1 |
| | https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/ | https://bit.ly/3QytIu6 |
| | null | https://practice.geeksforgeeks.org/problems/minimum-spanning-tree/1 |
| | null | https://bit.ly/3QV65vI |
| | null | https://practice.geeksforgeeks.org/problems/topological-sort/1 |
| | https://leetcode.com/problems/palindrome-linked-list/ | https://bit.ly/3JVrnXJ |
| | null | https://bit.ly/3dtFqHG |
| | https://leetcode.com/problems/longest-consecutive-sequence/solution/ | https://bit.ly/3ApytAD |
| | https://leetcode.com/problems/string-to-integer-atoi/ | https://bit.ly/3QUHBmf |
| | https://leetcode.com/problems/implement-queue-using-stacks/ | https://bit.ly/3Quthki |
| | https://leetcode.com/problems/koko-eating-bananas/ | https://bit.ly/3LSY491 |
| | https://leetcode.com/problems/binary-tree-inorder-traversal/ | https://bit.ly/3EORTB9 |
| | https://leetcode.com/problems/search-insert-position/#:~:text=Search%20Insert%20Position%20%2D%20LeetCode&text=Given%20a%20sorted%20array%20of,(log%20n)%20runtime%20complexity. | https://bit.ly/3pFDbUN |
| | null | https://bit.ly/3bZqbGc |
| | https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/ | https://practice.geeksforgeeks.org/problems/maximum-xor-of-two-numbers-in-an-array/0 |
| | https://leetcode.com/problems/is-graph-bipartite/ | https://practice.geeksforgeeks.org/problems/bipartite-graph/1 |
| | https://leetcode.com/problems/flatten-binary-tree-to-linked-list/ | https://bit.ly/3PFzwko |
| | https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/ | https://bit.ly/3CJ9Ezm |
| | null | https://bit.ly/3Pxf84L |
| | https://leetcode.com/problems/accounts-merge/ | https://practice.geeksforgeeks.org/problems/merging-details/1 |
| | https://leetcode.com/problems/insert-into-a-binary-search-tree/ | https://practice.geeksforgeeks.org/problems/insert-a-node-in-a-bst/1 |
| | null | https://bit.ly/3QNDw2W |
| | null | https://bit.ly/3TS3jcg |
| | https://leetcode.com/problems/binary-search-tree-iterator/ | https://practice.geeksforgeeks.org/problems/merge-two-bst-s/1 |
| | https://leetcode.com/problems/trapping-rain-water/ | https://bit.ly/3AqMa1W |
| | null | null |
| | https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/ | https://practice.geeksforgeeks.org/problems/form-a-palindrome1455/1 |
| | https://leetcode.com/problems/maximum-subarray/ | https://bit.ly/3dzyloY |
| | null | https://practice.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1 |
| | https://leetcode.com/problems/reverse-linked-list/ | https://bit.ly/3zY03mT |
| | null | https://bit.ly/3US225G |
| | null | https://bit.ly/3w9TKf8 |
| | https://leetcode.com/problems/sort-characters-by-frequency/ | https://bit.ly/3dEQp1d |
| | null | https://practice.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=longest-sub-array-with-sum-k |
| | https://leetcode.com/problems/recover-binary-search-tree/ | https://bit.ly/3PBJwea |
| | null | https://bit.ly/3QD4hHs |
| | null | https://practice.geeksforgeeks.org/problems/search-query-auto-complete/0?category%5B%5D=Strings&category%5B%5D=Strings&problemStatus=unsolved&difficulty%5B%5D=2&page=1&query=category%5B%5DStringsproblemStatusunsolveddifficulty%5B%5D2page1category%5B%5DStr |
| | https://practice.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1 | https://bit.ly/3Asftl0 |
| | https://leetcode.com/problems/minimum-cost-to-cut-a-stick/ | https://bit.ly/3K3ItCE |
| | null | https://practice.geeksforgeeks.org/problems/find-all-factorial-numbers-less-than-or-equal-to-n3548/0?problemType=functional&difficulty%5B%5D=-1&page=1&query=problemTypefunctionaldifficulty%5B%5D-1page1 |
| | https://practice.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1 | https://bit.ly/3PsqOFL |
| | null | https://bit.ly/3A30Anw |
| | null | https://practice.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=longest-sub-array-with-sum-k |
| |  | https://bit.ly/3FwxK3j |
| |  | https://bit.ly/3DYCIFb |
| | null | https://bit.ly/3Dqmgvx |
| | null | https://bit.ly/3LOkcBn |
| | https://leetcode.com/problems/max-consecutive-ones-iii/ | https://bit.ly/3RjRcCO |
| | https://leetcode.com/problems/delete-node-in-a-linked-list/ | https://bit.ly/3Apg5aX |
| | https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/ | https://practice.geeksforgeeks.org/problems/lowest-common-ancestor-in-a-bst/1 |
| | https://leetcode.com/problems/max-consecutive-ones/ | https://bit.ly/3piuaAN |
| | https://leetcode.com/problems/inorder-successor-in-bst/ | https://practice.geeksforgeeks.org/problems/predecessor-and-successor/1 |
| | null | https://bit.ly/3podAiY |
| | https://practice.geeksforgeeks.org/problems/m-coloring-problem-1587115620/1# | https://bit.ly/3Cepqnv |
| | https://leetcode.com/problems/longest-substring-without-repeating-characters/ | https://bit.ly/3wegomm |
| | https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/ | https://practice.geeksforgeeks.org/problems/count-the-reversals0401/1 |
| | https://leetcode.com/problems/best-time-to-buy-and-sell-stock/ | https://bit.ly/3Qrx6H9 |
| | https://bit.ly/3n3kedU | https://bit.ly/3Qx5AYB |
| | null | https://bit.ly/3C165p8 |
| | https://practice.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1# | https://bit.ly/3Pttpzj |
| | null | https://bit.ly/3JXtGcE |
| | https://leetcode.com/problems/powx-n/ | https://practice.geeksforgeeks.org/problems/power-of-numbers-1587115620/1?utm_source=youtube&amp;utm_medium=collab_striver_ytdescription&amp;utm_campaign=power-of-numbers |
| | https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/ | https://bit.ly/3T12HRu |
| | https://leetcode.com/problems/binary-tree-preorder-traversal/ | https://bit.ly/3Cb4VIh |
| | https://leetcode.com/problems/valid-anagram/#:~:text=Given%20two%20strings%20s%20and,the%20original%20letters%20exactly%20once.&text=Constraints%3A,.length%20%3C%3D%205%20*%2010 | https://bit.ly/3CcQTWs |
| | https://leetcode.com/problems/alien-dictionary/solution/ | https://practice.geeksforgeeks.org/problems/alien-dictionary/1 |
| | https://leetcode.com/problems/minimum-falling-path-sum/ | https://bit.ly/3PwqvtO |
| | https://leetcode.com/problems/power-of-two/ | https://practice.geeksforgeeks.org/problems/power-of-2-1587115620/1 |
| | https://practice.geeksforgeeks.org/problems/minimum-platforms-1587115620/1# | https://bit.ly/3bXX5Hm |
| |  | https://bit.ly/3QPJ39w |
| | null | https://bit.ly/3gprRLk |
| | https://leetcode.com/problems/insert-interval/ | https://bit.ly/3VeNtc1 |
| | null | https://bit.ly/3piCTD3 |
| | null | https://bit.ly/3UWkWrS |
| | null | https://bit.ly/3FtJUtZ |
| | null | https://practice.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/1 |
| | https://leetcode.com/problems/same-tree/ | https://bit.ly/3c2K52Z |
| | https://leetcode.com/problems/next-greater-element-i/ | https://bit.ly/3QNPQAs |
| | https://leetcode.com/problems/move-zeroes/ | https://bit.ly/3PrGIjT |
| | https://leetcode.com/problems/lfu-cache/ | https://bit.ly/3fpYDLQ |
| | https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/ | https://bit.ly/3pqu1er |
| | https://leetcode.com/problems/count-and-say/ | https://bit.ly/3AFdLwR |
| | null | https://bit.ly/3Pu0YBn |
| | null | https://bit.ly/3QSGvHz |
| | https://leetcode.com/problems/binary-tree-level-order-traversal/ | https://bit.ly/3K6t9VR |
| | https://leetcode.com/problems/maximal-rectangle/ | https://bit.ly/3C6BF5p |
| | null | https://practice.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1 |
| | https://leetcode.com/problems/shortest-palindrome/ | https://bit.ly/3PxCaIE |
| | null | https://bit.ly/3PxAWx1 |
| | null | https://bit.ly/3Eo1mhq |
| | https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/ | https://practice.geeksforgeeks.org/problems/buy-and-sell-a-share-at-most-twice/1 |
| | null | https://practice.geeksforgeeks.org/problems/odd-or-even3618/1 |
| | null | https://practice.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=detect-cycle-in-an-undirected-graph |
| | https://leetcode.com/problems/min-stack/ | https://bit.ly/3JVhFoe |
| | https://leetcode.com/problems/reverse-pairs/ | https://bit.ly/3SVIpJ6 |
| | null | https://bit.ly/3Xn0Kkw |
| | https://leetcode.com/problems/jump-game/ | https://bit.ly/3AbFYKe |
| | https://leetcode.com/problems/longest-common-prefix/ | https://bit.ly/3QKCyVu |
| | null | null |
| | https://leetcode.com/problems/binary-search/ | https://bit.ly/3QtKBpO |
| | null | https://bit.ly/3SLFFhs |
| | https://leetcode.com/problems/minimum-path-sum/ | https://bit.ly/3c1H9Uf |
| | https://leetcode.com/problems/maximum-xor-with-an-element-from-array/ | https://bit.ly/3tNYB3Y |
| | null | https://bit.ly/3GyNRya |
| | https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/ | https://bit.ly/3gkV5en |
| | https://leetcode.com/problems/non-overlapping-intervals/ | https://bit.ly/3UM6liN |
| | null | https://bit.ly/3y2BiWz |
| | null | https://practice.geeksforgeeks.org/problems/swap-two-numbers3844/1 |
| | https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/ | https://bit.ly/3SJogFR |
| | https://leetcode.com/problems/distinct-subsequences/ | https://practice.geeksforgeeks.org/problems/number-of-distinct-subsequences0909/1 |
| | https://leetcode.com/problems/median-of-two-sorted-arrays/ | https://bit.ly/3QPi7Xp |
| | null | https://practice.geeksforgeeks.org/problems/lcm-and-gcd4516/1 |
| | null | https://bit.ly/3w6hUaa |
| | https://leetcode.com/problems/number-of-operations-to-make-network-connected/ | https://bit.ly/3Esuzrd |
| | https://leetcode.com/problems/sort-colors/ | https://bit.ly/3dsROaZ |
| | https://leetcode.com/problems/sliding-window-maximum/ | https://bit.ly/3w7SSri |
| | null | https://bit.ly/3zWZoCs |
| | https://leetcode.com/problems/shortest-common-supersequence/ | https://practice.geeksforgeeks.org/problems/shortest-common-supersequence0322/1 |
| | https://leetcode.com/problems/remove-nth-node-from-end-of-list/ | https://bit.ly/3pobNKM |
| | https://leetcode.com/problems/cheapest-flights-within-k-stops/ | https://bit.ly/3OnPWi4 |
| | https://leetcode.com/problems/count-square-submatrices-with-all-ones/ | https://bit.ly/3UW7u7o |
| | https://leetcode.com/problems/jump-game-ii/ | https://bit.ly/3Av2ezM |
| |  | https://bit.ly/3dyXL6m |
| | https://leetcode.com/problems/house-robber-ii/ | https://bit.ly/3QCCpDQ |
| | null | https://bit.ly/3QAEsrY |
| | null | https://practice.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1 |
| | https://leetcode.com/problems/word-search/ | https://bit.ly/3Ca9xOL |
| | https://leetcode.com/problems/sum-of-beauty-of-all-substrings/ | https://bit.ly/3LRh089 |
| | https://leetcode.com/problems/swim-in-rising-water/ | https://bit.ly/3VftZ7i |
| | https://leetcode.com/problems/fibonacci-number/ | https://bit.ly/3QUkkk2 |
| | https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/ | https://bit.ly/3GycvyA |
| | https://leetcode.com/problems/lemonade-change/ | https://bit.ly/3DoFGRx |
| | https://leetcode.com/problems/missing-number/ | https://bit.ly/3A2pKTh |
| | https://leetcode.com/problems/sort-list/ | https://bit.ly/3dDuLdO |
| | null | https://bit.ly/3Cgg36D |
| | https://leetcode.com/problems/subarray-sum-equals-k/ | https://www.codingninjas.com/codestudio/problems/subarray-sums-i_1467103?utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_Arrayproblems |
| | null | https://bit.ly/3T1Va4U |
| | null | https://bit.ly/3gkAM0s |
| | https://leetcode.com/problems/valid-parenthesis-string/ | https://bit.ly/3K6ulZA |
| | null | null |
| | https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/ | https://bit.ly/3xYlR1F |
| | https://leetcode.com/problems/repeated-string-match/discuss/416144/Rabin-Karp-algorithm-C%2B%2B-implementation | https://practice.geeksforgeeks.org/problems/31272eef104840f7430ad9fd1d43b434a4b9596b/1 |
| | https://leetcode.com/problems/kth-missing-positive-number/#:~:text=Given%20an%20array%20arr%20of,13%2C...%5D. | https://bit.ly/3bUFY9l |
| | null | https://bit.ly/3PvwuPk |
| | https://leetcode.com/problems/minimum-window-substring/ | https://practice.geeksforgeeks.org/problems/smallest-window-in-a-string-containing-all-the-characters-of-another-string-1587115621/1 |
| | null | https://bit.ly/3AbFrrI |
| | https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/ | https://practice.geeksforgeeks.org/problems/maximum-profit4657/1 |
| | https://leetcode.com/problems/linked-list-cycle/ | https://bit.ly/3QN8PLn |
| |  | https://bit.ly/3sNZ1a2 |
| | null | https://bit.ly/3T3oW9M |
| | null | https://bit.ly/3Pld280 |
| | https://leetcode.com/problems/partition-array-for-maximum-sum/ | https://bit.ly/3AUSIGj |
| | https://leetcode.com/problems/reverse-words-in-a-string/ | https://practice.geeksforgeeks.org/problems/reverse-words-in-a-given-string5459/1 |
| | https://leetcode.com/problems/rotate-array/ | https://bit.ly/3dyCKZg |
| | https://leetcode.com/problems/merge-k-sorted-lists/ | https://bit.ly/3QObTXC |
| | null | https://practice.geeksforgeeks.org/problems/print-1-to-n-without-using-loops-1587115620/1 |
| | https://leetcode.com/problems/remove-k-digits/ | https://bit.ly/3pl6taP |
| | null | https://bit.ly/3flmw7c |
| | https://leetcode.com/problems/single-element-in-a-sorted-array/ | https://bit.ly/3AllLTj |
| | https://leetcode.com/problems/asteroid-collision/ | https://bit.ly/3Tpi0Tw |
| | https://leetcode.com/problems/partition-equal-subset-sum/ | https://practice.geeksforgeeks.org/problems/subset-sum-problem2014/1 |
| | null | https://practice.geeksforgeeks.org/problems/check-whether-k-th-bit-is-set-or-not-1587115620/1 |
| | https://leetcode.com/problems/word-ladder/ | https://practice.geeksforgeeks.org/problems/word-ladder/1 |
| | null | https://bit.ly/3PvAOhK |
| |  | https://bit.ly/3w6PwVv |
| | null | https://bit.ly/3K2epHv |
| | null | https://practice.geeksforgeeks.org/problems/count-palindromic-subsequences/1#:~:text=Given%20a%20string%20str%20of,formed%20from%20the%20string%20str.&text=Your%20Task%3A,read%20input%20or%20print%20anything. |
| | https://leetcode.com/problems/merge-sorted-array/ | https://bit.ly/3zRzmAo |
| | https://leetcode.com/problems/isomorphic-strings/ | https://bit.ly/3QwynwI |
| | https://leetcode.com/problems/number-of-provinces/#:~:text=A%20province%20is%20a%20group,the%20total%20number%20of%20provinces. | https://practice.geeksforgeeks.org/problems/number-of-provinces/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=number_of_provinces |
| | https://leetcode.com/problems/online-stock-span/ | https://bit.ly/3dzHopW |
| | https://leetcode.com/problems/kth-largest-element-in-an-array/ | https://bit.ly/3Avss5k |
| | https://leetcode.com/problems/largest-divisible-subset/ | https://bit.ly/3hYsWKC |
| | https://leetcode.com/problems/maximum-product-subarray/ | https://bit.ly/3PrlQsU |
| | https://leetcode.com/problems/pascals-triangle/ | https://bit.ly/3C95qlR |
| | null | https://practice.geeksforgeeks.org/problems/minimum-element-in-bst/1 |
| |  | https://bit.ly/3V9wY1v |
| | https://leetcode.com/problems/surrounded-regions/ | https://bit.ly/3T3oDLR |
| | https://leetcode.com/problems/binary-subarrays-with-sum/ | https://bit.ly/3S8xsCG |
| | https://leetcode.com/problems/subarrays-with-k-different-integers/ | https://bit.ly/3DLRtee |
| | https://leetcode.com/problems/balanced-binary-tree/ | https://bit.ly/3K9YbfB |
| | https://leetcode.com/problems/serialize-and-deserialize-binary-tree/ | https://bit.ly/3PqPDSE |
| | https://leetcode.com/problems/longest-palindromic-substring/ | https://bit.ly/3CeQ27D |
| | null | https://bit.ly/3AwVr8I |
| | https://leetcode.com/problems/house-robber/ | https://bit.ly/3QOMRs1 |
| | https://leetcode.com/problems/count-complete-tree-nodes/ | https://bit.ly/3EoPFXM |
| | https://leetcode.com/problems/parsing-a-boolean-expression/ | https://bit.ly/3QFAe2o |
| | https://leetcode.com/problems/validate-binary-search-tree/ | https://bit.ly/3poHDqN |
| | null | https://practice.geeksforgeeks.org/problems/count-total-set-bits-1587115620/1 |
| | null | https://bit.ly/3T2LPKu |
| | https://leetcode.com/problems/design-twitter/ | https://practice.geeksforgeeks.org/problems/design-twitter/1 |
| |  | https://bit.ly/3Epriup |
| | null | https://bit.ly/3QVb1jR |
| | https://leetcode.com/problems/lru-cache/ | https://bit.ly/3zZneNN |
| | null | https://practice.geeksforgeeks.org/problems/count-digits5716/1 |
| | https://leetcode.com/problems/majority-element/ | https://bit.ly/3SSpeA3 |
| | null | https://practice.geeksforgeeks.org/problems/minimum-number-of-jumps-1587115620/1 |
| | https://leetcode.com/problems/making-a-large-island/ | https://bit.ly/3i1yOmk |
| | null | https://bit.ly/3gpY19t |
| | https://leetcode.com/problems/shortest-path-in-binary-matrix/ | https://bit.ly/3ArrkzD |
| | https://leetcode.com/problems/divide-two-integers/ | https://practice.geeksforgeeks.org/problems/division-without-using-multiplication-division-and-mod-operator/0 |
| | null | https://bit.ly/3KcpHcB |
| | null | https://practice.geeksforgeeks.org/problems/longest-common-substring1452/1 |
| | null | https://bit.ly/3Eo8JVW |
| | https://leetcode.com/problems/climbing-stairs/ | https://practice.geeksforgeeks.org/problems/count-ways-to-reach-the-nth-stair-1587115620/1 |
| | https://leetcode.com/problems/top-k-frequent-elements/ | https://bit.ly/3wcIHla |
| | null | https://practice.geeksforgeeks.org/problems/sum-of-first-n-terms5843/1 |
| | https://leetcode.com/problems/number-of-longest-increasing-subsequence/ | https://bit.ly/3EohD60 |
| | https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1 | https://bit.ly/3pkqK0g |
| | https://leetcode.com/problems/two-sum/ | https://bit.ly/3SVYU8f |
| | https://leetcode.com/problems/sum-of-subarray-ranges/ | https://bit.ly/3SdfheS |
| | https://leetcode.com/problems/palindrome-partitioning/ | https://bit.ly/3T63roP |
| | https://leetcode.com/problems/rotate-list/description/ | https://bit.ly/3JXvemY |
| | https://leetcode.com/problems/single-number/ | https://bit.ly/3dudCD8 |
| | https://leetcode.com/problems/maximum-depth-of-binary-tree/ | https://bit.ly/3CitiDM |
| |  | https://bit.ly/3w9pEIt |
| |  | https://bit.ly/3Po7tpf |
| | null | null |
| | null | https://practice.geeksforgeeks.org/problems/all-divisors-of-a-number/1?utm_source=youtube&amp;utm_medium=collab_striver_ytdescription&amp;utm_campaign=all-divisors-of-a-number |
| |  | https://bit.ly/3C9HLli |
| | https://leetcode.com/problems/merge-intervals/ | https://bit.ly/3K6pRlt |
| | null | https://practice.geeksforgeeks.org/problems/implementing-ceil-in-bst/1 |
| | https://leetcode.com/problems/search-in-a-binary-search-tree/ | https://practice.geeksforgeeks.org/problems/search-a-node-in-bst/1 |
| | null | https://bit.ly/3QLpaAs |
| | null | https://practice.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=detect-cycle-in-an-undirected-graph |
| | https://leetcode.com/problems/linked-list-cycle-ii/ | https://bit.ly/3zuBr66 |
| | https://leetcode.com/problems/4sum/ | https://bit.ly/3psQR53 |
| | https://leetcode.com/problems/burst-balloons/ | https://bit.ly/3A9LdKp |
| | https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/ | https://bit.ly/3PBInmS |
| | null | null |
| | null | https://bit.ly/3TSxAHd |
| | https://leetcode.com/problems/palindrome-number/ | https://practice.geeksforgeeks.org/problems/palindrome0746/1 |
| | null | https://practice.geeksforgeeks.org/problems/minimum-spanning-tree/1 |
| | https://leetcode.com/problems/largest-odd-number-in-string/ | https://bit.ly/3UII5yp |
| | null | https://bit.ly/3CfQfYi |
| | https://leetcode.com/problems/binary-tree-preorder-traversal/ | https://bit.ly/3Cb4VIh |
| | null | https://bit.ly/3w5QSzC |
| | https://leetcode.com/problems/word-ladder-ii/ | https://practice.geeksforgeeks.org/problems/word-ladder-ii/1 |
| | https://leetcode.com/problems/powx-n/ | https://bit.ly/3QLc6uD |
| | https://leetcode.com/problems/find-median-from-data-stream/ | https://bit.ly/3QIknj4 |
| | https://leetcode.com/problems/network-delay-time/ | https://bit.ly/3pr6ObZ |
| | null | https://practice.geeksforgeeks.org/problems/largest-bst/1 |
| |  | https://bit.ly/3UaWJxk |
| | https://leetcode.com/problems/next-permutation/ | https://bit.ly/3dug78u |
| | null | https://practice.geeksforgeeks.org/problems/two-numbers-with-odd-occurrences5846/1 |
| | null | https://practice.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1 |
| | https://leetcode.com/problems/3sum/ | https://bit.ly/3C9uTvQ |
| | null | https://practice.geeksforgeeks.org/problems/set-the-rightmost-unset-bit4436/1 |
| | null | https://www.youtube.com/watch?v=RRVYpIET_RU |
| | null | https://bit.ly/3dGuwyv |
| |  | https://bit.ly/3DrwCLI |
| | https://www.interviewbit.com/problems/nearest-smaller-element/ | https://bit.ly/3wbhcIL |
| | null | https://bit.ly/3SVcOqW |
| | https://leetcode.com/problems/binary-tree-maximum-path-sum/ | https://bit.ly/3QAFjbW |
| | https://leetcode.com/problems/generate-parentheses/ | https://bit.ly/3K5keUw |
| | https://bit.ly/3MZQOct | https://bit.ly/3QMrMxP |
| | https://leetcode.com/problems/middle-of-the-linked-list/ | https://bit.ly/3dAjkn1 |
| | https://leetcode.com/problems/binary-tree-postorder-traversal/ | https://bit.ly/3SZauQ4 |
| | null | https://bit.ly/3pFvBcN |
| | null | https://bit.ly/3AxzhDG |
| | https://leetcode.com/problems/largest-rectangle-in-histogram/ | https://bit.ly/3AnVYd5 |
| | https://leetcode.com/problems/find-peak-element/#:~:text=Find%20Peak%20Element%20%2D%20LeetCode&text=A%20peak%20element%20is%20an,to%20any%20of%20the%20peaks. | https://bit.ly/3Apsuf3 |
| | https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/ | https://bit.ly/3AxzhDG |
| | https://leetcode.com/problems/search-in-rotated-sorted-array-ii/ | https://bit.ly/3Rm85Nb |
| | https://leetcode.com/problems/course-schedule-ii/discuss/293048/detecting-cycle-in-directed-graph-problem | https://practice.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1 |
| | null | https://bit.ly/3QlEoMx |
| | https://leetcode.com/problems/spiral-matrix/ | https://bit.ly/3ppEJ53 |
| | https://leetcode.com/problems/best-time-to-buy-and-sell-stock/ | https://practice.geeksforgeeks.org/problems/stock-buy-and-sell-1587115621/1 |
| | https://leetcode.com/problems/string-to-integer-atoi/ | https://bit.ly/3K4ySLV |
| | https://leetcode.com/problems/letter-combinations-of-a-phone-number/ | https://bit.ly/3K1INBO |
| | null | https://bit.ly/3w6yQx8 |
| | https://practice.geeksforgeeks.org/problems/bfs-traversal-of-graph/1 | https://practice.geeksforgeeks.org/problems/bfs-traversal-of-graph/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=bfs_of_graph |
| | https://leetcode.com/problems/add-two-numbers/ | https://bit.ly/3JZlGb7 |
| | https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/ | https://bit.ly/3popvgG |
| | null | null |
| | https://leetcode.com/problems/number-of-islands-ii/ | https://bit.ly/3A7BofH |
| | https://leetcode.com/problems/rotate-image/ | https://bit.ly/3PqpeV8 |
| | https://leetcode.com/problems/reverse-integer/ | https://practice.geeksforgeeks.org/problems/reverse-bits3556/1 |
| | null | https://practice.geeksforgeeks.org/problems/reverse-an-array/0 |
| | https://leetcode.com/problems/assign-cookies/ | https://bit.ly/3Wl3PBd |
| | https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/ | https://bit.ly/3xVYTZa |
| | https://leetcode.com/problems/coin-change/ | https://practice.geeksforgeeks.org/problems/-minimum-number-of-coins4426/1 |
| | null | https://bit.ly/3dEr73g |
| | https://leetcode.com/problems/symmetric-tree/ | https://bit.ly/3PCqBzP |
| | null | https://practice.geeksforgeeks.org/problems/sum-of-all-divisors-from-1-to-n4738/1 |
| | https://leetcode.com/problems/subsets/ | https://bit.ly/3STXz1t |
| | https://leetcode.com/problems/merge-intervals/ | https://bit.ly/3zRz904 |
| | https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/ | https://bit.ly/3AoZ4N5 |
| | https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/ | https://bit.ly/3AwUkpe |
| | https://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/ | https://bit.ly/3Ka7xYU |
| | https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/#:~:text=Input%3A%20nums%20%3D%20%5B2%2C,no%20rotation)%20to%20make%20nums. | https://bit.ly/3Ap9U6F |
| | null | https://bit.ly/3Vw4XB1 |
| | https://leetcode.com/problems/longest-increasing-subsequence/ | https://practice.geeksforgeeks.org/problems/longest-increasing-subsequence-1587115620/1 |
| | null | https://bit.ly/3U9k6XT |
| | null | https://bit.ly/3c690mm |
| | https://leetcode.com/problems/valid-palindrome/ | https://practice.geeksforgeeks.org/problems/palindrome-string0817/1 |
| | null | https://practice.geeksforgeeks.org/problems/frequency-of-array-elements-1587115620/0 |
| | https://leetcode.com/problems/rearrange-array-elements-by-sign/ | https://bit.ly/3Qr3sSs |
| | https://leetcode.com/problems/set-matrix-zeroes/ | https://bit.ly/3SVaSig |
| | https://leetcode.com/problems/minimize-max-distance-to-gas-station/ | https://bit.ly/3s40ybG |
| | https://leetcode.com/problems/binary-tree-right-side-view/ | https://bit.ly/3PCqyE9 |
| | null | https://bit.ly/3glc9kp |
| | https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/#:~:text=You%20are%20given%20the%20head,than%20or%20equal%20to%20x%20. | https://bit.ly/3QzoU7W |
| | https://leetcode.com/problems/search-a-2d-matrix-ii/ | https://www.codingninjas.com/codestudio/problems/search-in-a-row-wise-and-column-wise-sorted-matrix_839811 |
| | https://leetcode.com/problems/roman-to-integer/ | https://bit.ly/3AqBlNv |
| | https://leetcode.com/problems/binary-tree-postorder-traversal/ | https://bit.ly/3SZauQ4 |
| | https://leetcode.com/problems/search-a-2d-matrix/ | https://bit.ly/3dAwi3Z |
| | https://leetcode.com/problems/flood-fill/ | https://practice.geeksforgeeks.org/problems/flood-fill-algorithm1856/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=flood-fill-algorithm |
| | https://leetcode.com/problems/maximum-width-of-binary-tree/ | https://bit.ly/3dxUfZP |
| | https://leetcode.com/problems/two-sum-iv-input-is-a-bst/ | https://practice.geeksforgeeks.org/problems/find-a-pair-with-given-target-in-bst/1 |
| | null | https://practice.geeksforgeeks.org/problems/implementing-floyd-warshall2042/1 |
| | https://leetcode.com/problems/reverse-words-in-a-string/ | https://bit.ly/3SXyWB4 |
| | null | https://practice.geeksforgeeks.org/problems/rod-cutting0840/1 |
| | https://leetcode.com/problems/rotate-string/ | https://bit.ly/3K0HHq5 |
| | null | https://bit.ly/3SVf1me |
| | null | https://bit.ly/3QA600D |
| | https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/ | https://bit.ly/3QuCFEP |
| | https://leetcode.com/problems/critical-connections-in-a-network/discuss/382385/find-bridges-in-a-graph | https://bit.ly/3dFdvVe |
| | null | https://bit.ly/3JVcqot |
| | https://leetcode.com/problems/search-in-rotated-sorted-array/ | https://bit.ly/3QH471y |
| | null | https://bit.ly/3zWPiBj |
| | https://leetcode.com/problems/majority-element-ii/ | null |
| | https://leetcode.com/problems/course-schedule/ | https://practice.geeksforgeeks.org/problems/course-schedule/1 |
| | https://leetcode.com/problems/longest-repeating-character-replacement/ | https://bit.ly/3WhN89U |
