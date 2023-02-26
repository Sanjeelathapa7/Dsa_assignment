/*
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

//import java.util.ArrayList;
//import java.util.List;
//
//public class OneB {
//    //class is represented as a graph
//    //size of matrxi will be Vertices(vertices in graph)*V
//
//    int vertices;
//    //Matrix to define a graph
//    int [][] adjMatrix;
//    //broken node
//    int brokenNode;
//
//
//    //constructor
//    OneB(int vertices,int [][]edges,int brokenNode){
//        //vertices defining
//        this.vertices=vertices;
//        //define the sizes of array and no.of vertices
//        this.adjMatrix=new int[vertices][vertices];
//        //looped over the elements of edges and populate it to graph
//        for(int[]edge:edges){
//            //Call AddEdge func to populate
//            AddEdge(edge[0],edge[1]);
//        }
//        //defines the broken Node
//        this.brokenNode=brokenNode;
//    }
//    // Adds an edge to an undirected graph
//    //source as u destination a v
//    void AddEdge(int sou, int des){
//        //adding and edge from u to v
//        adjMatrix[sou][des]=1;
//        //since network graph is undirected, add an edge from u to v
//        adjMatrix[des][sou]=1;
//    }
//    // Removes a vertex and all edges connected to it
//       void remVertex(){
//           // Remove the vertex from the matrix
//           for(int i=0;i<V;i++){
//               adjMatrix[i][brokenNode]=0;
//               adjMatrix[brokenNode][i]=0;
//           }
//       }
//    // Returns a list of all disconnected Node
//    List<Integer> getDisconnectSubGraphs(){
//        //Call removeVertex function to remove the broken Node from Graph
//        remVertex();
//        //mark all the vertices as not visisted
//        boolean[] visited=new boolean[V];
//        //Defined a List of Integer as subgraphs that  returns disconnected node
//        List<Integer> subGraphs = new ArrayList<>();
//        // Find all subgraphs
//        for(int v=0;v<V;++v){
//            //Checks if Vertext is Visited or is broken or not
//            //If Visited, it is not necssary to visit again and also if it is broken then it automatically separates graphs
//            if(!visited[v]&& v!=brokenNode){
//                // Defined a subgraph for each as there might be multiple small disconnected due to one breakage point
//                List<Integer> subGraph = new ArrayList<>();
//                // Print all reachable vertices from v
//                Dfs(v, visited, subGraph);
//                // if the Subgraph is the breakpoint or if the subgraph has source of network then
//                //it is not disconnected from network
//                if(!subGraph.contains(brokenNode)&& !subGraph.contains(0)){
//                    //If above condition satisfied then all all small part of subgraph to subgraphs
//                    subGraphs.addAll(subGraph);
//
//                }
//
//            }
//        }
//        //Finally returned subgraphs
//        return subGraphs;
//    }
//    void dfs(int vertices,boolean[]visited, List<Integer>subGraph){
//        // Mark the current node as visited and add it to the subgraph
//        visited[v]=true;
//        subGraph.add(v);
//        // Recur for all the vertices adjacent to this vertex
//        for(int i=0;i<V;i++){
//            if(adjMatrix[v][i]==1 && !visited[i]){
//                dfs(i, visited, subGraph);
//            }
//        }
//
//    }
//    // Driver code
//    public static void main(String[] args) {
//        //Edges OF the Graph
//        int[][] edges = {{0,1}, {0,2}, {1,3}, {1,6}, {2,4}, {4,6}, {4,5}, {5,7}};
//        //broken Node
//        int brokenNode = 4;
//        // Create a graph
////        Graph graph = new Graph(8,edges,brokenNode);
////        System.out.println("Interuppted Networks:");
////        //Get Disconencted Networks
////        List<Integer> subGraphs = graph.getDisconnectedSubGraphs();
////        System.out.println(subGraphs);
//
//
//    }
//
//
//
//}
