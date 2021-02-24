/*
https://leetcode.com/problems/design-add-and-search-words-data-structure/
Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 

Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True
 

Constraints:

1 <= word.length <= 500
word in addWord consists lower-case English letters.
word in search consist of  '.' or lower-case English letters.
At most 50000 calls will be made to addWord and search.
 */
class WordDictionary {

    /** Initialize your data structure here. */
    TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        if (word == null || word.length() == 0) return;
        TrieNode cur = this.root;
        for (char c : word.toCharArray()) {
            if (cur.children[c - 'a'] == null) cur.children[c - 'a'] = new TrieNode();
            cur = cur.children[c - 'a'];
        }
        cur.word = word;
    }
    private boolean searchHelper(TrieNode node, String word, int idx) {
        //base
        if (idx == word.length()) {
            return node.word != null;
        }
        char c = word.charAt(idx);
        if (c == '.') {
            for (TrieNode nextNode : node.children) {
                if (nextNode != null && searchHelper(nextNode, word, idx + 1)) return true;
            }
            return false;
        } else {
            if (node.children[c - 'a'] == null) {
                return false;
            } else {
                return searchHelper(node.children[c - 'a'], word, idx + 1);
            }
        }
    }
    public boolean search(String word) {
        if (word == null || word.length() == 0) return true;
        return searchHelper(this.root, word, 0);
    }
}

class TrieNode {
    TrieNode[] children;
    String word;
    public TrieNode() {
        children = new TrieNode[26];
        word = null;
    }
}