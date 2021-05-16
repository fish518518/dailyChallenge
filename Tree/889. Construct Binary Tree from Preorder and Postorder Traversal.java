/**时间复杂度：O(N)，需要把所有的节点都创建出来，所以是O(N)
 * 空间复杂度：O(N)，需要一个hashmap来保存post，所以是O(N)，另外递归深度也是O(N)
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
 * Return any binary tree that matches the given preorder and postorder traversals.

Values in the traversals pre and post are distinct positive integers.

 

Example 1:

Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]
 

Note:

1 <= pre.length == post.length <= 30
pre[] and post[] are both permutations of 1, 2, ..., pre.length.
It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.
 */
 //Definition for a binary tree node.

// pre
// 中[preStart]
// 左[preStart + 1, leftRootIdxInPost - postStart + preStart + 1]
// 右[leftRootIdxInPost - postStart + preStart + 2, preEnd]

// post
// 左[postStart, leftRootIdxInPost]
// 右[leftRootIdxInPost + 1, postEnd - 1]
// 中[postEnd]
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
    private TreeNode constructFromPrePostHelper(int[] pre, int preStart, int preEnd, int[] post, int postStart, int postEnd) {
        if (preStart > preEnd) return null;
        if (preStart == preEnd) return new TreeNode(pre[preStart]);
        int rootVal = pre[preStart];
        TreeNode root = new TreeNode(rootVal);
        int leftRootVal = pre[preStart + 1];
        int leftRootIdxInPost = valToIdx.get(leftRootVal);
        TreeNode left = constructFromPrePostHelper(pre, preStart + 1, leftRootIdxInPost - postStart + preStart + 1, post, postStart, leftRootIdxInPost);
        TreeNode right = constructFromPrePostHelper(pre, leftRootIdxInPost - postStart + preStart + 2, preEnd, post, leftRootIdxInPost + 1, postEnd - 1);
        root.left = left;
        root.right = right;
        return root;
    }
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        //中左右->左子树根节点A，左右中->找到A，分开左右
        valToIdx = new HashMap<>();
        for (int i = 0; i < post.length; i++) {
            int val = post[i];
            valToIdx.put(val, i);
        }
        return constructFromPrePostHelper(pre, 0, pre.length - 1, post, 0, post.length - 1);
    }
}