import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Created by overm on 11/23/2016.
 */
public class TestFileMaker {

    private static final int N = 5000;
    private static final int maxStringLength = 100;
    private static final int textLength = 10;
    private Random rand;

    public TestFileMaker() {
        rand = new Random();
        Path path = Paths.get("data/bwtinverse/testfile10");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writeFile(writer);
        } catch (IOException e) {
            System.out.println("error!!!");
            System.out.println(e.getStackTrace());
        }
    }

    private void writeFile(BufferedWriter writer) throws IOException {
        int randPos = rand.nextInt(textLength);
        writer.write(gimmeAString(randPos));
        writer.write("$");
        writer.write(gimmeAString(textLength-randPos));
        writer.write("\n");
//        writer.write(Integer.toString(N) + "\n");
//        for(int i = 0; i < N; i++) {
//            writer.write(gimmeAString(maxStringLength));
//            writer.write("\n");
//        }
    }

    private String gimmeAString(int length) {
//        int l = rand.nextInt(length - 1);
//        l++;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(gimmeAChar(rand.nextInt(4)));
        }
        return sb.toString();
    }
    private char gimmeAChar(int c) {
        switch(c) {
            case 0: return 'A';
            case 1: return 'T';
            case 2: return 'C';
            case 3: return 'G';
            default: return 'Z';
        }
    }
    public static void main(String[] args) {
        TestFileMaker test = new TestFileMaker();
    }
}
