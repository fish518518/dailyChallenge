/**时间复杂度：一共n！种排列组合，所以一共O(n!)
 * 空间复杂度：O(N)，递归深度是O(N), used数组是O(N)，总共O(N)
 * https://leetcode.com/problems/beautiful-arrangement/
 * Suppose you have n integers labeled 1 through n. A permutation of those n integers perm (1-indexed) is considered a beautiful arrangement if for every i (1 <= i <= n), either of the following is true:

perm[i] is divisible by i.
i is divisible by perm[i].
Given an integer n, return the number of the beautiful arrangements that you can construct.

 

Example 1:

Input: n = 2
Output: 2
Explanation: 
The first beautiful arrangement is [1,2]:
    - perm[1] = 1 is divisible by i = 1
    - perm[2] = 2 is divisible by i = 2
The second beautiful arrangement is [2,1]:
    - perm[1] = 2 is divisible by i = 1
    - i = 2 is divisible by perm[2] = 1
Example 2:

Input: n = 1
Output: 1
 

Constraints:

1 <= n <= 15
 */
class Solution {
    int arrangements = 0;
    private void dfs(int idx, boolean[] used, int n) {
        if (idx == n + 1) {
            arrangements++;
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (!used[i] && (i % idx == 0 || idx % i == 0)) {
                used[i] = true;
                dfs(idx + 1, used, n);
                used[i] = false;
            }
        }
    }
    public int countArrangement(int n) {
        boolean[] used = new boolean[n + 1];
        dfs(1, used, n);
        return arrangements;
    }
}