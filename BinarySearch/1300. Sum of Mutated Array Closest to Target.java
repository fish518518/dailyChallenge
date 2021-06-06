/**
 * 时间复杂度：O(N*log(10e5)) => O(N)
 * 空间复杂度：O(1)
 * https://leetcode.com/problems/sum-of-mutated-array-closest-to-target/
 * Given an integer array arr and a target value target, return the integer value such that when we change all the integers larger than value in the given array to be equal to value, the sum of the array gets as close as possible (in absolute difference) to target.

In case of a tie, return the minimum such integer.

Notice that the answer is not neccesarilly a number from arr.

 

Example 1:

Input: arr = [4,9,3], target = 10
Output: 3
Explanation: When using 3 arr converts to [3, 3, 3] which sums 9 and that's the optimal answer.
Example 2:

Input: arr = [2,3,5], target = 10
Output: 5
Example 3:

Input: arr = [60864,25176,27249,21296,20204], target = 56803
Output: 11361
 

Constraints:

1 <= arr.length <= 104
1 <= arr[i], target <= 105
 */
class Solution {
    private int getMax(int[] arr) {
        int max = arr[0];
        for (int a : arr) {
            max = Math.max(a, max);
        }
        return max;
    }
    private int getSum(int[] arr, int value) {
        //如果越界就返回Integer.MAX_VALUE
        long sum = 0;
        for (int a : arr) {
            sum += Math.min(a, value);
        }
        return sum > Integer.MAX_VALUE ? Integer.MAX_VALUE: (int)sum;
    }
    public int findBestValue(int[] arr, int target) {
        //1 <= arr[i], target <= 10e5知道所有元素都是正数，target也是正数
        //要求一个value，所有>value的元素都变成value之后取和越接近target越好
        //首先考虑如果设value为一个小于0的数，那么sum就是负数，那么当value是0的时候，sum是0，因为target>=1所以sum是0肯定比sum是负数更接近target，所以一开始初始值是value=0，diff=target。并且value必须>=0
        //当value增加的时候，sum要么增加，要么不变
        //当value减少的时候，sum要么减少，要么不变
        //value的取值范围是[0, 10e5]
        int left = 0;
        int right = (int)Math.pow(10, 5);
        int result = 0;
        int diff = target;
        int max = getMax(arr);
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int sum = getSum(arr, mid);
            if (sum != Integer.MAX_VALUE) {
                int newDiff = Math.abs(sum - target);
                if (newDiff < diff) {
                    result = mid;
                    diff = newDiff;
                } else if (newDiff == diff) {
                    result = Math.min(result, mid);
                }
            }
            
            //要使得更接近
            if (sum >= target) {
                //想要找有没有更小的sum或者sum虽然相同，但是value更小
                right = mid - 1;
            } else {
                //想要找有没有更大的sum，或者sum虽然相同，但是value更小
                if (mid < max) {
                    left = mid + 1;
                } else {
                    left = max;
                    right = mid - 1;
                }
            }
        }
        return result;
    }
}