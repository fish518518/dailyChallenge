/**时间复杂度: O(Nlog(N)), 因为要排序
 * 空间复杂度: O(N), 用来排序
 * https://leetcode.com/problems/remove-covered-intervals/
 * Given a list of intervals, remove all intervals that are covered by another interval in the list.

Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.

After doing so, return the number of remaining intervals.

 

Example 1:

Input: intervals = [[1,4],[3,6],[2,8]]
Output: 2
Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
Example 2:

Input: intervals = [[1,4],[2,3]]
Output: 1
Example 3:

Input: intervals = [[0,10],[5,12]]
Output: 2
Example 4:

Input: intervals = [[3,10],[4,10],[5,11]]
Output: 2
Example 5:

Input: intervals = [[1,2],[1,4],[3,4]]
Output: 1
 

Constraints:

1 <= intervals.length <= 1000
intervals[i].length == 2
0 <= intervals[i][0] < intervals[i][1] <= 10^5
All the intervals are unique.
 */

import java.util.Arrays;
class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        int remaining = 0;
        int[] pre = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (cur[0] >= pre[0] && cur[1] <= pre[1]) {
                continue;
            } else {
                pre = cur;
                remaining++;
            }
        }
        return (remaining + 1);
    }
}