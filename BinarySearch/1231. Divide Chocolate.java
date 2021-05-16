/**
 * https://leetcode.com/problems/divide-chocolate/
 * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.

You want to share the chocolate with your K friends so you start cutting the chocolate bar into K+1 pieces using K cuts, each piece consists of some consecutive chunks.

Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.

Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.

 

Example 1:

Input: sweetness = [1,2,3,4,5,6,7,8,9], K = 5
Output: 6
Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]
Example 2:

Input: sweetness = [5,6,7,8,9,1,2,3,4], K = 8
Output: 1
Explanation: There is only one way to cut the bar into 9 pieces.
Example 3:

Input: sweetness = [1,2,2,1,2,2,1,2,2], K = 2
Output: 5
Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
 

Constraints:

0 <= K < sweetness.length <= 10^4
1 <= sweetness[i] <= 10^5

 */

 //时间复杂度：O(Nlog(totalSweet - minimumSweet))
//空间复杂度：O(1)
class Solution {
    private boolean isValid(int[] sweetness, int pieces, int minimumSweet) {
        int curPieces = 0;
        int curSweet = 0;
        for (int sweet : sweetness) {
            if (curSweet >= minimumSweet) {
                curPieces++;
                curSweet = sweet;
            } else {
                curSweet += sweet;
            }
        }
        if (curSweet >= minimumSweet) {
            curPieces++;
        }
        return curPieces >= pieces;
    }
    public int maximizeSweetness(int[] sweetness, int K) {
        //[minSweet, totalSweet]
        // int left = sweetness[0];
        // int right = 0;
        // for (int sweet : sweetness) {
        //     left = Math.min(left, sweet);
        //     right += sweet;
        // }
        int left = 1;
        int right = (int)1e9 / (K + 1);
        int ans = left;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isValid(sweetness, K + 1, mid)) {
                ans = Math.max(mid, left);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
}