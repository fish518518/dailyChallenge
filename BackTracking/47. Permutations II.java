/**
 * 时间复杂度：一共N!种组合，每种组合都要复制一遍到结果中，所以一共O(N*N!)
 * 空间复杂度：需要一个visited数组，O(N)，recursion最大深度是O(N)，所以一共O(N)
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
    private void backtracking(List<Integer> permutation, List<List<Integer>> permutations, int[] nums, boolean[] visited) {
        //base
        if (permutation.size() == nums.length) {
            permutations.add(new ArrayList<>(permutation));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (visited[i] == false && (i == 0 || nums[i] != nums[i - 1] || visited[i - 1] == true)) {
                permutation.add(cur);
                visited[i] = true;
                backtracking(permutation, permutations, nums, visited);
                permutation.remove(permutation.size() - 1);
                visited[i] = false;
            }
        }
        
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<Integer> permutation = new ArrayList<>();
        List<List<Integer>> permutations = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        for (boolean v : visited) {
            v = false;
        }
        backtracking(permutation, permutations, nums, visited);
        return permutations;
    }
}