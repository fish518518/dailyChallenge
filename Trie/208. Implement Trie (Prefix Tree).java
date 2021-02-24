/*
https://leetcode.com/problems/implement-trie-prefix-tree/
Implement a trie with insert, search, and startsWith methods.

Example:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");   
trie.search("app");     // returns true
Note:

You may assume that all inputs are consist of lowercase letters a-z.
All inputs are guaranteed to be non-empty strings.
*/
class Trie {
    TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        TrieNode cur = this.root;
        for (char c : word.toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new TrieNode();
            }
            cur = cur.children[c - 'a'];
        }
        cur.word = word;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null || word.length() == 0) return true;
        TrieNode cur = this.root;
        for (char c : word.toCharArray()) {
            if (cur.children[c - 'a'] == null) return false;
            cur = cur.children[c - 'a'];
        }
        return cur.word != null;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) return true;
        TrieNode cur = this.root;
        for (char c : prefix.toCharArray()) {
            if (cur.children[c - 'a'] == null) return false;
            cur = cur.children[c - 'a'];
        }
        return true;
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
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */