/*
https://leetcode.com/problems/palindrome-partitioning/
Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

A palindrome string is a string that reads the same backward as forward.

 

Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]
 

Constraints:

1 <= s.length <= 16
s contains only lowercase English letters.
*/
import java.util.*;
class Solution {
    // private boolean isPalindrom(String s, int left, int right) {
    //     while (left < right) {
    //         if (s.charAt(left) != s.charAt(right)) return false;
    //         left++;
    //         right--;
    //     }
    //     return true;
    // }
    private int[][] isPalindromMemo;//0代表false,1代表true
    private void addMemo(String s, int left, int right) {
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                isPalindromMemo[left][right] = 1;
                left--;
                right++;
            } else {
                return;
            }
        }
    }
    private void partitionHelper(List<List<String>> res, List<String> possible, int idx, String s) {
        //base
        if (idx == s.length()) {
            res.add(new ArrayList(possible));
            return;
        }
        //choices
        for (int i = idx + 1; i <= s.length(); i++) {
            if (isPalindromMemo[idx][i - 1] == 1) {
                String cur = s.substring(idx, i);
                possible.add(cur);
                partitionHelper(res, possible, i, s);
                possible.remove(possible.size() - 1);
            }
        }
    }
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> possible = new ArrayList<>();
        //利用中心扩散先把memo预计算
        int length = s.length();
        isPalindromMemo = new int[length][length];
        for (int i = 0; i < length; i++) {
            //以i为中心
            addMemo(s, i, i);
            //以i和i+1为中心
            addMemo(s, i, i + 1);
        }
        partitionHelper(res, possible, 0, s);
        return res;
    }
}