/**时间复杂度：O(N)
 * 空间复杂度：O(N)
 * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
 * Given an integer array nums and an integer k, return the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

 

Example 1:

Input: nums = [1,-1,5,-2,3], k = 3
Output: 4
Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
Example 2:

Input: nums = [-2,-1,2,1], k = 1
Output: 2
Explanation: The subarray [-1, 2] sums to 1 and is the longest.
 

Constraints:

1 <= nums.length <= 2 * 105
-104 <= nums[i] <= 104
-109 <= k <= 109
 */
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int maxLength = 0;
        Map<Integer, Integer> prefixsumToFirstIdx = new HashMap<>();
        prefixsumToFirstIdx.put(0, 0);
        int prefixSum = 0;
        for (int i = 1; i <= nums.length; i++) {
            prefixSum += nums[i - 1];
            int target = prefixSum - k;
            if (prefixsumToFirstIdx.containsKey(target)) {
                maxLength = Math.max(maxLength, i - prefixsumToFirstIdx.get(target));
            }
            if (!prefixsumToFirstIdx.containsKey(prefixSum)) {
                prefixsumToFirstIdx.put(prefixSum, i);
            }
        }
        return maxLength;
    }
}