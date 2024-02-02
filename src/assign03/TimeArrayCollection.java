package assign03;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class TimeArrayCollection {
    private static Random rand;

    public static void main(String[] args) {
        rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        // Do # of lookups and use the average running time.
        int timesToLoop = 10000;

        // For each problem size n . . .
        for (int n = 1000; n <= 20000; n += 1000) {

            // Create a new array containing random integers to feed into the contains and binarySearch tests.
            Integer[] randIntArray = new Integer[timesToLoop];
            for (int i = 0; i < timesToLoop; i++) {
                randIntArray[i] = randomInt();
            }

            // Generate a new "random" library of size n.
            ArrayCollection<Integer> randArrayCollection = new ArrayCollection<>();
            randArrayCollection.addAll(generateArrayCollection(n));
            ArrayList<Integer> randIntList = randArrayCollection.toSortedList(new IntegerComparator());

            long startTime, midpointTime, stopTime;

            // First, spin computing stuff until one second has gone by.
            // This allows this thread to stabilize.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }

            // Now, run the test.
            startTime = System.nanoTime();

            for (int i = 0; i < timesToLoop; i++) {
       //         randArrayCollection.toSortedList(new IntegerComparator());
		//		randArrayCollection.contains(randIntArray[i]);
				SearchUtil.binarySearch(randIntList, randIntArray[i], new IntegerComparator());
            }

            midpointTime = System.nanoTime();

            // Run a loop to capture the cost of running the "timesToLoop" loop
            for (int i = 0; i < timesToLoop; i++) {
                new IntegerComparator();
                new IntegerComparator();
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

    /**
     * Returns an ArrayCollection containing random integers.
     * <p>
     * Useful for collecting running times for operations on ArrayCollections of
     * varying size.
     *
     * @param size -- size of the ArrayCollection to be generated
     */
    private static ArrayCollection<Integer> generateArrayCollection(int size) {
        ArrayCollection<Integer> integerCollection = new ArrayCollection<>();

        while (integerCollection.size() <= size) {
            integerCollection.add(randomInt());
        }

        return integerCollection;
    }


    public static Integer randomInt() {
        return rand.nextInt();
    }

}