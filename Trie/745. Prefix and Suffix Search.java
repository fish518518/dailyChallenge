/**
 * https://leetcode.com/problems/prefix-and-suffix-search/
 * Design a special dictionary which has some words and allows you to search the words in it by a prefix and a suffix.

Implement the WordFilter class:

WordFilter(string[] words) Initializes the object with the words in the dictionary.
f(string prefix, string suffix) Returns the index of the word in the dictionary which has the prefix prefix and the suffix suffix. If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.
 

Example 1:

Input
["WordFilter", "f"]
[[["apple"]], ["a", "e"]]
Output
[null, 0]

Explanation
WordFilter wordFilter = new WordFilter(["apple"]);
wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".
 

Constraints:

1 <= words.length <= 15000
1 <= words[i].length <= 10
1 <= prefix.length, suffix.length <= 10
words[i], prefix and suffix consist of lower-case English letters only.
At most 15000 calls will be made to the function f.
 */

//解法1: 暴力哈希表
 class WordFilter {
    Map<String, Integer> prefixSuffixToIdx;
    public WordFilter(String[] words) {
        prefixSuffixToIdx = new HashMap<>();
        for (int k = 0; k < words.length; k++) {
            String word = words[k];
            for (int i = 0; i <= word.length(); i++) {
                String prefix = word.substring(0, i);
                for (int j = 0; j <= word.length(); j++) {
                    String suffix = word.substring(j, word.length());
                    prefixSuffixToIdx.put(prefix + '-' + suffix, k);
                }
            }
        }
    }
    
    public int f(String prefix, String suffix) {
        String key = prefix + '-' + suffix;
        if (prefixSuffixToIdx.containsKey(key)) return prefixSuffixToIdx.get(key);
        return -1;
    }
}