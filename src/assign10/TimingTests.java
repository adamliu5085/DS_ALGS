package assign10;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TimingTests {
    private static Random rand;

    private static final int percentage = 2;

    public static void main(String[] args) {
        rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        int timesToLoop = 25;
        for (int n = 1000; n <= 20000; n += 1000) {

            // Start Setup

            int k = n / percentage;

            BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();

            ArrayList<Integer> findKLargestList = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                findKLargestList.add(i);
            }

            Collections.shuffle(findKLargestList);

            // End Setup

            long startTime, midpointTime, stopTime;

            // First, spin computing stuff until one second has gone by.
            // This allows this thread to stabilize.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) {
            }

            // Now, run the test.
            startTime = System.nanoTime();

            for (int i = 0; i < timesToLoop; i++) {

                // Analysis Doc #1 ADD METHOD
                for (int j = 0; j < n; j++)
                	heap.add(findKLargestList.get(i));

                // Analysis Doc #1 EXTRACT MAX METHOD
//                while (heap.size() > 0)
//                    heap.extractMax();

                // Analysis Doc #1 PEEK METHOD
//                for (int j = 0; j < heap.size(); j++)
//                	heap.peek();
                // End Analysis Doc #1

                // Start Analysis Doc #4
                //FindKLargest.findKLargestHeap(findKLargestList, k);
				//FindKLargest.findKLargestSort(findKLargestList, k);

            }

            midpointTime = System.nanoTime();

            // Run a loop to capture the cost of running the "timesToLoop" loop
            for (int i = 0; i < timesToLoop; i++) {

                // Analysis Doc #1 ADD Cost Capture
//                while (heap.size() < n) {
//                    rand.nextInt(50000);
//                }
                // Analysis Doc #1 EXTRACT MAX Cost Capture
                while (heap.size() < n) {
                    heap.add(findKLargestList.get(i));
                }
                
                // Analysis Doc #1 PEEK MAX Cost Capture
//                while (heap.size() < n) {
//                    heap.add(rand.nextInt(50000));
//                }
//                for (int j = 0; j < heap.size(); j++) {
//
//                }
                
                // End Analysis Doc #1


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