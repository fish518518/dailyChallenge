/**时间复杂度：遍历一遍数组，O(N)
 * 空间复杂度：O(1)
 * https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product/
 * Given an array of integers nums, find the maximum length of a subarray where the product of all its elements is positive.

A subarray of an array is a consecutive sequence of zero or more values taken out of that array.

Return the maximum length of a subarray with positive product.

 

Example 1:

Input: nums = [1,-2,-3,4]
Output: 4
Explanation: The array nums already has a positive product of 24.
Example 2:

Input: nums = [0,1,-2,-3,-4]
Output: 3
Explanation: The longest subarray with positive product is [1,-2,-3] which has a product of 6.
Notice that we cannot include 0 in the subarray since that'll make the product 0 which is not positive.
Example 3:

Input: nums = [-1,-2,-3,0,1]
Output: 2
Explanation: The longest subarray with positive product is [-1,-2] or [-2,-3].
Example 4:

Input: nums = [-1,2]
Output: 1
Example 5:

Input: nums = [1,2,3,5,-6,4,0,10]
Output: 4
 

Constraints:

1 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
 * 
 */
class Solution {
    //错误点: 没有考虑乘法会越界
    public int getMaxLen(int[] nums) {
        int firstNegative = -1;
        int firstPositive = -1;
        int result = 0;
        int pre = 1;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            //pre *= num;
            if (num == 0) {
                firstNegative = -1;
                firstPositive = i;
                pre = 1;
            } else if ((pre < 0 && num > 0) || (pre > 0 && num < 0)) {//符号相反
                if (firstNegative == -1) {
                    firstNegative = i;
                } else {
                    result = Math.max(result, i - firstNegative);//[firstNegative + 1, i]
                }
                pre = -1;
            } else if ((pre < 0 && num < 0) || (pre > 0 && num > 0)) {//符号相同
                result = Math.max(result, i - firstPositive);//[firstPositive + 1, i]
                pre = 1;
            }
        }
        return result;
    }
}