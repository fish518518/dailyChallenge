/**时间复杂度：O(N)
 * 空间复杂度：O(N)
 * 同105
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.

 

Example 1:


Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]
Example 2:

Input: inorder = [-1], postorder = [-1]
Output: [-1]
 

Constraints:

1 <= inorder.length <= 3000
postorder.length == inorder.length
-3000 <= inorder[i], postorder[i] <= 3000
inorder and postorder consist of unique values.
Each value of postorder also appears in inorder.
inorder is guaranteed to be the inorder traversal of the tree.
postorder is guaranteed to be the postorder traversal of the tree.

 * 
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
// inorder
// 左 [inStart, rootIdxInInorder - 1]
// 中 rootIdxInInorder
// 右 [rootIdxInInorder + 1, inEnd]

// postorder
// 左 [postStart, rootIdxInInorder - 1 - inStart + postStart]
// 右 [postEnd - inEnd + rootIdxInInorder, postEnd - 1]
// 中(postEnd)
class Solution {
    Map<Integer, Integer> valToIdx;
    private TreeNode buildTreeHelper(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd) return null;
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        int rootIdxInInorder = valToIdx.get(rootVal);
        TreeNode left = buildTreeHelper(inorder, inStart, rootIdxInInorder - 1, postorder, postStart,rootIdxInInorder - 1 - inStart + postStart);
        TreeNode right = buildTreeHelper(inorder, rootIdxInInorder + 1, inEnd, postorder, postEnd - inEnd + rootIdxInInorder, postEnd - 1);
        root.left = left;
        root.right = right;
        return root;
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        //左中右， 左右中
        valToIdx = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            valToIdx.put(inorder[i], i);
        }
        return buildTreeHelper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }
}