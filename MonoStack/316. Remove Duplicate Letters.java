import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**时间复杂度：O(N)，每个字母出栈进栈最多一次。
 * 空间复杂度：O(1)，哈希表只存26个字母，stack只存unique字符，所以最多也只有26个字母。visited里面最多存26个字母。
 * https://leetcode.com/problems/remove-duplicate-letters/
 * Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

 

Example 1:

Input: s = "bcabc"
Output: "abc"
Example 2:

Input: s = "cbacdcbc"
Output: "acdb"
 

Constraints:

1 <= s.length <= 104
s consists of lowercase English letters.
 

Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 */
class Solution {
    public String removeDuplicateLetters(String s) {
        //是一个从小到大的单调栈
        //要判断能否把它去掉，首先要看它是不是最后一个，其次要看它是不是比我大
        //要判断是否是最后一个，需要一个hashMap来保存最后一个的idx
        Map<Character, Integer> charToLastIdx = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            charToLastIdx.put(c, i);
        }
        Stack<Character> stack = new Stack<>();
        Set<Character> visited = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //如果这个字母用过，比如现在有abcd,这个时候再来一个b，之前用过b，如果换成现在这个b，因为字符串是单调递增，所以就变成了acdb，字典序反而变小。
            //所以如果这个字母用过，就忽略
            if (!visited.contains(c)) {
                //如果字母没用过，就和之前的字母比，如果比之前的大，就放在后面，如果比之前的小，看看之前那个是否后面还能有，如果后面还能有，那就去掉。
                while (!stack.isEmpty() && charToLastIdx.get(stack.peek()) > i && stack.peek() > c) {
                    visited.remove(stack.peek());
                    stack.pop();
                }
                stack.push(c);
                visited.add(c);
            }  
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }
}