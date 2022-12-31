public interface Tree<T>{
    public void insert(T data);
    public void remove(T data);
    // in-order yeilds the natural-ordering
    public void traversal();
    public void ordering();
    public T getMin();
    public T getMax();
}