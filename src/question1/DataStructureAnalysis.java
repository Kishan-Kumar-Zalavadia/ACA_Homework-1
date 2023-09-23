package question1;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class DataStructureAnalysis {

    public static void main(String[] args) {
        int dataSize = 50000;
        int numLowestNumbers = 50;
        int numRandomInsertions = 5000;


        // Generate a random permutation of 1 to 50000
        int[] elements = generateRandomPermutation(dataSize);


        // Initialize data structures
        BinaryMinHeap minHeap = new BinaryMinHeap();
        AVLTree avlTree = new AVLTree();
        SplayTree splayTree = new SplayTree();

        // Measure Build Times
        measureBuildTime(minHeap, avlTree, splayTree, elements);

        // Measure Search Time
        measureSearchTime(minHeap, avlTree, splayTree, elements, numLowestNumbers);

        // Measure Insertion Time
        measureInsertionTime(minHeap, avlTree, splayTree, elements, numRandomInsertions);

        

    }


    public static void measureBuildTime(BinaryMinHeap minHeap, AVLTree avlTree, SplayTree splayTree, int[] elements){

        //Measure building time
        long[] heapBuildTimes = new long[5];
        long[] avlBuildTimes = new long[5];
        long[] splayBuildTimes = new long[5];

        // Count swaps/rotations for building
        int[] heapSwaps = new int[5];
        int[] avlRotations = new int[5];
        int[] splayRotations = new int[5];


        for (int i = 0; i < 5; i++) {
            //Build Binary Min Heap
            long startTime = System.nanoTime();
            for (int element : elements) {
                minHeap.insert(element);
                heapSwaps[i] = minHeap.getSwapsCount();
            }
            long endTime = System.nanoTime();
            heapBuildTimes[i] = endTime - startTime;


            //Build AVL Tree
            startTime = System.nanoTime();
            for (int element : elements) {
                avlTree.insert(element);
                avlRotations[i] = avlTree.getRotationCount();
            }
            endTime = System.nanoTime();
            avlBuildTimes[i] = endTime - startTime;


            // Build Splay Tree
            startTime = System.nanoTime();
            for (int element : elements) {
                splayTree.insert(element);
                splayRotations[i] = splayTree.getRotationCount();
            }
            endTime = System.nanoTime();
            splayBuildTimes[i] = endTime - startTime;
        }

        // Report build times and swaps/rotations
        //System.out.println("Building Times (nanoseconds): " + Arrays.toString(buildTimes));
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("BUILD TIME:");
        System.out.println("___________");
        print(Arrays.toString(heapBuildTimes),Arrays.toString(avlBuildTimes),Arrays.toString(splayBuildTimes));
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("SWAPS / ROTATIONS:");
        System.out.println("__________________");
        print(Arrays.toString(heapSwaps),Arrays.toString(avlRotations),Arrays.toString(splayRotations));
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("MEANS FOR BUILDING TIME:");
        System.out.println("_________________________");
        printMean(heapBuildTimes, avlBuildTimes, splayBuildTimes);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("STANDARD DEVIATION FOR BUILDING TIME:");
        System.out.println("_______________________________________");
        printStandardDeviation(heapBuildTimes, avlBuildTimes, splayBuildTimes);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();
    }

    // Measure Search Time
    public static void measureSearchTime(BinaryMinHeap minHeap, AVLTree avlTree, SplayTree splayTree, int[] elements ,int numLowestNumbers){
        // Create arrays to store search times for Min Heap, AVL Tree, and Splay Tree
        long[] searchTimeLowestBinaryMinHeap = new long[5];
        long[] searchTimeLowestAVLTree = new long[5];
        long[] searchTimeLowestSplayTree = new long[5];

        long[] searchTimeRandomBinaryMinHeap = new long[5];
        long[] searchTimeRandomAVLTree = new long[5];
        long[] searchTimeRandomSplayTree = new long[5];


        int[] lowestNumbers = new int[50];
        for (int i = 0; i < 50; i++) {
            lowestNumbers[i] = i + 1;
        }


        for (int i = 0; i < 5; i++) {
            // Generate 50 random numbers
            int[] randomNumbers = generateRandomNumbersNotInArray(elements, numLowestNumbers);

            // Measure search times for lowest numbers
            long startTime = System.nanoTime();
            for (int number : lowestNumbers) {
                boolean min = minHeap.search(number);
            }
            long endTime = System.nanoTime();
            searchTimeLowestBinaryMinHeap[i] = endTime - startTime;

            startTime = System.nanoTime();
            for (int number : lowestNumbers) {
                avlTree.search(number);
            }
            endTime = System.nanoTime();
            searchTimeLowestAVLTree[i] = endTime - startTime;

            startTime = System.nanoTime();
            for (int number : lowestNumbers) {
                splayTree.search(number);
            }
            endTime = System.nanoTime();
            searchTimeLowestSplayTree[i] = endTime - startTime;

            // Measure search times for random numbers
            startTime = System.nanoTime();
            for (int number : randomNumbers) {
                minHeap.search(number);
            }
            endTime = System.nanoTime();
            searchTimeRandomBinaryMinHeap[i] = endTime - startTime;

            startTime = System.nanoTime();
            for (int number : randomNumbers) {
                avlTree.search(number);
            }
            endTime = System.nanoTime();
            searchTimeRandomAVLTree[i] = endTime - startTime;

            startTime = System.nanoTime();
            for (int number : randomNumbers) {
                splayTree.search(number);
            }
            endTime = System.nanoTime();
            searchTimeRandomSplayTree[i] = endTime - startTime;
        }
        // Print the search times for Min Heap, AVL Tree, and Splay Tree for lowest and random numbers
        System.out.println("SEARCH TIME FOR LOWEST NUMBERS:");
        System.out.println("_______________________________");
        print(Arrays.toString(searchTimeLowestBinaryMinHeap),Arrays.toString(searchTimeLowestAVLTree),Arrays.toString(searchTimeLowestSplayTree));
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("MEAN OF SEARCH TIME FOR LOWEST NUMBERS:");
        System.out.println("_______________________________________");
        printMean(searchTimeLowestBinaryMinHeap, searchTimeLowestAVLTree, searchTimeLowestSplayTree);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("STANDARD DEVIATION OF SEARCH TIME FOR LOWEST NUMBERS: ");
        System.out.println("______________________________________________________");
        printStandardDeviation(searchTimeLowestBinaryMinHeap, searchTimeLowestAVLTree, searchTimeLowestSplayTree);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("SEARCH TIME FOR RANDOM NUMBERS:");
        System.out.println("_______________________________");
        print(Arrays.toString(searchTimeRandomBinaryMinHeap),Arrays.toString(searchTimeRandomAVLTree), Arrays.toString(searchTimeRandomSplayTree));
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("MEAN OF SEARCH TIME FOR RANDOM NUMBERS:");
        System.out.println("_______________________________________");
        printMean(searchTimeRandomBinaryMinHeap, searchTimeRandomAVLTree, searchTimeRandomSplayTree);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("STANDARD DEVIATION OF SEARCH TIME FOR RANDOM NUMBERS:");
        System.out.println("_____________________________________________________");
        printStandardDeviation(searchTimeRandomBinaryMinHeap, searchTimeRandomAVLTree, searchTimeRandomSplayTree);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();
    }

    // Measure Insertion Time
    public static void measureInsertionTime(BinaryMinHeap minHeap, AVLTree avlTree, SplayTree splayTree, int[] elements, int numRandomInsertions){
        // Perform insertion of 5000 random numbers not in the original data structures
        int[] randomInsertionNumbers = generateRandomNumbersNotInArray(elements, numRandomInsertions);

        // Arrays to store insertion times for each data structure
        long[] insertionTimesMinHeap = new long[5];
        long[] insertionTimesAVLTree = new long[5];
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
                avlTree.insert(number);
            }
            long endTimeAVL = System.nanoTime();
            insertionTimesAVLTree[i] = endTimeAVL - startTimeAVL;

            // Splay Tree insertion
            long startTimeSplayTree = System.nanoTime();
            for (int number : randomInsertionNumbers) {
                splayTree.insert(number);
            }
            long endTimeSplayTree = System.nanoTime();
            insertionTimesSplayTree[i] = endTimeSplayTree - startTimeSplayTree;
        }

        // Report insertion times for each data structure
        System.out.println("INSERTION TIME:");
        System.out.println("_______________");
        print(Arrays.toString(insertionTimesMinHeap),Arrays.toString(insertionTimesAVLTree),Arrays.toString(insertionTimesSplayTree));
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("MEAN FOR INSERTION:");
        System.out.println("___________________");
        printMean(insertionTimesMinHeap, insertionTimesAVLTree, insertionTimesSplayTree);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("STANDARD DEVIATION FOR INSERTION:");
        System.out.println("__________________________________");
        printStandardDeviation(insertionTimesMinHeap, insertionTimesAVLTree, insertionTimesSplayTree);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();
    }

    // Print Means Times
    public  static void printMean(long[] minHeap, long[] avlTree, long[] splayTree){
        System.out.println("Min Heap: "+calculateMean(minHeap));
        System.out.println("AVL Tree: "+calculateMean(avlTree));
        System.out.println("Splay Tree: "+calculateMean(splayTree));

    }

    public static void printStandardDeviation(long[] minHeap, long[] avlTree, long[] splayTree){
        System.out.println("Binary Min Heap: "+calculateStandardDeviation(minHeap));
        System.out.println("AVL Tree: "+calculateStandardDeviation(avlTree));
        System.out.println("Splay Tree: "+calculateStandardDeviation(splayTree));
    }

    public static void print(String minHeap, String avlTree, String splayTree){
        System.out.println("Binary Min Heap: "+minHeap);
        System.out.println("AVL Tree: "+avlTree);
        System.out.println("splayTree: "+splayTree);
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
