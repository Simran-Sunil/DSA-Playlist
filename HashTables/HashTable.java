public class HashTable {

    private static final int TABLE_SIZE = 10;
    private HashItem[] hashTable;

    public HashTable(){
        hashTable = new HashItem[TABLE_SIZE];
    }

    // O(1) running time 
    public int get(int key){
        int index = hash(key);

        // this is a search miss - the item with the given key 
        // is not present in the HashTable
        if(hashTable[index] == null){
            return -1;
        }

        HashItem item = hashTable[index];

        while(item != null && item.getKey() != key){
            item = item.getNextItem();
        }

        if(item == null){
            return -1;
        }
        return item.getValue();

    }

    // O(1) running time 
    public void put(int key, int value){
        int index = hash(key);

        // there is no collision (the 'index' is empty)
        if(hashTable[index] == null){
            System.out.println("No collsion - simple insertion....");
            hashTable[index] = new HashItem(key, value);
        }else{
            // we know that there is already an item in the index
            System.out.println("Collision when inserting the key "+ key);
            HashItem item = hashTable[index];

            // this is why the running time maybe O(N)
            while(item.getNextItem() != null){
                System.out.println("Considering the next item in the linked list "+item.getValue());
                item = item.getNextItem();
            }

            System.out.println("Finally we have found the place to insert...");
            item.setNextItem(new HashItem(key, value));
        }
    }

    // it transforms the key into an index of underlying array
    // PRIME NUMBERS
    private int hash(int key){
        return key % TABLE_SIZE;
    }
}
