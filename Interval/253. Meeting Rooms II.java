/**
 * https://leetcode.com/problems/meeting-rooms-ii/
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
时间复杂度：O(Nlog(N))，考虑到要排序
空间复杂度：O (N)
 

Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: 1
 

Constraints:

1 <= intervals.length <= 104
0 <= starti < endi <= 106
 */
class Event {
    int type;
    int time;
    public Event(int type, int time) {
        this.type = type;
        this.time = time;
    }
}
class Solution {
    private static final int START = 1;
    private static final int END = -1;
    public int minMeetingRooms(int[][] intervals) {
        int res = -1;
        int rooms = 0;
        List<Event> events = new ArrayList<>();
        for (int[] interval : intervals) {
            Event startEvent = new Event(START, interval[0]);
            Event endEvent = new Event(END, interval[1]);
            events.add(startEvent);
            events.add(endEvent);
        }
        Collections.sort(events, (a, b) -> {
            if (a.time == b.time) {
                if (a.type == END) {
                    return -1;
                } else {
                    return 1;
                }
            }
            return (a.time - b.time);
        });
        for (Event event : events) {
            if (event.type == START) {
                rooms++;
                res = Math.max(rooms, res);
            } else {
                rooms--;
            }
        }
        return res;
    }
}