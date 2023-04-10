public class Fibonacci {
    
    public int head(int n){

        // we hev to define the base case F(0) = 0 & F(1) = 1
        if(n == 0) return 0;
        if(n == 1) return 1;

        // first we call the method recursively
        int fib1 = head(n-1);
        int fib2 = head(n-2);

        // Java calculates the same values several times
        // ( this is why we need to use DYNAMIC PROGRAMMING)
        
        // make some operations
        int result = fib1 + fib2;
        return result;
    }

    public int tail(int n, int a, int b){

        if(n == 0) return a;
        if(n == 1) return b;

        return tail(n-1, b, a+b);
    }
}
