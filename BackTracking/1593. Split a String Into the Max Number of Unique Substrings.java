/**
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
    int maxNum;
    private void backtracking(String s, StringBuilder pre, Set<String> added, int idx, int substringsNum) {
        //base
        String preStr = pre.toString();
        if (idx == s.length()) {
            if (pre.length() == 0) {
                maxNum = Math.max(maxNum, substringsNum);
            } else if (!added.contains(preStr)) {
                maxNum = Math.max(maxNum, substringsNum + 1);
            }
            return;
        }
        char c = s.charAt(idx);
        if (pre.length() > 0 && !added.contains(preStr)) {
            //new partition
            added.add(preStr);
            backtracking(s, new StringBuilder().append(c), added, idx + 1, substringsNum + 1);
            added.remove(preStr);
        }
        //add to previous
        pre.append(c);
        backtracking(s, pre, added, idx + 1, substringsNum);
        pre.deleteCharAt(pre.length() - 1);
    }
    public int maxUniqueSplit(String s) {
        maxNum = 0;
        StringBuilder pre = new StringBuilder();
        Set<String> added = new HashSet<>();
        backtracking(s, pre, added, 0, 0);
        return maxNum;
    }
}