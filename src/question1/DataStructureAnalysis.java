package question1;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class DataStructureAnalysis {

    public static void main(String[] args) {
        int[] elements = generateRandomPermutation(50000); // Generate a random permutation of 1 to 50000
        // Initialize data structures
        BinaryMinHeap minHeap = new BinaryMinHeap();
        AVLTree avlTree = new AVLTree();
        SplayTree splayTree = new SplayTree();

        // Count swaps/rotations for building
        int[] heapSwaps = new int[5];
        int[] avlRotations = new int[5];
        int[] splayRotations = new int[5];

        //Measure building time
        //Build Binary Min Heap
        long[] heapBuildTimes = new long[5];
        for (int i = 0; i < 5; i++) {
            long startTime = System.nanoTime();
            for (int element : elements) {
                minHeap.insert(element);
                heapSwaps[i] = minHeap.getSwapsCount();
            }
            long endTime = System.nanoTime();
            heapBuildTimes[i] = endTime - startTime;
        }

        //Build AVL Tree
        long[] avlBuildTimes = new long[5];
        for (int i = 0; i < 5; i++) {
            long startTime = System.nanoTime();
            for (int element : elements) {
                avlTree.insertElement(element);
                avlRotations[i] = avlTree.getRotationCount();
            }
            long endTime = System.nanoTime();
            avlBuildTimes[i] = endTime - startTime;
        }

        // Build Splay Tree
        long[] splayBuildTimes = new long[5];
        for (int i = 0; i < 5; i++) {
            long startTime = System.nanoTime();
            for (int element : elements) {
                splayTree.insert(element);
                splayRotations[i] = splayTree.getRotationCount();
            }
            long endTime = System.nanoTime();
            splayBuildTimes[i] = endTime - startTime;
        }

        // Report build times and swaps/rotations
        //System.out.println("Building Times (nanoseconds): " + Arrays.toString(buildTimes));
        System.out.println("----------------------------------");
        System.out.println();
        System.out.println("Building Time for Binary Min Heap (nanoseconds): "+Arrays.toString(heapBuildTimes));
        System.out.println("Building Time for AVL Tree (nanoseconds): "+Arrays.toString(avlBuildTimes));
        System.out.println("Building Time for Splay Tree (nanoseconds): "+Arrays.toString(splayBuildTimes));
        System.out.println();
        System.out.println("Heap Swaps: " + Arrays.toString(heapSwaps));
        System.out.println("AVL Rotations: " + Arrays.toString(avlRotations));
        System.out.println("Splay Rotations: " + Arrays.toString(splayRotations));
        System.out.println("-----------------");
        System.out.println();

        System.out.println("MEANS FOR BUILDING TIME:");
        System.out.println("Means for Binary Min Heap: "+calculateMean(heapBuildTimes));
        System.out.println("Means for AVL Tree: "+calculateMean(avlBuildTimes));
        System.out.println("Means for Splay Tree: "+calculateMean(splayBuildTimes));
        System.out.println("-----------------");
        System.out.println();

        System.out.println("STANDARD DEVIATION FOR BUILDING TIME");
        System.out.println("Standard Deviation for Binary Min Heap: "+calculateStandardDeviation(heapBuildTimes));
        System.out.println("Standard Deviation for AVL Tree: "+calculateStandardDeviation(avlBuildTimes));
        System.out.println("Standard Deviation for Splay Tree: "+calculateStandardDeviation(splayBuildTimes));
        System.out.println("----------------------------------");
        System.out.println();

        // Measure search times
        // Create arrays to store search times for Min Heap, AVL Tree, and Splay Tree
        long[] searchTimeLowestBinaryMinHeap = new long[5];
        long[] searchTimeLowestAVL = new long[5];
        long[] searchTimeLowestSplayTree = new long[5];

        long[] searchTimeRandomBinaryMinHeap = new long[5];
        long[] searchTimeRandomAVL = new long[5];
        long[] searchTimeRandomSplayTree = new long[5];


        int[] lowestNumbers = new int[50];
        for (int i = 0; i < 50; i++) {
            lowestNumbers[i] = i + 1;
        }


        for (int i = 0; i < 5; i++) {
            // Generate 50 random numbers

            int[] randomNumbers = generateRandomNumbersNotInArray(elements, 50);

            // Measure search times for lowest numbers
            long startTime = System.nanoTime();
            for (int number : lowestNumbers) {
                boolean min = minHeap.contains(number);
            }
            long endTime = System.nanoTime();
            searchTimeLowestBinaryMinHeap[i] = endTime - startTime;

            startTime = System.nanoTime();
            for (int number : lowestNumbers) {
                avlTree.search(number);
            }
            endTime = System.nanoTime();
            searchTimeLowestAVL[i] = endTime - startTime;

            startTime = System.nanoTime();
            for (int number : lowestNumbers) {
                splayTree.search(number);
            }
            endTime = System.nanoTime();
            searchTimeLowestSplayTree[i] = endTime - startTime;

            // Measure search times for random numbers
            startTime = System.nanoTime();
            for (int number : randomNumbers) {
                minHeap.contains(number);
            }
            endTime = System.nanoTime();
            searchTimeRandomBinaryMinHeap[i] = endTime - startTime;

            startTime = System.nanoTime();
            for (int number : randomNumbers) {
                avlTree.search(number);
            }
            endTime = System.nanoTime();
            searchTimeRandomAVL[i] = endTime - startTime;

            startTime = System.nanoTime();
            for (int number : randomNumbers) {
                splayTree.search(number);
            }
            endTime = System.nanoTime();
            searchTimeRandomSplayTree[i] = endTime - startTime;
        }
        // Print the search times for Min Heap, AVL Tree, and Splay Tree for lowest and random numbers
        System.out.println("Search Times for Lowest Numbers in Binary Min Heap: "+Arrays.toString(searchTimeLowestBinaryMinHeap));
        System.out.println("Search Times for Lowest Numbers in AVL Tree: "+Arrays.toString(searchTimeLowestAVL));
        System.out.println("Search Times for Lowest Numbers in Splay Tree: "+Arrays.toString(searchTimeLowestSplayTree));
        System.out.println("-----------------");
        System.out.println();

        System.out.println("MEAN OF SEARCH TIME FOR LOWEST NUMBERS:");
        System.out.println("Mean of Search Times for Lowest Numbers in Binary Min Heap: "+calculateMean(searchTimeLowestBinaryMinHeap));
        System.out.println("Mean of Search Times for Lowest Numbers in AVL Tree: "+calculateMean(searchTimeLowestAVL));
        System.out.println("Mean of Search Time for Search Times for Lowest Numbers in Splay Tree: "+calculateMean(searchTimeLowestSplayTree));
        System.out.println("-----------------");
        System.out.println();

        System.out.println("STANDARD DEVIATION OF SEARCH TIME FOR LOWEST NUMBERS: ");
        System.out.println("Standard Deviation of Search Times for Lowest Number in Binary Min Heap: "+calculateStandardDeviation(searchTimeLowestBinaryMinHeap));
        System.out.println("Standard Deviation of Search Times for Lowest Numbers in AVL Tree: "+calculateStandardDeviation(searchTimeLowestAVL));
        System.out.println("Standard Deviation of Search Times for Lowest Numbers in Splay Tree: "+calculateStandardDeviation(searchTimeLowestSplayTree));
        System.out.println("----------------------------------");
        System.out.println();

        System.out.println("Search Times for Random Numbers in Binary Min Heap: "+Arrays.toString(searchTimeRandomBinaryMinHeap));
        System.out.println("Search Times for Random Numbers in AVL Tree: "+Arrays.toString(searchTimeRandomAVL));
        System.out.println("Search Times for Random Numbers in Splay Tree: "+Arrays.toString(searchTimeRandomSplayTree));
        System.out.println("-----------------");
        System.out.println();

        System.out.println("MEAN OF SEARCH TIME FOR RANDOM NUMBERS:");
        System.out.println("Mean of Search Times for Random Numbers in Binary Min Heap: "+calculateMean(searchTimeRandomBinaryMinHeap));
        System.out.println("Mean of Search Times for Random Numbers in AVL Tree: "+calculateMean(searchTimeRandomAVL));
        System.out.println("Means of Search Times for Random Numbers in Splay Tree: "+calculateMean(searchTimeRandomSplayTree));
        System.out.println("-----------------");
        System.out.println();

        System.out.println("STANDARD DEVIATION OF SEARCH TIME FOR RANDOM NUMBERS:");
        System.out.println("Standard Deviation of Search Times for Random Numbers in Binary Min Heap: "+calculateStandardDeviation(searchTimeRandomBinaryMinHeap));
        System.out.println("Standard Deviation of Search Times for Random Numbers in AVL Tree: "+calculateStandardDeviation(searchTimeRandomAVL));
        System.out.println("Standard Deviation of Search Times for Random Numbers in Splay Tree: "+calculateStandardDeviation(searchTimeRandomSplayTree));
        System.out.println("----------------------------------");
        System.out.println();


        // Perform insertion of 5000 random numbers not in the original data structures
        int[] randomInsertionNumbers = generateRandomNumbersNotInArray(elements, 5000);

        // Arrays to store insertion times for each data structure
        long[] insertionTimesMinHeap = new long[5];
        long[] insertionTimesAVL = new long[5];
        long[] insertionTimesSplayTree = new long[5];

        for (int i = 0; i < 5; i++) {
            // Binary Min Heap insertion
            long startTimeMinHeap = System.nanoTime();
            for (int number : randomInsertionNumbers) {
                minHeap.insert(number);
            }
            long endTimeMinHeap = System.nanoTime();
            insertionTimesMinHeap[i] = endTimeMinHeap - startTimeMinHeap;

            // AVL Tree insertion
            long startTimeAVL = System.nanoTime();
            for (int number : randomInsertionNumbers) {
                avlTree.insertElement(number);
            }
            long endTimeAVL = System.nanoTime();
            insertionTimesAVL[i] = endTimeAVL - startTimeAVL;

            // Splay Tree insertion
            long startTimeSplayTree = System.nanoTime();
            for (int number : randomInsertionNumbers) {
                splayTree.insert(number);
            }
            long endTimeSplayTree = System.nanoTime();
            insertionTimesSplayTree[i] = endTimeSplayTree - startTimeSplayTree;
        }

        // Report insertion times for each data structure
        System.out.println("Insertion Times for Binary Min Heap (nanoseconds): " + Arrays.toString(insertionTimesMinHeap));
        System.out.println("Insertion Times for AVL Tree (nanoseconds): " + Arrays.toString(insertionTimesAVL));
        System.out.println("Insertion Times for Splay Tree (nanoseconds): " + Arrays.toString(insertionTimesSplayTree));
        System.out.println("-----------------");
        System.out.println();

        System.out.println("MEAN FOR INSERTION:");
        System.out.println("Mean of Insertion Times for Binary Min Heap: "+calculateMean(insertionTimesMinHeap));
        System.out.println("Mean of Insertion Times for AVL Tree: "+calculateMean(insertionTimesAVL));
        System.out.println("Mean of Insertion Times for Splay Tree: "+calculateMean(insertionTimesSplayTree));
        System.out.println("-----------------");
        System.out.println();

        System.out.println("STANDARD DEVIATION FOR INSERTION:");
        System.out.println("Standard Deviation of Insertion Times for Binary Min Heap: "+calculateStandardDeviation(insertionTimesMinHeap));
        System.out.println("Standard Deviation of Insertion Times for AVL Tree: "+calculateStandardDeviation(insertionTimesAVL));
        System.out.println("Standard Deviation of Insertion Times for Splay Tree: "+calculateStandardDeviation(insertionTimesSplayTree));
        System.out.println("----------------------------------");
        System.out.println();
    }

    // Helper method to generate a random permutation of numbers from 1 to n
    private static int[] generateRandomPermutation(int n) {
        int[] permutation = new int[n];
        for (int i = 0; i < n; i++) {
            permutation[i] = i + 1;
        }
        shuffleArray(permutation);
        return permutation;
    }

    // Helper method to shuffle an array randomly
    private static void shuffleArray(int[] array) {
        Random random = ThreadLocalRandom.current();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    // Helper method to generate an array of random numbers not in the original array
//    private static int[] generateRandomNumbersNotInArray(int[] originalArray, int count) {
//        Set<Integer> originalSet = new HashSet<>();
//        for (int num : originalArray) {
//            originalSet.add(num);
//        }
//
//        int[] randomNumbers = new int[count];
//        int generatedCount = 0;
//
//        Random random = ThreadLocalRandom.current();
//        while (generatedCount < count) {
//            int randomNumber = random.nextInt(50000) + 1;
//            if (!originalSet.contains(randomNumber)) {
//                randomNumbers[generatedCount] = randomNumber;
//                generatedCount++;
//            }
//        }
//
//        return randomNumbers;
//    }
    private static int[] generateRandomNumbersNotInArray(int[] originalArray, int count) {
        Set<Integer> originalSet = new HashSet<>();
        for (int num : originalArray) {
            originalSet.add(num);
        }

        int[] randomNumbers = new int[count];
        int generatedCount = 0;

        Random random = ThreadLocalRandom.current();
        while (generatedCount < count) {
            int randomNumber = random.nextInt(50000,60000);
//            System.out.println("Rand:"+randomNumber);
            if (!originalSet.contains(randomNumber)) {
                randomNumbers[generatedCount] = randomNumber;
                generatedCount++;
            }
        }

        return randomNumbers;
    }

    public static double calculateMean(long[] data) {
        double sum = 0;
        for (long value : data) {
            sum += value;
        }
        return sum / data.length;
    }

    // Calculate the standard deviation of an array of data
    public static double calculateStandardDeviation(long[] data) {
        double mean = calculateMean(data);
        double sumOfSquaredDifferences = 0;

        for (long value : data) {
            double difference = value - mean;
            sumOfSquaredDifferences += difference * difference;
        }

        double variance = sumOfSquaredDifferences / data.length;
        return Math.sqrt(variance);
    }
}
