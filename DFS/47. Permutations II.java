/**时间复杂度：O(N * N!)
 * 空间复杂度：O(N)
 * https://leetcode.com/problems/permutations-ii/
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.

 

Example 1:

Input: nums = [1,1,2]
Output:
[[1,1,2],
 [1,2,1],
 [2,1,1]]
Example 2:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 

Constraints:

1 <= nums.length <= 8
-10 <= nums[i] <= 10
 */
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    private void dfs(int[] nums, boolean[] used, List<Integer> possible) {
        
        if (possible.size() == nums.length) {
            res.add(new ArrayList(possible));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            
            if (used[i] == false && (i == 0 || nums[i] != nums[i - 1] || used[i - 1] == true)) {
                used[i] = true;
                possible.add(cur);
                dfs(nums, used, possible);
                used[i] = false;
                possible.remove(possible.size() - 1);
            }
        }
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        List<Integer> possible = new ArrayList<>();
        dfs(nums, used, possible);
        return res;
    }
}