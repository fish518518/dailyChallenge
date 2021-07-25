/**时间复杂度：排序O(Nlog(N))，一共有N个元素，每个元素都可以取或者不取，所以第一次有(N + 1)个选择，第二次有N个选择，以此类推，一直到最后只有一个选择，一共是O(N!)
空间复杂度：排序需要O(N)空间，递归深度最大是O(N)，所以是O(N)
 * https://leetcode.com/problems/letter-tile-possibilities/
 * You have n  tiles, where each tile has one letter tiles[i] printed on it.

Return the number of possible non-empty sequences of letters you can make using the letters printed on those tiles.

 

Example 1:

Input: tiles = "AAB"
Output: 8
Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
Example 2:

Input: tiles = "AAABBC"
Output: 188
Example 3:

Input: tiles = "V"
Output: 1
 

Constraints:

1 <= tiles.length <= 7
tiles consists of uppercase English letters.
 */
class Solution {
    private int res = 0;
    private void backtracking(char[] tilesArr, boolean[] visited, int count) {
        if (count == tilesArr.length) return;
        for (int i = 0; i < tilesArr.length; i++) {
            char cur = tilesArr[i];
            if (visited[i] == false && (i == 0 || cur != tilesArr[i - 1] || visited[i - 1] == true)) {
                visited[i] = true;
                res++;
                backtracking(tilesArr, visited, count + 1);
                visited[i] = false;
            }
        }
    }
public int numTilePossibilities(String tiles) {
    char[] tilesArr = tiles.toCharArray();
    Arrays.sort(tilesArr);
    boolean[] visited = new boolean[tilesArr.length];
    for (boolean v : visited) {
        v = false;
    }
    backtracking(tilesArr, visited, 0);
    return res;
}
}