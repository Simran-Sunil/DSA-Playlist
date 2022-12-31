public class Stack<T extends Comparable<T>> {
    
    // Its follows LIFO while performing pop operation
    private Node<T> head;
    private int count;

    //O(1)
    public void push(T data){
        count++;
        // this is when the linkedlist is empty
        if(head == null){
            head = new Node<>(data);
        }else{
            // the linkedlist already contains some items
            Node<T> oldHead = head;
            head = new Node<>(data);
            head.setNextNode(oldHead);
        }

    }

    // removes the last item we inserted / pushed in O(1)
    public T pop(){
        if(isEmpty()){
            return null;
        }
        T item = head.getData();
        head = head.getNextNode();
        count--;
        return item;

    }

    //O(1)
    public int size(){
        return count;
    }

    //O(1)
    public boolean isEmpty(){
        return count == 0;
    }
}
