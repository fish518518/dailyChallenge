/**
时间复杂度：O(N), 遍历一次数组
空间复杂度：O(N), 给set用
https://leetcode.com/problems/perfect-rectangle/
Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.

Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is represented as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).


Example 1:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [3,2,4,4],
  [1,3,2,4],
  [2,3,3,4]
]

Return true. All 5 rectangles together form an exact cover of a rectangular region.
 

 

Example 2:

rectangles = [
  [1,1,2,3],
  [1,3,2,4],
  [3,1,4,2],
  [3,2,4,4]
]

Return false. Because there is a gap between the two rectangular regions.
 

 

Example 3:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [3,2,4,4]
]

Return false. Because there is a gap in the top center.
 

 

Example 4:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [2,2,4,4]
]

Return false. Because two of the rectangles overlap with each other.
 */
 class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        if (rectangles == null || rectangles.length == 0) return false;
        //找出最左下角的点，和最右上角的点
        int X1 = Integer.MAX_VALUE;
        int Y1 = Integer.MAX_VALUE;
        int X2 = Integer.MIN_VALUE;
        int Y2 = Integer.MIN_VALUE;
        int totalSize = 0;
        Set<String> points = new HashSet<>();
        for (int[] rec : rectangles) {
            X1 = Math.min(X1, rec[0]);
            Y1 = Math.min(Y1, rec[1]);
            X2 = Math.max(X2, rec[2]);
            Y2 = Math.max(Y2, rec[3]);
            int x1 = rec[0];
            int y1 = rec[1];
            int x2 = rec[2];
            int y2 = rec[3];
            int size = (x2 - x1) * (y2 - y1);
            totalSize += size;
            String[] curPoints = {x1 + "," + y1, x1 + "," + y2, x2 + "," + y1, x2 + "," + y2};
            for (String point : curPoints) {
                if (points.contains(point)) {
                    points.remove(point);
                } else {
                    points.add(point);
                }
            }
        }
        int bigRecSize = (X2 - X1) * (Y2 - Y1);
        //检查面积之和是不是等于bigRecSize
        if (totalSize != bigRecSize) return false;
        //检查一下除了四个角以外，其余的顶点都是出现2次，只有四个角出现1次
        if (points.size() == 4 && points.contains(X1 + "," + Y1)
           && points.contains(X1 + "," + Y2)
           && points.contains(X2 + "," + Y1)
           && points.contains(X2 + "," + Y2)) {
            return true;
        } else {
            return false;
        }
    }
}