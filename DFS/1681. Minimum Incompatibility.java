/**时间复杂度：O(k^N)，每个元素都有k个选择
空间复杂度：排序需要O(N)，递归深度O(N)，所以O(N)
 * https://leetcode.com/problems/minimum-incompatibility/
 * You are given an integer array nums​​​ and an integer k. You are asked to distribute this array into k subsets of equal size such that there are no two equal elements in the same subset.

A subset's incompatibility is the difference between the maximum and minimum elements in that array.

Return the minimum possible sum of incompatibilities of the k subsets after distributing the array optimally, or return -1 if it is not possible.

A subset is a group integers that appear in the array with no particular order.

 

Example 1:

Input: nums = [1,2,1,4], k = 2
Output: 4
Explanation: The optimal distribution of subsets is [1,2] and [1,4].
The incompatibility is (2-1) + (4-1) = 4.
Note that [1,1] and [2,4] would result in a smaller sum, but the first subset contains 2 equal elements.
Example 2:

Input: nums = [6,3,8,1,3,1,2,2], k = 4
Output: 6
Explanation: The optimal distribution of subsets is [1,2], [2,3], [6,8], and [1,3].
The incompatibility is (2-1) + (3-2) + (8-6) + (3-1) = 6.
Example 3:

Input: nums = [5,3,3,6,3,3], k = 3
Output: -1
Explanation: It is impossible to distribute nums into 3 subsets where no two elements are equal in the same subset.
 

Constraints:

1 <= k <= nums.length <= 16
nums.length is divisible by k
1 <= nums[i] <= nums.length

 */
class Solution {
    int minIncompatibalities = -1;
    private void backtracking(int[] nums, int k, int count, int min, int max, int incompatibilities, int idx, boolean[] visited, int lastAdded, int size) {
        //base
        if (minIncompatibalities != -1 && incompatibilities >= minIncompatibalities) {
            return;
        }
        if (count == k) {
            incompatibilities += max - min;
            if (minIncompatibalities == -1) {
                minIncompatibalities = incompatibilities;
            } else {
                minIncompatibalities = Math.min(minIncompatibalities, incompatibilities);
            }
        }
        int subsetSize = nums.length / k;
        if (size == subsetSize) {
            backtracking(nums, k, count + 1, -1, -1, incompatibilities + max - min, 0, visited, -1, 0);
            return;
        }
        int pre = -1;
        for (int i = idx; i < nums.length; i++) {
            int cur = nums[i];
            if (visited[i] == false && cur != pre && (lastAdded == -1 || cur != lastAdded)) {
                pre = cur;
                visited[i] = true;
                backtracking(nums, k, count, min == -1 ? cur : Math.min(min, cur), max == -1 ? cur : Math.max(max, cur), incompatibilities, i + 1, visited, cur, size + 1);
                visited[i] = false;
            }
        }
    }
    public int minimumIncompatibility(int[] nums, int k) {
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        for (boolean v : visited) {
            v = false;
        }
        backtracking(nums, k, 0, -1, -1, 0, 0, visited, -1, 0);
        return minIncompatibalities;
    }
}