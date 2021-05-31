import java.util.HashMap;
import java.util.Map;

/**时间复杂度：O(N)
 * 空间复杂度：O(1)
 *  https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * Given a string s, find the length of the longest substring without repeating characters.

 

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
Example 4:

Input: s = ""
Output: 0
 

Constraints:

0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.
 */

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        Map<Character, Integer> charToIdx = new HashMap<>();
        int result = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (charToIdx.containsKey(c) && charToIdx.get(c) >= left) {
                left = charToIdx.get(c) + 1;
            }
            charToIdx.put(c, right - 1);
            result = Math.max(result, right - left); //[left, right = 1]
        }
        return result;
    }
}