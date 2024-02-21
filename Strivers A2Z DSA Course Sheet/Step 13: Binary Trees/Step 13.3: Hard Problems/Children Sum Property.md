# Children Sum Property

## Question 1 - Check if Property Holds

- https://www.codingninjas.com/studio/problems/children-sum-property_8357239
- i return -1 from left or right if property does not hold
- else return the node's value / node's children's sum
- if left or right return -1, means property is not satisfied, so return -1
- else, check if sum in children = parent. if yes, return the sum. else return -1

```java
public class Solution {

    public static boolean isParentSum(Node root) {
        return _isParentSum(root) != -1;
    }

    private static int _isParentSum(Node root) {

        if (root == null) return 0;

        if (root.left == null && root.right == null) {
            return root.data;
        }

        int leftSum = _isParentSum(root.left);
        int rightSum = _isParentSum(root.right);
        if (leftSum == -1 || rightSum == -1) return -1;
        
        int childrenSum = leftSum + rightSum;
        return (childrenSum == root.data) ? childrenSum : -1;
    }
}
```

## Question 2 - Convert so that Sum Property Holds

- https://www.geeksforgeeks.org/convert-an-arbitrary-binary-tree-to-a-tree-that-holds-children-sum-property/
- note - question does not say we need to make minimum increments
- one intuitive solution - increase parent to sum of children
- but what if sum of children is less than parent?
- so, we first go down - if sum of children is less than parent, then make both the same as parent
- this ensures when coming up, sum of children is greater than the parent, and the parent would have to be updated
- this also works when one of left or right is null, because then parent would not have to be updated
- we would not have to decrease the parent - either it stays the same, or it is incremented
- could not find practice link

```java
solve(node) {

  if (node == null) return;

  int childrenSum = 0;
  if (node.left != null) childrenSum += node.left.data;
  if (node.right != null) childrenSum += node.right.data;

  if (childrenSum < node.data) {
    if (node.left != null) node.left.data = node.data;
    if (node.right != null) node.right.data = node.data;
  }

  solve(node.left);
  solve(node.right);

  int updatedChildrenSum = 0;
  if (node.left != null) updatedChildrenSum += node.left.data;
  if (node.right != null) updatedChildrenSum += node.right.data;
  node.data = updatedChildrenSum;
}
```
