import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**时间复杂度：最差情况是遍历所有排列组合，也就是O(2^N)
 * 空间复杂度：O(N)，需要建立一个visited数组。recursion的最差情况下，高度是N，所以是O(2N)，也就是O(N)
 * https://leetcode.com/problems/combination-sum-ii/
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

 

Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: 
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5
Output: 
[
[1,2,2],
[5]
]
 

Constraints:

1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30
 */
class Solution {
    private void backtracking(int[] candidates, int target, List<Integer> combination, List<List<Integer>> combinations, int idx, int sum, boolean[] visited) {
        if (sum == target) {
            combinations.add(new ArrayList<>(combination));
            return;
        }
        if (sum > target) return;
        for (int i = idx; i < candidates.length; i++) {
            int cur = candidates[i];
            //如果和前面那个元素相等，那么只有前面那个加进去过才可以加
            if (i == 0 || candidates[i - 1] != cur || visited[i - 1] == true) {
                sum += cur;
                combination.add(cur);
                visited[i] = true;
                backtracking(candidates, target, combination, combinations, i + 1, sum, visited);
                sum -= cur;
                combination.remove(combination.size() - 1);
                visited[i] = false;
            }
        }
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> combination = new ArrayList<>();
        List<List<Integer>> combinations = new ArrayList<>();
        Arrays.sort(candidates);
        boolean[] visited = new boolean[candidates.length];
        for (boolean v : visited) {
            v = false;
        }
        backtracking(candidates, target, combination, combinations, 0, 0, visited);
        return combinations;
    }
}