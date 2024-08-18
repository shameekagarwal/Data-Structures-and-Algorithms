# Remove Duplicate Letters

- https://leetcode.com/problems/remove-duplicate-letters/
- if suppose current answer formed is "axy" and we encounter a "c"
- if x and y occur again in the string later, we can get rid of them and add a c instead
- so, we use a stack
- keep popping from stack
  - till the stack is not empty
  - till the top is greater than incoming character
  - till the top is already present inside the string later
- for checking the third condition efficiently, i maintain a map(char, set(index))
- if the index lookup size is non zero, only then we are allowed to pop
- when we finish processing a character, we always remove this character from the set
- finally, only do all of this if the incoming character is not already present inside the stack - i use a set for this
- note - i used o(n) space complexity, but i could have used o(26) - we only ned to know if the last occurrence of the character being popped is greater than current index. if yes, we can pop the character

```java
class Solution {

    public String removeDuplicateLetters(String s) {

        Map<Character, Set<Integer>> lookup = new HashMap<>();

        char[] arr = s.toCharArray();

        for (char i = 'a'; i <= 'z'; i++) {
            lookup.put(i, new HashSet<>());
        }

        for (int i = 0; i < arr.length; i++) {
            lookup.get(arr[i]).add(i);
        }

        Deque<Character> stack = new ArrayDeque<>();
        Set<Character> used = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {

            if (!used.contains(arr[i])) {

                while (!stack.isEmpty() && stack.peekLast() > arr[i] && 
                    !lookup.get(stack.peekLast()).isEmpty()) {

                    used.remove(stack.peekLast());
                    stack.removeLast();
                }

                used.add(arr[i]);
                stack.addLast(arr[i]);
            }

            lookup.get(arr[i]).remove(i);
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.removeFirst());
        }

        return sb.toString();
    }
}
```
