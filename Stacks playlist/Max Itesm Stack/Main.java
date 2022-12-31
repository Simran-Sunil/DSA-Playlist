import javax.crypto.Mac;

public class Main {
    public static void main(String[] args){
        MaxItemStack maxItemStack = new MaxItemStack();
        
        maxItemStack.push(12);
        maxItemStack.push(30);
        maxItemStack.push(1);
        maxItemStack.push(57);

        System.out.println(maxItemStack.getMaxItem());
    }
}
