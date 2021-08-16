/**
 *?时间复杂度：一共N个节点，所以是O(N)，每个节点都要复制一次结果给最终结果，所以是O(N^2)，最后排序是O(Nlog(N))，所以最终是O(N^2)
 *?空间复杂度：排序需要O(N)空间，递归深度是O(N)，所以一共是O(N)
 * https://leetcode.com/problems/brace-expansion/
 * You are given a string s representing a list of words. Each letter in the word has one or more options.

If there is one option, the letter is represented as is.
If there is more than one option, then curly braces delimit the options. For example, "{a,b,c}" represents options ["a", "b", "c"].
For example, if s = "a{b,c}", the first character is always 'a', but the second character can be 'b' or 'c'. The original list is ["ab", "ac"].

Return all words that can be formed in this manner, sorted in lexicographical order.

 

Example 1:

Input: s = "{a,b}c{d,e}f"
Output: ["acdf","acef","bcdf","bcef"]
Example 2:

Input: s = "abcd"
Output: ["abcd"]
 

Constraints:

1 <= s.length <= 50
s consists of curly brackets '{}', commas ',', and lowercase English letters.
s is guaranteed to be a valid input.
There are no nested curly brackets.
All characters inside a pair of consecutive opening and ending curly brackets are different.
 */
class Solution {
    private void backtracking(String s, StringBuilder word, List<String> res, int startIdx) {
        if (startIdx == s.length()) {
            res.add(new String(word.toString()));
            return;
        }
        char cur = s.charAt(startIdx);
        if (cur == '{') {
            int idx = startIdx + 1;
            while (s.charAt(idx) != '}') {
                idx++;
            }
            for (int i = startIdx + 1; i < idx; i++) {
                if (s.charAt(i) != ',') {
                    word.append(s.charAt(i));
                    backtracking(s, word, res, idx + 1);
                    word.deleteCharAt(word.length() - 1);
                }
            }
        } else {
            word.append(cur);
            backtracking(s, word, res, startIdx + 1);
            word.deleteCharAt(word.length() - 1);
        }
    }
    public String[] expand(String s) {
        StringBuilder word = new StringBuilder();
        List<String> res = new ArrayList<>();
        backtracking(s, word, res, 0);
        String[] r = new String[res.size()];
        for (int i = 0; i < res.size(); i++) {
            r[i] = res.get(i);
        }
        Arrays.sort(r);
        return r;
    }
}