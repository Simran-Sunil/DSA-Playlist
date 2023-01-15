public class App {

    public static void main(String[] args){
        Heaps heap = new Heaps();

        for(int i=0;i<10000;i++){
            heap.insert(i);
        }
        // heap.insert(6);
        // heap.insert(1);
        // heap.insert(12);
        // heap.insert(-2);
        // heap.insert(3);
        // heap.insert(8);
        // heap.insert(-5);

        heap.heapSort();
    }

}
