import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/maximum-width-ramp/
 * Given an array nums of integers, a ramp is a tuple (i, j) for which i < j and nums[i] <= nums[j].  The width of such a ramp is j - i.

Find the maximum width of a ramp in nums.  If one doesn't exist, return 0.

 

Example 1:

Input: nums = [6,0,8,2,1,5]
Output: 4
Explanation: 
The maximum width ramp is achieved at (i, j) = (1, 5): nums[1] = 0 and nums[5] = 5.
Example 2:

Input: nums = [9,8,1,0,1,9,4,0,4,1]
Output: 7
Explanation: 
The maximum width ramp is achieved at (i, j) = (2, 9): nums[2] = 1 and nums[9] = 1.
 

Note:

2 <= nums.length <= 50000
0 <= nums[i] <= 50000
 */
//解法1: 时间复杂度O(N(logN))
//空间复杂度：O(N)
class Solution {
    private int find(List<Integer> descending, int[] nums, int num) {
        int res = -1;
        int left = 0;
        int right = descending.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[descending.get(mid)] <= num) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res == -1 ? -1 : descending.get(res);
    }
    public int maxWidthRamp(int[] nums) {
        //对于固定的nums[j]，要找nums[i]，就是在[0, j)的index里面找符合nums[i] <= nums[j]，假设我们找到了nums[a]，那么就算nums[b](b > a)也符合条件，我们也不会再选择它了。所以nums[b]如果<=nums[a]的情况下，我们都可以直接忽略。唯一会选择nums[b]作为nums[i]的选择的条件就是,nums[a]不符合条件，也就是nums[a]>nums[j]，而nums[b]因为小于nums[a]而符合条件。
        //所以对于固定的nums[j]只要保持一个[0, j)的单调递减区间即可
        //因为是单调递减，所以对于nums[j]来说就是找[0, j)区间里面左边第一个符合nums[i] <= nums[j]的index，也就是二分，O(log(N))
        //对于j有n个选择，所以总时间复杂度是O(Nlog(N))
        List<Integer> descending = new ArrayList<>();
        int res = 0;
        for (int j = 0; j < nums.length; j++) {
            int num = nums[j];
            if (descending.size() != 0) {
                int i = find(descending, nums, num);//找最左边符合小于等于num的idx
                if (i != -1) {
                    res = Math.max(res, j - i);
                }
            }
            //如果符合递减条件放入nums[j]
            if (descending.size() == 0 || nums[descending.get(descending.size() - 1)] > num) {
                descending.add(j);
            }
        }
        return res;
    }
}

//解法2: 时间复杂度O(N)
class Solution2 {
    public int maxWidthRamp(int[] nums) {
        //比解法1更近一步，如果nums[j]能找到一个nums[i]符合条件，那么nums[j - 1]肯定不会找nums[i]，因为这样就算符合条件，width也不会更大。
        //所以保持一个单调递减栈，从后往前遍历nums，如果有符合条件的直接计算并弹出以后都不需要用了
        Stack<Integer> stack = new Stack<>();//idx
        for (int i = 0; i < nums.length; i++) {
            if (stack.isEmpty() || nums[stack.peek()] > nums[i]) {
                stack.push(i);
            }
            
        }
        //此时stack已经是一个单调递减栈，里面存的是idx
        int res = 0;
        for (int j = nums.length - 1; j >= 0; j--) {
            //判断是否符合条件
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[j]) {
                res = Math.max(res, j - stack.peek());
                stack.pop();
            }
        }
        return res;
    }
}