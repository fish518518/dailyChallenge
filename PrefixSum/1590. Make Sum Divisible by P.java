import java.util.HashMap;
import java.util.Map;

/**
 * 时间复杂度：O(N)，遍历数组
 * 空间复杂度：O(N)，哈希表
 * https://leetcode.com/problems/make-sum-divisible-by-p/
 * Given an array of positive integers nums, remove the smallest subarray (possibly empty) such that the sum of the remaining elements is divisible by p. It is not allowed to remove the whole array.

Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.

A subarray is defined as a contiguous block of elements in the array.

 

Example 1:

Input: nums = [3,1,4,2], p = 6
Output: 1
Explanation: The sum of the elements in nums is 10, which is not divisible by 6. We can remove the subarray [4], and the sum of the remaining elements is 6, which is divisible by 6.
Example 2:

Input: nums = [6,3,5,2], p = 9
Output: 2
Explanation: We cannot remove a single element to get a sum divisible by 9. The best way is to remove the subarray [5,2], leaving us with [6,3] with sum 9.
Example 3:

Input: nums = [1,2,3], p = 3
Output: 0
Explanation: Here the sum is 6. which is already divisible by 3. Thus we do not need to remove anything.
Example 4:

Input: nums = [1,2,3], p = 7
Output: -1
Explanation: There is no way to remove a subarray in order to get a sum divisible by 7.
Example 5:

Input: nums = [1000000000,1000000000,1000000000], p = 3
Output: 0
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109
1 <= p <= 109
 */
class Solution {
    //坑1. java余数可能为负数。余数要搞到[0-p)之间
    //坑2. prefixSum可能越界
    public int minSubarray(int[] nums, int p) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum = (totalSum + num) % p;//每一次都模p，以免越界
        }
        if (totalSum % p == 0) return 0;
        int remainder = (totalSum % p + p) % p;//利用(a%b+b)%b调整余数范围到[0, p)
        int result = nums.length;
        Map<Integer, Integer> remainderToIdx = new HashMap<>();//idx is not included
        remainderToIdx.put(0, 0);
        int prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum = (prefixSum + nums[i]) % p;//到i+1之前的所有和
            int r = (prefixSum % p + p) % p;//当前的余数
            int target = ((r - remainder) % p + p) % p;//要找的余数
            if (remainderToIdx.containsKey(target)) {
                result = Math.min(result, i - remainderToIdx.get(target) + 1); //[key, i]
            }
            remainderToIdx.put(r, i + 1);
        }
        return (result == nums.length ? -1 : result);
    }
}
