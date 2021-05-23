// 时间复杂度：O(N)
// 空间复杂度：O(1)
//678. Valid Parenthesis String
// Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.

// The following rules define a valid string:

// Any left parenthesis '(' must have a corresponding right parenthesis ')'.
// Any right parenthesis ')' must have a corresponding left parenthesis '('.
// Left parenthesis '(' must go before the corresponding right parenthesis ')'.
// '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
 

// Example 1:

// Input: s = "()"
// Output: true
// Example 2:

// Input: s = "(*)"
// Output: true
// Example 3:

// Input: s = "(*))"
// Output: true
 

// Constraints:

// 1 <= s.length <= 100
// s[i] is '(', ')' or '*'.
class Solution {
    public boolean checkValidString(String s) {
        //有效意味着任何时候，没有match的左括号数量>=0，且最后，左括号数量==0
        int minUnmatchedLeft = 0;//有效的情况下至少有多少个左括号没有match(*尽可能都替换成右括号)
        int maxUnmatchedLeft = 0;//有效的情况下最多有多少个左括号没有match(*尽可能都替换成左括号)
        for (char c : s.toCharArray()) {
            if (c == '(') {
                minUnmatchedLeft++;
                maxUnmatchedLeft++;
            } else if (c == ')') {
                minUnmatchedLeft--;
                maxUnmatchedLeft--;
            } else {
                maxUnmatchedLeft++;
                minUnmatchedLeft--;
            }
            if (maxUnmatchedLeft < 0) return false;
            if (minUnmatchedLeft < 0) minUnmatchedLeft = 0;
        }
        return minUnmatchedLeft == 0;
    }
}
