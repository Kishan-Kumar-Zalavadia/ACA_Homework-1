package question2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MedianFinder {

    public double findMedian(List<List<Integer>> sortedLists) {
        int totalSize = getTotalSize(sortedLists);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // Max heap for the left half
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // Min heap for the right half

        long startTime = System.nanoTime();
        for (List<Integer> sortedList : sortedLists) {
            for (int value : sortedList) {
                if (maxHeap.isEmpty() || value <= maxHeap.peek()) {
                    maxHeap.offer(value);
                } else {
                    minHeap.offer(value);
                }

                // Balance the heaps
                if (maxHeap.size() > minHeap.size() + 1) {
                    minHeap.offer(maxHeap.poll());
                } else if (minHeap.size() > maxHeap.size()) {
                    maxHeap.offer(minHeap.poll());
                }
            }
        }
        long endTime = System.nanoTime();
        long runTime = endTime - startTime;
        System.out.println("_____________________________________________________________");
        System.out.println("_____________________________________________________________");
        System.out.println("Run Time: " + runTime + " nanoseconds");


        if (totalSize % 2 == 0) {
            // If even number of elements, return the average of the two middle elements
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            // If odd number of elements, return the middle element from the maxHeap
            return maxHeap.peek();
        }
    }

    private int getTotalSize(List<List<Integer>> sortedLists) {
        int totalSize = 0;
        for (List<Integer> list : sortedLists) {
            totalSize += list.size();
        }
        return totalSize;
//        return sortedLists.size()*sortedLists.get(0).size();
    }


    private static class Element {
        int value;
        int listIndex;

        public Element(int value, int listIndex) {
            this.value = value;
            this.listIndex = listIndex;
        }
    }

    private static List<List<Integer>> generateRandomData(int N, int M) {
        List<List<Integer>> sortedLists = new ArrayList<>();

        Random random = new Random();

        for (int school = 0; school < N; school++) {
            List<Integer> sortedList = new ArrayList<>();
            for (int student = 0; student < M; student++) {
                // Generate random height values (e.g., between 100 and 200 cm)
                int height = random.nextInt(101) + 100;
                sortedList.add(height);
            }
            sortedList.sort(Integer::compareTo); // Sort the heights in ascending order
            sortedLists.add(sortedList);
        }

        return sortedLists;
    }

    public static void main(String[] args) throws FileNotFoundException {

        Scanner fileScanner = new Scanner(new File("/Users/kishankumarz/Library/CloudStorage/GoogleDrive-kishankumarz3131@gmail.com/My Drive/Important/[02] USA/[02] UNT/[02] Classes/[01] Analysis_of Computer_Algoritms/[02] ACA_Assisgnment/[01] HomeWork/ACA_HomeWork-1/src/question1/input")); // Open the file for reading

        List<List<Integer>> inputSortedList = new ArrayList<>();

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            if (line.isEmpty()) {
                continue; // Skip empty lines
            }

            String[] values = line.split(" ");
            List<Integer> innerList = new ArrayList<>();

            for (String value : values) {
                innerList.add(Integer.parseInt(value));
            }

            inputSortedList.add(innerList);
        }
        System.out.println(inputSortedList);
        MedianFinder finder = new MedianFinder();
        double median = finder.findMedian(inputSortedList);
        System.out.println("Median: " + median);

        fileScanner.close();

    }
}
