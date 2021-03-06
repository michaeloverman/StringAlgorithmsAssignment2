import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BurrowsWheelerTransform {
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

    String BWT(String text) {
        StringBuilder result = new StringBuilder();

        // write your code here
        String[] matrix = new String[text.length()];
        matrix[0] = text;
        for(int i = 1; i < text.length(); i++) {
            matrix[i] = text.substring(i) + text.substring(0, i);
        }
        Arrays.sort(matrix);
        for(String s : matrix) {
            result.append(s.charAt(text.length() - 1));
        }

        return result.toString();
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("data/bwtinverse/testfile1K"));
        new BurrowsWheelerTransform().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        System.out.println(BWT(text));
    }
}
