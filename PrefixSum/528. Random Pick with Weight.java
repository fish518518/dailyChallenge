/**
 * 时间复杂度：init - O(N)，random - O(log(N))
 * 空间复杂度：init - O(N)，random - O(1)
 * https://leetcode.com/problems/random-pick-with-weight/
 * You are given an array of positive integers w where w[i] describes the weight of ith index (0-indexed).

We need to call the function pickIndex() which randomly returns an integer in the range [0, w.length - 1]. pickIndex() should return the integer proportional to its weight in the w array. For example, for w = [1, 3], the probability of picking the index 0 is 1 / (1 + 3) = 0.25 (i.e 25%) while the probability of picking the index 1 is 3 / (1 + 3) = 0.75 (i.e 75%).

More formally, the probability of picking index i is w[i] / sum(w).

 

Example 1:

Input
["Solution","pickIndex"]
[[[1]],[]]
Output
[null,0]

Explanation
Solution solution = new Solution([1]);
solution.pickIndex(); // return 0. Since there is only one single element on the array the only option is to return the first element.
Example 2:

Input
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output
[null,1,1,1,1,0]

Explanation
Solution solution = new Solution([1, 3]);
solution.pickIndex(); // return 1. It's returning the second element (index = 1) that has probability of 3/4.
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 0. It's returning the first element (index = 0) that has probability of 1/4.

Since this is a randomization problem, multiple answers are allowed so the following outputs can be considered correct :
[null,1,1,1,1,0]
[null,1,1,1,1,1]
[null,1,1,1,0,0]
[null,1,1,1,0,1]
[null,1,0,1,0,0]
......
and so on.
 

Constraints:

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.
 */
class Solution {
    private int[] prefixSum;
    public Solution(int[] w) {
        prefixSum = new int[w.length];
        prefixSum[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + w[i]; //[0, 1, 2], [3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16], [17], [18, 19, 20, 21, 22, 23, 24]
            //[3, 17, 18, 25]
            //[0, 25)
            //random: 2
        }
    }
    
    public int pickIndex() {
        int random = (int)(Math.random() * (prefixSum[prefixSum.length - 1]));//[0, 1) -> [0, 24]
        int left = 0;
        int right = prefixSum.length - 1;
        int ans = 0;
        //找第一个大于random的idx
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (prefixSum[mid] > random) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
