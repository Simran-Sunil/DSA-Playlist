public class Stack<T> {

    private T[] stack;
    private int count;

    public Stack(){
        stack = (T[]) new Object[1];
    }

    // we just hve to add the new item to the end of the array O(1)
    public void push(T newData){
        // arrays are not dynamic datastructure
        // so we need to resize the underlying array if necessary.
        // if many items : we double the size of the array.
        // if lesser items : we decrease ( shrink ) the size of the array.
        if(count == stack.length)
            resize(2*stack.length);

        // we hve to insert into the array
        stack[count++] = newData;
    }

    // returns the last item we have inserted O(1)
    public T pop(){
        if(isEmpty()) return null;

        T item = stack[--count];
        //obselete references - avoid memory leaks
        stack[count] = null;

        // maybe we have to resize the array and decrease the size
        //the stack is 25% full
        if(count > 0 && count == stack.length / 4){
            resize(stack.length / 2);
        }
        return item;
    }


    // helper methods - O(1) time complexity
    public boolean isEmpty(){
        return count == 0;
    }

    // size method O(1)
    public int size(){
        return count;
    }

    // this is the bottleneck of the application O(N)
    private void resize(int capacity){

        System.out.println("Need to resize the array");
        T[] stackCopy = (T[]) new Object[capacity];
        // copy the item one by one
        for(int i=0;i<count;i++)
            stackCopy[i] = stack[i];
        
        // update the reference
        stack = stackCopy;
    }
}
