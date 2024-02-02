package assign05;


import java.util.ArrayList;
import java.util.Random;

public class TimingTests {
    private static Random rand;

    public static void main(String[] args) {
        rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        int timesToLoop = 500;

        ArrayList<ArrayList<Integer>> constantPermutedList = new ArrayList<>();
        for (int n = 1000; n <= 20000; n += 1000){
            constantPermutedList.add(ArrayListSorter.generatePermuted(n));
//            constantPermutedList.add(ArrayListSorter.generateAscending(n));
//            constantPermutedList.add(ArrayListSorter.generateDescending(n));
        }

        // For each threshold
        for (int j = 2; j < 3; j++) {
            if (j == 0)
                ArrayListSorter.insertionThreshold = 1;
            if (j == 1)
                ArrayListSorter.insertionThreshold = 10;
            if (j == 2)
                ArrayListSorter.insertionThreshold = 25;
            if (j == 3)
                ArrayListSorter.insertionThreshold = 50;
            if (j == 4)
                ArrayListSorter.insertionThreshold = 100;
            System.out.println("Threshold: # " + j);
            
        
            
        //For each pivot
//        for (int k = 2; k < 3; k++) {
//        	if (k==0)
//        		ArrayListSorter.pivotSelection = 0;
//        	if (k==1)
//        		ArrayListSorter.pivotSelection = 1;
//        	if (k==2)
//        		ArrayListSorter.pivotSelection = 2;
//        		
//        	System.out.println("Pivot Selection # " + k);
//        

        int l = 0;

            // For each problem size n . . .
            for (int n = 1000; n <= 20000; n += 1000) {
//                ArrayList<Integer> intArray = ArrayListSorter.generatePermuted(n);
                ArrayList<Integer> intArray = constantPermutedList.get(l);
//			ArrayList<Integer> intArrayAscending = ArrayListSorter.generateAscending(n)
//           ArrayList<Integer> intArrayDescending = ArrayListSorter.generateDescending(n);

                long startTime, midpointTime, stopTime;

                // First, spin computing stuff until one second has gone by.
                // This allows this thread to stabilize.
                startTime = System.nanoTime();
                while (System.nanoTime() - startTime < 1000000000) { // empty block
                }

                // Now, run the test.
                startTime = System.nanoTime();

                for (int i = 0; i < timesToLoop; i++) {
                    ArrayList<Integer> intArrayCopy = new ArrayList<>(intArray);
                    ArrayListSorter.mergesort(intArrayCopy);
                    //ArrayListSorter.quicksort(intArrayCopy);
                }

                midpointTime = System.nanoTime();

                // Run a loop to capture the cost of running the "timesToLoop" loop
                for (int i = 0; i < timesToLoop; i++) {
                    ArrayList<Integer> intArrayCopy = new ArrayList<>(intArray);
                }

                stopTime = System.nanoTime();

                // Compute the time, subtract the cost of running the loop
                // from the cost of running the loop and doing the lookups.
                // Average it over the number of runs.
                double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
                        / (double) timesToLoop;

                System.out.println(averageTime);
//                n + "\t" +
                        l++;
            }
            System.out.println("");
        }
    }


}