/**时间复杂度：O(Nlog(N))，因为要排序
 * 空间复杂度：O(N)，因为排序
 * https://leetcode.com/problems/non-overlapping-intervals/
 * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

 

Example 1:

Input: [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
Example 2:

Input: [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
Example 3:

Input: [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 

Note:

You may assume the interval's end point is always bigger than its start point.
Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
 */
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0];
        });
        System.out.println();
        int removeCount = 0;
        int[] pre = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (cur[0] >= pre[1]) {
                pre = cur;
            } else {
                if (pre[1] > cur[1]) {
                    pre = cur;
                }
                removeCount++;
            }
        }
        return removeCount;
    }
}