public class HeadTailRecursion{
    
    public void head(int n){
        System.out.println("Calling the function with n=" + n);
        if(n == 0) return;

        // we call the method recursively
        head(n-1);

        // we do some operations
        // operations = System.out.println();
        System.out.println("The value of n="+n);
    }

    public void tail(int n){
        System.out.println("Calling the function with n=" + n);
        if(n == 0) return;

        // do some operations
        System.out.println("The value of n="+n);

        // we call the method recursively
        head(n-1);
    }
}