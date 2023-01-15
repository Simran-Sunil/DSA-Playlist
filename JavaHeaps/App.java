import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class App {
    public static void main(String[] args){
        // by default the heap is MAX Heap
        Queue<Person> heap = new PriorityQueue<>(Collections.reverseOrder());

        // O(logN)
        heap.add(new Person("Kevin", 34));
        heap.add(new Person("Daniel", 12));
        heap.add(new Person("Ana", 67));
        heap.add(new Person("Adam", 18));
        heap.add(new Person("Stephen", 52));
        heap.add(new Person("Michael", 23));
        
        // O(N)
        heap.contains(0);

        // when we consider all N items in O(logN) = O(NlogN)
        while(!heap.isEmpty()){
            // O(logN)
            System.out.println(heap.poll());
        }
    }
    
}
