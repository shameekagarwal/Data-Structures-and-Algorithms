class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            boolean isDivisibleByThree = (i % 3 == 0);
            boolean isDivisibleByFive = (i % 5 == 0);
            if (isDivisibleByThree && isDivisibleByFive) result.add("FizzBuzz");
            else if (isDivisibleByThree) result.add("Fizz");
            else if (isDivisibleByFive) result.add("Buzz");
            else result.add(Integer.toString(i));
        }
        return result;
    }
}

// https://leetcode.com/problems/fizz-buzz/

