package assign04;

import assign03.IntegerComparator;

import java.util.Random;

public class TimingTests {
   private static Random rand;

   public static void main(String[] args) {
       rand = new Random();
       rand.setSeed(System.currentTimeMillis());
       // TimesToLoop oroo  fAreAnagrams
//        int timesToLoop = 75;
       // TimesToLoop for GetLargestAnagramGroup
       int timesToLoop = 5;
       // For each problem size n . . .
       for (int n = 1000; n <= 20000; n += 1000) {
           // Setup for areAnagrams
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
//             String string1 = "";
//             String string2 = "";
//             for (int i = 0; i < n; i++) {
//                 string1 += alphabet.charAt(randomAlphabetInt());
//                 string2 += alphabet.charAt(randomAlphabetInt());
//             }
           // Setup for getLargestAnagramGroup
           String[] arrayOfString = new String[n];
           for (int i = 0; i < n; i++) {
               for (int j = 0; j < 5; j++) {
                   arrayOfString[i] += alphabet.charAt(randomAlphabetInt());
               }
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
  //             AnagramChecker.areAnagrams(string1, string2);
               AnagramChecker.getLargestAnagramGroup(arrayOfString);
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

   public static Integer randomAlphabetInt() {
       return rand.nextInt(26);
   }
}
