/**时间复杂度：每个元素可以有k个选择放到k个subset里面，所以O(k^N)
 * 空间复杂度：最多有k个递归，所以是O(N)?
 * https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
 * Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.

 

Example 1:

Input: nums = [4,3,2,3,5,2,1], k = 4
Output: true
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
Example 2:

Input: nums = [1,2,3,4], k = 3
Output: false
 

Constraints:

1 <= k <= nums.length <= 16
1 <= nums[i] <= 104
The frequency of each element is in the range [1, 4].
 */

class Solution {
    private boolean res = false;
    private void dfs(int[] nums, int k, int subset, int idx, int sum, int targetSum, boolean[] visited) {
        //base
        if (res || sum > targetSum) return;
        if (k - 1 == subset) {
            res = true;
            return;
        }
        if (sum == targetSum) {
            dfs(nums, k, subset + 1, nums.length - 1, 0, targetSum, visited);
            return;
        }
        int pre = -1;
        for (int i = idx; i >= 0; i--) {
            int cur = nums[i];
            if (!visited[i] && cur != pre) {
                    visited[i] = true;
                    pre = cur;
                    dfs(nums, k, subset, i - 1, sum + cur, targetSum, visited);
                    visited[i] = false;
            }
        }
    }
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        boolean[] visited = new boolean[nums.length];
        for (boolean v : visited) {
            v = false;
        }
        if (total % k != 0) return res;
        Arrays.sort(nums);
        dfs(nums, k, 0, nums.length - 1, 0, total / k, visited);
        return res;
    }
}