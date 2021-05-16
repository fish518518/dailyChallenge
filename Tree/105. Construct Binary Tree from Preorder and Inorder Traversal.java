/**时间复杂度：需要把每一个节点都造出来，所以是O(N)
 * 空间复杂度：O(N)递归的深度就是树的深度，最差情况下，树的深度是N，此外题目需要一个hashmap来存inorder，所以也是O(N)
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.

 

Example 1:


Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
Example 2:

Input: preorder = [-1], inorder = [-1]
Output: [-1]
 

Constraints:

1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.
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
// preorder
// 中(preStart)
// 左(preStart + 1, rootIdxInInOrder - inStart + preStart)    
// 右(rootIdxInInOrder - inStart + preStart + 1, preEnd)

// inorder
// 左(inStart, rootIdxInInOrder - 1)
// 中rootIdxInInOrder
// 右(rootIdxInInOrder + 1, inEnd)
import java.util.*;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
class Solution {
    Map<Integer, Integer> valToIdx;
    private TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd){
        if (preStart > preEnd) return null;
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int rootIdxInInOrder = valToIdx.get(rootVal);
        TreeNode leftNode = buildTreeHelper(preorder, preStart + 1, rootIdxInInOrder - inStart + preStart, inorder, inStart, rootIdxInInOrder - 1);
        TreeNode rightNode = buildTreeHelper(preorder, rootIdxInInOrder - inStart + preStart + 1, preEnd, inorder, rootIdxInInOrder + 1, inEnd);
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //中左右，左中右
        valToIdx = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            valToIdx.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
}