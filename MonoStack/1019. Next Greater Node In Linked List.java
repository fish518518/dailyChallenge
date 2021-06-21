import java.util.Stack;

/**
 * 时间复杂度：O(N)
 * 空间复杂度：O(1)
 * https://leetcode.com/problems/next-greater-node-in-linked-list/
 * We are given a linked list with head as the first node.  Let's number the nodes in the list: node_1, node_2, node_3, ... etc.

Each node may have a next larger value: for node_i, next_larger(node_i) is the node_j.val such that j > i, node_j.val > node_i.val, and j is the smallest possible choice.  If such a j does not exist, the next larger value is 0.

Return an array of integers answer, where answer[i] = next_larger(node_{i+1}).

Note that in the example inputs (not outputs) below, arrays such as [2,1,5] represent the serialization of a linked list with a head node value of 2, second node value of 1, and third node value of 5.

 

Example 1:

Input: [2,1,5]
Output: [5,5,0]
Example 2:

Input: [2,7,4,3,5]
Output: [7,0,5,5,0]
Example 3:

Input: [1,7,5,1,9,2,5,1]
Output: [7,9,9,9,0,5,0,0]
 

Note:

1 <= node.val <= 10^9 for each node in the linked list.
The given list has length in the range [0, 10000].
 */
class Solution {
    public int[] nextLargerNodes(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode cur = head;
        int length = 0;
        while (cur != null) {
            length++;
            ListNode next = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
            cur = next;
        }
        cur = dummy.next;
        int[] ans = new int[length];
        int idx = length - 1;
        Stack<Integer> s = new Stack<>();
        while (idx >= 0) {
            while (!s.isEmpty() && s.peek() <= cur.val) {
                s.pop();
            }
            if (s.isEmpty()) {
                ans[idx] = 0;
            } else {
                ans[idx] = s.peek();
            }
            s.push(cur.val);
            cur = cur.next;
            idx--;
        }
        return ans;
    }
}