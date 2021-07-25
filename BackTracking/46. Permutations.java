/**时间复杂度：因为一共有n!种排列组合方式，每一种排列组合产生了一个结果数组以后，要把它复制到最终结果当中，所以是O(n * n !),但是因为数组长度不超过6，所以是常数O(1)
空间复杂度：不包含output的话，空间复杂度是O(N)，因为数组长度不超过6，所以是O(1)
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
    private void backtracking(List<Integer> permutation, List<List<Integer>> permutations, int[] nums, Set<Integer> added) {
        if (permutation.size() == nums.length) {
            permutations.add(new ArrayList<>(permutation));
            return;
        }
        for (int num : nums) {
            if (!added.contains(num)) {
                added.add(num);
                permutation.add(num);
                backtracking(permutation, permutations, nums, added);
                added.remove(num);
                permutation.remove(permutation.size() - 1);
            }
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> permutation = new ArrayList<>();
        List<List<Integer>> permutations = new ArrayList<>();
        Set<Integer> added = new HashSet<>();
        backtracking(permutation, permutations, nums, added);
        return permutations;
    }
}