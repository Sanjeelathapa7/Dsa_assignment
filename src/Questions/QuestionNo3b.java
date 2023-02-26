package Questions;
/*
b)
you are provided certain string and pattern, return true if pattern entirely matches
the string otherwise return false.
Note: if pattern contains char @ it matches entire sequence of characters and # matches any single character within string.
Input: String a=“tt”, pattern =”@”
Output: true
Input: String a=“ta”, pattern =”t”
Output: false
Input: String a=“ta”, pattern =”t#”
Output: true
 */

public class QuestionNo3b {

    // This method checks if the input string matches the pattern string
    public static boolean matches(String input, String pattern) {

        // Initialize the input and pattern indices to zero
        int inputIndex = 0;
        int patternIndex = 0;

        // Get the length of the input and pattern strings
        int inputLength = input.length();
        int patternLength = pattern.length();

        // Loop through the input and pattern strings until one of them is fully processed
        while (inputIndex < inputLength && patternIndex < patternLength) {

            // Get the current character in the pattern
            char currentChar = pattern.charAt(patternIndex);

            // If the current character is an "@" symbol, search for the next character in the input
            if (currentChar == '@') {
                patternIndex++;
                if (patternIndex == patternLength) {
                    // If "@" is the last character in the pattern, return true
                    return true;
                }
                // Otherwise, iterate through the input string until the next character is found
                char nextChar = pattern.charAt(patternIndex);
                while (inputIndex < inputLength && input.charAt(inputIndex) != nextChar) {
                    inputIndex++;
                }
                // If the end of the input string is reached before the next character is found, return false
                if (inputIndex == inputLength) {
                    return false;
                }
            }

            // If the current character is a "#" symbol, advance to the next character in both input and pattern strings
            else if (currentChar == '#') {
                inputIndex++;
                patternIndex++;
            }

            // If the current character in the pattern matches the corresponding character in the input, advance to the next character in both input and pattern strings
            else if (input.charAt(inputIndex) == currentChar) {
                inputIndex++;
                patternIndex++;
            }

            // If the current character in the pattern does not match the corresponding character in the input, return false
            else {
                return false;
            }
        }

        // If both input and pattern strings are fully processed, return true
        return (inputIndex == inputLength && patternIndex == patternLength);
    }

    // This is the main method that tests the "matches" method with different input and pattern strings
    public static void main(String[] args) {

        String input = "tt";
        String pattern = "@";
        boolean isMatch = matches(input, pattern);
        System.out.println(isMatch);

        String input2 = "ta";
        String pattern2 = "t";
        boolean isMatch2 = matches(input2, pattern2);
        System.out.println(isMatch2);

        String input3 = "ta";
        String pattern3 = "t#";
        boolean isMatch3 = matches(input3, pattern3);
        System.out.println(isMatch3);
    }
}
