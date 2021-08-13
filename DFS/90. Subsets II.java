/**时间复杂度：O(N*2^N)
 * 空间复杂度：O(N)
 * https://leetcode.com/problems/subsets-ii/
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

 

Example 1:

Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
Example 2:

Input: nums = [0]
Output: [[],[0]]
 

Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
 */
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    private void dfs(int[] nums, int idx, boolean[] used, List<Integer> subset) {
        //base
        if (idx == nums.length) {
            res.add(new ArrayList(subset));
            return;
        }
        int cur = nums[idx];
        dfs(nums, idx + 1, used, subset);
        if (idx == 0 || cur != nums[idx - 1] || (used[idx - 1] == true)) {
            used[idx] = true;
            subset.add(cur);
            dfs(nums, idx + 1, used, subset);
            used[idx] = false;
            subset.remove(subset.size() - 1);
        }
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        boolean[] used = new boolean[nums.length];
        List<Integer> subset = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, used, subset);
        return res;
    }
}