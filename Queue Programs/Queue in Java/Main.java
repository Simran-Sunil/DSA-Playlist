import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args){
        Queue<Task> q = new LinkedList<>();

        // add() inserts new item into the queue with O(1).
        q.add(new Task(1));
        q.add(new Task(2));
        q.add(new Task(3));

        //element() can help return the first item.
        //System.out.println(q.element());

        // remove() method is a dequeue in O(1)
        while(!q.isEmpty()){
            Task task = q.remove();
            task.execute();
        }
        
    }
}
