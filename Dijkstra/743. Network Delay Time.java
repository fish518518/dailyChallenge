/**
 * https://leetcode.com/problems/network-delay-time/
You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.

 

Example 1:



```
Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2

```
Example 2:

```
Input: times = [[1,2,1]], n = 2, k = 1
Output: 1

```
Example 3:

```
Input: times = [[1,2,1]], n = 2, k = 2
Output: -1

```
 

Constraints:

- 1 <= k <= n <= 100
- 1 <= times.length <= 6000
- times[i].length == 3
- 1 <= ui, vi <= n
- ui != vi
- 0 <= wi <= 100
- All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
*/
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> nodeToNeighbors = new HashMap<>();
        //init
        for (int[] time : times) {
            int node = time[0];
            int neighbor = time[1];
            int edge = time[2];
            if (!nodeToNeighbors.containsKey(node)) {
                nodeToNeighbors.put(node, new LinkedList<>());
            }
            nodeToNeighbors.get(node).add(new int[]{neighbor, edge});
        }
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        minHeap.offer(new int[]{k, 0});
        Set<Integer> visited = new HashSet<>();
        int maxDelayTime = 0;
        while (!minHeap.isEmpty()) {
            int[] pair = minHeap.poll();
            int node = pair[0];
            int edge = pair[1];
            if (!visited.contains(node)) {
                if (nodeToNeighbors.containsKey(node)) {
                    for (int[] p : nodeToNeighbors.get(node)) {
                        minHeap.offer(new int[]{p[0], p[1] + edge});
                    }
                }
                maxDelayTime = Math.max(maxDelayTime, edge);
                visited.add(node);
            }  
        }
        if (visited.size() != n) {
            return -1;
        }
        return maxDelayTime;
    }
}