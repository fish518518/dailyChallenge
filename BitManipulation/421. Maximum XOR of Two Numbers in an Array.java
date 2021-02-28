/**
 * https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
 * Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 ≤ i ≤ j < n.

Follow up: Could you do this in O(n) runtime?

 

Example 1:

Input: nums = [3,10,5,25,2,8]
Output: 28
Explanation: The maximum result is 5 XOR 25 = 28.
Example 2:

Input: nums = [0]
Output: 0
Example 3:

Input: nums = [2,4]
Output: 6
Example 4:

Input: nums = [8,10,2]
Output: 10
Example 5:

Input: nums = [14,70,53,83,49,91,36,80,92,51,66,70]
Output: 127
 

Constraints:

1 <= nums.length <= 2 * 104
0 <= nums[i] <= 231 - 1

 */

class TrieNode {
    Map<Character, TrieNode> children;
    public TrieNode() {
        children = new HashMap<>();
    }
}
class Solution {
    public void insert(TrieNode root, String str) {
        TrieNode cur = root;
        for (int i = 1; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new TrieNode());
            }
            cur = cur.children.get(c);
        }
    }
    public int findMaximumXOR(int[] nums) {
        //find the maxBinaryNumLength;
        int maxNum = nums[0];
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }
        int maxNumLength = Integer.toBinaryString(maxNum).length();
        TrieNode root = new TrieNode();
        int maxXOR = 0;
        for (int num : nums) {
            int curXOR = 0;
            int bitMask = 1;
            bitMask = bitMask << maxNumLength;
            num = num | bitMask; //adjust num length to maxLength + 1;
            //update trie tree
            insert(root, Integer.toBinaryString(num));
            //calculate curXOR
            TrieNode node = root;
            String numStr = Integer.toBinaryString(num);
            for (int i = 1; i < numStr.length(); i++) {
                char c = numStr.charAt(i);
                char target = c == '0' ? '1' : '0';
                if (node.children.containsKey(target)) {
                    node = node.children.get(target);
                    curXOR = curXOR << 1 | 1;
                } else {
                    node = node.children.get(c);
                    curXOR = curXOR << 1;
                }
            }
            maxXOR = Math.max(maxXOR, curXOR);
        }
        return maxXOR;
    }
}