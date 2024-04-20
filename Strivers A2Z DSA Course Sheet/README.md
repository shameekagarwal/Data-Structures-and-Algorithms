# Strivers A2Z DSA Course Sheet

### Random Revision Generation Script

to help with revision, so that the topic etc is not revealed. run it inside the console tab after opening the a2z link

```js
const script = async () => {

  const steps = await fetch("https://adminapi.takeuforward.org/api/sheets/double/strivers_a2z_sheet")
    .then(res => res.json());
  const sub_steps = steps.flatMap(step => step.sub_steps);
  const topics = sub_steps.flatMap(sub_step => sub_step.topics);
  const links = topics.map(topic => ({ leetcode: topic.lc_link, coding_ninjas: topic.cs_link }))
    .filter(link => link.leetcode != null || link.coding_ninjas != null)
    .map(link => ({
      leetcode: link.leetcode?.split('#')[0] || '',
      coding_ninjas: link.coding_ninjas?.split('?')[0] || ''
    }));
  const randomized = links.map(value => ({ ...value, sort: Math.random() }))
    .sort((a, b) => a.sort - b.sort);
  
  const rows = randomized.map(link => `| | ${link.leetcode} | ${link.coding_ninjas} |`);
  const table = [`| completed | leetcode | coding_ninjas |`,
    `| --------- | -------- | ------------- |`,
    ...rows];

  const formattedTable = table.reduce((a, b) => `${a}\n${b}`);
  console.log(formattedTable);
}

script();
```

### Random Revision Progress

