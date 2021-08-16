/**时间复杂度：O(N*N！)
 * 空间复杂度：O(N^2)，保存整个子结果
 * https://leetcode.com/problems/n-queens/
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.

 

Example 1:


Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
Example 2:

Input: n = 1
Output: [["Q"]]
 

Constraints:

1 <= n <= 9
 */
class Solution {
    List<List<String>> res = new ArrayList<>();
    private List<String> constructor(char[][] board) {
        List<String> r = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < board[row].length; col++) {
                sb.append(board[row][col]);
            }
            r.add(sb.toString());
        }
        return r;
    }
    private boolean isValid(char[][] board, int row, int col) {
        //up
        for (int r = 0; r < row; r++) {
            if (board[r][col] == 'Q') {
                return false;
            }
        }
        //leftup
        int r = row - 1;
        int c = col - 1;
        while (r >= 0 && c >= 0) {
            if (board[r][c] == 'Q') {
                return false;
            }
            r--;
            c--;
        }
        //rightup
        r = row - 1;
        c = col + 1;
        while (r >= 0 && c < board.length) {
            if (board[r][c] == 'Q') {
                return false;
            }
            r--;
            c++;
        }
        return true;
    }
    private void dfs(int n, int idx, char[][] board) {
        if (idx == n) {
            res.add(constructor(board));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isValid(board, idx, i)) {
                board[idx][i] = 'Q';
                dfs(n, idx + 1, board);
                board[idx][i] = '.';
            }
        }
    }
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        dfs(n, 0, board);
        return res;
    }
}