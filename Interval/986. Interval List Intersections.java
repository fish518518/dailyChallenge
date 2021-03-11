/**时间复杂度：O(N + M)
 * 空间复杂度：O(1)
 * https://leetcode.com/problems/interval-list-intersections/
 * You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

A closed interval [a, b] (with a < b) denotes the set of real numbers x with a <= x <= b.

The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].

 

Example 1:


Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Example 2:

Input: firstList = [[1,3],[5,9]], secondList = []
Output: []
Example 3:

Input: firstList = [], secondList = [[4,8],[10,12]]
Output: []
Example 4:

Input: firstList = [[1,7]], secondList = [[3,10]]
Output: [[3,7]]
 

Constraints:

0 <= firstList.length, secondList.length <= 1000
firstList.length + secondList.length >= 1
0 <= starti < endi <= 109
endi < starti+1
0 <= startj < endj <= 109
endj < startj+1

 */
class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if (firstList.length == 0 || secondList.length == 0) {
            return new int[0][0];
        }
        List<int[]> resInList = new ArrayList<>();
        int idx1 = 0;
        int idx2 = 0;
        while (idx1 < firstList.length && idx2 < secondList.length) {
            int[] interval1 = firstList[idx1];
            int[] interval2 = secondList[idx2];
            int start1 = interval1[0];
            int start2 = interval2[0];
            int end1 = interval1[1];
            int end2 = interval2[1];
            //has intersection
            if (Math.max(start1, start2) <= Math.min(end1, end2)) {
                resInList.add(new int[]{Math.max(start1, start2), Math.min(end1, end2)});
            }
            if (interval1[1] < interval2[1]) {
                idx1++;
            } else {
                idx2++;
            }
        }
        return resInList.toArray(new int[resInList.size()][]);
    }
}