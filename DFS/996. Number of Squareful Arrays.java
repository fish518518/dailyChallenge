/**时间复杂度：O(N!)
空间复杂度：O(N)
 * https://leetcode.com/problems/number-of-squareful-arrays/
 * An array is squareful if the sum of every pair of adjacent elements is a perfect square.

Given an integer array nums, return the number of permutations of nums that are squareful.

Two permutations perm1 and perm2 are different if there is some index i such that perm1[i] != perm2[i].

 

Example 1:

Input: nums = [1,17,8]
Output: 2
Explanation: [1,8,17] and [17,8,1] are the valid permutations.
Example 2:

Input: nums = [2,2,2]
Output: 1
 

Constraints:

1 <= nums.length <= 12
0 <= nums[i] <= 109
 */

class Solution {
    int ans = 0;
    private boolean perfectSquare(int cur, int pre) {
        if (pre == -1) {
            return true;
        } else {
            double sq = Math.sqrt(cur + pre);
            return (sq - Math.floor(sq) == 0);
        }
    }
    private void backtracking(int[] nums, int addedNum, boolean[] used, int pre) {
        //base
        if (addedNum == nums.length) {
            ans++;
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i] && (i == 0 || nums[i] != nums[i - 1] || used[i - 1]) && perfectSquare(nums[i], pre)) {
                used[i] = true;
                backtracking(nums, addedNum + 1, used, nums[i]);
                used[i] = false;
            }
        }
    }
    public int numSquarefulPerms(int[] nums) {
        boolean[] used = new boolean[nums.length];
        for (boolean u : used) {
            u = false;
        }
        Arrays.sort(nums);
        backtracking(nums, 0, used, -1);
        return ans;
    }
}