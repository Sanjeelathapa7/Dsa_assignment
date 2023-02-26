package Questions;/*
Question 6
a)Implement Questions.Huffman encoding and decoding.
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SixA {
    class HuffmanNode {
        int data;
        char c;
        HuffmanNode left;
        HuffmanNode right;
    }

    // Prints the encoded huffman code
    public static void printCode(HuffmanNode root, String s) {
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {
            System.out.println(root.c + ":" + s);
            return;
        }
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

    // Encoding
    public HuffmanNode encode(char[] charArray, int[] charFreq) {
        int n = charArray.length;
        PriorityQueue<HuffmanNode> q = new PriorityQueue<>(n, new MyComparator());
        for (int i = 0; i < n; i++) {
            HuffmanNode hn = new HuffmanNode();
            hn.c = charArray[i];
            hn.data = charFreq[i];

            hn.left = null;
            hn.right = null;
            q.add(hn);
        }
        HuffmanNode root = null;
        while (q.size() > 1) {
            HuffmanNode x = q.peek();
            q.poll();
            HuffmanNode y = q.peek();
            q.poll();
            HuffmanNode f = new HuffmanNode();
            f.data = x.data + y.data;
            f.c = '-';
            f.left = x;
            f.right = y;
            root = f;
            q.add(f);
        }
        printCode(root, "");
        return root;
    }

    // Decoding the huffman tree
    public void decode(HuffmanNode root, String str) {
        ArrayList<Character> characters = new ArrayList<>();
        ArrayList<Integer> frequencies = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            HuffmanNode current = root;
            while (current.c == '-') {
                if (str.charAt(i) == '0') {
                    current = current.left;
                    i++;
                } else {
                    current = current.right;
                    i++;
                }
            }
            char c = current.c;
            int f = current.data;
            characters.add(current.c);
            frequencies.add(current.data);
        }
        printDecode(characters, frequencies);
    }

    public static void printDecode(ArrayList<Character> characters, ArrayList<Integer> frequencies) {
        for (int i = 0; i < characters.size(); i++) {
            System.out.println(characters.get(i) + ":" + frequencies.get(i));
        }
    }

    class MyComparator implements Comparator<HuffmanNode> {
        public int compare(SixA.HuffmanNode x, SixA.HuffmanNode y) {
            // Used to sort the character in the sequence of increasing frequency
            return x.data - y.data;
        }
    }

    // Driver method
    public static void main(String[] args) {
        char[] ch = { 'A', 'B', 'C', 'D', 'E' };
        int[] fre = { 4, 7, 3, 2, 7 };

        SixA sixA = new SixA();
        HuffmanNode hn = sixA.encode(ch, fre);
        System.out.println(hn.data);
        String cha = "000100111011";
        sixA.decode(hn, cha);

    }
}
