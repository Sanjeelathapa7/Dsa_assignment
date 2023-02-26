package Questions;
/* ques 6
b)You are given an array of different words and target words. Each character of a word represents a
different digit ranging from 0 to 9, and no two character are linked to same digit.
If the sum of the numbers represented by each word on the array equals the sum the number represented
by the targeted word, return true; otherwise, return false. Note: Provided list of words and targeted
 word is in the form of equation
Input: words = ["SIX","SEVEN","SEVEN"], result = "TWENTY"
Output: true
Explanation:
 s=6
 I=5
X=0,
E=8,
V=7,
N=2,
T=1,
W=3,
Y=4
SIX +SEVEN + SEVEN = TWENTY
650 + 68782 + 68782 = 138214
 */

import java.util.HashSet;
import java.util.Set;

class SixB {
    // POW_10 is used to calculate charCount by units
    private static final int[] POW_10 = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000};

    // isSolvable method takes in an array of words and a result string and returns a boolean indicating if a valid mapping of digits to characters exists
    public boolean isSolvable(String[] words, String result) {
        // charSet stores all unique characters from words and result
        Set<Character> charSet = new HashSet<>();
        // charCount stores the sum of the decimal values of each character in each position of the words and result
        int[] charCount = new int[91]; // ASCII of A..Z chars are in range 65..90
        // nonLeadingZero is used to prevent leading zeros in the mapping of digits to characters
        boolean[] nonLeadingZero = new boolean[91];
        for (String word : words) {
            // Convert each word to a char array to iterate through each character
            char[] cs = word.toCharArray();
            for (int i = 0; i < cs.length; i++) {
                // If the character is the first in the word and the word has more than one character, mark it as not being allowed to be decoded as 0
                if (i == 0 && cs.length > 1) nonLeadingZero[cs[i]] = true;
                // Add the character to the set of unique characters
                charSet.add(cs[i]);
                // Increment the charCount of the character by the decimal value of its position in the word
                charCount[cs[i]] += POW_10[cs.length - i - 1]; // charCount is calculated by units
            }
        }
        // Repeat the process for the result string
        char[] cs = result.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (i == 0 && cs.length > 1) nonLeadingZero[cs[i]] = true;
            charSet.add(cs[i]);
            charCount[cs[i]] -= POW_10[cs.length - i - 1]; // charCount is calculated by units
        }
        // used is used to track which digits have already been used in the mapping of digits to characters
        boolean[] used = new boolean[10];
        // Convert the set of unique characters to a char array to iterate through each character
        char[] charList = new char[charSet.size()];
        int i = 0;
        for (char c : charSet) charList[i++] = c;
        // Call the backtracking helper method to attempt to find a valid mapping of digits to characters
        return backtracking(used, charList, nonLeadingZero, 0, 0, charCount);
    }

    // backtracking method is used to attempt to find a valid mapping of digits to characters
    private boolean backtracking(boolean[] used, char[] charList, boolean[] nonLeadingZero, int step, int diff, int[] charCount) {
        // If all characters have been mapped to digits, check if the difference between the sum of the words and the result is 0
        if (step == charList.length) return diff == 0; // difference between sum of words and result equal to 0
        // Iterate through all possible digits (0-9) for the current character
        for (int d = 0; d <= 9; d++) {
            char c = charList[step];
            if (!used[d] // each different characters must map to different digits
                    && (d > 0 || !nonLeadingZero[c])) {  // decoded as one number without leading zeros.
                used[d] = true;
                if (backtracking(used, charList, nonLeadingZero, step + 1, diff + charCount[c] * d, charCount))
                    return true;
                used[d] = false;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        SixB sixB = new SixB();
        String[] words = {"SIX","SEVEN","SEVEN"};
        String result = "TWENTY";
        Boolean answer = sixB.isSolvable(words,result);
        System.out.println(answer);
    }
}
