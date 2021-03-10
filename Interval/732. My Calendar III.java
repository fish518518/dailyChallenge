/**
 * 时间复杂度O(Nlog(N))，对于N个book来说，每个book都需要O(Nlog(N))的时间来排序
 * 空间复杂度O(N)
 * https://leetcode.com/problems/my-calendar-iii/
 * A k-booking happens when k events have some non-empty intersection (i.e., there is some time that is common to all k events.)

You are given some events [start, end), after each given event, return an integer k representing the maximum k-booking between all the previous events.

Implement the MyCalendarThree class:

MyCalendarThree() Initializes the object.
int book(int start, int end) Returns an integer k representing the largest integer such that there exists a k-booking in the calendar.
 

Example 1:

Input
["MyCalendarThree", "book", "book", "book", "book", "book", "book"]
[[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
Output
[null, 1, 1, 2, 3, 3, 3]

Explanation
MyCalendarThree myCalendarThree = new MyCalendarThree();
myCalendarThree.book(10, 20); // return 1, The first event can be booked and is disjoint, so the maximum k-booking is a 1-booking.
myCalendarThree.book(50, 60); // return 1, The second event can be booked and is disjoint, so the maximum k-booking is a 1-booking.
myCalendarThree.book(10, 40); // return 2, The third event [10, 40) intersects the first event, and the maximum k-booking is a 2-booking.
myCalendarThree.book(5, 15); // return 3, The remaining events cause the maximum K-booking to be only a 3-booking.
myCalendarThree.book(5, 10); // return 3
myCalendarThree.book(25, 55); // return 3
 

Constraints:

0 <= start < end <= 109
At most 400 calls will be made to book.
 */
class Event {
    int type;
    int time;
    public Event(int type, int time) {
        this.type = type;
        this.time = time;
    }
}
class MyCalendarThree {
    private static final int START = 1;
    private static final int END = -1;
    List<Event> events;
    public MyCalendarThree() {
        events = new ArrayList<>();
    }
    
    public int book(int start, int end) {
        events.add(new Event(START, start));
        events.add(new Event(END, end));
        Collections.sort(events, (a, b) -> {
            if (a.time == b.time) {
                if (a.type == END) {
                    return -1;
                } else {
                    return 1;
                }
            }
            return a.time - b.time;
        });
        int res = 0;
        int k = 0;
        for (Event e : events) {
            if (e.type == START) {
                k++;
            } else {
                k--;
            }
            res = Math.max(res, k);
        }
        return res;
    }
}