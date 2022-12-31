public class Main {
    public static void main(String[] args){
        Queue<Integer> q = new Queue<>();

        q.enqueue(10);
        q.enqueue(100);
        q.enqueue(1000);

        System.out.println(q.size());

        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
    }
}
