/**
 * 时间复杂度：O(2^N)
 * 空间复杂度：O(N)
 * https://leetcode.com/problems/subsets/
 * Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

 

Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]
 

Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
 */
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    private void dfs(int[] nums, int idx, List<List<Integer>> res, List<Integer> subset) {
        
        //base
        if (idx == nums.length) {
            res.add(new ArrayList(subset));
            return;
        }
        
        int cur = nums[idx];
        dfs(nums, idx + 1, res, subset);
        subset.add(cur);
        
        dfs(nums, idx + 1, res, subset);
        subset.remove(subset.size() - 1);
    }
    public List<List<Integer>> subsets(int[] nums) {
        
        List<Integer> subset = new ArrayList();
        dfs(nums, 0, res, subset);
        return res;
    }
}