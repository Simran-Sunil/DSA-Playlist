import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String args[]){
        Map<Integer, String> map = new HashMap<>();
        
        // insert into the map O(1) - if there is no collisions
        map.put(1, "Adam");
        map.put(2, "Steve");
        map.put(3, "John");
        map.put(4, "Lisa");
        // we can also add NULL as keys
        map.put(null, "This value is null");
        
        // we can retrive the items based on keys O(1)
        System.out.println(map.get(2));
        System.out.println(map.get(null));

        // removes item in O(1)
        map.remove(2);

        for(Integer key: map.keySet()){
            System.out.println(map.get(key));
        }

        for(Map.Entry<Integer, String> entry: map.entrySet()){
            System.out.println(entry.getKey()+ " - " + entry.getValue());
        }
    }
    
}
