/**时间复杂度：一共最多有2^N种可能性，每种可能性要复制一次O(N)，所以一共O(N*2^N)
 * 空间复杂度：最大递归深度是O(N)
 * https://leetcode.com/problems/letter-case-permutation/
 * Given a string s, we can transform every letter individually to be lowercase or uppercase to create another string.

Return a list of all possible strings we could create. You can return the output in any order.

 

Example 1:

Input: s = "a1b2"
Output: ["a1b2","a1B2","A1b2","A1B2"]
Example 2:

Input: s = "3z4"
Output: ["3z4","3Z4"]
Example 3:

Input: s = "12345"
Output: ["12345"]
Example 4:

Input: s = "0"
Output: ["0"]
 

Constraints:

s will be a string with length between 1 and 12.
s will consist only of letters or digits.

 */
class Solution {
    private void backtracking(String s, StringBuilder possible, List<String> res) {
        //base
        if (possible.length() == s.length()) {
            res.add(new String(possible.toString()));
            return;
        }
        int idx = possible.length();
        char cur = s.charAt(idx);
        if (cur < '0' || cur > '9') {
            //if is a letter
            possible.append(Character.toLowerCase(cur));
            backtracking(s, possible, res);
            possible.deleteCharAt(possible.length() - 1);
            possible.append(Character.toUpperCase(cur));
            backtracking(s, possible, res);
            possible.deleteCharAt(possible.length() - 1);
        } else {
            possible.append(cur);
            backtracking(s, possible, res);
            possible.deleteCharAt(possible.length() - 1);
        }
        
    }
    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        StringBuilder possible = new StringBuilder();
        backtracking(s, possible, res);
        return res;
    }
}