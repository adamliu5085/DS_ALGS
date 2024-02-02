package comprehensive;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class TimingTests {

    private static Random rand;

    public static void main(String[] args) {

        rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        int timesToLoop = 10;

        ByteArrayOutputStream a = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(a);

        String[] test1args = {"C:/Users/adaml/eclipse-workspace/CS2420-Assignments/testGrammar.g", "1"};

        for (int n = 1000; n <= 20000; n += 1000) {
            String[] test2args = {"C:/Users/adaml/eclipse-workspace/CS2420-Assignments/src/comprehensive/assignment_extension_request.g", n + ""};

            SimpleGrammarGenerator.main(new String[]{n + ""});
            PrintStream old = System.out;
            System.setOut(ps);
            System.out.flush();

            long startTime, midpointTime, stopTime;

            // First, spin computing stuff until one second has gone by.
            // This allows this thread to stabilize.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) {
            }

            // Now, run the test.
            startTime = System.nanoTime();

            for (int i = 0; i < timesToLoop; i++) {
//                RandomPhraseGenerator.main(test1args);
                RandomPhraseGenerator.main(test2args);
            }

            midpointTime = System.nanoTime();

            // Run a loop to capture the cost of running the "timesToLoop" loop
            for (int i = 0; i < timesToLoop; i++) {

            }

            stopTime = System.nanoTime();

            System.setOut(old);

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and doing the lookups.
            // Average it over the number of runs.
            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
                    / (double) timesToLoop;

            System.out.println(n + "\t" + averageTime);
        }
    }
}