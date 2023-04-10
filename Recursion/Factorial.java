public class Factorial {
    
     // Code for head recursion
    public int head(int n){
        if(n == 1) return 1;

        // first we call the method recursively
        int res1 = head(n-1);
        // we do some operation
        int result = n * res1;

        return result;
    }

    // Code for tail recursion
    public int tail(int n, int accumulator){

        if(n == 1) return accumulator;

        return tail(n-1, n * accumulator);
    }
}
