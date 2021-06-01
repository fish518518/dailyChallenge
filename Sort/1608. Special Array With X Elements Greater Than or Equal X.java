/**时间复杂度：O(nlog(n))， 排序以及后面遍历n，对每个n用二分查找log(n)，总共nlog(n)
 * 空间复杂度：O(log(n))，排序
 * https://leetcode.com/problems/special-array-with-x-elements-greater-than-or-equal-x/
 * You are given an array nums of non-negative integers. nums is considered special if there exists a number x such that there are exactly x numbers in nums that are greater than or equal to x.

Notice that x does not have to be an element in nums.

Return x if the array is special, otherwise, return -1. It can be proven that if nums is special, the value for x is unique.

 

Example 1:

Input: nums = [3,5]
Output: 2
Explanation: There are 2 values (3 and 5) that are greater than or equal to 2.
Example 2:

Input: nums = [0,0]
Output: -1
Explanation: No numbers fit the criteria for x.
If x = 0, there should be 0 numbers >= x, but there are 2.
If x = 1, there should be 1 number >= x, but there are 0.
If x = 2, there should be 2 numbers >= x, but there are 0.
x cannot be greater since there are only 2 numbers in nums.
Example 3:

Input: nums = [0,4,3,0,4]
Output: 3
Explanation: There are 3 values that are greater than or equal to 3.
Example 4:

Input: nums = [3,6,7,7,0]
Output: -1
 

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 1000
 */
class Solution {
    private int findLastNumSmallerThanX(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int idx = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                idx = mid;
                left = mid + 1;
            }
        }
        return idx;
    }
    public int specialArray(int[] nums) {
        //x取值范围[0, nums.length]
        //先排序，nlog(n)。然后对于n种选择，找到第一个小于它的值的idx(log(n))，总共也是nlog(n)
        Arrays.sort(nums);
        for (int x = 0; x <= nums.length; x++) {
            int idx = findLastNumSmallerThanX(nums, x);//[idx + 1, end]
            if ((nums.length - 1 - idx) == x) {
                return x;
            }
        }
        return -1;
    }
}
