import java.io.BufferedReader;
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

    private void countCharPositions(String string) {
        lastColumn = new char[string.length()];
        lastToFirst = new int[string.length()];
        aCounter = 0;
        cCounter = 0;
        gCounter = 0;
        tCounter = 0;

        for (int i = 0; i < string.length(); i++) {
            switch(lastColumn[i] = string.charAt(i)) {
                case 'A':
                    lastToFirst[i] = ++aCounter;
                    break;
                case 'C':
                    lastToFirst[i] = ++cCounter;
                    break;
                case 'G':
                    lastToFirst[i] = ++gCounter;
                    break;
                case 'T':
                    lastToFirst[i] = ++tCounter;
                    break;
                default:
            }
        }
        cCounter += aCounter;
        gCounter += cCounter;
    }
    private char[] lastColumn;
    private int[] lastToFirst;
//    private void printArrays() {
//        for(int i = 0; i < lastColumn.length; i++) {
//            System.out.print(lastColumn[i] + " ");
//            System.out.println(lastToFirst[i] + " ");
//        }
////        System.out.println("");
////        for(int i = 0; i < lastColumn.length; i++) {
////        }
////        System.out.println("");
//        System.out.println("C offset: " + aCounter);
//        System.out.println("G offset: " + cCounter);
//        System.out.println("T offset: " + gCounter);
//    }
    String inverseBWT(String bwt) {
        StringBuilder result = new StringBuilder();
        result.append('$');
        // write your code here
        countCharPositions(bwt);

        int pointer = 0;

        for(int i = 0; i < lastColumn.length - 1; i++) {
            char nextChar = lastColumn[pointer];
            result.append(nextChar);
            pointer = lastToFirst[pointer];
            switch(nextChar) {
                case 'C':
                    pointer += aCounter;
                    break;
                case 'G':
                    pointer += cCounter;
                    break;
                case 'T':
                    pointer += gCounter;
                    break;
                default:
            }
        }

        return result.reverse().toString();
    }


    static public void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("data/bwtinverse/sample3"));
//        long start = System.nanoTime();
        new InverseBWT().run();
//        double duration = (System.nanoTime() - start) / 1000000000.0;
//        System.out.println("Time for completion: " + duration);
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        System.out.println(inverseBWT(bwt));
    }
}
