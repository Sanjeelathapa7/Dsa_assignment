package Questions;/*
Question 3
a)
You are given an even length array; divide it in half and return possible minimum product difference
of any two subarrays.
Note that the minimal product difference is the smallest absolute difference between any two arrays
a and b, which is computed by calculating the difference after multiplying each element of the arrays
a and b.
Input: {5,2,4,11}
Output: 2
{5,4} {2,11} result into minimum product difference.
 */

public class QuestionNo3a {
//    calculates the product of an integer array
    public static int product(int[] subaaray){
        if (subaaray.length == 0){
            return 0;
        }
        int res = 1;
        for (int i = 0;i<subaaray.length;i++){
            res = res*subaaray[i];
        }
        return res;
    }
//loops through all possible combinations of subarrays
    public static int findminimumdifference(int[] array){
        int mindiff = Integer.MAX_VALUE;
        int n = array.length;
        for (int i = 0; i < (1 << n); i++) {
            if (Integer.bitCount(i) == n / 2) {
                int[] subarray1 = new int[n / 2];
                int[] subarray2 = new int[n / 2];
                int index1 = 0;
                int index2 = 0;
                for (int j = 0; j < n; j++) {
                    if ((i & (1 << j)) > 0) {
                        subarray1[index1++] = array[j];
                    } else {
                        subarray2[index2++] = array[j];
                    }
                }
                int subarray1product = product(subarray1);
                int subarray2product = product(subarray2);
                int curr_min_diff = 0;
                if (subarray2product>subarray1product){
//                    calculates the product and the difference between the two subarrays
                    curr_min_diff = subarray2product-subarray1product;
                }else{
                    curr_min_diff = subarray1product-subarray2product;
                }
                if (curr_min_diff<mindiff){
                    mindiff = curr_min_diff;
                }

            }
        }
        return mindiff;


    }

//initializes an array of integers and calls the "findminimumdifference" method to obtain the minimum difference between the products of the subarrays
    public static void main(String[] args) {
        int[] array = {5, 2, 4, 11};
        int answer = findminimumdifference(array);
        System.out.println(answer);

    }
}
