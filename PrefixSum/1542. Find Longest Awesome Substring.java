import java.util.HashMap;
import java.util.Map;

/**
 * 时间复杂度： O(N), 遍历数组
 * 空间复杂度：O(N)，哈希表
 * https://leetcode.com/problems/find-longest-awesome-substring/
 * Given a string s. An awesome substring is a non-empty substring of s such that we can make any number of swaps in order to make it palindrome.

Return the length of the maximum length awesome substring of s.

 

Example 1:

Input: s = "3242415"
Output: 5
Explanation: "24241" is the longest awesome substring, we can form the palindrome "24142" with some swaps.
Example 2:

Input: s = "12345678"
Output: 1
Example 3:

Input: s = "213123"
Output: 6
Explanation: "213123" is the longest awesome substring, we can form the palindrome "231132" with some swaps.
Example 4:

Input: s = "00"
Output: 2
 

Constraints:

1 <= s.length <= 10^5
s consists only of digits.
 */
class Solution {
    public int longestAwesome(String s) {
        //求最长subarray，使得如果length是偶数，prefixA ^ prefixB == 0。如果length是奇数，prefixA ^ prefixB的1只有一个。bitmask中出现次数偶数是0，奇数是1
        //9876543210 - 0000000000
        Map<Integer, Integer> bitmaskToIdx = new HashMap<>();
        bitmaskToIdx.put(0, 0);
        int result = 0;
        int prefix = 0;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            prefix = prefix ^ (1 << (cur - '0'));//[start, i]
            //情况1: 全部相同
            if (bitmaskToIdx.containsKey(prefix)) {
                int key = bitmaskToIdx.get(prefix);//[start, key - 1]
                int length = i - key + 1;//[key, i]
                result = Math.max(result, length);
            } else {
                bitmaskToIdx.put(prefix, i + 1);
            }
            //情况2: 一个不同
            for (int j = 0; j <= 9; j++) {
                int target = prefix ^ (1 << j);
                if (bitmaskToIdx.containsKey(target)) {
                    int key = bitmaskToIdx.get(target);//[start, key - 1]
                    int length = i - key + 1;//[key, i]
                    result = Math.max(result, length); 
                }
            }
        }
        return result;
    }
}