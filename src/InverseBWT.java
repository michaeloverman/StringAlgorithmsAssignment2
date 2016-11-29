import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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

    private int aCounter;
    private int cCounter;
    private int gCounter;
    private int tCounter;
//    private int sCounter;

    private int[] countCharPositions(char[] arr) {
        int[] result = new int[arr.length];
        aCounter = 0;
        cCounter = 0;
        gCounter = 0;
        tCounter = 0;
//        sCounter = 0;

        for (int i = 0; i < arr.length; i++) {
            switch(arr[i]) {
                case 'A':
                    result[i] = ++aCounter;
                    break;
                case 'C':
                    result[i] = ++cCounter;
                    break;
                case 'G':
                    result[i] = ++gCounter;
                    break;
                case 'T':
                    result[i] = ++tCounter;
                    break;
//                case '$':
//                    sCounter++;
//                    break;
                default:
            }
        }
        cCounter += aCounter;
        gCounter += cCounter;
//

        return result;
    }
    String inverseBWT(String bwt) {
        StringBuilder result = new StringBuilder();
        result.append('$');
        // write your code here
        char[] lastColumn = bwt.toCharArray();

        int[] nextOccurence = countCharPositions(lastColumn);

        int pointer = 0;

        for(int i = 0; i < lastColumn.length - 1; i++) {
            char nextChar = lastColumn[pointer];
            result.insert(0, nextChar);
//            firstColumn[pointer] = '.';
            switch(nextChar) {
                case 'A':
                    pointer = nextOccurence[pointer];
                    break;
                case 'C':
                    pointer = nextOccurence[pointer] + aCounter;
                    break;
                case 'G':
                    pointer = nextOccurence[pointer] + cCounter;
                    break;
                case 'T':
                    pointer = nextOccurence[pointer] + gCounter;
                    break;
                default:
            }
//            pointer = nextOccurence[pointer];
        }


        return result.toString();
    }

    private int indexOf(char c, int position) {
        int p = 1;
        switch(c) {
            case 'T':
                p += gCounter;
            case 'G':
                p += cCounter;
            case 'C':
                p += aCounter;
            case 'A':
                p += position;
                return p;
            default:
        }
        return -1;
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("data/bwtinverse/testfile100K"));
        long start = System.nanoTime();
        new InverseBWT().run();
        double duration = (System.nanoTime() - start) / 1000000000.0;
        System.out.println("Time for completion: " + duration);
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        System.out.println(inverseBWT(bwt));
    }
}
