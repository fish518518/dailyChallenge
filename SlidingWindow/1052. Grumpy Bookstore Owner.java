/**时间复杂度：O(N)
 * 空间复杂度：O(1)
 * https://leetcode.com/problems/grumpy-bookstore-owner/
 * Today, the bookstore owner has a store open for customers.length minutes.  Every minute, some number of customers (customers[i]) enter the store, and all those customers leave after the end of that minute.

On some minutes, the bookstore owner is grumpy.  If the bookstore owner is grumpy on the i-th minute, grumpy[i] = 1, otherwise grumpy[i] = 0.  When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise they are satisfied.

The bookstore owner knows a secret technique to keep themselves not grumpy for X minutes straight, but can only use it once.

Return the maximum number of customers that can be satisfied throughout the day.

 

Example 1:

Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
Output: 16
Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes. 
The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 

Note:

1 <= X <= customers.length == grumpy.length <= 20000
0 <= customers[i] <= 1000
0 <= grumpy[i] <= 1
 */
class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int maxGrummpyCustomers = 0;
        int grumpyCustomers = 0;
        int left = 0;
        int right;
        for (right = 0; right < X; right++) {
            if (grumpy[right] == 1) {
                grumpyCustomers += customers[right];
            }
        }
        maxGrummpyCustomers = Math.max(maxGrummpyCustomers, grumpyCustomers);
        while (right < customers.length) {
            if (grumpy[right] == 1) {
                grumpyCustomers += customers[right];
            }
            if (grumpy[left] == 1) {
                grumpyCustomers -= customers[left];
            }
            maxGrummpyCustomers = Math.max(maxGrummpyCustomers, grumpyCustomers);
            right++;
            left++;
        }
        int res = 0;
        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i] == 0) {
                res += customers[i];
            }
        }
        return res + maxGrummpyCustomers;
    }
}