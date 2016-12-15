package uk.ac.cam.jf549.algorithms;

public class MaxHeap {
    private char heapName;
    private int heapSize;
    private char[] heap;

    public MaxHeap(char name) {
        this.heapName = name;
        this.heapSize = 0;
        this.heap = new char[10];
    }

    public MaxHeap(char name, String values) {
        this.heapName = name;
        this.heapSize = values.length();
        this.heap = values.toCharArray();

        for (int i = (heapSize - 1) / 2; i >= 0; --i) {
            heapify(i);
        }
    }

    public void insert(char c) {
        if (heapSize >= heap.length) {
            char[] newHeap = new char[heap.length * 2 + 1];
            System.arraycopy(heap, 0, newHeap, 0, heap.length);
            heap = newHeap;
        }

        heap[heapSize] = c;
        ++heapSize;

        for (int i = heapSize - 1; i > 0 && heap[i] > heap[parent(i)]; i = parent(i)) {
            swap(i, parent(i));
        }
    }

    public char getMax() {
        if (heapSize < 1) {
            return '_';
        }

        char result = heap[0];
        heap[0] = heap[heapSize - 1];
        --heapSize;
        heapify(0);
        return result;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private void swap(int i, int j) {
        char tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    private void heapify(int iRoot) {
        int iLeft = 2 * iRoot + 1;
        int iRight = 2 * iRoot + 2;
        int iLargest = iRoot;

        if (iLeft < heapSize && heap[iLeft] > heap[iRoot]) {
            iLargest = iLeft;
        }

        if (iRight < heapSize && heap[iRight] > heap[iLargest]) {
            iLargest = iRight;
        }

        if (iLargest != iRoot) {
            swap(iLargest, iRoot);
            heapify(iLargest);
        }
    }

    public static void main(String[] args) {
        char c;
        MaxHeap h = new MaxHeap('h', "CAMBRIDGEALGORITHMS");
        c = h.getMax();
        System.out.println(c); // expect T
        h.insert('Z');
        h.insert('A');
        c = h.getMax();
        System.out.println(c); // expect Z
        c = h.getMax();
        System.out.println(c); // expect S
    }
}
