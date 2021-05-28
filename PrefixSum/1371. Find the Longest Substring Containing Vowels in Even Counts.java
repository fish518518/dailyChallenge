import java.util.HashMap;
import java.util.Map;

/**https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/
 * Given the string s, return the size of the longest substring containing each vowel an even number of times. That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.

 

Example 1:

Input: s = "eleetminicoworoep"
Output: 13
Explanation: The longest substring is "leetminicowor" which contains two each of the vowels: e, i and o and zero of the vowels: a and u.
Example 2:

Input: s = "leetcodeisgreat"
Output: 5
Explanation: The longest substring is "leetc" which contains two e's.
Example 3:

Input: s = "bcbcbc"
Output: 6
Explanation: In this case, the given string "bcbcbc" is the longest because all vowels: a, e, i, o and u appear zero times.
 

Constraints:

1 <= s.length <= 5 x 10^5
s contains only lowercase English letters.
 */
class Solution {
    public int findTheLongestSubstring(String s) {
        //找最远的bitmask相同的idx, bitmask: 0 代表 偶数，1 代表奇数，aeiou
        Map<Character, Integer> vowelToIdx = new HashMap<>();
        vowelToIdx.put('a', 0);
        vowelToIdx.put('e', 1);
        vowelToIdx.put('i', 2);
        vowelToIdx.put('o', 3);
        vowelToIdx.put('u', 4); //{'a': 0, e': 1, 'i': 2, 'o': 3, 'u': 4}
        Map<Integer, Integer> bitMaskToIdx = new HashMap<>();
        bitMaskToIdx.put(0, 0);
        int longest = 0;
        int bitMask = 0;
        for (int i = 0; i < s.length(); i++) {//到i + 1之前
            char cur = s.charAt(i);
            if (vowelToIdx.containsKey(cur)) {
                System.out.println(1 << (4 - vowelToIdx.get(cur)));
                bitMask = bitMask ^ (1 << (4 - vowelToIdx.get(cur)));//'e': 01000
            }
            if (bitMaskToIdx.containsKey(bitMask)) {
                int length = i - bitMaskToIdx.get(bitMask) + 1;//[v, i]
                longest = Math.max(length, longest);
            } else {
                bitMaskToIdx.put(bitMask, i + 1);
            }
        }
        return longest;
    }
}