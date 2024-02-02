package assign09;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TimingTests {
    private static Random rand;

    public static void main(String[] args) {
        rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        int timesToLoop = 25;
        for (int n = 1000; n <= 20000; n += 1000) {
        	
//             Setup
            ArrayList<StudentBadHash> badHashList = new ArrayList<>();
//            ArrayList<StudentMediumHash> mediumHashList = new ArrayList<>();
//            ArrayList<StudentGoodHash> goodHashList = new ArrayList<>();

            Random generator = new Random();

            for (int i = 0; i < n; i++) {
                badHashList.add(new StudentBadHash(1000000 + generator.nextInt(999999), "student" + i, "lastName" + i));
//                mediumHashList.add(new StudentMediumHash(1000000 + generator.nextInt(999999), "student" + i, "lastName" + i));
//                goodHashList.add(new StudentGoodHash(1000000 + generator.nextInt(999999), "student" + i, "lastName" + i));
            }

            HashTable<StudentBadHash, Integer> badHashTable = new HashTable<>();
//            HashTable<StudentMediumHash, Integer> mediumHashTable = new HashTable<>();
//            HashTable<StudentGoodHash, Integer> goodHashTable = new HashTable<>();

            for (int j = 0; j < n; j++) {
                    badHashTable.put(badHashList.get(j), j);
//                	mediumHashTable.put(mediumHashList.get(j), j);
//                goodHashTable.put(goodHashList.get(j), j);
            }

            // Analysis Doc #3
//            HashMap<StudentGoodHash, Integer> javaHashMap = new HashMap();

            // End Setup

            long startTime, midpointTime, stopTime;

            // First, spin computing stuff until one second has gone by.
            // This allows this thread to stabilize.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }

            // Now, run the test.
            startTime = System.nanoTime();

            for (int i = 0; i < timesToLoop; i++) {
                // Analysis Doc #2
               for (int j = 0; j < n; j++) {
                    badHashTable.containsKey(badHashList.get(j));
//                	mediumHashTable.containsKey(mediumHashList.get(j));
//                	goodHashTable.containsKey(goodHashList.get(j));
                }
                if (!(i == timesToLoop - 1)) {
                   badHashTable.collisionCount = 0;
//                    mediumHashTable.collisionCount = 0;
//                    goodHashTable.collisionCount = 0;
                }

                // Analysis Doc #3
    //            for (int j = 0; j < n; j++) {
  //                  javaHashMap.put(goodHashList.get(j), j);
//                }

            }

            midpointTime = System.nanoTime();

            // Run a loop to capture the cost of running the "timesToLoop" loop
            for (int i = 0; i < timesToLoop; i++) {
                // Analysis Doc #2
                for (int j = 0; j < n; j++) {
                    badHashList.get(j);
//                	mediumHashList.get(j);
//                	goodHashList.get(j);
                }

                // Analysis Doc #3
//                for (int j = 0; j < n; j++) {
//                    goodHashList.get(j);
//                }

            }

            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and doing the lookups.
            // Average it over the number of runs.
            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
                    / (double) timesToLoop;

            System.out.println(n + "\t" + averageTime + "\t" + badHashTable.collisionCount);
//            System.out.println(n + "\t" + averageTime + "\t" + mediumHashTable.collisionCount);
//            System.out.println(n + "\t" + averageTime );

            badHashTable.clear();
//            mediumHashTable.clear();
//            goodHashTable.clear();
//            javaHashMap.clear();
        }

    }
}