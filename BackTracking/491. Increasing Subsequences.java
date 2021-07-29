/**时间复杂度：对于数组中的每个元素，都有取或者不取两种可能，所以一共是O(2^N)种可能，每个可能如果符合条件都要复制一遍给结果，所以是O(N*2^N)
空间复杂度：树的深度是O(N)，临时保存结果的变量也是O(N)，所以是O(N)
 * https://leetcode.com/problems/increasing-subsequences/
 * Given an integer array nums, return all the different possible increasing subsequences of the given array with at least two elements. You may return the answer in any order.

The given array may contain duplicates, and two equal integers should also be considered a special case of increasing sequence.

 

Example 1:

Input: nums = [4,6,7,7]
Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
Example 2:

Input: nums = [4,4,3,2,1]
Output: [[4,4]]
 

Constraints:

1 <= nums.length <= 15
-100 <= nums[i] <= 100
 */
class Solution {
    private boolean canAdd(List<Integer> subsequence, int[] nums, int idx, Set<Integer> added) {
        if (added.contains(nums[idx])) return false;
        if (subsequence.size() != 0 && nums[idx] < subsequence.get(subsequence.size() - 1)) return false;
        return true;
    }
    private void backtracking(List<Integer> subsequence, List<List<Integer>> res, int[] nums, int idx) {
        if (subsequence.size() >= 2) {
            res.add(new ArrayList(subsequence));
        }
        //base
        if (idx == nums.length) return;
        Set<Integer> added = new HashSet<>();
        for (int i = idx; i < nums.length; i++) {
            int cur = nums[i];
            if (canAdd(subsequence, nums, i, added)) {
                subsequence.add(cur);
                backtracking(subsequence, res, nums, i + 1);
                subsequence.remove(subsequence.size() - 1);
            }
            added.add(cur);
        }
    }
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<Integer> subsequence = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        backtracking(subsequence, res, nums, 0);
        return res;
    }
}