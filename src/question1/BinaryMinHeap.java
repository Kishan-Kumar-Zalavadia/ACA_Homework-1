package question1;

import java.util.Arrays;

public class BinaryMinHeap {
    private int[] heap;
    private int size;
    private int capacity;
    private int swapCount; // Variable to keep track of swap count

    public BinaryMinHeap(int capacity) {
        this.capacity = capacity;
        heap = new int[capacity];
        size = 0;
    }

    public BinaryMinHeap() {
        this(100); // Default capacity
    }

    public void insert(int value) {
        if (size >= capacity) {
            // Handle heap resizing if needed
            capacity *= 2;
            heap = Arrays.copyOf(heap, capacity);
        }

        int index = size;
        heap[index] = value;
        size++;

        // Heapify up
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap[index] < heap[parentIndex]) {
                // Swap with parent if smaller
                int temp = heap[index];
                heap[index] = heap[parentIndex];
                heap[parentIndex] = temp;
                index = parentIndex;
                swapCount++; // Increment swap count
            } else {
                break;
            }
        }
    }

    public int findMin() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;

        // Heapify down
        int index = 0;
        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = index;

            if (leftChild < size && heap[leftChild] < heap[smallest]) {
                smallest = leftChild;
            }

            if (rightChild < size && heap[rightChild] < heap[smallest]) {
                smallest = rightChild;
            }

            if (smallest != index) {
                // Swap with the smallest child
                int temp = heap[index];
                heap[index] = heap[smallest];
                heap[smallest] = temp;
                index = smallest;
                swapCount++; // Increment swap count
            } else {
                break;
            }
        }

        return min;
    }

    public void delete(int value) {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        // Step 1: Find the element to delete
        int indexToDelete = -1;
        for (int i = 0; i < size; i++) {
            if (heap[i] == value) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete == -1) {
            throw new IllegalArgumentException("Value not found in the heap");
        }

        // Step 2: Replace the element with the last element in the heap
        heap[indexToDelete] = heap[size - 1];
        size--;
    }

        public int peekMin() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap[0];
    }

    public boolean search(int value) {
        for (int i = 0; i < size; i++) {
            if (heap[i] == value) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSwapsCount() {
        return swapCount;
    }

}
