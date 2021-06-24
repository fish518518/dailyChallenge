import java.util.Stack;

/**
 * 
 * 时间复杂度：O(N)
 * 空间复杂度:O(N)，用于stack
 * https://leetcode.com/problems/remove-k-digits/
 * Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.

 

Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 

Constraints:

1 <= k <= num.length <= 105
num consists of only digits.
num does not have any leading zeros except for the zero itself.

 */
class Solution {
    public String removeKdigits(String num, int k) {
        //移除k位以后，剩下的数字一定是num.length - k位
        //从左往右，假如之前是3，后面来了一个2，如果不移掉3，那么结果肯定是3xxxx，用2替换3之后，结果变成了2xxxx，所以结论就是如果有比之前小的，一定要把之前的替换掉
        //最后保持了一个单调递增栈
        //如果有更多需要移除的，就从栈顶移除掉
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (!stack.isEmpty() && stack.peek() > c && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        while (!stack.isEmpty() && k > 0) {
            stack.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            char d = stack.pop();
            sb.insert(0, d);
        }
        while (sb.length() > 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}