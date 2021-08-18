/**
 * https://leetcode.com/problems/word-break-ii/
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

 

Example 1:

Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]
Example 2:

Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []
 

Constraints:

1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
 */

class Solution {
    List<String> res = new ArrayList<>();
    private String buildStr(List<StringBuilder> sentence) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sentence.size(); i++) {
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(sentence.get(i).toString());
        }
        return sb.toString();
    }
    private void dfs(String s, Set<String> dictionary, int idx, StringBuilder sb, List<StringBuilder> sentence, int maxWordLength) {
        if (idx == s.length()) {
            if (dictionary.contains(sb.toString())) {
                sentence.add(sb);
                res.add(buildStr(sentence));
                sentence.remove(sentence.size() - 1);
            }
            return;
        }
        if (sb.length() > maxWordLength) {
            return;
        }
        if (sb.length() > 0 && dictionary.contains(sb.toString())) {
            sentence.add(sb);
            dfs(s, dictionary, idx, new StringBuilder(), sentence, maxWordLength);
            sentence.remove(sentence.size() - 1);
        }
        char c = s.charAt(idx);
        sb.append(c);
        dfs(s, dictionary, idx + 1, sb, sentence, maxWordLength);
        sb.deleteCharAt(sb.length() - 1);
    }
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dictionary = new HashSet<>();
        int maxWordLength = 0;
        for (int i = 0; i < wordDict.size(); i++) {
            String word = wordDict.get(i);
            dictionary.add(word);
            maxWordLength = Math.max(maxWordLength, word.length());
        }
        List<StringBuilder> sentence = new ArrayList<>();
        dfs(s, dictionary, 0, new StringBuilder(), sentence, maxWordLength);
        return res;
    }
}