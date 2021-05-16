/**时间复杂度：O(nlog(sum)),因为对于每一次二分，都要去遍历一次数组，查看这个mid是否valid
 * 空间复杂度：O(1)
 * https://leetcode.com/problems/split-array-largest-sum/
 * Given an array nums which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.

Write an algorithm to minimize the largest sum among these m subarrays.

 

Example 1:

Input: nums = [7,2,5,10,8], m = 2
Output: 18
Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
Example 2:

Input: nums = [1,2,3,4,5], m = 2
Output: 9
Example 3:

Input: nums = [1,4,4], m = 3
Output: 4
 

Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 106
1 <= m <= min(50, nums.length)
 */
class Solution {
    public int splitArray(int[] nums, int m) {
        long right = 0;//所有元素的和
        long left = 0;//最大的那个元素
        for (int num : nums) {
            right += num;
            left = Math.max(left, num);
        }
        long ans = right;
        //找能满足条件（所有子元素和小于它）的最小值
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long sum = 0;
            int count = 1;
            for (int i = 0; i < nums.length; i++) {
                if (sum + nums[i] <= mid) {
                    sum += nums[i];
                } else {
                    sum = nums[i];
                    count++;
                }
            }
            if (count <= m) {
                //说明此时mid符合条件
                ans = Math.min(ans, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return (int)ans;
    }
}