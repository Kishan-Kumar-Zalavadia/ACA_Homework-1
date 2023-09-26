package question2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MedianFinder {

    // Function to merge N sorted ArrayLists.
    public static List<Integer> mergeNSortedArrayLists(List<List<Integer>> arrays) {
        List<Integer> result = new ArrayList<>();

        // Create an array of indices to keep track of the current position in each input list.
        int[] indices = new int[arrays.size()];

        boolean allListsEmpty = false;

        long startTime = System.nanoTime();
        while (!allListsEmpty) {
            allListsEmpty = true;
            int minValue = Integer.MAX_VALUE;
            int minIndex = -1;

            for (int i = 0; i < arrays.size(); i++) {
                if (indices[i] < arrays.get(i).size()) {
                    allListsEmpty = false;
                    int currentValue = arrays.get(i).get(indices[i]);
                    if (currentValue < minValue) {
                        minValue = currentValue;
                        minIndex = i;
                    }
                }
            }

            if (minIndex != -1) {
                result.add(minValue);
                indices[minIndex]++;
            }
        }
        long endTime = System.nanoTime();
        long runTime = endTime - startTime;
        System.out.println("_____________________________________________________________________________________");
        System.out.println("Run Time: " + runTime + " nanoseconds");
        return result;
    }

    // Function to find the median of a sorted list.
    public double findMedian(List<Integer> sortedList) {
        int totalSize = sortedList.size();
        int middle = totalSize / 2;
        if (totalSize % 2 == 0) {
            // If even number of elements, the median is the average of the two middle elements.
            return (sortedList.get(middle - 1) + sortedList.get(middle)) / 2.0;
        } else {
            // If an odd number of elements, the median is the middle element.
            return sortedList.get(middle);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String inputFilePath = "/Users/kishankumarz/Library/CloudStorage/GoogleDrive-kishankumarz3131@gmail.com/My Drive/Important/[02] USA/[02] UNT/[02] Classes/[01] Analysis_of Computer_Algoritms/[02] ACA_Assisgnment/[01] HomeWork/ACA_HomeWork-1/src/question2/input";
        Scanner fileScanner = new Scanner(new File(inputFilePath));

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
        System.out.println("_____________________________________________________________________________________");
        System.out.println("Input: " + inputSortedList);

        List<Integer> mergedList = mergeNSortedArrayLists(inputSortedList);
        System.out.println("Sorted Array: " + mergedList);

        MedianFinder finder = new MedianFinder();
        double median = finder.findMedian(mergedList);
        System.out.println("Median: " + median);
        System.out.println("_____________________________________________________________________________________");
        fileScanner.close();
    }
}
