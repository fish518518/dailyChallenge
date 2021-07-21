/**时间复杂度：O(N * 2^N),最多有2^N种组合，每一种组合都要将结果copy到res里面，每次copy都是O(N)
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
    
    private void backtracking(List<List<Integer>> res, List<Integer> subset, int[] nums, int idx, boolean[] added) {
        //base
        if (idx == nums.length) {
            res.add(new ArrayList<>(subset));
            return;
        }
        //no add
        backtracking(res, subset, nums, idx + 1, added);
        //add
        if (idx == 0 || nums[idx] != nums[idx - 1] || added[idx - 1] == true) {
            subset.add(nums[idx]);
            added[idx] = true;
            backtracking(res, subset, nums, idx + 1, added);
            subset.remove(subset.size() - 1);
            added[idx] = false;
        }
        
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> subset = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] added = new boolean[nums.length];
        for (int i = 0; i < added.length; i++) {
            added[i] = false;
        }
        backtracking(res, subset, nums, 0, added);
        return res;
    }
}