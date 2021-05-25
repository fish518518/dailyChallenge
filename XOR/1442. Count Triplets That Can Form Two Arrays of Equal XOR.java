/**https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/
 * 时间复杂度：O(N*N)，遍历一遍数组取得prefixXor需要O(N)，同时在哈希表中找配对，因为配对也是一个List，所以遍历配对复杂度也是O(N)，一共O(N*N)
 * 空间复杂度：O(N)，用于哈希表
 * Given an array of integers arr.

We want to select three indices i, j and k where (0 <= i < j <= k < arr.length).

Let's define a and b as follows:

a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
Note that ^ denotes the bitwise-xor operation.

Return the number of triplets (i, j and k) Where a == b.

 

Example 1:

Input: arr = [2,3,1,6,7]
Output: 4
Explanation: The triplets are (0,1,2), (0,2,2), (2,3,4) and (2,4,4)
Example 2:

Input: arr = [1,1,1,1,1]
Output: 10
Example 3:

Input: arr = [2,3]
Output: 0
Example 4:

Input: arr = [1,3,5,7,9]
Output: 3
Example 5:

Input: arr = [7,11,12,9,5,2,7,17,22]
Output: 8
 

Constraints:

1 <= arr.length <= 300
1 <= arr[i] <= 10^8

 */
class Solution {
    public int countTriplets(int[] arr) {
        Map<Integer, List> prefixXorToIndexes = new HashMap<>();
        prefixXorToIndexes.put(0, new LinkedList<Integer>());
        prefixXorToIndexes.get(0).add(0);
        int prefixXor = 0;
        int result = 0;
        //当前前缀和是prefixXor[i + 1]，要找和自己相同的前缀和，找到的是prefixXor[idx]，这样找到的区间代表[idx, i]是0，可以组成i - idx种组合
        for (int i = 0; i < arr.length; i++) {
            prefixXor ^=  arr[i];//[0, i]的xor
            if (prefixXorToIndexes.containsKey(prefixXor)) {
                List indexes = prefixXorToIndexes.get(prefixXor);
                for (int j = 0; j < indexes.size(); j++) {//[idx, i]
                    int idx = (int)indexes.get(j);
                    result += i - idx;
                }
            }
            if (prefixXorToIndexes.containsKey(prefixXor)) {
                prefixXorToIndexes.get(prefixXor);
            } else {
                prefixXorToIndexes.put(prefixXor, new LinkedList<Integer>());
            }
            prefixXorToIndexes.get(prefixXor).add(i + 1);
        }
        return result;
    }
}