package Questions;


import java.util.LinkedList;
import java.util.Queue;

/* 2.b) You are given an array of binary trees that represent different cities where a certain corporation
has its branch office and the organization wishes to provide service by constructing a service center.
Building service centers at any node, i.e., a city can give service to its directly connected cities where
it can provide service to its parents, itself, and its immediate children. Returns the smallest number of
service centers required by the corporation to provide service to all connected cities. Note that: the root
node represents the head office and other connected nodes represent the branch office connected to the head
office maintaining some kind of hierarchy.

Input: tree= {0,0, null, 0, null, 0, null, null, 0}
Output: 2
Explanation: construction of two service centers denoted by black markk will be enough to provide service
to all cities.
*/
class TwoB{
    TwoB left;
    TwoB right;
    int data;

    TwoB(int data){
        this.data=data;
        this.left=this.right=null;
    }
    TwoB(){

    }
//takes an array of objects as input, and returns the root of a binary tree constructed from the input array
    public TwoB addToTree(Object[] input) {
        if (input == null || input.length == 0) {
            return null;
        }

        TwoB twoB = new TwoB((int) input[0]);
        Queue<TwoB> queue = new LinkedList<>();
        queue.offer(twoB);

        for (int i = 1; i < input.length; i += 2) {
            TwoB curr = queue.poll();
            if (input[i] != null) {
                curr.left = new TwoB((int) input[i]);
                queue.offer(curr.left);
            }
            if (i+1 < input.length && input[i+1] != null) {
                curr.right = new TwoB((int) input[i+1]);
                queue.offer(curr.right);
            }
        }

        return twoB;
    }


}



class ConstructionServiceCenter{
    int res = 0;
    public int minCameraCover(TwoB twoB) {

        return (dfs(twoB) < 1 ? 1 : 0) + res;
    }
//takes a TreeNode as input and returns an integer representing the state of the node
    public int dfs(TwoB twoB) {

        if (twoB == null) return 2;
        int left = dfs(twoB.left), right = dfs(twoB.right);
        if (left == 0 || right == 0) {
            res++;
            return 1;
        }
        return left == 1 || right == 1 ? 2 : 0;
    }
//calls the minCameraCover method to find the minimum number of service centers required to cover all cities in the binary tree
    public static void main(String[] args) {
        Object[] tree= {0,0, null, 0, null, 0, null, null, 0 , 0 ,null,0};
        TwoB twoB = new TwoB().addToTree(tree);
        int ans = new ConstructionServiceCenter().minCameraCover(twoB);
        System.out.println(ans);


    }

}

