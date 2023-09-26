package question2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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



    public double findMedian(List<Integer> sortedLists) {

        int totalSize = sortedLists.size();
        int middle = totalSize/2;
        if(totalSize % 2 == 0){
            // If even number of elements, medium the average of the two middle elements
            return (sortedLists.get(middle - 1)+sortedLists.get(middle))/2.0;
        }
        else{
            // If odd number of elements, medium is middle element
            return sortedLists.get(middle);
        }


//        if (totalSize % 2 == 0) {
//            // If even number of elements, return the average of the two middle elements
//            return (maxHeap.peek() + minHeap.peek()) / 2.0;
//        } else {
//            // If odd number of elements, return the middle element from the maxHeap
//            return maxHeap.peek();
//        }
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
        System.out.println("_____________________________________________________________________________________");
        System.out.println("Input: "+inputSortedList);

        List<Integer> mergedList = mergeNSortedArrayLists(inputSortedList);
        System.out.println("Sorted Array: "+mergedList);

        MedianFinder finder = new MedianFinder();
        double median = finder.findMedian(mergedList);
        System.out.println("Median: " + median);
        System.out.println("_____________________________________________________________________________________");
        fileScanner.close();

    }
}


