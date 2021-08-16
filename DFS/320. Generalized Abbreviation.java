/*
https://leetcode.com/problems/generalized-abbreviation/
A word's generalized abbreviation can be constructed by taking any number of non-overlapping substrings and replacing them with their respective lengths. For example, "abcde" can be abbreviated into "a3e" ("bcd" turned into "3"), "1bcd1" ("a" and "e" both turned into "1"), and "23" ("ab" turned into "2" and "cde" turned into "3").

Given a string word, return a list of all the possible generalized abbreviations of word. Return the answer in any order.

 

Example 1:

Input: word = "word"
Output: ["4","3d","2r1","2rd","1o2","1o1d","1or1","1ord","w3","w2d","w1r1","w1rd","wo2","wo1d","wor1","word"]
Example 2:

Input: word = "a"
Output: ["1","a"]
 

Constraints:

1 <= word.length <= 15
word consists of only lowercase English letters.

*/

import java.util.*;
class Solution {
    private void generateAbbreviationsHelper(List<String> res, StringBuilder abbreviation, String word, int idx, int abbreviated) {
        int length = abbreviation.length();
        if (idx == word.length()) {
            if (abbreviated != 0) abbreviation.append(String.valueOf(abbreviated));
            res.add(new String(abbreviation));
            abbreviation.setLength(length);
            return;
        }
        //add number
        generateAbbreviationsHelper(res, abbreviation, word, idx + 1, abbreviated + 1);
        //add char
        if (abbreviated != 0) abbreviation.append(String.valueOf(abbreviated));
        abbreviation.append(word.charAt(idx));
        generateAbbreviationsHelper(res, abbreviation, word, idx + 1, 0);
        abbreviation.setLength(length);
    }
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        StringBuilder abbreviation = new StringBuilder();
        generateAbbreviationsHelper(res, abbreviation, word, 0, 0);
        return res;
    }
}