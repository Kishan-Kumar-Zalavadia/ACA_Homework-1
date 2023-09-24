package question2;

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

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Enter data manually");
        System.out.println("2. Random generated data");
        System.out.println("Enter your choice:");
        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                System.out.print("Enter the number of schools: ");
                int numberOfInnerLists = scanner.nextInt();

                // Create a list of lists to store the data
                List<List<Integer>> inputSortedList = new ArrayList<>();

                // Loop to get user input for each inner list
                for (int i = 0; i < numberOfInnerLists; i++) {
                    System.out.println("Enter the heights for school " + (i + 1) + " in non-decreasing order (enter a negative number to finish):");
                    List<Integer> innerList = new ArrayList<>();

                    // Keep taking input until the user enters a negative number
                    while (true) {
                        int num = scanner.nextInt();
                        if (num < 0) {
                            break;
                        }
                        innerList.add(num);
                    }

                    inputSortedList.add(innerList);
                }
                // Close the scanner
                scanner.close();

                MedianFinder finder = new MedianFinder();
                double median = finder.findMedian(inputSortedList);
                System.out.println("Median: " + median);
                break;
            case 2:
                System.out.println("Enter number of schools:");
                int noOfSchools = scanner.nextInt();
                System.out.println("Enter number of students per school:");
                int studentsPerSchool = scanner.nextInt();

                List<List<Integer>> randomSortedLists = generateRandomData(noOfSchools, studentsPerSchool);

//                for(int i=0 ;i<noOfSchools;i++){
//                    System.out.println("_____________________________________________________________");
//                    System.out.println("School-"+(i+1)+": ");
//                    for(int j=0;j<studentsPerSchool;j++){
//                        System.out.print(randomSortedLists.get(i).get(j)+" ");
//                    }
//                    System.out.println();
//                }
                finder = new MedianFinder();
                median = finder.findMedian(randomSortedLists);
                System.out.println("Median: " + median);
                break;
        }

    }
}
