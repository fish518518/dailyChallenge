/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

 

Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
Example 3:

Input: root = [1,2], p = 1, q = 2
Output: 1
 

Constraints:

The number of nodes in the tree is in the range [2, 105].
-109 <= Node.val <= 109
All Node.val are unique.
p != q
p and q will exist in the tree.

 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    //一个node是lca，代表1. 左右子树分别有p和q 或者 2. 自己是p或者q，自己的孩子包含另外一个
    TreeNode lca = null;
    private boolean[] traverse(TreeNode node, TreeNode p, TreeNode q) {
        //返回当前节点是否包含p或者q，并且顺便更新lca
        boolean[] res = new boolean[]{false, false};
        if (node == null) return res;
        
        boolean[] leftRes = traverse(node.left, p, q);//返回左子树是否包含节点p或者q
        boolean leftContainsP = leftRes[0];
        boolean leftContainsQ = leftRes[1];
        
        boolean[] rightRes = traverse(node.right, p, q);//返回右子树是否包含节点p或者q
        boolean rightContainsP = rightRes[0];
        boolean rightContainsQ = rightRes[1];
        
        if (lca == null) {
            if ((leftContainsP && rightContainsQ) || (leftContainsQ && rightContainsP) || (node == p && (leftContainsQ || rightContainsQ)) || (node == q && (leftContainsP || rightContainsP))) {
                lca = node;
            }
        }
        if (node == p || leftContainsP || rightContainsP) res[0] = true;
        if (node == q || leftContainsQ || rightContainsQ) res[1] = true;
        return res;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        traverse(root, p, q); 
        return lca;
    }
}