| completed | leetcode | coding_ninjas |
| --------- | -------- | ------------- |
| ✅,❌,✅ |  | https://www.codingninjas.com/studio/problems/longest-increasing-subsequence_630459 |
| ✅ | https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/ | https://www.codingninjas.com/codestudio/problems/smallest-divisor-with-the-given-limit_1755882 |
| ✅ | https://leetcode.com/problems/word-ladder-ii/ | https://www.codingninjas.com/codestudio/problems/shortest-transition-paths_8391015 |
| ✅ | https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/ | https://www.codingninjas.com/codestudio/problems/rose-garden_2248080 |
| ❌ |  | https://www.codingninjas.com/studio/problems/longest-common-substring_1235207 |
| ✅ | https://leetcode.com/problems/sliding-window-maximum/ | https://www.codingninjas.com/studio/problems/sliding-maximum-_701652 |
| ✅ | https://leetcode.com/problems/maximum-depth-of-binary-tree/ | https://www.codingninjas.com/studio/problems/height-of-binary-tree_4609628 |
| ✅ | https://leetcode.com/problems/search-a-2d-matrix/ | https://www.codingninjas.com/codestudio/problems/search-in-a-2d-matrix_980531 |
| ✅ |  | https://www.codingninjas.com/studio/problems/min-heap-implementation_5480527 |
| ✅❌ (iterative soln 2) | https://leetcode.com/problems/binary-tree-postorder-traversal/ | https://www.codingninjas.com/studio/problems/postorder-traversal_2035933 |
| ✅ | https://leetcode.com/problems/valid-anagram/ | https://www.codingninjas.com/studio/problems/anagram-pairs_626517 |
| ✅ |  | https://www.codingninjas.com/studio/problems/ceil-from-bst_920464 |
| ✅ | https://leetcode.com/problems/rotate-array/ | https://www.codingninjas.com/codestudio/problems/left-rotate-an-array-by-one_5026278 |
| ✅ | https://leetcode.com/problems/maximum-width-of-binary-tree/ | https://www.codingninjas.com/studio/problems/maximum-width-in-binary-tree_763671 |
| ❌ | https://practice.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1 | https://www.codingninjas.com/studio/problems/job-sequencing-problem_1169460 |
| ✅ | https://www.interviewbit.com/problems/nearest-smaller-element/ | https://www.codingninjas.com/studio/problems/immediate-smaller-element-_1062597 |
| ✅✅ | https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/ | https://www.codingninjas.com/studio/problems/best-time-to-buy-and-sell-stock-ii_630282 |
| ✅ | https://leetcode.com/problems/longest-common-subsequence/ | https://www.codingninjas.com/studio/problems/longest-common-subsequence_624879 |
| ✅ | https://practice.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1 | https://www.codingninjas.com/studio/problems/maximum-meetings_1062658 |
| ✅❌ |  | https://www.codingninjas.com/codestudio/problems/nth-root-of-m_1062679 |
| ✅ |  | https://www.codingninjas.com/studio/problems/stack-implementation-using-array_3210209 |
| ✅ | https://leetcode.com/problems/word-search/ | https://www.codingninjas.com/studio/problems/word-search---l_892986 |
| ✅ |  | https://www.codingninjas.com/codestudio/problems/longest-subarray-with-zero-sum_6783450 |
| ✅ | https://leetcode.com/problems/diameter-of-binary-tree/ | https://www.codingninjas.com/studio/problems/diameter-of-binary-tree_920552 |
| ✅ | https://leetcode.com/problems/count-good-numbers/ | https://www.codingninjas.com/studio/problems/good-numbers_625508 |
| ✅ | https://leetcode.com/problems/search-in-rotated-sorted-array-ii/ | https://www.codingninjas.com/codestudio/problems/search-in-a-rotated-sorted-array-ii_7449547 |
| ✅ | https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/ | https://www.codingninjas.com/studio/problems/-most-stones-removed-with-same-row-or-column_1376597 |
| ✅ | https://leetcode.com/problems/roman-to-integer/ | https://www.codingninjas.com/studio/problems/roman-numeral-to-integer_981308 |
| ✅ |  | https://www.codingninjas.com/studio/problems/if-else-decision-making_8357235 |
| ✅ | https://leetcode.com/problems/coin-change/ | https://www.codingninjas.com/studio/problems/minimum-elements_3843091 |
| ❌ | https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/ | https://www.codingninjas.com/codestudio/problems/maximum-points-from-cards_8391016 |
| ✅ | https://leetcode.com/problems/longest-consecutive-sequence/ | https://www.codingninjas.com/codestudio/problems/longest-successive-elements_6811740 |
| ✅ |  | https://www.codingninjas.com/studio/problems/frog-jump_3621012 |
| | https://leetcode.com/problems/two-sum-iv-input-is-a-bst/ | https://www.codingninjas.com/studio/problems/pair-sum-in-bst._920493 |
| | https://leetcode.com/problems/count-complete-tree-nodes/ | https://www.codingninjas.com/studio/problems/nodes-in-complete-binary-tree_1281151 |
| |  | https://www.codingninjas.com/studio/problems/subset-sum-equal-to-k_1550954 |
| |  | https://www.codingninjas.com/studio/problems/postfix-to-prefix_1788455 |
| | https://leetcode.com/problems/swim-in-rising-water/ | https://www.codingninjas.com/studio/problems/minimum-time_8360128 |
| | https://leetcode.com/problems/word-break/ | https://www.codingninjas.com/studio/problems/word-break-1_758963 |
| | https://practice.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1 | https://www.codingninjas.com/studio/problems/bottom-view-of-binary-tree_893110 |
| | https://leetcode.com/problems/median-of-two-sorted-arrays/ | https://www.codingninjas.com/codestudio/problems/median-of-two-sorted-arrays_985294 |
| | https://leetcode.com/problems/distinct-subsequences/ | https://www.codingninjas.com/studio/problems/subsequence-counting_3755256 |
| |  | https://www.codingninjas.com/codestudio/problems/print-1-to-n_628290 |
| | https://leetcode.com/problems/house-robber-ii/ | https://www.codingninjas.com/studio/problems/house-robber-ii_839733 |
| | https://leetcode.com/problems/longest-palindromic-subsequence/ | https://www.codingninjas.com/studio/problems/longest-palindromic-subsequence_842787 |
| | https://leetcode.com/problems/largest-odd-number-in-string/ |  |
| | https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/ | https://www.codingninjas.com/studio/problems/best-time-to-buy-and-sell-stock-with-transaction-fee_3118974 |
| | https://leetcode.com/problems/binary-tree-right-side-view/ | https://www.codingninjas.com/studio/problems/left-view-of-binary-tree_625707 |
| | https://leetcode.com/problems/longest-repeating-character-replacement/ | https://www.codingninjas.com/studio/problems/longest-repeating-substring_980523 |
| |  | https://www.codingninjas.com/codestudio/problems/odd-even_7993579 |
| |  | https://www.codingninjas.com/studio/problems/merge-k-sorted-arrays_975379 |
| |  | https://www.codingninjas.com/codestudio/problems/hcf-and-lcm_840448 |
| | https://leetcode.com/problems/largest-rectangle-in-histogram/ | https://www.codingninjas.com/studio/problems/largest-rectangle-in-a-histogram_1058184 |
| | https://leetcode.com/problems/longest-palindromic-substring/ |  |
| |  | https://www.codingninjas.com/studio/problems/quick-sort_5844 |
| | https://leetcode.com/problems/minimize-max-distance-to-gas-station/ | https://www.codingninjas.com/codestudio/problems/minimise-max-distance_7541449 |
| |  | https://www.codingninjas.com/codestudio/problems/single-source-shortest-path_8416371 |
| |  | https://www.codingninjas.com/studio/problems/minimum-spanning-tree_631769 |
| | https://leetcode.com/problems/subsets-ii/ | https://www.codingninjas.com/studio/problems/get-all-unique-subsets_624393 |
| | https://leetcode.com/problems/min-stack/ | https://www.codingninjas.com/studio/problems/min-stack_3843991 |
| | https://leetcode.com/problems/valid-parentheses/ | https://www.codingninjas.com/studio/problems/valid-parentheses_795104 |
| |  | https://www.codingninjas.com/codestudio/problems/longest-subarray-with-sum-k_6682399 |
| | https://practice.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1 | https://www.codingninjas.com/studio/problems/dfs-traversal_630462 |
| | https://leetcode.com/problems/number-of-longest-increasing-subsequence/ | https://www.codingninjas.com/studio/problems/number-of-longest-increasing-subsequence_3751627 |
| |  | https://www.codingninjas.com/codestudio/problems/row-of-a-matrix-with-maximum-ones_982768 |
| | https://leetcode.com/problems/combination-sum-iii/ | https://www.codingninjas.com/studio/problems/combination-sum-iii_5038357 |
| | https://leetcode.com/problems/validate-binary-search-tree/ | https://www.codingninjas.com/studio/problems/check-bst_5975 |
| | https://leetcode.com/problems/is-graph-bipartite/ | https://www.codingninjas.com/studio/problems/check-bipartite-graph-_920551 |
| |  | https://www.codingninjas.com/codestudio/problems/more-subsequence_8842355 |
| | https://leetcode.com/problems/maximum-product-subarray/ | https://www.codingninjas.com/codestudio/problems/subarray-with-maximum-product_6890008 |
| | https://leetcode.com/problems/sum-of-beauty-of-all-substrings/ | https://www.codingninjas.com/studio/problems/sum-of-beauty-of-all-substrings_8143656 |
| |  | https://www.codingninjas.com/studio/problems/data-type_8357232 |
| | https://leetcode.com/problems/number-of-islands-ii/ | https://www.codingninjas.com/studio/problems/largest-island_840701 |
| | https://leetcode.com/problems/max-consecutive-ones-iii/ | https://www.codingninjas.com/studio/problems/maximum-consecutive-ones_892994 |
| |  | https://www.codingninjas.com/studio/problems/reverse-stack-using-recursion_631875 |
| |  | https://www.codingninjas.com/studio/problems/subarrays-with-sum-‘k'_6922076 |
| |  | https://www.codingninjas.com/studio/problems/replace-each-element-of-array-with-its-corresponding-rank_975384 |
| | https://leetcode.com/problems/reverse-nodes-in-k-group/ | https://www.codingninjas.com/studio/problems/reverse-list-in-k-groups_983644 |
| | https://leetcode.com/problems/3sum/ | https://www.codingninjas.com/codestudio/problems/three-sum_6922132 |
| | https://leetcode.com/problems/delete-node-in-a-bst/ | https://www.codingninjas.com/studio/problems/delete-node-in-bst_920381 |
| |  | https://www.codingninjas.com/studio/problems/selection-sort_624469 |
| | https://leetcode.com/problems/4sum/ | https://www.codingninjas.com/codestudio/problems/4sum_5713771 |
| | https://leetcode.com/problems/find-median-from-data-stream/ | https://www.codingninjas.com/studio/problems/median-in-a-stream_975268 |
| |  | https://www.codingninjas.com/studio/problems/prime-factorisation_1760849 |
| | https://leetcode.com/problems/shortest-common-supersequence/ | https://www.codingninjas.com/studio/problems/shortest-common-supersequence_4244493 |
| | https://leetcode.com/problems/search-in-a-binary-search-tree/ | https://www.codingninjas.com/studio/problems/search-in-bst_1402878 |
| |  | https://www.codingninjas.com/studio/problems/set-the-rightmost-unset-bit_8160456 |
| | https://leetcode.com/problems/trapping-rain-water/ | https://www.codingninjas.com/studio/problems/trapping-rain-water_630519 |
| | https://leetcode.com/problems/making-a-large-island/ | https://www.codingninjas.com/studio/problems/making-the-largest-island_1381282 |
| | https://leetcode.com/problems/binary-tree-postorder-traversal/ | https://www.codingninjas.com/studio/problems/postorder-traversal_2035933 |
| | https://leetcode.com/problems/find-a-peak-element-ii/ | https://www.codingninjas.com/codestudio/problems/find-peak-element_7449073 |
| |  | https://www.codingninjas.com/studio/problems/minimum-operations_8360665 |
| |  | https://www.codingninjas.com/studio/problems/-print-n-times_8380707 |
| | https://leetcode.com/problems/next-greater-element-ii/ | https://www.codingninjas.com/studio/problems/next-greater-element-ii_6212757 |
| |  | https://www.codingninjas.com/codestudio/problems/convert-min-heap-to-max-heap_1381084 |
| | https://leetcode.com/problems/majority-element/ | https://www.codingninjas.com/codestudio/problems/majority-element_6783241 |
| |  | https://www.codingninjas.com/studio/problems/chocolate-pickup_3125885 |
| | https://leetcode.com/problems/subsets/ | https://www.codingninjas.com/studio/problems/print-subsequences_8416366 |
| |  | https://www.codingninjas.com/studio/problems/bit-manipulation_8142533 |
| | https://leetcode.com/problems/lfu-cache/ | https://www.codingninjas.com/codestudio/problems/lfu-cache_8381892 |
| | https://leetcode.com/problems/binary-tree-preorder-traversal/ | https://www.codingninjas.com/studio/problems/preorder-binary-tree_5948 |
| | https://leetcode.com/problems/single-element-in-a-sorted-array/ | https://www.codingninjas.com/codestudio/problems/unique-element-in-sorted-array_1112654 |
| | https://leetcode.com/problems/split-array-largest-sum/ | https://www.codingninjas.com/codestudio/problems/largest-subarray-sum-minimized_7461751 |
| |  | https://www.codingninjas.com/codestudio/problems/floor-from-bst_625868 |
| | https://leetcode.com/problems/reverse-words-in-a-string/ | https://www.codingninjas.com/studio/problems/reverse-words-in-a-string_696444 |
| | https://leetcode.com/problems/koko-eating-bananas/ | https://www.codingninjas.com/codestudio/problems/minimum-rate-to-eat-bananas_7449064 |
| |  | https://www.codingninjas.com/studio/problems/tree-traversals_981269 |
| |  | https://www.codingninjas.com/studio/problems/add-one-to-a-number-represented-as-linked-list_920557 |
| |  | https://www.codingninjas.com/studio/problems/bellman-ford_2041977 |
| |  | https://www.codingninjas.com/studio/problems/time-to-burn-tree_1469067 |
| |  | https://www.codingninjas.com/studio/problems/remove-duplicates-from-a-sorted-doubly-linked-list_2420283 |
| | https://leetcode.com/problems/sort-colors/ | https://www.codingninjas.com/codestudio/problems/sort-an-array-of-0s-1s-and-2s_892977 |
| | https://leetcode.com/problems/minimum-bit-flips-to-convert-number/ | https://www.codingninjas.com/studio/problems/flip-bits_8160405 |
| | https://practice.geeksforgeeks.org/problems/minimum-platforms-1587115620/1 | https://www.codingninjas.com/studio/problems/minimum-number-of-platforms_799400 |
| |  | https://www.codingninjas.com/codestudio/problems/first-occurence-of-a-pattern-in-a-text_8416393 |
| | https://leetcode.com/problems/palindrome-number/ | https://www.codingninjas.com/studio/problems/palindrome-number_624662 |
| | https://leetcode.com/problems/boundary-of-binary-tree/ | https://www.codingninjas.com/studio/problems/boundary-traversal-of-binary-tree_790725 |
| |  | https://www.codingninjas.com/codestudio/problems/articulation-point_840708 |
| |  | https://www.codingninjas.com/studio/problems/creating-and-printing_1214551 |
| | https://leetcode.com/problems/longest-substring-without-repeating-characters/ | https://www.codingninjas.com/studio/problems/longest-substring-without-repeating-characters_630418 |
| |  | https://www.codingninjas.com/studio/problems/rod-cutting-problem_800284 |
| |  | https://www.codingninjas.com/codestudio/problems/convert-prefix-to-postfix_8391014 |
| |  | https://www.codingninjas.com/codestudio/problems/largest-element-in-the-array-largest-element-in-the-array_5026279 |
| | https://leetcode.com/problems/assign-cookies/ | https://www.codingninjas.com/codestudio/problems/assign-cookies_8390826 |
| | https://leetcode.com/problems/sum-of-subarray-minimums/ | https://www.codingninjas.com/codestudio/problems/sum-of-subarray-minimums_8365431 |
| |  | https://www.codingninjas.com/studio/problems/count-strongly-connected-components-(kosaraju’s-algorithm)_1171151 |
| | https://leetcode.com/problems/inorder-successor-in-bst/ | https://www.codingninjas.com/studio/problems/predecessor-and-successor-in-bst_893049 |
| | https://bit.ly/3ocRQW0 | https://www.codingninjas.com/studio/problems/count-distinct-substrings_985292 |
| |  | https://www.codingninjas.com/studio/problems/minimal-cost_8180930 |
| |  | https://www.codingninjas.com/codestudio/problems/ninja-and-the-second-order-elements_6581960 |
| |  | https://www.codingninjas.com/studio/problems/topological-sorting_973003 |
| | https://leetcode.com/problems/binary-search/ | https://www.codingninjas.com/codestudio/problems/binary-search_972 |
| | https://leetcode.com/problems/recover-binary-search-tree/ | https://www.codingninjas.com/studio/problems/fix-bst_873137 |
| | https://leetcode.com/problems/count-and-say/ | https://www.codingninjas.com/studio/problems/look-and-say-sequence_668478 |
| | https://leetcode.com/problems/symmetric-tree/ | https://www.codingninjas.com/studio/problems/symmetric-tree_981177 |
| | https://leetcode.com/problems/surrounded-regions/ | https://www.codingninjas.com/studio/problems/replace-‘o’-with-‘x’_630517 |
| | https://leetcode.com/problems/count-square-submatrices-with-all-ones/ |  |
| | https://leetcode.com/problems/odd-even-linked-list/ | https://www.codingninjas.com/studio/problems/segregate-even-and-odd-nodes-in-a-linked-list_1116100 |
| | https://leetcode.com/problems/search-in-rotated-sorted-array/ | https://www.codingninjas.com/codestudio/problems/search-in-rotated-sorted-array_1082554 |
| | https://leetcode.com/problems/course-schedule/ | https://www.codingninjas.com/studio/problems/course-schedule-ii_1069243 |
| | https://leetcode.com/problems/binary-tree-preorder-traversal/ | https://www.codingninjas.com/studio/problems/preorder-binary-tree_5948 |
| | https://leetcode.com/problems/add-two-numbers/ | https://www.codingninjas.com/studio/problems/add-two-numbers_1170520 |
| | https://leetcode.com/problems/rearrange-array-elements-by-sign/ | https://www.codingninjas.com/codestudio/problems/alternate-numbers_6783445 |
| | https://leetcode.com/problems/kth-largest-element-in-a-stream/ | https://www.codingninjas.com/studio/problems/kth-largest-element-in-a-stream_800301 |
| | https://leetcode.com/problems/implement-trie-prefix-tree/ | https://www.codingninjas.com/studio/problems/trie-implementation_1062581 |
| |  | https://www.codingninjas.com/studio/problems/printing-longest-increasing-subsequence_8360670 |
| |  | https://www.codingninjas.com/studio/problems/is-heap-possible-from-the-array |
| |  | https://www.codingninjas.com/studio/problems/count-of-greater-elements-to-the-right_8365436 |
| | https://leetcode.com/problems/course-schedule-ii/discuss/293048/detecting-cycle-in-directed-graph-problem | https://www.codingninjas.com/studio/problems/detect-cycle-in-a-directed-graph-_920545 |
| | https://leetcode.com/problems/minimum-window-substring/ | https://www.codingninjas.com/studio/problems/minimum-window-substring_1215004 |
| | https://leetcode.com/problems/binary-tree-inorder-traversal/ | https://www.codingninjas.com/studio/problems/preorder-binary-tree_5948 |
| | https://leetcode.com/problems/rotate-list/description/ | https://www.codingninjas.com/studio/problems/rotate-linked-list_920454 |
| | https://leetcode.com/problems/lru-cache/ | https://www.codingninjas.com/studio/problems/lru-cache-implementation_670276 |
| |  | https://www.codingninjas.com/codestudio/problems/occurrence-of-x-in-a-sorted-array_630456 |
| | https://leetcode.com/problems/insert-into-a-binary-search-tree/ | https://www.codingninjas.com/studio/problems/insert-into-a-binary-search-tree_1279913 |
| | https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/ | https://www.codingninjas.com/studio/problems/print-nodes-at-distance-k-from-a-given-node_842560 |
| | https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/ | https://www.codingninjas.com/studio/problems/number-of-ways_8360639 |
| | https://leetcode.com/problems/binary-tree-level-order-traversal/ | https://www.codingninjas.com/studio/problems/zigzag-binary-tree-traversal_920532 |
| | https://leetcode.com/problems/hand-of-straights/ | https://www.codingninjas.com/studio/problems/groups-in-ninja-land._1459215 |
| | https://practice.geeksforgeeks.org/problems/top-view-of-binary-tree/1 | https://www.codingninjas.com/studio/problems/top-view-of-binary-tree_799401 |
| |  | https://www.codingninjas.com/studio/problems/implement-upper-bound_8165383 |
| | https://leetcode.com/problems/rotting-oranges/ | https://www.codingninjas.com/studio/problems/rotting-oranges_701655 |
| | https://leetcode.com/problems/powx-n/ | https://www.codingninjas.com/studio/problems/power-of-numbers_8157729 |
| |  | https://www.codingninjas.com/codestudio/problems/linear-search_6922070 |
| |  | https://www.codingninjas.com/studio/problems/n-to-1-without-loop_8357243 |
| |  | https://www.codingninjas.com/studio/problems/sum-of-all-divisors_8360720 |
| | https://leetcode.com/problems/subarrays-with-k-different-integers/ | https://www.codingninjas.com/studio/problems/subarrays-with-at-most-‘k’-distinct-values_1473804 |
| | https://leetcode.com/problems/binary-search-tree-iterator/ | https://www.codingninjas.com/studio/problems/merge-two-bsts_920474 |
| |  | https://www.codingninjas.com/studio/problems/topological-sorting_973003 |
| | https://leetcode.com/accounts/login/?next=/problems/find-the-celebrity/ | https://www.codingninjas.com/studio/problems/the-celebrity-problem_982769 |
| | https://leetcode.com/problems/subsets/ | https://www.codingninjas.com/studio/problems/subsequences-of-string_985087 |
| | https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/ | https://www.codingninjas.com/codestudio/problems/ninja-and-the-sorted-check_6581957 |
| |  | https://www.codingninjas.com/studio/problems/sum-of-even-odd_624650 |
| |  | https://www.codingninjas.com/studio/problems/find-character-case_58513 |
| |  | https://www.codingninjas.com/codestudio/problems/rotation_7449070 |
| | https://leetcode.com/problems/string-to-integer-atoi/ | https://www.codingninjas.com/studio/problems/implement-atoi-function_981270 |
| |  | https://www.codingninjas.com/studio/problems/insert-node-at-the-beginning_8144739 |
| | https://leetcode.com/problems/n-queens/ | https://www.codingninjas.com/studio/problems/n-queens_696453 |
| |  | https://www.codingninjas.com/studio/problems/reverse-an-array_8365444 |
| |  | https://www.codingninjas.com/studio/problems/creating-and-printing_1214551 |
| | https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/ | https://www.codingninjas.com/studio/problems/vertical-order-traversal_3622711 |
| | https://leetcode.com/problems/serialize-and-deserialize-binary-tree/ | https://www.codingninjas.com/studio/problems/serialize-and-deserialize-binary-tree_920328 |
| | https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/ | https://www.codingninjas.com/studio/problems/construct-binary-tree-from-inorder-and-preorder-traversal_920539 |
| | https://leetcode.com/problems/combination-sum/ | https://www.codingninjas.com/studio/problems/combination-sum_981296 |
| | https://leetcode.com/problems/divide-two-integers/ | https://www.codingninjas.com/studio/problems/-divide-two-integers_1112617 |
| | https://leetcode.com/problems/string-to-integer-atoi/ | https://www.codingninjas.com/studio/problems/implement-atoi-function_981270 |
| |  | https://www.codingninjas.com/studio/problems/flatten-a-linked-list_1112655 |
| |  | https://www.codingninjas.com/codestudio/problems/count-digits_8416387 |
| |  | https://www.codingninjas.com/studio/problems/all-root-to-leaf-paths-in-binary-tree._983599 |
| |  | https://www.codingninjas.com/studio/problems/count-palindromic-subsequences_1062696 |
| |  | https://www.codingninjas.com/studio/problems/l-to-r-xor_8160412 |
| | https://leetcode.com/problems/merge-intervals/ | https://www.codingninjas.com/studio/problems/merge-intervals_699917 |
| | https://leetcode.com/problems/unique-paths/ | https://www.codingninjas.com/studio/problems/unique-paths_1081470 |
| | https://leetcode.com/problems/search-insert-position/ | https://www.codingninjas.com/codestudio/problems/algorithm-to-find-best-insert-position-in-sorted-array_839813 |
| | https://leetcode.com/problems/network-delay-time/ | https://www.codingninjas.com/studio/problems/network-delay-time_1382215 |
| |  | https://www.codingninjas.com/codestudio/problems/square-root-integral_893351 |
| |  | https://www.codingninjas.com/studio/problems/unbounded-knapsack_1215029 |
| |  | https://www.codingninjas.com/studio/problems/day-23-:-infix-to-postfix-_1382146 |
| |  | https://www.codingninjas.com/codestudio/problems/longest-subarray-with-sum-k_6682399 |
| | https://leetcode.com/problems/linked-list-cycle/ | https://www.codingninjas.com/studio/problems/cycle-detection-in-a-singly-linked-list_628974 |
| |  | https://www.codingninjas.com/studio/problems/0-1-knapsack_920542 |
| |  | https://www.codingninjas.com/studio/problems/kruskal’s-minimum-spanning-tree-algorithm_1082553 |
| |  | https://www.codingninjas.com/studio/problems/bubble-sort_624380 |
| |  | https://www.codingninjas.com/studio/problems/check-whether-k-th-bit-is-set-or-not_5026446 |
| | https://leetcode.com/problems/binary-tree-postorder-traversal/ | https://www.codingninjas.com/studio/problems/postorder-traversal_2035933 |
| | https://leetcode.com/problems/sort-characters-by-frequency/ | https://www.codingninjas.com/studio/problems/sorting-characters-by-frequency_1263699 |
| | https://leetcode.com/problems/isomorphic-strings/ | https://www.codingninjas.com/studio/problems/isomorphic-strings-_1117636 |
| | https://leetcode.com/problems/rotate-image/ | https://www.codingninjas.com/codestudio/problems/rotate-the-matrix_6825090 |
| | https://leetcode.com/problems/implement-strstr/ | https://www.codingninjas.com/codestudio/problems/pattern-searching-using-z-algorithm_8395752 |
| | https://practice.geeksforgeeks.org/problems/m-coloring-problem-1587115620/1 | https://www.codingninjas.com/studio/problems/m-coloring-problem_981273 |
| | https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/ |  |
| |  | https://www.codingninjas.com/studio/problems/reverse-a-doubly-linked-list_1116098 |
| | https://leetcode.com/problems/candy/ | https://www.codingninjas.com/studio/problems/candies_893290 |
| | https://leetcode.com/problems/rotate-string/ | https://www.codingninjas.com/studio/problems/check-if-one-string-is-a-rotation-of-another-string_1115683 |
| | https://leetcode.com/problems/kth-missing-positive-number/ | https://www.codingninjas.com/codestudio/problems/kth-missing-element_893215 |
| | https://leetcode.com/problems/move-zeroes/ | https://www.codingninjas.com/codestudio/problems/ninja-and-the-zero-s_6581958 |
| |  | https://www.codingninjas.com/studio/problems/unique-binary-tree_8180906 |
| |  | https://www.codingninjas.com/studio/problems/ninja’s-training_3621003 |
| | https://leetcode.com/problems/count-number-of-nice-subarrays/ | https://www.codingninjas.com/studio/problems/count-distinct-subarrays-with-at-most-k-odd-elements_1069335 |
| |  | https://www.codingninjas.com/studio/problems/connect-n-ropes-with-minimum-cost_625783 |
| |  | https://www.codingninjas.com/studio/problems/swap-two-numbers_1380853 |
| | https://leetcode.com/problems/next-permutation/ | https://www.codingninjas.com/codestudio/problems/next-greater-permutation_6929564 |
| | https://leetcode.com/problems/implement-strstr/ | https://www.codingninjas.com/codestudio/problems/search-pattern-kmp-algorithm_8416386 |
| | https://leetcode.com/problems/01-matrix/ | https://www.codingninjas.com/studio/problems/distance-of-nearest-cell-having-1-in-a-binary-matrix_1169913 |
| |  | https://www.codingninjas.com/studio/problems/introduction-to-linked-list_8144737 |
| | https://leetcode.com/problems/spiral-matrix/ | https://www.codingninjas.com/codestudio/problems/spiral-matrix_6922069 |
| |  | https://www.codingninjas.com/studio/problems/check-prime_624934 |
| | https://leetcode.com/problems/minimum-falling-path-sum/ | https://www.codingninjas.com/studio/problems/maximum-path-sum-in-the-matrix_797998 |
| |  | https://www.codingninjas.com/codestudio/problems/sorted-array_6613259 |
| | https://leetcode.com/problems/binary-tree-maximum-path-sum/ | https://www.codingninjas.com/studio/problems/maximum-sum-path-of-a-binary-tree._1214968 |
| |  | https://www.codingninjas.com/studio/problems/merge-sort_5846 |
| | https://leetcode.com/problems/copy-list-with-random-pointer/ | https://www.codingninjas.com/studio/problems/clone-a-linked-list-with-random-pointers_983604 |
| | https://leetcode.com/problems/pascals-triangle/ | https://www.codingninjas.com/codestudio/problems/print-pascal-s-triangle_6917910 |
| | https://leetcode.com/problems/maximal-rectangle/ |  |
| | https://leetcode.com/problems/binary-tree-inorder-traversal/ | https://www.codingninjas.com/studio/problems/inorder-traversal_3839605 |
| | https://leetcode.com/problems/word-ladder/ | https://www.codingninjas.com/studio/problems/word-ladder_1102319 |
| | https://leetcode.com/problems/middle-of-the-linked-list/ | https://www.codingninjas.com/studio/problems/middle-of-linked-list_973250 |
| |  | https://www.codingninjas.com/codestudio/problems/print-longest-common-subsequence_8416383 |
| | https://leetcode.com/problems/wildcard-matching/ | https://www.codingninjas.com/studio/problems/wildcard-pattern-matching_701650 |
| | https://leetcode.com/problems/burst-balloons/ | https://www.codingninjas.com/codestudio/problems/burst-balloons_628471 |
| | https://leetcode.com/problems/next-greater-element-i/ | https://www.codingninjas.com/studio/problems/next-greater-element_670312 |
| | https://leetcode.com/problems/merge-k-sorted-lists/ | https://www.codingninjas.com/studio/problems/merge-k-sorted-lists_992772 |
| | https://leetcode.com/problems/edit-distance/ | https://www.codingninjas.com/studio/problems/shortest-common-supersequence_4244493 |
| |  | https://www.codingninjas.com/studio/problems/longest-bitonic-sequence_1062688 |
| | https://leetcode.com/problems/sort-list/ | https://www.codingninjas.com/studio/problems/sort-linked-list_625193 |
| | https://leetcode.com/problems/jump-game-ii/ | https://www.codingninjas.com/studio/problems/jump-game_893178 |
| | https://leetcode.com/problems/majority-element-ii/ | https://www.codingninjas.com/codestudio/problems/majority-element_6915220 |
| | https://leetcode.com/problems/search-a-2d-matrix-ii/ | https://www.codingninjas.com/codestudio/problems/search-in-a-sorted-2d-matrix_6917532 |
| | https://www.interviewbit.com/problems/maximum-sum-combinations/ | https://www.codingninjas.com/studio/problems/median-in-a-stream_975268 |
| | https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1 | https://www.codingninjas.com/studio/problems/rat-in-a-maze-_8842357 |
| |  | https://www.codingninjas.com/studio/problems/longest-subarray-with-sum-k_5713505 |
| | https://leetcode.com/problems/number-of-provinces/ | https://www.codingninjas.com/studio/problems/find-the-number-of-states_1377943 |
| | https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/ | https://www.codingninjas.com/studio/problems/minimum-cost-to-make-string-valid_1115770 |
| | https://leetcode.com/problems/top-k-frequent-elements/ | https://www.codingninjas.com/studio/problems/k-most-frequent-elements_3167808 |
| | https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/ | https://www.codingninjas.com/studio/problems/longest-substring-with-at-most-k-distinct-characters_2221410 |
| |  | https://www.codingninjas.com/studio/problems/nth-fibonacci-number_74156 |
| | https://leetcode.com/problems/fibonacci-number/ | https://www.codingninjas.com/studio/problems/print-fibonacci-series_7421617 |
| |  | https://www.codingninjas.com/codestudio/problems/print-1-to-n_628290 |
| |  | https://www.codingninjas.com/studio/problems/matrix-chain-multiplication_624880 |
| | https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/ | https://www.codingninjas.com/codestudio/problems/rotated-array_1093219 |
| | https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/ | https://www.codingninjas.com/studio/problems/construct-bst-from-preorder-traversal_2689307 |
| | https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/ | https://www.codingninjas.com/studio/problems/zigzag-binary-tree-traversal_920532 |
| | https://leetcode.com/problems/subarray-sum-equals-k/ | https://www.codingninjas.com/studio/problems/subarray-sums-i_1467103 |
| | https://leetcode.com/problems/implement-stack-using-queues/ | https://www.codingninjas.com/studio/problems/stack-using-queue_795152 |
| | https://leetcode.com/problems/binary-tree-inorder-traversal/ | https://www.codingninjas.com/studio/problems/inorder-traversal_3839605 |
| | https://leetcode.com/problems/valid-parenthesis-string/ | https://www.codingninjas.com/studio/problems/balanced-parentheses_8162202 |
| | https://leetcode.com/problems/repeated-string-match/discuss/416144/Rabin-Karp-algorithm-C%2B%2B-implementation | https://www.codingninjas.com/codestudio/problems/stringmatch-rabincarp_1115738 |
| |  | https://www.codingninjas.com/studio/problems/minimum-element-in-bst_8160462 |
| |  | https://www.codingninjas.com/studio/problems/detect-cycle-in-a-directed-graph_1062626 |
| | https://www.interviewbit.com/problems/subarray-with-given-xor/ | https://www.codingninjas.com/codestudio/problems/subarrays-with-xor-k_6826258 |
| | https://leetcode.com/problems/design-twitter/ | https://www.codingninjas.com/codestudio/problems/design-twitter_8380711 |
| |  | https://www.codingninjas.com/studio/problems/children-sum-property_8357239 |
| |  | https://www.codingninjas.com/studio/problems/insertion-sort_624381 |
| |  | https://www.codingninjas.com/codestudio/problems/number-of-inversions_6840276 |
| | https://practice.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1 | https://www.codingninjas.com/studio/problems/fractional-knapsack_975286 |
| | https://www.spoj.com/problems/AGGRCOW/ | https://www.codingninjas.com/codestudio/problems/aggressive-cows_1082559 |
| |  | https://www.codingninjas.com/studio/problems/minimum-spanning-tree_631769 |
| | https://leetcode.com/problems/sum-of-subarray-ranges/ | https://www.codingninjas.com/studio/problems/subarray-range-sum_8365419 |
| |  | https://www.codingninjas.com/studio/problems/number-of-nodes_8162204 |
| | https://leetcode.com/problems/powx-n/ | https://www.codingninjas.com/studio/problems/find-x-raised-to-power-n-_626560 |
| | https://leetcode.com/problems/best-time-to-buy-and-sell-stock/ | https://www.codingninjas.com/codestudio/problems/best-time-to-buy-and-sell-stock_6194560 |
| |  | https://www.codingninjas.com/codestudio/problems/superior-elements_6783446 |
| | https://leetcode.com/problems/minimum-cost-to-cut-a-stick/ | https://www.codingninjas.com/studio/problems/rod-cutting-problem_800284 |
| | https://leetcode.com/problems/sudoku-solver/ | https://www.codingninjas.com/studio/problems/sudoku-solver_8416969 |
| |  | https://www.codingninjas.com/studio/problems/bubble-sort_624380 |
| | https://leetcode.com/problems/delete-node-in-a-linked-list/ | https://www.codingninjas.com/studio/problems/delete-node-of-linked-list_8160463 |
| | https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/ | https://www.codingninjas.com/studio/problems/best-time-to-buy-and-sell-stock-iv_1080698 |
| |  | https://www.codingninjas.com/studio/problems/delete-last-node-of-a-doubly-linked-list_8160469 |
| |  | https://www.codingninjas.com/codestudio/problems/postfix-to-infix_8382386 |
| |  | https://www.codingninjas.com/studio/problems/floyd-warshall_2041979 |
| |  | https://www.codingninjas.com/studio/problems/data-stream-as-disjoint-intervals_1382362 |
| | https://leetcode.com/problems/merge-intervals/ | https://www.codingninjas.com/codestudio/problems/merge-all-overlapping-intervals_6783452 |
| | https://leetcode.com/problems/single-number/ | https://www.codingninjas.com/studio/problems/one-odd-occurring_4606074 |
| | https://leetcode.com/problems/triangle/ | https://www.codingninjas.com/studio/problems/triangle_1229398 |
| | https://bit.ly/3n3kedU | https://www.codingninjas.com/studio/problems/longest-common-prefix_2090383 |
| | https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/ | https://www.codingninjas.com/studio/problems/delete-middle-node_763267 |
| | https://leetcode.com/problems/merge-sorted-array/ | https://www.codingninjas.com/codestudio/problems/merge-two-sorted-arrays-without-extra-space_6898839 |
| |  | https://www.codingninjas.com/studio/problems/implement-a-priority-queue-_1743878 |
| |  | https://www.codingninjas.com/studio/problems/nth-fibonacci-number_74156 |
| |  | https://www.codingninjas.com/studio/problems/day-23-:-infix-to-postfix-_1382146 |
| |  | https://www.codingninjas.com/studio/problems/matrix-chain-multiplication_624880 |
| | https://leetcode.com/problems/number-of-operations-to-make-network-connected/ | https://www.codingninjas.com/studio/problems/number-of-operations-to-make-graph-connected._1385179 |
| |  | https://www.codingninjas.com/studio/problems/sort-linked-list-of-0s-1s-2s_1071937 |
| | https://leetcode.com/problems/generate-parentheses/ | https://www.codingninjas.com/studio/problems/generate-all-parenthesis_920445 |
| |  | https://www.codingninjas.com/codestudio/problems/lower-bound_8165382 |
| | https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/ | https://www.codingninjas.com/studio/problems/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum._842494 |
| | https://leetcode.com/problems/flood-fill/ | https://www.codingninjas.com/studio/problems/flood-fill-_1082141 |
| | https://leetcode.com/problems/intersection-of-two-linked-lists/ | https://www.codingninjas.com/studio/problems/-intersection-of-two-linked-lists_630457 |
| |  | https://www.codingninjas.com/studio/problems/binary-search-trees_8160443 |
| | https://leetcode.com/problems/palindrome-linked-list/ | https://www.codingninjas.com/studio/problems/check-if-linked-list-is-palindrome_985248 |
| | https://leetcode.com/problems/insert-interval/ | https://www.codingninjas.com/studio/problems/-insert-interval_285893 |
| | https://leetcode.com/problems/longest-string-chain/ | https://www.codingninjas.com/studio/problems/longest-string-chain_3752111 |
| | https://leetcode.com/problems/find-eventual-safe-states/ | https://www.codingninjas.com/studio/problems/safe-nodes-in-the-graph_1376703 |
| | https://leetcode.com/problems/expression-add-operators/ | https://www.codingninjas.com/studio/problems/find-x-raised-to-power-n-_626560 |
| |  | https://www.codingninjas.com/studio/problems/ceiling-in-a-sorted-array_1825401 |
| | https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/ | https://www.codingninjas.com/codestudio/problems/maximum-xor_3119012 |
| | https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/ |  |
| | https://leetcode.com/problems/shortest-path-in-binary-matrix/ | https://www.codingninjas.com/studio/problems/shortest-path-in-a-binary-maze_893065 |
| | https://leetcode.com/problems/linked-list-cycle-ii/ | https://www.codingninjas.com/studio/problems/linked-list-cycle-ii_1112628 |
| |  | https://www.codingninjas.com/studio/problems/size-of-largest-bst-in-binary-tree_893103 |
| | https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/ | https://www.codingninjas.com/studio/problems/best-time-to-buy-and-sell-stock-iii_1071012 |
| | https://leetcode.com/problems/palindrome-partitioning/ | https://www.codingninjas.com/studio/problems/palindrome-partitioning_626181 |
| | https://leetcode.com/problems/reverse-linked-list/ | https://www.codingninjas.com/studio/problems/reverse-linked-list_920513 |
| | https://leetcode.com/problems/target-sum/ | https://www.codingninjas.com/studio/problems/target-sum_4127362 |
| |  | https://www.codingninjas.com/studio/problems/fruits-and-baskets_985356 |
| | https://leetcode.com/problems/find-peak-element/ | https://www.codingninjas.com/codestudio/problems/find-peak-element_1081482 |
| | https://leetcode.com/problems/missing-number/ |  |
| |  | https://www.codingninjas.com/studio/problems/search-in-a-linked-list_975381 |
| | https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/ | https://www.codingninjas.com/studio/problems/minimum-insertions-to-make-a-string-palindrome_985293 |
| | https://leetcode.com/problems/remove-duplicates-from-sorted-array/ | https://www.codingninjas.com/codestudio/problems/remove-duplicates-from-sorted-array_1102307 |
| | https://leetcode.com/problems/minimum-path-sum/ | https://www.codingninjas.com/studio/problems/minimum-path-sum_985349 |
| | https://leetcode.com/problems/number-of-enclaves/ | https://www.codingninjas.com/studio/problems/matrix-traps_8365440 |
| | https://leetcode.com/problems/maximum-xor-with-an-element-from-array/ | https://www.codingninjas.com/studio/problems/maximum-xor-with-an-element-from-array_1382020 |
| | https://leetcode.com/problems/frequency-of-the-most-frequent-element/ | https://www.codingninjas.com/studio/problems/k-most-occurrent-numbers_625382 |
| |  | https://www.codingninjas.com/studio/problems/partitions-with-given-difference_3751628 |
| |  | https://www.codingninjas.com/studio/problems/prime-factorisation_1760849 |
| |  | https://www.codingninjas.com/studio/problems/sum-of-first-n-numbers_8876068 |
| | https://leetcode.com/problems/asteroid-collision/ | https://www.codingninjas.com/studio/problems/asteroid-collision_977232 |
| |  | https://www.codingninjas.com/studio/problems/count-nodes-of-linked-list_5884 |
| | https://leetcode.com/problems/max-consecutive-ones/ | https://www.codingninjas.com/codestudio/problems/traffic_6682625 |
| | https://leetcode.com/problems/house-robber/ | https://www.codingninjas.com/studio/problems/maximum-sum-of-non-adjacent-elements_843261 |
| |  | https://www.codingninjas.com/studio/problems/dijkstra's-shortest-path_985358 |
| | https://leetcode.com/problems/reverse-integer/ | https://www.codingninjas.com/studio/problems/reverse-bits_2181102 |
| | https://leetcode.com/problems/longest-common-prefix/ | https://www.codingninjas.com/studio/problems/longest-common-prefix_628874 |
| |  | https://www.codingninjas.com/studio/problems/print-all-divisors-of-a-number_1164188 |
| | https://leetcode.com/problems/remove-outermost-parentheses/ | https://www.codingninjas.com/codestudio/problems/maximum-nesting-depth-of-the-parentheses_8144741 |
| |  | https://www.codingninjas.com/codestudio/problems/implement-queue-using-arrays_8390825 |
| | https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/ | https://www.codingninjas.com/studio/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance_1264289 |
| | https://leetcode.com/problems/kth-largest-element-in-an-array/ | https://www.codingninjas.com/codestudio/problems/k-largest-elements_796005 |
| | https://leetcode.com/problems/binary-tree-inorder-traversal/ | https://www.codingninjas.com/studio/problems/inorder-traversal_3839605 |
| |  | https://www.codingninjas.com/codestudio/problems/fa-1-ece_4606261 |
| | https://leetcode.com/problems/binary-subarrays-with-sum/ | https://www.codingninjas.com/studio/problems/count-substrings-with-k-ones_3128698 |
| | https://leetcode.com/problems/partition-array-for-maximum-sum/ | https://www.codingninjas.com/studio/problems/partition-array-for-maximum-sum_3755255 |
| |  | https://www.codingninjas.com/codestudio/problems/create-binary-tree_8360671 |
| |  | https://www.codingninjas.com/studio/problems/dijkstra's-shortest-path_985358 |
| | https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/ | https://www.codingninjas.com/studio/problems/construct-binary-tree-from-inorder-and-postorder-traversal_1266106 |
| |  | https://www.codingninjas.com/studio/problems/insert-at-end-of-doubly-linked-list_8160464 |
| |  | https://www.codingninjas.com/studio/problems/data-stream-as-disjoint-intervals_1382362 |
| | https://leetcode.com/problems/shortest-palindrome/ | https://www.codingninjas.com/studio/problems/minimum-characters-for-palindrome_893000 |
| | https://leetcode.com/problems/longest-increasing-subsequence/ | https://www.codingninjas.com/studio/problems/longest-increasing-subsequence_630459 |
| | https://leetcode.com/problems/remove-k-digits/ | https://www.codingninjas.com/studio/problems/remove-k-digits_1461221 |
| | https://leetcode.com/problems/climbing-stairs/ | https://www.codingninjas.com/studio/problems/count-ways-to-reach-the-n-th-stairs_798650 |
| | https://leetcode.com/problems/unique-paths-ii/ | https://www.codingninjas.com/studio/problems/unique-paths-ii_977241 |
| | https://leetcode.com/problems/partition-equal-subset-sum/ | https://www.codingninjas.com/studio/problems/partition-equal-subset-sum-_892980 |
| | https://leetcode.com/problems/cheapest-flights-within-k-stops/ | https://www.codingninjas.com/studio/problems/saving-money_1171195 |
| |  | https://www.codingninjas.com/codestudio/problems/k-th-element-of-2-sorted-array_1164159 |
| |  | https://www.codingninjas.com/studio/problems/delete-all-occurrences-of-a-given-key-in-a-doubly-linked-list_8160461 |
| | https://leetcode.com/problems/longest-happy-prefix/ | https://www.codingninjas.com/studio/problems/longest-prefix-which-is-suffix_3146849 |
| | https://leetcode.com/problems/delete-operation-for-two-strings/ | https://www.codingninjas.com/studio/problems/minimum-number-of-deletions-and-insertions_4244510 |
| | https://leetcode.com/problems/task-scheduler/ | https://www.codingninjas.com/studio/problems/task-scheduler_1070424 |
| |  | https://www.codingninjas.com/studio/problems/switch-case-statement_8357244 |
| | https://leetcode.com/problems/remove-nth-node-from-end-of-list/ | https://www.codingninjas.com/studio/problems/delete-kth-node-from-end_799912 |
| | https://leetcode.com/problems/parsing-a-boolean-expression/ | https://www.codingninjas.com/studio/problems/boolean-evaluation_1214650 |
| | https://leetcode.com/problems/reverse-words-in-a-string/ | https://www.codingninjas.com/codestudio/problems/reverse-words_7037425 |
| | https://leetcode.com/problems/letter-combinations-of-a-phone-number/ | https://www.codingninjas.com/studio/problems/letter-phone_626178 |
| | https://leetcode.com/problems/alien-dictionary/solution/ | https://www.codingninjas.com/studio/problems/alien-dictionary_630423 |
| |  | https://www.codingninjas.com/studio/problems/count-total-set-bits_784 |
| | https://leetcode.com/problems/reverse-linked-list/ | https://www.codingninjas.com/studio/problems/reverse-linked-list_920513 |
| |  | https://www.codingninjas.com/studio/problems/implement-stack-with-linked-list_1279905 |
| |  | https://www.codingninjas.com/studio/problems/painter-s-partition-problem_1089557 |
| |  | https://www.codingninjas.com/codestudio/problems/create-binary-tree_8360671 |
| | https://leetcode.com/problems/non-overlapping-intervals/ | https://www.codingninjas.com/studio/problems/non-overlapping-intervals_3169341 |
| |  | https://www.codingninjas.com/studio/problems/detect-cycle-in-an-undirected-graph-_758967 |
| | https://leetcode.com/problems/number-of-distinct-islands-ii/ | https://www.codingninjas.com/studio/problems/distinct-islands_630460 |
| | https://leetcode.com/problems/best-time-to-buy-and-sell-stock/ | https://www.codingninjas.com/studio/problems/best-time-to-buy-and-sell-stock_893405 |
| | https://bit.ly/3qwT4OL | https://www.codingninjas.com/studio/problems/trie-delete-operation_1062663 |
| |  | https://www.codingninjas.com/codestudio/problems/shortest-path-in-dag_8381897 |
| | https://leetcode.com/problems/flatten-binary-tree-to-linked-list/ | https://www.codingninjas.com/studio/problems/flatten-binary-tree-to-linked-list_1112615 |
| | https://leetcode.com/problems/reverse-pairs/ | https://www.codingninjas.com/codestudio/problems/team-contest_6840309 |
| | https://leetcode.com/problems/accounts-merge/ | https://www.codingninjas.com/studio/problems/accounts-merge_1089558 |
| |  | https://www.codingninjas.com/studio/problems/find-the-number-of-states_1377943 |
| | https://leetcode.com/problems/online-stock-span/ | https://www.codingninjas.com/studio/problems/stock-span_5243295 |
| |  | https://www.codingninjas.com/codestudio/problems/sort-stack_1229505 |
| |  | https://www.codingninjas.com/codestudio/problems/bitwise-basic-operations_8382552 |
| | https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/ | https://www.codingninjas.com/studio/problems/lca-of-binary-tree_920541 |
| |  | https://www.codingninjas.com/codestudio/problems/implement-queue-using-linked-list_8161235 |
| | https://leetcode.com/problems/path-with-minimum-effort/ | https://www.codingninjas.com/studio/problems/path-with-minimum-effort_1380958 |
| | https://leetcode.com/problems/maximal-rectangle/ | https://www.codingninjas.com/studio/problems/maximum-size-rectangle-sub-matrix-with-all-1's_893017 |
| | https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/ | https://www.codingninjas.com/studio/problems/find-the-number-of-states_1377943 |
| | https://leetcode.com/problems/minimum-window-subsequence/ | https://www.codingninjas.com/studio/problems/minimum-window-subsequence_2181133 |
| |  | https://www.codingninjas.com/codestudio/problems/median-of-a-row-wise-sorted-matrix_1115473 |
| |  | https://www.codingninjas.com/codestudio/problems/missing-and-repeating-numbers_6828164 |
| | https://practice.geeksforgeeks.org/problems/bfs-traversal-of-graph/1 | https://www.codingninjas.com/studio/problems/bfs-in-graph_973002 |
| |  | https://www.codingninjas.com/studio/problems/-binary-strings-with-no-consecutive-1s._893001 |
| |  | https://www.codingninjas.com/studio/problems/prefix-to-infix_1215000 |
| | https://leetcode.com/problems/lemonade-change/ | https://www.codingninjas.com/codestudio/problems/lemonade-change_8224112 |
| | https://leetcode.com/problems/rotate-array/ | https://www.codingninjas.com/codestudio/problems/rotate-array_1230543 |
| |  | https://www.codingninjas.com/studio/problems/detect-cycle-in-an-undirected-graph-_758967 |
| |  | https://www.codingninjas.com/studio/problems/find-pairs-with-given-sum-in-doubly-linked-list_1164172 |
| |  | https://www.codingninjas.com/studio/problems/two-numbers-with-odd-occurrences_8160466 |
| | https://leetcode.com/problems/single-number/ | https://www.codingninjas.com/codestudio/problems/find-the-single-element_6680465 |
| | https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/ | https://www.codingninjas.com/studio/problems/count-substring-with-abc_8160465 |
| | https://leetcode.com/problems/coin-change-2/ | https://www.codingninjas.com/studio/problems/ways-to-make-coin-change_630471 |
| |  | https://www.codingninjas.com/studio/problems/count-with-k-different-characters_1214627 |
| | https://leetcode.com/problems/maximum-subarray/ | https://www.codingninjas.com/codestudio/problems/maximum-subarray-sum_630526 |
| |  | https://www.codingninjas.com/studio/problems/count-frequency-in-a-range_8365446 |
| | https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/ | https://www.codingninjas.com/studio/problems/best-time-to-buy-and-sell-stock-with-cooldown_3125969 |
| | https://leetcode.com/problems/implement-queue-using-stacks/ | https://www.codingninjas.com/studio/problems/day-25-:-queue-using-stack_799482 |
| | https://leetcode.com/problems/set-matrix-zeroes/ | https://www.codingninjas.com/codestudio/problems/zero-matrix_1171153 |
| |  | https://www.codingninjas.com/studio/problems/find-length-of-loop_8160455 |
| | https://bit.ly/3MZQOct | https://www.codingninjas.com/codestudio/problems/allocate-books_1090540 |
| | https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/ | https://www.codingninjas.com/codestudio/problems/capacity-to-ship-packages-within-d-days_1229379 |
| |  | https://www.codingninjas.com/studio/problems/subset-sum_3843086 |
| | https://leetcode.com/problems/balanced-binary-tree/ | https://www.codingninjas.com/studio/problems/is-height-balanced-binary-tree_975497 |
| | https://leetcode.com/problems/armstrong-number/ | https://www.codingninjas.com/studio/problems/check-armstrong_589 |
| |  | https://www.codingninjas.com/studio/problems/introduction-to-doubly-linked-list_8160413 |
| | https://leetcode.com/problems/count-primes/ | https://www.codingninjas.com/studio/problems/prime-factorisation_1760849 |
| | https://leetcode.com/problems/palindrome-partitioning-ii/ | https://www.codingninjas.com/studio/problems/palindrome-partitioning-ll_873266 |
| | https://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/ | https://www.codingninjas.com/studio/problems/find-minimum-number-of-coins_975277 |
| | https://leetcode.com/problems/two-sum/ | https://www.codingninjas.com/codestudio/problems/reading_6845742 |
| | https://leetcode.com/problems/same-tree/ | https://www.codingninjas.com/studio/problems/check-identical-trees_799364 |
| |  | https://www.codingninjas.com/studio/problems/sjf_1172165 |
| | https://leetcode.com/problems/valid-palindrome/ | https://www.codingninjas.com/studio/problems/check-palindrome-recursive_624386 |
| | https://leetcode.com/problems/kth-smallest-element-in-a-bst/ | https://www.codingninjas.com/studio/problems/kth-smallest-node-in-bst_920441 |
| | https://leetcode.com/problems/combination-sum-ii/ | https://www.codingninjas.com/studio/problems/combination-sum-ii_1112622 |
| |  | https://www.codingninjas.com/studio/problems/insertion-sort_624381 |
| | https://leetcode.com/problems/jump-game/ | https://www.codingninjas.com/studio/problems/jump-game_3164697 |
| | https://leetcode.com/problems/power-of-two/ | https://www.codingninjas.com/studio/problems/power-of-two_893061 |
| | https://leetcode.com/problems/course-schedule-ii/ | https://www.codingninjas.com/studio/problems/course-schedule-ii_1069243 |
| |  | https://www.codingninjas.com/studio/problems/factorial-numbers-not-greater-than-n_8365435 |
| | https://leetcode.com/problems/largest-divisible-subset/ | https://www.codingninjas.com/studio/problems/divisible-set_3754960 |
| |  | https://www.codingninjas.com/codestudio/problems/tree-traversal_981269 |
| | https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/ | https://www.codingninjas.com/codestudio/problems/first-and-last-position-of-an-element-in-sorted-array_1082549 |
| |  | https://www.codingninjas.com/studio/problems/subset-sum_630213 |
| |  | https://www.codingninjas.com/studio/problems/counting-graphs_8357237 |
| |  | https://www.codingninjas.com/studio/problems/count-subsets-with-sum-k_3952532 |
