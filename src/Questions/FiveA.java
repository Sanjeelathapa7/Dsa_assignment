package Questions;

/*
Question 5
a)
You are given a 2D array containing coordinates and height of rectangle such that
height[i]=[xi,yi,hi], where xi the x coordinate of left edge, yi represents x coordinate of
right edge of rectangle and hi represents the height of the peaks of each rectangle.
If you want to construct a border line over the peaks of rectangle represented in bar chart,
return the key coordinates required to build a border line that contacts the peaks of the given chart.
Note: key points are the left coordinates of shape representing peaks where you need to draw
 boarder line.
 */

import java.util.*;

class FiveA {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res=new ArrayList<>();

        // list to store the starting and ending points of all buildings with their heights
        List<int[]> heights=new ArrayList<>();

        // transform each building into its starting and ending points with heights
        transformBuilding(buildings, heights);

        // sort the starting and ending points in ascending order based on x-coordinate (left endpoint of a segment)
        // if two points have the same x-coordinate, sort them by their y-coordinate (height of the segment)
        Collections.sort(heights, (a,b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);

        // max heap to keep track of the maximum height of all segments
        PriorityQueue<Integer> pq=new PriorityQueue<Integer>((a,b)->(b-a));

        // add 0 to the max heap as the initial maximum height
        pq.add(0);
        int prevMax=0;

        // process each point in order
        for(int[] height : heights) {
            if(height[1] < 0) {
                // the current point is a starting point of a segment
                // add the height of this segment to the max heap
                pq.add(-height[1]);
            }
            else {
                // the current point is an ending point of a segment
                // remove the height of this segment from the max heap
                pq.remove(height[1]);
            }

            // get the current maximum height from the max heap
            int currentMax = pq.peek();
            if(currentMax != prevMax) {
                // if the current maximum height is different from the previous maximum height
                // add this point to the result list
                List<Integer> subResult = new ArrayList<>();
                subResult.add(height[0]);
                subResult.add(currentMax);
                res.add(subResult);
                prevMax = currentMax;
            }
        }

        return res;
    }

    // transform each building into its starting and ending points with heights
    // for example, [1,2,3] -> [1,-3] and [2,3] -> [2,3]
    private void transformBuilding(int[][] buildings, List<int[]> heights) {
        for(int[] building : buildings) {
            heights.add(new int[]{building[0], -building[2]});
            heights.add(new int[]{building[1], building[2]});
        }
    }

    public static void main(String[] args) {
        int[][] height = {{1,4,10},{2,5,15},{5,8,12},{9,11,1},{11,13,15}};
        FiveA fiveA = new FiveA();
        List<List<Integer>> ans = fiveA.getSkyline(height);
        System.out.println(ans);
    }
}

