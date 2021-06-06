/**
 * 时间复杂度：O(log(10e9) * n)
 * 空间复杂度：O(n)，排序用
 * https://leetcode.com/problems/magnetic-force-between-two-balls/
 * In universe Earth C-137, Rick discovered a special form of magnetic force between two balls if they are put in his new invented basket. Rick has n empty baskets, the ith basket is at position[i], Morty has m balls and needs to distribute the balls into the baskets such that the minimum magnetic force between any two balls is maximum.

Rick stated that magnetic force between two different balls at positions x and y is |x - y|.

Given the integer array position and the integer m. Return the required force.

 

Example 1:


Input: position = [1,2,3,4,7], m = 3
Output: 3
Explanation: Distributing the 3 balls into baskets 1, 4 and 7 will make the magnetic force between ball pairs [3, 3, 6]. The minimum magnetic force is 3. We cannot achieve a larger minimum magnetic force than 3.
Example 2:

Input: position = [5,4,3,2,1,1000000000], m = 2
Output: 999999999
Explanation: We can use baskets 1 and 1000000000.
 

Constraints:

n == position.length
2 <= n <= 10^5
1 <= position[i] <= 10^9
All integers in position are distinct.
2 <= m <= position.length
 */
import java.util.Arrays;

class Solution {
    private boolean isOK(int[] position, int m, int minMagnetic) {
        //测试是否可以最小minMagnetic放下m个球
        int ballCount = 1;
        int preIdx = 0;
        for (int i = 1; i < position.length; i++) {
            if (position[i] - position[preIdx] >= minMagnetic) {
                preIdx = i;
                ballCount++;
            }
        }
        return ballCount >= m;
    }
    public int maxDistance(int[] position, int m) {
        //二分，最小磁力范围[1, 10e9-1]
        int left = 1;
        int right = (int)Math.pow(10, 9) - 1;
        int result = 1;
        Arrays.sort(position);
        while (left <= right) {
            int mid = left + (right - left) / 2;//minMagnetic
            if (isOK(position, m, mid)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
}