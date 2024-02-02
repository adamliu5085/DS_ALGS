package assign06;


import java.util.Random;

public class TimingTests {
    private static Random rand;

    public static void main(String[] args) {
        rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        int timesToLoop = 999;

        // For each problem size n . . .
        for (int n = 1000; n <= 30000; n += 1000) {

  //          LinkedListStack<Integer> stack = new LinkedListStack<>();
                        ArrayStack<Integer> stack = new ArrayStack<>();

            for (int i = 0; i < n; i++) {
                stack.push(i);
            }

            long startTime, midpointTime, stopTime;

            // First, spin computing stuff until one second has gone by.
            // This allows this thread to stabilize.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }

            // Now, run the test.
            startTime = System.nanoTime();

            for (int i = 0; i < timesToLoop; i++) {
  //              stack.push(1);
     //               stack.peek();
                    stack.pop();

            }

            midpointTime = System.nanoTime();

            // Run a loop to capture the cost of running the "timesToLoop" loop
            for (int i = 0; i < timesToLoop; i++) {

            }

            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and doing the lookups.
            // Average it over the number of runs.
            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
                    / (double) timesToLoop;

            System.out.println(n + "\t" + averageTime);

        }
    }


}
