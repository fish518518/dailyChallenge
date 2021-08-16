/**时间复杂度：N个字符，每个字符对应了最多4种选择，所以一共有4^N种组合，每种组合要复制一份结果，所以是O(N * 4^N)
 * 空间复杂度：O(N)
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



 

Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:

Input: digits = ""
Output: []
Example 3:

Input: digits = "2"
Output: ["a","b","c"]
 

Constraints:

0 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].
 */

class Solution {
    List<String> res = new ArrayList<>();
    private void dfs(String digits, int idx, StringBuilder combination, char[][] numToLetters) {
        if (combination.length() == digits.length()) {
            res.add(new String(combination.toString()));
            return;
        }
        char cur = digits.charAt(idx);
        for (char letter : numToLetters[cur - '0']) {
            combination.append(letter);
            dfs(digits, idx + 1, combination, numToLetters);
            combination.deleteCharAt(combination.length() - 1);
        }
    }
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return res;
        }
        StringBuilder combination = new StringBuilder();
        char[][] numToLetters = new char[][]{{}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
        dfs(digits, 0, combination, numToLetters);
        return res;
    }
}