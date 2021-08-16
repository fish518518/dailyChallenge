/*
https://leetcode.com/problems/generate-parentheses/
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 

Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]
 

Constraints:

1 <= n <= 8
*/

import java.util.*;
class Solution {
    private void generateParenthesisHelper(int leftParentheses, int rightParentheses, StringBuilder combination, List<String> res) {
        if (leftParentheses == 0 && rightParentheses == 0) {
            res.add(new String(combination));
            return;
        }
        if (leftParentheses < 0 || rightParentheses < 0 || leftParentheses > rightParentheses) {
            return;
        }
        combination.append('(');
        generateParenthesisHelper(leftParentheses - 1, rightParentheses, combination, res);
        combination.deleteCharAt(combination.length() - 1);
        
        combination.append(')');
        generateParenthesisHelper(leftParentheses, rightParentheses - 1, combination, res);
        combination.deleteCharAt(combination.length() - 1);
    }
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder combination = new StringBuilder();
        int leftParentheses = n;
        int rightParentheses = n;
        generateParenthesisHelper(leftParentheses, rightParentheses, combination, res);
        return res;
    }
}