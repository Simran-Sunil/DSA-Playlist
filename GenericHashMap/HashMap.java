
@SuppressWarnings("unchecked")
public class HashMap<Key, Value> {
    
    private Key[] keys;
    private Value[] values;
    // number of items with default value as 0.
    private int n;
    // it manages the resize operation
    private int capacity;

    public HashMap(){
        this.keys = (Key[]) new Object[Constants.TABLE_SIZE];
        this.values = (Value[]) new Object[Constants.TABLE_SIZE];
        this.capacity = Constants.TABLE_SIZE;
    }

    public HashMap(int capacity){
        this.keys = (Key[]) new Object[Constants.TABLE_SIZE];
        this.values = (Value[]) new Object[Constants.TABLE_SIZE];
        this.capacity = capacity;
    }

    public void remove(Key key){
        if(key == null) return;

        int index = hash(key);

        // first we want to find the item we want to get rid of
        while(!keys[index].equals(key)){
            index = (index + 1) % capacity;
        }

        // we delete the item : they key + the value
        keys[index] = null;
        values[index] = null;

        // we have to reconstruct the table from the item deleted:
        // there is a hole in the table:
        // the get() method will not work properly otherwise
        index = (index + 1) % capacity;

        while(keys[index] != null){
            Key tempKey = keys[index];
            Value tempValue = values[index];

            keys[index] = null;
            values[index] = null;

            // we have to decrement the size, because with the put() method
            // it will be increased again - so it will be fine !!!
            n--;
            put(tempKey, tempValue);
        }
        n--;
        // resize -- less than 33% of the capacity
        if(n <= capacity / 3){
            System.out.println("Resizing the table: Halfing the size......");
            resize(capacity / 2);
        } 
    }

    //O(1)
    public void put(Key key, Value value){
        if(key == null || value == null) return;

        // load balance is 0.75 : so when the table is 75% full we resize it -> double its size
        // why ? when it is nearly empty --> we waste a lot of memory for no reason
        // when it is nearly full --> there will be lot of collisions --> O(1) will
        // reduce to O(N) or something like that
        if(n >= capacity * 0.75){
            System.out.println("Doubling the size of the hash table......");
            resize(2*capacity);
        }

        int index = hash(key);

        // maybe there is a collision and there is already an item
        // inserted into that given index, so we have to
        // find an empty slot --> hence the condition != null
        while(keys[index] != null){
            // update
            if(keys[index].equals(key)){
                values[index] = value;
                return;
            }

            index = (index + 1) % capacity;
        }
        // we have managed to found the array index
        // where we can update the value --> so update accordingly !!!
        keys[index] = key;
        values[index] = value;
        n++;  
    }

    // O(N)
    private void resize(int newCapacity) {
        HashMap<Key, Value> newMap = new HashMap<>(newCapacity);

        // have to rehash the table entries because the hash-function relies heavily on the
        // size: ~key.hashCode() % sizeOfTable !!!
        // So it is a O(N) operation --> we should make as few resize operation as possible
        for(int i=0;i<capacity;i++){
            if(keys[i] != null){
                newMap.put(keys[i], values[i]);
            }
        }

        keys = newMap.getKeys();
        values = newMap.getValues();
        capacity = newMap.getCapacity();
    }

    // O(1)
    public Value get(Key key){
        if(key == null) return null;

        // O(1) approach
        int index = hash(key);

        // we have to consider the items right after each other because
        // the item could have been shifted down
        // because of the linear probing
        while(keys[index] != null){
            if(keys[index].equals(key)){
                return values[index];
            }
            // we have to check the next slot / array bucket
            index = (index + 1) % capacity;
        }
        // search miss : no value with the given key
        return null;
    }

    // most important function
    private int hash(Key key){
        return Math.abs(key.hashCode()) % capacity;
    }

    public boolean isEmpty(){
        return this.n == 0;
    }

    public int size(){
        return this.n;
    }

    public Key[] getKeys() {
        return keys;
    }

    public void setKeys(Key[] keys) {
        this.keys = keys;
    }

    public Value[] getValues() {
        return values;
    }

    public void setValues(Value[] values) {
        this.values = values;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    
}
