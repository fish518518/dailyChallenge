/**时间复杂度：遍历数组，O(N)
 * 空间复杂度：哈希表，O(N)
 * https://leetcode.com/problems/binary-subarrays-with-sum/
 * In an array nums of 0s and 1s, how many non-empty subarrays have sum goal?

 

Example 1:

```
Input: nums = [1,0,1,0,1], goal = 2
Output: 4
Explanation: 
The 4 subarrays are bolded below:
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]

```
 

Note:

1. nums.length <= 30000
2. 0 <= goal <= nums.length
3. nums[i] is either 0 or 1.
 * 
 */
class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        Map<Integer, Integer> preSumToCount = new HashMap<>();
        preSumToCount.put(0, 1);
        int lastPrefixSum = 0;
        int result = 0;
        
        for (int i = 0; i < nums.length; i++) {
            int curPrefixSum =  lastPrefixSum + nums[i];
            int target = curPrefixSum - goal;
            if (preSumToCount.containsKey(target)) {
                result += preSumToCount.get(target);
            }
            preSumToCount.put(curPrefixSum, preSumToCount.getOrDefault(curPrefixSum, 0) + 1);
            lastPrefixSum = curPrefixSum;
        }
        
        return result;
    }
}