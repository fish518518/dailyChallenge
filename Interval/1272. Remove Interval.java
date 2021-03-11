/**
 * 时间复杂度：O(N)
 * 空间复杂度：O(1)
 * https://leetcode.com/problems/remove-interval/
 * A set of real numbers can be represented as the union of several disjoint intervals, where each interval is in the form [a, b). A real number x is in the set if one of its intervals [a, b) contains x (i.e. a <= x < b).

You are given a sorted list of disjoint intervals intervals representing a set of real numbers as described above, where intervals[i] = [ai, bi] represents the interval [ai, bi). You are also given another interval toBeRemoved.

Return the set of real numbers with the interval toBeRemoved removed from intervals. In other words, return the set of real numbers such that every x in the set is in intervals but not in toBeRemoved. Your answer should be a sorted list of disjoint intervals as described above.

 

Example 1:


Input: intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
Output: [[0,1],[6,7]]
Example 2:


Input: intervals = [[0,5]], toBeRemoved = [2,3]
Output: [[0,2],[3,5]]
Example 3:

Input: intervals = [[-5,-4],[-3,-2],[1,2],[3,5],[8,9]], toBeRemoved = [-1,4]
Output: [[-5,-4],[-3,-2],[4,5],[8,9]]
 

Constraints:

1 <= intervals.length <= 104
-109 <= ai < bi <= 109
 */
class Solution {
    private void addInterval(List<List<Integer>> res, int start, int end) {
        List<Integer> subRes = new ArrayList<>();
        subRes.add(start);
        subRes.add(end);
        res.add(subRes);
    }
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> res = new ArrayList<>();
        int removeStart = toBeRemoved[0];
        int removeEnd = toBeRemoved[1];
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            if (Math.max(start, removeStart) >= Math.min(end, removeEnd)) {
                //no intersection
                addInterval(res, start, end);
            } else {
                if (start < removeStart) {
                    addInterval(res, start, removeStart);
                }
                if (end > removeEnd) {
                    addInterval(res, removeEnd, end);
                }
            }
        }
        return res;
    }
}