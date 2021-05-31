import java.util.HashMap;
import java.util.Map;

/**
 * 时间复杂度：O(N)，遍历s，从未后退。
 * 空间复杂度：O(1)，哈希表最多存放26个字母
 * https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
 * Given a string s, return the length of the longest substring that contains at most two distinct characters.

 

Example 1:

Input: s = "eceba"
Output: 3
Explanation: The substring is "ece" which its length is 3.
Example 2:

Input: s = "ccaabbb"
Output: 5
Explanation: The substring is "aabbb" which its length is 5.
 

Constraints:

1 <= s.length <= 104
s consists of English letters.
 */
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int left = 0;
        int right = 0;
        Map<Character, Integer> charToCount = new HashMap<>();
        int distinctCount = 0;
        int result = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (charToCount.containsKey(c) && charToCount.get(c) != 0) {
                charToCount.put(c, charToCount.get(c) + 1);
            } else {
                charToCount.put(c, 1);
                distinctCount++;
            }
            while (distinctCount > 2) {
                char d = s.charAt(left);
                left++;
                charToCount.put(d, charToCount.get(d) - 1);
                if (charToCount.get(d) == 0) {
                    distinctCount--;
                }
            }
            result = Math.max(result, right - left); //[left, right - 1]
        }
        return result;
    }
}