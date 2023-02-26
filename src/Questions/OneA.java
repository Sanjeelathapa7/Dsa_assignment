package Questions;/*
Question 1
a)
There are n nations linked by train routes. You are given a 2D array indicating routes between countries
and the time required to reach the target country, such that E[i]=[xi,yi,ki], where xi represents the
 source country, yi represents the destination country, and ki represents the time required to go from xi
  to yi. If you are also given information on the charges, you must pay while entering any country.
   Create an algorithm that returns the cheapest route from county A to county B with a time constraint.
 */

import java.util.*;

// Define a class called OneA to represent a node in the graph
class OneA {
    int id;      // id of the node
    int time;    // time taken to reach this node
    int cost;    // cost of reaching this node

    // Constructor to initialize the node with its id, time, and cost
    public OneA(int id, int time, int cost) {
        this.id = id;
        this.time = time;
        this.cost = cost;
    }
}

// Define a class called CheapestPath to find the cheapest path in the graph
class CheapestPath {
    // Define a static method to find the cheapest path in the graph
    public static int findCheapestPath(int[][] edges, int[] charges, int source, int destination, int timeconstraint) {
        // Create a graph represented as an adjacent list using a HashMap
        Map<Integer, List<OneA>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int time = edge[2];
            int cost = charges[to];
            List<OneA> list = graph.getOrDefault(from, new ArrayList<>());
            list.add(new OneA(to, time, cost));
            graph.put(from, list);
        }

        // Set distances and visited to initial values
        int[] distances = new int[charges.length];
        boolean[] visited = new boolean[charges.length];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        // Use a priority queue to select the node with the smallest distance
        PriorityQueue<OneA> queue = new PriorityQueue<>((a, b) -> a.time - b.time);
        queue.offer(new OneA(source, 0, charges[source]));

        // Use Dijkstra's algorithm with the time constraint
        while (!queue.isEmpty()) {
            OneA a = queue.poll();
            if (a.id == destination) {
                return a.cost;
            }
            if (visited[a.id]) {
                continue;
            }
            visited[a.id] = true;
            for (OneA neighbor : graph.getOrDefault(a.id, new ArrayList<>())) {
                int newTime = a.time + neighbor.time;
                int newCost = a.cost + charges[neighbor.id];
                if (newTime <= timeconstraint && newCost < distances[neighbor.id]) {
                    distances[neighbor.id] = newCost;
                    queue.offer(new OneA(neighbor.id, newTime, newCost));
                }
            }
        }
        return -1;
    }

    // Define a main method to test the CheapestPath class
    public static void main(String[] args) {
        int a[][] = {{0, 1, 5}, {0, 3, 2}, {1, 2, 5}, {3, 4, 5}, {4, 5, 6}, {2, 5, 5}};
        System.out.println(findCheapestPath(
                a, new int[]{10, 2, 3, 25, 25, 4}, 0, 5, 14));
    }
}






