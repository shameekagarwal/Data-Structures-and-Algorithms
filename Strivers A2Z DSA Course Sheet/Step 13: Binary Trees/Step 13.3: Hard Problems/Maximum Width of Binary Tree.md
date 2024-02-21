# Maximum Width of Binary Tree

- it actually means number of nodes between them when we do a level order traversal
- trick - number the nodes - left child is numbered 2x, right child is numbered 2x + 1
- so, subtract these numbers

![./maximum-width-initial](./maximum-width-initial.png)

- issue - imagine a skewed tree, with 10^5 nodes (left skewed) - the number will easily overflow very quickly - because 1st node would be 1, 2nd node 2, 3rd node 4 and so on
- trick - we try to make first node of the level start from 1 always - how? left child is 2x-1, right child is 2x. this way, nodes on all levels always start from 1

![./maximum-width-final](./maximum-width-final.png)

- notice the use ofo peekFirst and peekLast to calculate minimum and maximum tags

```java
class Solution {

    public int widthOfBinaryTree(TreeNode root) {
        
        if (root == null) return 0;
        
        int result = 0;
        Deque<QueueElement> queue = new ArrayDeque<>();
        queue.addLast(new QueueElement(root, 1));

        while (!queue.isEmpty()) {
            
            int currentLevelSize = queue.size();
            int minTagSeen = queue.peekFirst().tag;
            int maxTagSeen = queue.peekLast().tag;
            result = Math.max(result, maxTagSeen - minTagSeen + 1);
            
            for (int i = 0; i < currentLevelSize; i++) {
                
                QueueElement element = queue.removeFirst();
                
                if (i == 0) minTagSeen = element.tag;
                if (i == currentLevelSize - 1) maxTagSeen = element.tag;

                if (element.node.left != null) {
                    queue.addLast(new QueueElement(element.node.left, 2 * element.tag - 1));
                }

                if (element.node.right != null) {
                    queue.addLast(new QueueElement(element.node.right, 2 * element.tag));
                }
            }
        }

        return result;
    }

    static class QueueElement {

        TreeNode node;
        int tag;

        QueueElement(TreeNode node, int tag) {
            this.node = node;
            this.tag = tag;
        }

        @Override
        public String toString() {
            return "QueueElement(node=" + node + ", tag=" + tag + ")";
        }
    }
}
```
