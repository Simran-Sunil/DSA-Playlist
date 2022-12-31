public class Main {
    public static void main(String[] args){
        Algorithm algo = new Algorithm();
        algo.interpretExpression("( ( 5 * 2 ) + ( 2 + 5 ) )");
        algo.result();
    }
}

/*Note:
- Java has inbuilt Stack class so we can import it using import java.util.Stack;
- Time complexity of push() - O(1)
- Time complexity of peek() - O(1) and returns the last item we have inserted without removing it.
- Time complexity of pop() - O(1), it removes the last item and returns its value.
- Uses: In stack memory, back button in websites and undo operation in photo editors like photoshop.
*/ 