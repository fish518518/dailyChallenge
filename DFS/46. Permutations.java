/**时间复杂度：一共有n!种，每一种都要复制一份结果。所以是O(n*n!)
 * 空间复杂度：O(n)，递归深度n
 * https://leetcode.com/problems/permutations/
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

 

Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:

Input: nums = [1]
Output: [[1]]
 

Constraints:

1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.
 */
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    private void dfs(int[] nums, boolean[] visited, List<Integer> possible) {
        if (possible.size() == nums.length) {
            res.add(new ArrayList(possible));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                possible.add(nums[i]);
                dfs(nums, visited, possible);
                visited[i] = false;
                possible.remove(possible.size() - 1);
            }
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> possible = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfs(nums, visited, possible);
        return res;
    }
}