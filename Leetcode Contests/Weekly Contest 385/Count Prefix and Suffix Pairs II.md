# Count Prefix and Suffix Pairs II

- https://leetcode.com/problems/count-prefix-and-suffix-pairs-ii/
- instead of the usual trie, where we have 26 pointers
- now, we will have pointers for a combination of 2 characters
- because e.g. if ab needs to be a prefix and suffix of abcba
  - first a needs to match both first and last a
  - second b needs to match both second and second last b
- so, we store both i and n - 1 - i for each character in the word
- we use the [count variation](../../Strivers%20A2Z%20DSA%20Course%20Sheet/Step%2017:%20Tries/Step%2017.2:%20Problems/Implement%20Trie%20ll.md)
- finally, when i was trying to use ptrs as so - `private TrieNode[][] ptr = new TrieNode[26][26];`, i got memory limit exceeded. my doubt, to confirm - does initializing an array of objects reserve memory for storing address i.e. an integer, even though all elements, of the array will be null?
- so, i switched to `map<hash, TrieNode>` which solved the problem. hash - `char_1 * 26 + char_2`

```java
class Solution {

    public long countPrefixSuffixPairs(String[] words) {

        int n = words.length;
        long result = 0;
        Trie trie = new Trie();

        for (int i = n - 1; i > -1; i--) {
            result += (trie.insert(words[i].toCharArray()) - 1);
        }

        return result;
    }

    static class Trie {

        private TrieNode head = new TrieNode();

        public int insert(char[] word) {

            int n = word.length;
            TrieNode current = head;

            for (int i = 0; i < n; i++) {

                int front = word[i] - 'a';
                int back = word[n - 1 - i] - 'a';
                int hash = (front * 26) + back;

                if (current.ptr.get(hash) == null) {
                    current.ptr.put(hash, new TrieNode());
                }

                current = current.ptr.get(hash);
                current.count += 1;
            }

            return current.count;
        }

        static class TrieNode {

            private Map<Integer, TrieNode> ptr = new HashMap<>();
            private int count = 0;
        }
    }
}
```
