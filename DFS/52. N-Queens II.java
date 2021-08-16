/**时间复杂度：O(N!)
 * 空间复杂度：O(N)，就是一个数组只保留皇后的位置
 * https://leetcode.com/problems/n-queens-ii/
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return the number of distinct solutions to the n-queens puzzle.

 

Example 1:


Input: n = 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
Example 2:

Input: n = 1
Output: 1
 

Constraints:

1 <= n <= 9
 */
class Solution {
    int res = 0;
    private boolean isValid(int[] queens, int row, int col) {
        //up
        for (int r = 0; r < row; r++) {
            if (queens[r] == col) {
                return false;
            }
        }
        //upLeft
        int r = row - 1;
        int c = col - 1;
        while (r >= 0 && c >= 0) {
            if (queens[r] == c) {
                return false;
            }
            r--;
            c--;
        }
        //upRight
        r = row - 1;
        c = col + 1;
        while (r >= 0 && c < queens.length) {
            if (queens[r] == c) {
                return false;
            }
            r--;
            c++;
        }
        return true;
    }
    private void dfs(int n, int idx, int[] queens) {
        if (idx == n) {
            res++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isValid(queens, idx, i)) {
                queens[idx] = i;
                dfs(n, idx + 1, queens);
                queens[idx] = -1;;
            }
        }
    }
    public int totalNQueens(int n) {
        int[] queens = new int[n];
        for (int q : queens) {
            q = -1;
        }
        dfs(n, 0, queens);
        return res;
    }
}