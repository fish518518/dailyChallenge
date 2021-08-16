/**时间复杂度：每个字符，都有10个选择，所以是O(10^N)
 * 空间复杂度：递归深度O(N)，N是总字符数
 * https://leetcode.com/problems/verbal-arithmetic-puzzle/
 * Given an equation, represented by words on left side and the result on right side.

You need to check if the equation is solvable under the following rules:

Each character is decoded as one digit (0 - 9).
Every pair of different characters they must map to different digits.
Each words[i] and result are decoded as one number without leading zeros.
Sum of numbers on left side (words) will equal to the number on right side (result). 
Return True if the equation is solvable otherwise return False.

 

Example 1:

Input: words = ["SEND","MORE"], result = "MONEY"
Output: true
Explanation: Map 'S'-> 9, 'E'->5, 'N'->6, 'D'->7, 'M'->1, 'O'->0, 'R'->8, 'Y'->'2'
Such that: "SEND" + "MORE" = "MONEY" ,  9567 + 1085 = 10652
Example 2:

Input: words = ["SIX","SEVEN","SEVEN"], result = "TWENTY"
Output: true
Explanation: Map 'S'-> 6, 'I'->5, 'X'->0, 'E'->8, 'V'->7, 'N'->2, 'T'->1, 'W'->'3', 'Y'->4
Such that: "SIX" + "SEVEN" + "SEVEN" = "TWENTY" ,  650 + 68782 + 68782 = 138214
Example 3:

Input: words = ["THIS","IS","TOO"], result = "FUNNY"
Output: true
Example 4:

Input: words = ["LEET","CODE"], result = "POINT"
Output: false
 

Constraints:

2 <= words.length <= 5
1 <= words[i].length, result.length <= 7
words[i], result contain only uppercase English letters.
The number of different characters used in the expression is at most 10.
 */
class Solution {
    boolean solvable = false; 
    
    private boolean isEqual(int target, String resultStr, boolean[] used, int[] charToNum) {
        String targetStr = String.valueOf(target);
        if (targetStr.length() != resultStr.length()) return false;
        for (int i = 0; i < targetStr.length(); i++) {
            char curInTarget = targetStr.charAt(i);//1234, 1
            char curInResult = resultStr.charAt(i);//MONE, M
            if (charToNum[curInResult - 'A'] == curInTarget - '0') {
                continue;
            } else if (charToNum[curInResult - 'A'] == -1 && used[curInTarget - '0'] == false) {
                charToNum[curInResult - 'A'] = curInTarget - '0';
                used[curInTarget - '0'] = true;
            } else {
                return false;
            }
        }
        return true;
    }
    
    private void backtracking(String[] words, String result, boolean[] used, int wordIdx, int charIdx, int[] charToNum, int total, int curSum) {
        //base        
        if (solvable) return;

        if (wordIdx == words.length) {
            if (isEqual(total, result, used.clone(), charToNum.clone())) {
                solvable = true;
            }
            return;
        }
        if (charIdx == words[wordIdx].length()) {
            backtracking(words, result, used, wordIdx + 1, 0, charToNum, total + curSum, 0);
            return;
        }
        if (charIdx != 0 && curSum == 0) {
            return;
        }
        
        char cur = words[wordIdx].charAt(charIdx);
        if (charToNum[cur - 'A'] == -1) {
            //没有对应
            for (int i = 0; i < used.length; i++) {
                if (used[i] == false) {
                    used[i] = true;
                    charToNum[cur - 'A'] = i;
                    backtracking(words, result, used, wordIdx, charIdx + 1, charToNum, total, curSum * 10 + i);
                    charToNum[cur - 'A'] = -1;
                    used[i] = false;
                }
            }
        } else {
            int newcurSum = curSum * 10 + charToNum[cur - 'A'];
            backtracking(words, result, used, wordIdx, charIdx + 1, charToNum, total, newcurSum);
        }
    }
    
    public boolean isSolvable(String[] words, String result) {
        boolean[] used = new boolean[10];
        int[] charToNum = new int[26];
        
        for (int i = 0; i < charToNum.length; i++) {
            charToNum[i] = -1;
        }
        backtracking(words, result, used, 0, 0, charToNum, 0, 0);
        return solvable;
    }
}