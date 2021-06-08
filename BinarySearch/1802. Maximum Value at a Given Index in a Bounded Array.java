/**
 * 时间复杂度：O(1)对于二分，时间复杂度是O(log(maxSum))，每一次都是计算和O(1)，所以总共O(1)
 * 空间复杂度：O(1)
 * https://leetcode.com/problems/maximum-value-at-a-given-index-in-a-bounded-array/
 *
 * You are given three positive integers: n, index, and maxSum. You want to construct an array nums (0-indexed) that satisfies the following conditions:

nums.length == n
nums[i] is a positive integer where 0 <= i < n.
abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1.
The sum of all the elements of nums does not exceed maxSum.
nums[index] is maximized.
Return nums[index] of the constructed array.

Note that abs(x) equals x if x >= 0, and -x otherwise.

 

Example 1:

Input: n = 4, index = 2,  maxSum = 6
Output: 2
Explanation: nums = [1,2,2,1] is one array that satisfies all the conditions.
There are no arrays that satisfy all the conditions and have nums[2] == 3, so 2 is the maximum nums[2].
Example 2:

Input: n = 6, index = 1,  maxSum = 10
Output: 3
 

Constraints:

1 <= n <= maxSum <= 109
0 <= index < n
 */

class Solution {
    //错误点：没有考虑到sum会越界，按照题目说明可以得到用long不会越界
    //错误点2: 没有用公式直接加，导致超时
    private boolean isQualified(int n, int index, int valueOnIndex, int target) {
        //verify if sum is not larger than target
        long sum = 0;
        int startValue = Math.max(0, valueOnIndex - index);
        int endValue = Math.max(0, valueOnIndex - n + 1 + index);
        long left = (long)(startValue + valueOnIndex) * (valueOnIndex - startValue + 1) / 2;
        long right = (long)(valueOnIndex + endValue) * (valueOnIndex - endValue + 1) / 2;
        sum = left + right - valueOnIndex;
        return sum <= target;
    }
    public int maxValue(int n, int index, int maxSum) {
        //数组长度为n，全部都是正的，相邻两个元素之间相差<=1，和不超过maxSum，使得index位置的值最大
        //index位置的值取值范围是[1, maxSum]
        //假如知道index位置的值为value，那么它相邻1的位置的两个元素，取值范围是[Math.max(1, value - 1), value + 1]，相邻2的位置的两个元素，取值范围是[Math.max(1, value - 2), value + 2]，以此类推
        //由此可以知道和的范围，如果和的下限<=maxSum，说明符合条件。
        //二分
        //可以事先所有元素都减少1，maxSum变成maxSum-n，方便计算
        int left = 0;
        int right = maxSum - 1;
        int result = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isQualified(n, index, mid, maxSum - n)) {
                left = mid + 1;
                result = mid;
            } else {
                right = mid - 1;
            }
        }
        return result + 1;
    }
}