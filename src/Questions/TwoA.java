//import java.util.LinkedList;
//
//class Two {
//    public int gcd(int n1, int n2) {
//        if (n2 == 0) {
//            return n1;
//        }
//        return gcd(n2, n1 % n2);
//    }
//
//    public void dfs(int[] nums, LinkedList<Integer>[] tree, int depth, int node, boolean[] visited, int[] ans, Map<Integer,int[]> map, boolean[][] poss) {
//        if(visited[node]) return;
//        visited[node] = true;
//        int ancestor = -1;
//        int d = Integer.MAX_VALUE;
//        for(int i = 1; i < 51; i++) {
//            if(poss[nums[node]][i] && map.containsKey(i)) {
//                if(depth - map.get(i)[0] <= d) {
//                    d = depth - map.get(i)[0];
//                    ancestor = map.get(i)[1];
//                }
//            }
//        }
//        ans[node] = ancestor;
//        int[] exist = (map.containsKey(nums[node])) ? map.get(nums[node]) :  new int[]{-1,-1};
//        map.put(nums[node],new int[]{depth,node});
//        for(int child : tree[node]) {
//            if(visited[child]) continue;
//            dfs(nums, tree