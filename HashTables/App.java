public class App {
    
    public static void main(String args[]){
        HashTable table = new HashTable();
        
        table.put(1, 10);
        table.put(11, 20);
        table.put(21, 30);
        table.put(31, 40);
        table.put(41, 50);

        System.out.println(table.get(1));
    }
}
