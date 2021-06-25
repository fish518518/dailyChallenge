/**时间复杂度：O(N)
 * 空间复杂度：O(N)
 * https://leetcode.com/problems/maximum-subarray-min-product/
 * The min-product of an array is equal to the minimum value in the array multiplied by the array's sum.

For example, the array [3,2,5] (minimum value is 2) has a min-product of 2 * (3+2+5) = 2 * 10 = 20.
Given an array of integers nums, return the maximum min-product of any non-empty subarray of nums. Since the answer may be large, return it modulo 109 + 7.

Note that the min-product should be maximized before performing the modulo operation. Testcases are generated such that the maximum min-product without modulo will fit in a 64-bit signed integer.

A subarray is a contiguous part of an array.

 

Example 1:

Input: nums = [1,2,3,2]
Output: 14
Explanation: The maximum min-product is achieved with the subarray [2,3,2] (minimum value is 2).
2 * (2+3+2) = 2 * 7 = 14.
Example 2:

Input: nums = [2,3,3,1,2]
Output: 18
Explanation: The maximum min-product is achieved with the subarray [3,3] (minimum value is 3).
3 * (3+3) = 3 * 6 = 18.
Example 3:

Input: nums = [3,1,5,6,4,2]
Output: 60
Explanation: The maximum min-product is achieved with the subarray [5,6,4] (minimum value is 4).
4 * (5+6+4) = 4 * 15 = 60.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 107
 */
class Solution {
    public int maxSumMinProduct(int[] nums) {
        //解法思路：对于nums[i]来说，如果以它为最小值，那么最大的min-product肯定是向左扩张到第一个比它小的元素a，向右扩张到第一个比它小的元素b
        //[...a,x,x,nums[i],x,x,b...] => [x,x,nums[i],x,x]
        //那么可以用单调栈O(N)取得左边第一个比它小的idx，和右边第一个比它小的idx
        //如果左边没有比它小的，那么返回-1，如果右边没有比它小的，那么返回nums.length
        //又利用prefixsum可以取得这一段的sum，也是O(N)
        //所以总时间复杂度就是O(N)
        Stack<Integer> stack = new Stack<>();
        int[] left = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                left[i] = -1;
            } else {
                left[i] = stack.peek();
            }
            stack.push(i);
        }
        stack = new Stack<>();
        int[] right = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                right[i] = nums.length;
            } else {
                right[i] = stack.peek();
            }
            stack.push(i);
        }
        long[] prefixsum = new long[nums.length + 1];
        prefixsum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            prefixsum[i] = (long)prefixsum[i - 1] + (long)nums[i - 1];
        }
        long res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int min = nums[i];//[left[i] + 1， right[i] - 1]
            long sum = prefixsum[right[i]] - prefixsum[left[i] + 1];
            res = Math.max(res, sum * min);
        }
        return (int)(res % 1000000007);
    }
}