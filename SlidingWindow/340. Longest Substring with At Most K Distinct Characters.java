import java.util.HashMap;
import java.util.Map;

/**时间复杂度：O(N)，遍历s不后退
 * 孔吉娜复杂度：O(1),只保存最多26个字母
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 * Given a string s and an integer k, return the length of the longest substring of s that contains at most k distinct characters.

 

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: The substring is "ece" with length 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: The substring is "aa" with length 2.
 

Constraints:

1 <= s.length <= 5 * 104
0 <= k <= 50

 * 
 */
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
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
            while (distinctCount > k) {
                char d = s.charAt(left);
                left++;
                charToCount.put(d, charToCount.get(d) - 1);
                if (charToCount.get(d) == 0) {
                    distinctCount--;
                }
            }
            result = Math.max(result, right - left);
        }
        return result;
    }
}
