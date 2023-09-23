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
//        int totalSize = 0;
        return sortedLists.size()*sortedLists.get(0).size();
//        for (List<Integer> list : sortedLists) {
//            totalSize += list.size();
//        }
//        System.out.println(totalSize);
//        return totalSize;
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
        int N = 10000; // Number of schools
        int M = 3000; // Number of students in each school
        List<List<Integer>> sortedLists = generateRandomData(N, M);

//        for(int i=0 ;i<N;i++){
//            for(int j=0;j<M;j++){
//                System.out.print("School-"+(i+1)+": "+sortedLists.get(i).get(j)+" ");
//            }
//            System.out.println();
//        }

//        List<List<Integer>> sortedList2 = new ArrayList<>();
//        sortedList2.add(new ArrayList<>());
//        sortedList2.add(new ArrayList<>());
//        sortedList2.add(new ArrayList<>());
//
//        sortedList2.get(0).add(10);
//        sortedList2.get(0).add(20);
//        sortedList2.get(0).add(30);
//
//        sortedList2.get(1).add(15);
//        sortedList2.get(1).add(25);
//        sortedList2.get(1).add(35);
//
//        sortedList2.get(2).add(5);
//        sortedList2.get(2).add(10);
//        sortedList2.get(2).add(15);

        MedianFinder finder = new MedianFinder();
        double median = finder.findMedian(sortedLists);
        System.out.println("Median: " + median);
    }
}
