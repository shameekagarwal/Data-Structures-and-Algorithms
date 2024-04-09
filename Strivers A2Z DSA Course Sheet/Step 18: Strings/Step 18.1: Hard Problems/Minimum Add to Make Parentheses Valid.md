# Minimum Add to Make Parentheses Valid

- https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
- how i solved - difficult to explain - 
- if ( is encountered, add 1 to state, else subtract 1 from state
- now, if state ever touches -1 - keep on incrementing i till it is less than n and char at i is )
- these are the "consecutive extra closing braces", for which we need to add an extra opening brace
- so, add (-state) to result, make state as 0
- after quitting out of loop - we can be sure state will be >= 0
- if it is > 0, these are the extra opening braces, for which we need to add closing braces
- so, add state to result again

```java
class Solution {

    public int minAddToMakeValid(String s) {

        char[] str = s.toCharArray();
        int n = str.length;

        int state = 0;

        int result = 0;

        for (int i = 0; i < n;) {

            if (str[i] == '(') {
                state += 1;
                i += 1;
            } else {
                state -= 1;
                i += 1;
            }

            if (state < 0) {
                
                while (i < n && str[i] == ')') {
                    state -= 1;
                    i += 1;
                }
                result += (-state);
                state = 0;
            }
        }

        result += state;

        return result;
    }
}
```

## Solution 2

- while it might be doing the same thing, much more intuitive
- every time state goes to -1, we increment the count of "to add opening brace", and make state as 0
- out of the loop, state contains the value of "to add closing brace"
- we return "to add opening brace" + "to add closing brace"

```java
class Solution {

    public int minAddToMakeValid(String s) {

        char[] str = s.toCharArray();
        int n = str.length;

        int state = 0;
        int result = 0;
        int addedOpen = 0;

        for (int i = 0; i < n;) {

            if (str[i] == '(') {
                state += 1;
                i += 1;
            } else {
                state -= 1;
                i += 1;
            }

            if (state == -1) {
                state = 0;
                addedOpen += 1;
            }
        }

        int addedClose = state;

        return addedOpen + addedClose;
    }
}
```
