import java.util.ArrayList;
import java.util.List;

/**时间复杂度：最大深度是T/M，每个节点最大分支是N, 所以按照等积数列求和公式O(N^(T/M))
 * 空间复杂度：如果M是数组中的最小值，T是target，那么树的深度最大会是T/M，所以O(T/M)。
 * https://leetcode.com/problems/combination-sum/
 * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

 

Example 1:

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
Example 2:

Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
Example 3:

Input: candidates = [2], target = 1
Output: []
Example 4:

Input: candidates = [1], target = 1
Output: [[1]]
Example 5:

Input: candidates = [1], target = 2
Output: [[1,1]]
 

Constraints:

1 <= candidates.length <= 30
1 <= candidates[i] <= 200
All elements of candidates are distinct.
1 <= target <= 500
 */
class Solution {
    private void backtracking(int[] candidates, int target, List<Integer> combination, List<List<Integer>> combinations, int idx, int sum) {
        //basic case
        if (sum == target) {
            combinations.add(new ArrayList<>(combination));
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            int cur = candidates[i];
            combination.add(cur);
            sum += cur;
            backtracking(candidates, target, combination, combinations, i, sum);
            combination.remove(combination.size() - 1);
            sum -= cur;
        }
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> combination = new ArrayList<>();
        List<List<Integer>> combinations = new ArrayList<>();
        backtracking(candidates, target, combination, combinations, 0, 0);
        return combinations;
    }
}