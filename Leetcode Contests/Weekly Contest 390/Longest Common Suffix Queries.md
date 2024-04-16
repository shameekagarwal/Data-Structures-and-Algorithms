# Longest Common Suffix Queries

- https://leetcode.com/problems/longest-common-suffix-queries/
- tries
- insert all words of wordsContainer into the trie in reverse order (since we want to match suffix)
- each node of trie also contains "longest word in wordsContainer with the same suffix" ka length and idx
- now, for each word in wordsQuery, we just need to traverse the trie (again in reverse order) till a trie node exists
- another important note - `root.update(len, i);` - this way, we ensure that root contains the details for the shortest string in wordsContainer - useful when the wordsQuery has no matching suffix - answer is word with shortest length in wordsContainer

```java
class Solution {

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {

        int m = wordsContainer.length;
        int n = wordsQuery.length;
        
        TrieNode root = new TrieNode(Integer.MAX_VALUE, -1);
        
        for (int i = 0; i < m; i++) {
            
            char[] word = wordsContainer[i].toCharArray();
            int len = word.length;
            
            root.update(len, i);
            
            TrieNode current = root;
            
            for (int j = len - 1; j > -1; j--) {
                if (current.children[word[j] - 'a'] == null) {
                    current.children[word[j] - 'a'] = new TrieNode(word.length, i);
                } else {
                    current.children[word[j] - 'a'].update(word.length, i);
                }
                current = current.children[word[j] - 'a'];
            }
        }
        
        int[] result = new int[n];
        
        for (int i = 0; i < n; i++) {
            
            char[] word = wordsQuery[i].toCharArray();
            int len = word.length;
            
            TrieNode current = root;
            
            for (int j = len - 1; j > -1; j--) {

                if (current.children[word[j] - 'a'] == null) break;
                current = current.children[word[j] - 'a'];
            }
            
            result[i] = current.idx;
        }
        
        return result;
    }
    
    static class TrieNode {
        
        TrieNode[] children;
        int longestLength;
        int idx;
        
        TrieNode(int longestLength, int idx) {
            children = new TrieNode['z' - 'a' + 1];
            this.longestLength = longestLength;
            this.idx = idx;
        }
        
        void update(int length, int idx) {
            if (length < longestLength) {
                longestLength = length;
                this.idx = idx;
            }
        }
    }
}
```
