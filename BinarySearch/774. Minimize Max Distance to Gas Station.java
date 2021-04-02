/**时间复杂度：O(Nlog(maxDistance))
 * 空间复杂度：O(1)
 * https://leetcode.com/problems/minimize-max-distance-to-gas-station/
 * You are given an integer array stations that represents the positions of the gas stations on the x-axis. You are also given an integer k.

You should add k new gas stations. You can add the stations anywhere on the x-axis, and not necessarily on an integer position.

Let penalty() be the maximum distance between adjacent gas stations after adding the k new stations.

Return the smallest possible value of penalty(). Answers within 10-6 of the actual answer will be accepted.

 

Example 1:

Input: stations = [1,2,3,4,5,6,7,8,9,10], k = 9
Output: 0.50000
Example 2:

Input: stations = [23,24,36,39,46,56,57,65,84,98], k = 1
Output: 14.00000
 

Constraints:

10 <= stations.length <= 2000
0 <= stations[i] <= 108
stations is sorted in a strictly increasing order.
1 <= k <= 106
 */
class Solution {
    private boolean isValid(int[] stations, int k, double maxDistance) {
        int newStationsCount = 0;
        for (int i = 1; i < stations.length; i++) {
            double distance = (double)(stations[i] - stations[i - 1]);
            newStationsCount += Math.ceil(distance / maxDistance) - 1; 
        }
        return newStationsCount <= k;
    }
    public double minmaxGasDist(int[] stations, int k) {
        double left = 0;
        double right = stations[stations.length - 1] - stations[0];
        double ans = right;
        while (left <= right) {
            double mid = (left + right) / 2.0;
            if (isValid(stations, k, mid)) {
                ans = mid;
                right = mid - 1e-6;
            } else {
                left = mid + 1e-6;
            }
        }
        return ans;
    }
}