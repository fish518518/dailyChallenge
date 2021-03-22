/**时间复杂度O(Nlog(N))，用于排序，和heap操作
 * 空间复杂度O(N)，用于存哈希Set和排序和Heap
 * https://leetcode.com/problems/the-skyline-problem/
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.

The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:

lefti is the x coordinate of the left edge of the ith building.
righti is the x coordinate of the right edge of the ith building.
heighti is the height of the ith building.
You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list, which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends. Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.

Note: There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]

 

Example 1:


Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
Explanation:
Figure A shows the buildings of the input.
Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.
Example 2:

Input: buildings = [[0,2,3],[2,5,3]]
Output: [[0,3],[5,0]]
 

Constraints:

1 <= buildings.length <= 104
0 <= lefti < righti <= 231 - 1
1 <= heighti <= 231 - 1
buildings is sorted by lefti in non-decreasing order.
 */

import java.util.*;
class Event {
    int x;
    int type;
    int buildingIdx;
    public Event(int x, int type, int buildingIdx) {
        this.x = x;
        this.type = type;
        this.buildingIdx = buildingIdx;
    }
}
class Solution {
    private static final int UP = 1;
    private static final int DOWN = -1;
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < buildings.length; i++) {
            int[] building = buildings[i];
            events.add(new Event(building[0], UP, i));
            events.add(new Event(building[1], DOWN, i));
        }
        Collections.sort(events, (a, b) -> a.x - b.x);
        Queue<Integer> maxHeap = new PriorityQueue<>((buildingIdx1, buildingIdx2) -> {
            int[] building1 = buildings[buildingIdx1];
            int[] building2 = buildings[buildingIdx2];
            return building2[2] - building1[2];
        });
        int height = 0;
        Set<Integer> removedIdx = new HashSet<>();
        int i = 0;
        while (i < events.size()) {
            int x = events.get(i).x;
            
            while (i < events.size() && events.get(i).x == x) {
                Event event = events.get(i);
                if (event.type == UP) {
                    maxHeap.offer(event.buildingIdx);
                } else {
                    removedIdx.add(event.buildingIdx);
                }
                i++;
            }
            while (removedIdx.contains(maxHeap.peek())) {
                maxHeap.poll();
            }
            int curHeight = maxHeap.isEmpty() ? 0 : buildings[maxHeap.peek()][2];
            if (curHeight != height) {
                List<Integer> subRes = new ArrayList<>();
                subRes.add(x);
                subRes.add(curHeight);
                height = curHeight;
                res.add(subRes);
            }
        }
        return res;
    }
}
/**

1. 把每个楼拆成up和down两个事件
2. 把同一个横轴x的事件都一起处理
3. 处理完后看高度有没有变化，每次高度有变化的时候要更新点
高度保存在heap里面，最大堆，所以顶上是最大的，每次有down事件的时候就把这个building加入remove的set里面，每次去顶上拿的时候，如果发现是removed的，就pop，一直到没有remove为止
*/