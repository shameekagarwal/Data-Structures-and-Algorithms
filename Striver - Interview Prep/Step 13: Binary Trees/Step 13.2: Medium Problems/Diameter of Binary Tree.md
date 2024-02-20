# Diameter of Binary Tree

- https://leetcode.com/problems/diameter-of-binary-tree/
- maximum path between any two nodes
- but, this need not pass through the root
- for a node, diameter through it = left height + right height
- naive - O(n^2)
  ```java
  class Solution {
  
      public int diameterOfBinaryTree(TreeNode root) {
          if (root == null) return 0;
          int leftDiameter = diameterOfBinaryTree(root.left);
          int rightDiameter = diameterOfBinaryTree(root.right);
          int leftHeight = height(root.left);
          int rightHeight = height(root.right);
          return Math.max(leftHeight + rightHeight, Math.max(leftDiameter, rightDiameter));
      }
  
      private int height(TreeNode root) {
          if (root == null) return 0;
          return Math.max(height(root.left), height(root.right)) + 1;
      }
  }
  ```
- optimal - O(n)
  ```java
  class Solution {
  
      public int diameterOfBinaryTree(TreeNode root) {
          return _diameterOfBinaryTree(root).diameter;
      }
  
      private Diameter _diameterOfBinaryTree(TreeNode root) {
          
          if (root == null) return new Diameter(0, 0);
          
          Diameter leftDiameter = _diameterOfBinaryTree(root.left);
          Diameter rightDiameter = _diameterOfBinaryTree(root.right);
          
          int height = Math.max(leftDiameter.height, rightDiameter.height) + 1;
          int maxDiameterAtCurrentNode = leftDiameter.height + rightDiameter.height;
  
          return new Diameter(
              height,
              Math.max(maxDiameterAtCurrentNode, Math.max(leftDiameter.diameter, rightDiameter.diameter))
          );
      }
  
      private static class Diameter {
  
          int height;
          int diameter;
  
          Diameter(int height, int diameter) {
              this.height = height;
              this.diameter = diameter;
          }
      }
  }
  ```
