import java.util.Arrays;

/**
 * 时间复杂度：O(N)
 * 空间复杂度：O(1)
 * https://leetcode.com/problems/sell-diminishing-valued-colored-balls/
 * You have an inventory of different colored balls, and there is a customer that wants orders balls of any color.

The customer weirdly values the colored balls. Each colored ball's value is the number of balls of that color you currently have in your inventory. For example, if you own 6 yellow balls, the customer would pay 6 for the first yellow ball. After the transaction, there are only 5 yellow balls left, so the next yellow ball is then valued at 5 (i.e., the value of the balls decreases as you sell more to the customer).

You are given an integer array, inventory, where inventory[i] represents the number of balls of the ith color that you initially own. You are also given an integer orders, which represents the total number of balls that the customer wants. You can sell the balls in any order.

Return the maximum total value that you can attain after selling orders colored balls. As the answer may be too large, return it modulo 109 + 7.

 

Example 1:


Input: inventory = [2,5], orders = 4
Output: 14
Explanation: Sell the 1st color 1 time (2) and the 2nd color 3 times (5 + 4 + 3).
The maximum total value is 2 + 5 + 4 + 3 = 14.
Example 2:

Input: inventory = [3,5], orders = 6
Output: 19
Explanation: Sell the 1st color 2 times (3 + 2) and the 2nd color 4 times (5 + 4 + 3 + 2).
The maximum total value is 3 + 2 + 5 + 4 + 3 + 2 = 19.
Example 3:

Input: inventory = [2,8,4,10,6], orders = 20
Output: 110
Example 4:

Input: inventory = [1000000000], orders = 1000000000
Output: 21
Explanation: Sell the 1st color 1000000000 times for a total value of 500000000500000000. 500000000500000000 modulo 109 + 7 = 21.
 

Constraints:

1 <= inventory.length <= 105
1 <= inventory[i] <= 109
1 <= orders <= min(sum(inventory[i]), 109)
 */
class Solution {
    private int getTotalProfit(int[] inventory, int orders, int K) {
        //贪心，从最高价开始卖
        //大于K的都卖掉了，等于K的卖掉了一部份
        int modulo = (int)Math.pow(10, 9) + 7;
        long profit = 0;
        // (A + B) mod C = (A mod C + B mod C) mod C
        for (int i = inventory.length - 1; i >= 0; i--) {
            int cur = inventory[i];
            if (K + 1 > cur) continue;
            //卖了[k+1, cur]
            profit += (long)(K + 1 + cur) * (cur - K) / 2 % modulo;
            orders -= (cur - K);
        }
        //剩下的都是K价格卖掉的
        profit += (long)K * orders % modulo;//错误点：越界，这里要先转成long，不然相乘的时候还是int会越界
        return (int)(profit % modulo);
    }
    private boolean isOK(int[] inventory, int K, int orders) {
        //可以卖掉的小球数量>=orders
        long count = 0;
        for (int i : inventory) {
            if (i >= K) {
                count += i - K + 1;
            }
            if (count > Integer.MAX_VALUE) {
                return true;
            }
        }
        return count >= orders;
    }
    public int maxProfit(int[] inventory, int orders) {
        //二分价格为K，大于K的都卖掉了（A）。一部分等于K的卖掉了（B）。A+B == orders
        //目标是找出最大的K，使得买到的count >= orders
        //K的取值范围是[0, 10e9]
        int left = 1;
        int right = (int)Math.pow(10, 9);
        int K = 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isOK(inventory, mid, orders)) {
                K = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return getTotalProfit(inventory, orders, K);
    }
}
