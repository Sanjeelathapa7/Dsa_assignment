package Questions;/*
b)
Assume an electric vehicle must go from source city s to destination city d.
You can locate many service centers along the journey that allow for the replacement of batteries;
however, each service center provides batteries with a specific capacity. You are given a 2D array in
which servicecenter[i]=[xi,yj] indicates that the ith service center is xi miles from the source city
and offers yj miles after the automobile can travel after replacing batteries at specific service
centers. Return the number of times the car's batteries need to be replaced before arriving at the
destination.
Input: serviceCenters = [{10,60},{20,30},{30,30},{60,40}], targetMiles= 100, startChargeCapacity = 10
Output: 2
Explanation: The car can go 10 miles on its initial capacity; after 10 miles, the car replaces batteries with a capacity of 60 miles; and after travelling 50 miles, at position 60 we change batteries with a capacity of 40 miles; and ultimately, we may arrive at our destination.
 */

import java.util.ArrayList;

public class FiveB {

    // method to calculate the number of battery replacements needed
    public int numBatteryReplacements(int[][] serviceCenters, int targetMiles, int startChargeCapacity) {

        // initialize count of battery replacements and current miles traveled
        int count = 0;
        int currentMiles = startChargeCapacity;

        // create arraylists to store distances and capacities of service centers
        ArrayList<Integer> distances = new ArrayList<>();
        ArrayList<Integer> capacities = new ArrayList<>();

        // iterate through serviceCenters array to add distances and capacities to arraylists
        for (int[] serviceCenter : serviceCenters) {
            distances.add(serviceCenter[0]);
            capacities.add(serviceCenter[1]);
        }

        // iterate through distances array to check if current miles traveled is less than distance to next service center
        for (int i = 0; i < distances.size(); i++) {
            if (distances.get(i) > currentMiles) { // if current miles is less than distance to next service center
                currentMiles = capacities.get(i - 1); // set current miles to the capacity of the previous service center
                count++; // increment the count of battery replacements needed
            }
        }

        // check if current miles traveled is less than target miles
        if (currentMiles < targetMiles) {
            count++; // if yes, increment the count of battery replacements needed
        }

        // return the count of battery replacements needed
        return count;
    }

    public static void main(String[] args) {
        // create service center list and initialize the Question5b class
        int [][] serviceCenterList={{10,60},{20,30},{30,30},{60,40}};
        FiveB fiveB=new FiveB();

        // call the numBatteryReplacements method to get the final answer
        int finalAnswer=fiveB.numBatteryReplacements(serviceCenterList,100,10);

        // print the final answer
        System.out.println("the car's batteries need to be replaced: "+finalAnswer +"times.");
    }
}
