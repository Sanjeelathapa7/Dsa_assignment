package Questions;
/*
1(b)
Assume you were hired to create an application for an ISP, and there is n number of network devices,
such as routers, that are linked together to provides internet access to home user users.
You are given a 2D array that represents network connections between these network devices
such that a[i]=[xi,yi] where xi is connected to yi device.  Suppose there is a power outage on a
 certain device provided as int n represents id of the device on which power failure occurred)),
 Write an algorithm to return impacted network devices due to breakage of the link between network devices.
  These impacted device list assists you notify linked consumers that there is a power outage and it will
  take some time to rectify an issue. Note that: node 0 will always represent a source of internet
  or gateway to international network..
 */


import java.util.ArrayList;
import java.util.List;

// Define a class named OneB
class OneB {

    // Define class variables
    // V is the number of vertices in the graph
    int V;
    // adjMatrix is used to represent the graph as an adjacency matrix
    // The size of the matrix will be V * V
    int[][] adjMatrix;
    // brokenNode represents the node that is broken in the network
    int brokenNode;

    // Constructor to initialize class variables
    OneB(int V, int[][] edges, int brokenNode) {
        // Set the number of vertices
        this.V = V;
        // Initialize the adjacency matrix with size V * V
        this.adjMatrix = new int[V][V];
        // Add all the edges to the graph using addEdge() function
        for (int[] edge : edges) {
            addEdge(edge[0], edge[1]);
        }
        // Set the broken node
        this.brokenNode = brokenNode;
    }

    // Add an edge to the undirected graph
    void addEdge(int src, int dest) {
        // Add an edge from src to dest
        adjMatrix[src][dest] = 1;
        // Since the network graph is undirected, add an edge from dest to src as well
        adjMatrix[dest][src] = 1;
    }

    // Remove the broken node from the graph and all its edges
    void removeVertex() {
        // Remove the broken node from the matrix
        for (int i = 0; i < V; i++) {
            adjMatrix[i][brokenNode] = 0;
            adjMatrix[brokenNode][i] = 0;
        }
    }

    // Return a list of disconnected subgraphs
    List<Integer> getDisconnectedSubgraphs() {
        // Remove the broken node from the graph
        removeVertex();
        // Initialize the visited array to keep track of visited vertices
        boolean[] visited = new boolean[V];
        // Initialize a list to store the disconnected subgraphs
        List<Integer> subgraphs = new ArrayList<>();

        // Find all subgraphs
        for (int v = 0; v < V; ++v) {
            // If the vertex is not visited and is not the broken node,
            // it means that it belongs to a new subgraph
            if (!visited[v] && v != brokenNode) {
                // Initialize a new subgraph list for this vertex
                List<Integer> subgraph = new ArrayList<>();
                // Traverse all the vertices in this subgraph using DFSUtil()
                DFSUtil(v, visited, subgraph);
                // If the subgraph does not contain the broken node or the source node (0),
                // it means that it is not disconnected from the network
                if (!subgraph.contains(brokenNode) && !subgraph.contains(0)) {
                    // Add all the vertices in this subgraph to the subgraphs list
                    subgraphs.addAll(subgraph);
                }
            }
        }
        // Return the list of disconnected subgraphs
        return subgraphs;
    }

    // Recursive function to traverse all vertices in a subgraph using Depth-First Search (DFS)
    void DFSUtil(int v, boolean[] visited, List<Integer> subgraph) {
        // Mark the current vertex as visited and add it to the subgraph list
        visited[v] = true;
        subgraph.add(v);
        // Recur for all the vertices adjacent to this vertex
        for (int i = 0; i < V; ++i) {
            // If the vertex is adjacent to the current vertex and is not visited,
            // then traverse it recursively
            if (adjMatrix[v][i] == 1 && !visited[i]) {
                DFSUtil(i, visited, subgraph);
            }
        }
    }
    public static void main(String[] args) {
        //Edges OF the Graph
        int[][] edges = {{0,1}, {0,2}, {1,3}, {1,6}, {2,4}, {4,6}, {4,5}, {5,7}};
        //broken Node
        int brokenNode = 4;
        // Create a graph
        OneB oneB = new OneB(8,edges,brokenNode);

        System.out.println("Interuppted Networks:");
        //Get Disconencted Networks
        List<Integer> subgraphs = oneB.getDisconnectedSubgraphs();
        System.out.println(subgraphs);

    }
    }

