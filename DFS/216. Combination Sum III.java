import java.util.ArrayList;
import java.util.List;

/**时间复杂度：最差情况每种组合都尝试一次，O(1)
 * 空间复杂度：stack最大深度O(1)
 * https://leetcode.com/problems/combination-sum-iii/
 * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:

Only numbers 1 through 9 are used.
Each number is used at most once.
Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.

 

Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Explanation:
1 + 2 + 4 = 7
There are no other valid combinations.
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6],[1,3,5],[2,3,4]]
Explanation:
1 + 2 + 6 = 9
1 + 3 + 5 = 9
2 + 3 + 4 = 9
There are no other valid combinations.
Example 3:

Input: k = 4, n = 1
Output: []
Explanation: There are no valid combinations.
Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are no valid combination.
Example 4:

Input: k = 3, n = 2
Output: []
Explanation: There are no valid combinations.
Example 5:

Input: k = 9, n = 45
Output: [[1,2,3,4,5,6,7,8,9]]
Explanation:
1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 = 45
There are no other valid combinations.
 

Constraints:

2 <= k <= 9
1 <= n <= 60
 */
class Solution {
    private void backtracking(List<Integer> combination, List<List<Integer>> combinations, int totalCount, int target, int count, int sum, int numberToAdd) {
        if (count == totalCount) {
            if (sum == target) {
                combinations.add(new ArrayList<>(combination));
            }
            return;
        }
        for (int i = numberToAdd; i <= 9; i++) {
            combination.add(i);
            count++;
            sum += i;
            backtracking(combination, combinations, totalCount, target, count, sum, i + 1);
            combination.remove(combination.size() - 1);
            count--;
            sum -= i;
        }
    }
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> combination = new ArrayList<>();
        List<List<Integer>> combinations = new ArrayList<>();
        backtracking(combination, combinations, k, n, 0, 0, 1);
        return combinations;
    }
}