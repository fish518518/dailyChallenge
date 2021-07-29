/**时间复杂度：对于每个元素都有两个选择， 一个是加入前面那段，一个是另起一段，所以一共是O(2^N)种组合，每种组合都要更新一下结果，所以是O(N*2^N)
 * 空间复杂度：树的高度就是O(N)
 * https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings/
 * Given a string s, return the maximum number of unique substrings that the given string can be split into.

You can split string s into any list of non-empty substrings, where the concatenation of the substrings forms the original string. However, you must split the substrings such that all of them are unique.

A substring is a contiguous sequence of characters within a string.

 

Example 1:

Input: s = "ababccc"
Output: 5
Explanation: One way to split maximally is ['a', 'b', 'ab', 'c', 'cc']. Splitting like ['a', 'b', 'a', 'b', 'c', 'cc'] is not valid as you have 'a' and 'b' multiple times.
Example 2:

Input: s = "aba"
Output: 2
Explanation: One way to split maximally is ['a', 'ba'].
Example 3:

Input: s = "aa"
Output: 1
Explanation: It is impossible to split the string any further.
 

Constraints:

1 <= s.length <= 16

s contains only lower case English letters.


 */
class Solution {
    private int res;
    private void backtracking(String s, int idx, int length, Set<String> added) {
        //base
        if (idx == s.length()) {
            res = Math.max(res, length);
        }
        for (int i = idx + 1; i <= s.length(); i++) {
            String subStr = s.substring(idx, i);
            if (!added.contains(subStr)) {
                added.add(subStr);
                backtracking(s, i, length + 1, added);
                added.remove(subStr);
            }
        }
    }
    public int maxUniqueSplit(String s) {
        res = 0;
        Set<String> added = new HashSet<>();
        backtracking(s, 0, 0, added);
        return res;
    }
}