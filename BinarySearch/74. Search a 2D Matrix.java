/**时间复杂度：O(log(M + N))
 * 空间复杂度：O(1)
 * https://leetcode.com/problems/search-a-2d-matrix/
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
 

Example 1:


Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true
Example 2:


Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-104 <= matrix[i][j], target <= 104
 */
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0;
        int right = rows - 1;
        int row = rows - 1;
        //找第一个大于等于target的元素
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cur = matrix[mid][cols - 1];
            if (cur == target) {
                return true;
            } else if (cur < target) {
                left = mid + 1;
            } else {
                row = mid;
                right = mid - 1;
            }
        }
        //目标在row这一行
        left = 0;
        right = cols - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cur = matrix[row][mid];
            if (cur == target) {
                return true;
            } else if (cur < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}