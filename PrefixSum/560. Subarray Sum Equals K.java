/**时间复杂度：O(N)
 * 空间复杂度：O(N)
 * https://leetcode.com/problems/subarray-sum-equals-k/
 * Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.

 

Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2
 

Constraints:

1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107
 */
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixsumToCount = new HashMap<>();
        prefixsumToCount.put(0, 1);
        int res = 0;
        int prefixSum = 0;
        for (int i = 1; i <= nums.length; i++) {
            prefixSum += nums[i - 1];
            int target = prefixSum - k;
            if (prefixsumToCount.containsKey(target)) {
                res += prefixsumToCount.get(target);
            }
            prefixsumToCount.put(prefixSum, prefixsumToCount.getOrDefault(prefixSum, 0) + 1);
        }
        return res;
    }
}