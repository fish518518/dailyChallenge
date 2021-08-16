/**时间复杂度：K个位置，每个位置可以有N个选择，所以一共O(K*K^N)
 * 空间复杂度：O(K)
 * https://leetcode.com/problems/combinations/
 * Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].

You may return the answer in any order.

 

Example 1:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
Example 2:

Input: n = 1, k = 1
Output: [[1]]
 

Constraints:

1 <= n <= 20
1 <= k <= n
 */

class Solution {
    List<List<Integer>> res = new ArrayList<>();
    private void dfs(int n, int k, List<Integer> combination) {
        if (combination.size() == k) {
            res.add(new ArrayList(combination));
            return;
        }
        int start = 0;
        if (combination.size() != 0) {
            start = combination.get(combination.size() - 1);
        }
        for (int num = start + 1; num <= n; num++) {
            combination.add(num);
            dfs(n, k, combination);
            combination.remove(combination.size() - 1);
        }
    }
    public List<List<Integer>> combine(int n, int k) {
        List<Integer> combination = new ArrayList<>();
        dfs(n, k, combination);
        return res;
    }
}