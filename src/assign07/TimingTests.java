package assign07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeSet;

public class TimingTests {
    private static Random rand;

    public static void main(String[] args) {
        rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        int timesToLoop = 50;

        ArrayList<ArrayList<Integer>> listOfLists = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int n = 1000; n <= 20000; n += 1000) {
            list = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                list.add(i);
            }
            listOfLists.add(list);

            for (int i = 0; i < listOfLists.size(); i++) {
            	Collections.shuffle(listOfLists.get(i));
            }
        }

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        TreeSet<Integer> treeSet = new TreeSet<>();

        // For each problem size n . . .

        int problemSizeIndex = 0;

        // BST / TreeSet Loop
        for (int j = 0; j <= 1; j++) {

            for (int n = 1000; n <= 20000; n += 1000) {
            	
                bst.addAll(listOfLists.get(problemSizeIndex));
                treeSet.addAll(listOfLists.get(problemSizeIndex));

                long startTime, midpointTime, stopTime;

                // First, spin computing stuff until one second has gone by.
                // This allows this thread to stabilize.
                startTime = System.nanoTime();
                while (System.nanoTime() - startTime < 1000000000) { // empty block
            }

                // Now, run the test.
                startTime = System.nanoTime();

                for (int i = 0; i < timesToLoop; i++) {
                    // Analysis doc #3
//                    for (int k = 0; k < listOfLists.get(problemSizeIndex).size(); k++) {
//                        bst.contains(listOfLists.get(problemSizeIndex).get(k));
//                    }

                    // Analysis doc #4
                    if (j == 0) {
                        bst.addAll(listOfLists.get(problemSizeIndex));
//                        for (int k = 0; k < listOfLists.get(problemSizeIndex).size(); k++) {
//                            bst.contains(listOfLists.get(problemSizeIndex).get(k));
//                        }
                    }

                    if (j == 1) {
                        treeSet.addAll(listOfLists.get(problemSizeIndex));
//                        for (int k = 0; k < listOfLists.get(problemSizeIndex).size(); k++) {
//                            treeSet.contains(listOfLists.get(problemSizeIndex).get(k));
//                        }
                    }
                }

                midpointTime = System.nanoTime();

                // Run a loop to capture the cost of running the "timesToLoop" loop
                for (int i = 0; i < timesToLoop; i++) {
                    // Analysis doc #3
//                    for (int k = 0; k < listOfLists.get(problemSizeIndex).size(); k++) {
//                        listOfLists.get(problemSizeIndex).get(k);
//                    }

                    // Analysis doc #4
//                    if (j == 0) {
//                       bst.addAll(listOfLists.get(problemSizeIndex));
//                        for (int k = 0; k < listOfLists.get(problemSizeIndex).size(); k++) {
//                            listOfLists.get(problemSizeIndex).get(k);
//                        }
//                    }

//                    if (j == 1) {
//                        treeSet.addAll(listOfLists.get(problemSizeIndex));
//                        for (int k = 0; k < listOfLists.get(problemSizeIndex).size(); k++) {
//                            listOfLists.get(problemSizeIndex).get(k);
//                        }
//                    }
               }

                stopTime = System.nanoTime();

                problemSizeIndex++;

                bst.clear();
                treeSet.clear();

                // Compute the time, subtract the cost of running the loop
                // from the cost of running the loop and doing the lookups.
                // Average it over the number of runs.
                double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
                        / (double) timesToLoop;

                System.out.println(n + "\t" + averageTime);

            }
            problemSizeIndex = 0;
        // j loop bracket    
        }
    }
}