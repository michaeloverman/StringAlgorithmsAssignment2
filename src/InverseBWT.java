import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class InverseBWT {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    private int indexOf(char c, int position, char[] arr) {
        int count = 0;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == c && count++ == position) return i;
        }
        return -1;
    }

    private int[] countCharPositions(char[] arr) {
        int[] result = new int[arr.length];
        int aCounter = 0;
        int cCounter = 0;
        int gCounter = 0;
        int tCounter = 0;
        int sCounter = 0;

        for (int i = 0; i < arr.length; i++) {
            switch(arr[i]) {
                case 'A':
                    result[i] = aCounter++;
                    break;
                case 'C':
                    result[i] = cCounter++;
                    break;
                case 'G':
                    result[i] = gCounter++;
                    break;
                case 'T':
                    result[i] = tCounter++;
                    break;
                case '$':
                    result[i] = sCounter++;
                    break;
                default:
            }
        }
        return result;
    }
    String inverseBWT(String bwt) {
        StringBuilder result = new StringBuilder();

        // write your code here
        char[] lastColumn = bwt.toCharArray();
        char[] firstColumn = Arrays.copyOf(lastColumn, lastColumn.length);
        Arrays.sort(firstColumn);

        int[] lastPositionCount = countCharPositions(lastColumn);
//        int[] firstPositionCount = countCharPositions(firstColumn);

        int pointer = 0;
        for(int i = 0; i < lastColumn.length; i++) {
            if(pointer == -1) {
                System.out.println("Problem..." + (i-1));
                break;
            }
            result.insert(0, firstColumn[pointer]);
//            firstColumn[pointer] = '.';
            char toFind = lastColumn[pointer];
            pointer = indexOf(toFind, lastPositionCount[pointer], firstColumn);
        }


        return result.toString();
    }

    static public void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("data/bwtinverse/sample2"));
        new InverseBWT().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        System.out.println(inverseBWT(bwt));
    }
}
