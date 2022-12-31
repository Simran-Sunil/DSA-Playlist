import java.lang.Thread.State;

import javax.naming.InvalidNameException;

public class Main {
    public static void main(String[] args) {
        Stack<String> name = new Stack<>();

        name.push("Simran");
        name.push("Thanay");
        name.push("Sonal");
        name.push("Shriya");
        name.push("Saanvi");

        while(!name.isEmpty()){
            System.out.println("Popped name is:"+ name.pop());
        }
    }
}
