public class App {
    
    public static void main(String args[]){
        HashTable table = new HashTable();
        
        table.put(1, 10);
        table.put(2, 20);
        table.put(3, 30);
        table.put(13, 40);
        table.put(23, 50);

        System.out.println(table.get(23));
    }
}
