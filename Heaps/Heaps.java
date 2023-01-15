import javax.management.RuntimeErrorException;

public class Heaps {

    // one - dimensional array representation of MAX HEAP
    private int[] heap;
    // number of items in a heap
    private int heapSize;

    public Heaps(){
        heap = new int[Constants.CAPACITY];
    }

    public void insert(int data){
        if(isFull()) throw new RuntimeException("Heap is full.....");
        
        // we append the data to the end of the array
        heap[heapSize] = data;
        heapSize++;

        // we have to check the heap properties
        // we start with the last item that has index heapSize - 1
        // we have to check the nodes (parent nodes) upto the root node O(logN)
        fixUp(heapSize-1);
    }

    private void fixUp(int index) {
        //index of the parent
        int parentIndex = (index - 1) / 2;

        // in worst-case scenario we have to consider all the nodes upto the root node(index 0)
        // in a amaximum heap the parent is always larger
        if(index > 0 && heap[index] > heap[parentIndex]){
            swap(index, parentIndex);
            fixUp(parentIndex);
        }
    }

    // the max item is the root node (index 0) in a MAX HEAP in O(1)
    public int getMax(){
        return heap[0];
    }

    // heapsort is doing poll() operation for N times - O(NlogN)
    public void heapSort(){
        int n = heapSize;

        for(int i=0;i<n;i++){
            int max = poll();
            System.out.println(max);
        }
    }

    // that removes and returns the max item in O(logN)
    public int poll(){
        int max = getMax();

        // swap the root node with the last item
        swap(0, heapSize - 1);
        heapSize--;

        // fix the heap properties if needed
        fixDown(0);

        return max;
    }

    // O(logN)
    private void fixDown(int index) {
        int leftChildIndex = 2*index+1;
        int rightChildIndex = 2*index+2;

        // in a max heap the parent is always larger than the children
        int largestIndex = index;

        // we compare the leftChild with the parent
        if(leftChildIndex < heapSize && heap[leftChildIndex] > heap[index]){
            largestIndex = leftChildIndex;
        }

        // we compare the right child with the max(parent, leftchild)
        if(rightChildIndex < heapSize && heap[rightChildIndex] > heap[largestIndex]){
            largestIndex = rightChildIndex;
        }

        // if one of the children is larger than the parent so we have to swap items
        // otherwise the heap properties are not violated
        if(index != largestIndex){
            swap(index, largestIndex);
            // until the heap properties are violated we keep calling the method recursively.
            fixDown(largestIndex);
        } 
    }

    //O(1)
    public boolean isEmpty(){
        return heapSize == 0;
    }

    //O(1)
    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    //O(1)
    private boolean isFull(){
        return heapSize == heap.length;
    } 
}
