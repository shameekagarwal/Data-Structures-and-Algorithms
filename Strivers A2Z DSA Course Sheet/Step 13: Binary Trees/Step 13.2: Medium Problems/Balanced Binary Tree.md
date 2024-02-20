#  Balanced Binary Tree

- https://leetcode.com/problems/balanced-binary-tree/
- max difference between left and right subtree should be 1
- naive solution - O(n^2) 
  ```java
  class Solution {
  
      public boolean isBalanced(TreeNode root) {
          if (root == null) return true;
          int leftHeight = height(root.left);
          int rightHeight = height(root.right);
          if (Math.abs(leftHeight - rightHeight) > 1) return false;
          return isBalanced(root.left) && isBalanced(root.right);
      }
  
      private int height(TreeNode root) {
          if (root == null) return 0;
          return Math.max(height(root.left), height(root.right)) + 1;
      }
  }
  ```
- optimal - O(N) - return -1 if either of the subtrees is not balanced, else return the height

  ```java
  class Solution {
  
      public boolean isBalanced(TreeNode root) {
          return _isBalanced(root) != -1;
      }
  
      private int _isBalanced(TreeNode root) {
          
          if (root == null) return 0;
          
          int leftHeight = _isBalanced(root.left);
          if (leftHeight == -1) return -1;
  
          int rightHeight = _isBalanced(root.right);
          if (rightHeight == -1) return -1;
  
          if (Math.abs(leftHeight - rightHeight) > 1) return -1;
  
          return Math.max(leftHeight, rightHeight) + 1;
      }
  }
  ```
