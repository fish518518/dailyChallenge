import java.util.HashMap;
import java.util.Map;

/**
 * 时间复杂度：O(N)
 * 空间复杂度：O(N)
 * https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
 * You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. Note that this modifies the array for future operations.

Return the minimum number of operations to reduce x to exactly 0 if it's possible, otherwise, return -1.

 

Example 1:

Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
Example 2:

Input: nums = [5,6,7,8,9], x = 4
Output: -1
Example 3:

Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 104
1 <= x <= 109
 */

class Solution {
    public int minOperations(int[] nums, int x) {
        if (x == 0) return 0;
        Map<Integer, Integer> prefixToIdx = new HashMap<>();
        prefixToIdx.put(0, 0);
        
        long prefix = 0;
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            if (prefix > Integer.MAX_VALUE) {
                break;
            } else {
                if (!prefixToIdx.containsKey((int)prefix)) {
                    prefixToIdx.put((int)prefix, i + 1);
                }
            }
        }
        int result = Integer.MAX_VALUE;
        long suffix = 0;
        if (prefixToIdx.containsKey(x)) {
            result = Math.min(result, prefixToIdx.get(x));
        } 
        for (int i = nums.length - 1; i >= 0; i--) {
            suffix += nums[i];//[i, end]
            if (suffix > Integer.MAX_VALUE) return -1;
            int target = (int)(x - suffix);
            if (prefixToIdx.containsKey(target)) {
                int left = prefixToIdx.get(target); //[0, left - 1]
                if (left - 1 < i) {//左右不能overlap
                    result = Math.min(result, left + nums.length - i);
                }
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}