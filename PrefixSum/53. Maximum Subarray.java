/**时间复杂度：O(N)
 * 空间复杂度：O(N)
 * https://leetcode.com/problems/maximum-subarray/
 * 53. Maximum Subarray
Easy

13182

628

Add to List

Share
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

 

Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Example 2:

Input: nums = [1]
Output: 1
Example 3:

Input: nums = [5,4,-1,7,8]
Output: 23
 

Constraints:

1 <= nums.length <= 3 * 104
-105 <= nums[i] <= 105
 */
class Solution {
    public int maxSubArray(int[] nums) {
        int largestSum = Integer.MIN_VALUE;
        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        int minPrefixSum = 0;
        for (int i = 1; i <= nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
            int diff = prefixSum[i] - minPrefixSum;
            largestSum = Math.max(largestSum, diff);
            minPrefixSum = Math.min(minPrefixSum, prefixSum[i]);
        }
        return largestSum;
    }
}