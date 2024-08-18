# Alien Dictionary

- https://leetcode.com/problems/alien-dictionary/
- construct a graph using adjacent words - dependency from word at i to i-1
- why - we want lexicographically smaller node to come first, and A->B means topological sort is A, B
- note - dictionaries like this are invalid - `["ab", "a"]` - check them separately, because our algorithm would not construct any edge for them anyway

```java
class Solution {

    public String alienOrder(String[] words) {

        for (int i = 1; i < words.length; i++) {

            if (words[i].length() < words[i - 1].length()) {

                String substring = words[i - 1].substring(0, words[i].length());

                if (words[i].equals(substring)) {
                    return "";
                }
            }
        }

        Map<Character, List<Character>> adj = constructDependencyGraph(words);
        List<Character> characters = getCharacters(words);
        Map<Character, Integer> indegree = constructIndegree(adj, characters);
        List<Character> topologicalSort = kahn(indegree, adj, characters);
        
        if (topologicalSort.size() != characters.size()) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        for (char node : topologicalSort) {
            result.append(node);
        }

        return result.toString();
    }

    private List<Character> kahn(Map<Character, Integer> indegree, Map<Character, List<Character>> adj, List<Character> characters) {

        List<Character> topologicalSort = new ArrayList<>();
        Deque<Character> queue = new ArrayDeque<>();

        for (char node : characters) {

            if (!indegree.containsKey(node)) {
                queue.addLast(node);
            }
        }

        while (!queue.isEmpty()) {

            char node = queue.removeFirst();
            topologicalSort.add(node);

            for (char neighbor : adj.getOrDefault(node, List.of())) {

                indegree.put(neighbor, indegree.get(neighbor) - 1);

                if (indegree.get(neighbor) == 0) {
                    queue.addLast(neighbor);
                }
            }
        }

        return topologicalSort;
    }

    private Map<Character, Integer> constructIndegree(Map<Character, List<Character>> adj, List<Character> characters) {

        Map<Character, Integer> indegree = new HashMap<>();

        for (int i = 0; i < characters.size(); i++) {

            for (char neighbor : adj.getOrDefault(characters.get(i), List.of())) {

                indegree.put(neighbor, indegree.getOrDefault(neighbor, 0) + 1);
            }
        }

        return indegree;
    }

    private Map<Character, List<Character>> constructDependencyGraph(String[] words) {

        Map<Character, List<Character>> adj = new HashMap<>();

        for (int i = 1; i < words.length; i++) {

            char[] from = words[i - 1].toCharArray();
            char[] to = words[i].toCharArray();

            for (int j = 0; j < Math.min(from.length, to.length); j++) {

                if (from[j] != to[j]) {

                    if (!adj.containsKey(from[j])) {
                        adj.put(from[j], new ArrayList<>());
                    }

                    adj.get(from[j]).add(to[j]);

                    break;
                }
            }
        }

        return adj;
    }

    private List<Character> getCharacters(String[] words) {

        Set<Character> characters = new HashSet<>();

        for (String word : words) {
            for (char c : word.toCharArray()) {
                characters.add(c);
            }
        }

        return characters.stream().toList();
    }
}
```
